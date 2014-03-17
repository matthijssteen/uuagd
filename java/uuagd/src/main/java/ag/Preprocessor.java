package ag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.args.CmdArgsLexer;
import parser.pretty.PrettyAGLexer;
import parser.pretty.PrettyAGParser;
import parser.rewrite.RewriteAGLexer;
import util.Strings;

class Preprocessor {
	final private static Pattern ALT_PAT = Pattern.compile("([A-Z][a-zA-Z0-9_]*|\\*)");
	
	final private String uuagcOptions;
	final private File agFile;
	final private String castFn;
	final private String castTy;
	final private Settings settings;
	
	final private File agHsFile;
	final private Map<File, File> agTempFiles;
	
	private RuleContext prettyTree;
	private PrettyListener agInfo;
	private AttrUsageFactory attrUsageFactory;
	private File agTempFile;
	
	public Preprocessor(String uuagcOptions, File agFile, String castFn, String castTy, Settings settings) throws Exception {
		this.uuagcOptions = uuagcOptions;
		this.agFile = agFile;
		this.castFn = castFn;
		this.castTy = castTy;
		this.settings = settings;
		
		agHsFile = new File(getWithoutExt(agFile.getPath()) + ".hs");
		agTempFiles = new HashMap<>();
	}
	
	public void preprocessAndCompile() throws Exception {
		parsePrettyAG();
		gatherAGInfo();
		processAG();
		compileAG();
	}
	
	private void parsePrettyAG() throws Exception {
		File hsTempFile = File.createTempFile("uuagd", ".hs");
		
		exec("uuagc -H --pretty -o " + hsTempFile.getPath() + " " + agFile.getPath(), System.out);
		
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(hsTempFile));
		PrettyAGLexer lexer = new PrettyAGLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PrettyAGParser parser = new PrettyAGParser(tokens);
		prettyTree = parser.root();
		
		Files.delete(hsTempFile.toPath());
	}
	
	private void gatherAGInfo() {
		ParseTreeWalker walker = new ParseTreeWalker();
		agInfo = new PrettyListener();
		walker.walk(agInfo, prettyTree);
		
		attrUsageFactory = new AttrUsageFactory(agInfo, settings);
	}
	
	private void processAG() throws Exception {
		agTempFile = createTempAG(agFile);
		processAG(agFile, true);
	}
	
	private void processAG(File agFile, boolean isRoot) throws Exception {
		Map<Token, AttrUsage> attrUsages = new HashMap<>();
		Map<Token, AttrUsage[]> tupleAttrUsages = new HashMap<>();
		Map<Token, File> agFiles = new HashMap<>();
		List<Token> agTokens = new ArrayList<>();

		Set<String> dataTypes = null;
		Set<String> alts = null;
		
		// First lex the attribute grammar to determine which tokens have to be rewritten
		// for the debugging to work, like creating local aliases for non-local attributes.
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(agFile));
		RewriteAGLexer lexer = new RewriteAGLexer(input);
		Token token = lexer.nextToken();
        while(token.getType() != Recognizer.EOF) {
        	switch (token.getType()) {
        		case RewriteAGLexer.INCLUDE: {
        			String filename = removeOutsideChars(removeLeading(token.getText(), "include"));
        			File file = new File(filename);
        			if (!file.isAbsolute()) {
        				file = new File(agFile.getParent() + File.separator + filename);
        			}
        			createTempAG(file);
        			agFiles.put(token, file);
        			
        			agTokens.add(token);
        		}
        		break;
        		case RewriteAGLexer.SEM: {
        			dataTypes = parseTypes(removeLeading(token.getText(), "sem"));
        		}
        		break;
        		case RewriteAGLexer.ALT: {
        			alts = parseTypes(removeLeading(token.getText(), "|"));
        		}
        		break;
        		case RewriteAGLexer.ATTR: {
        			if (dataTypes != null && alts != null) {
        				AttrUsage usage = attrUsageFactory.create(dataTypes, alts, token.getText());
        				
        				if (usage.needsAlias()) {
	        				attrUsages.put(token, usage);
	        				agTokens.add(token);
	        			}
        			}
        			else {
        				error("An attribute should always be defined as part of a semantic alternative.");
        			}
        		}
        		break;
        		case RewriteAGLexer.ATTR_TUPLE: {
        			if (dataTypes != null && alts != null) {
        				String[] tupleTexts = removeOutsideChars(removeTrailing(token.getText(), "=")).split(",");
	        			AttrUsage[] usages = new AttrUsage[tupleTexts.length];
	        			for (int j = 0; j < tupleTexts.length; j++) {
	        				usages[j] = attrUsageFactory.create(dataTypes, alts, tupleTexts[j]);
	        			}
	        			tupleAttrUsages.put(token, usages);
	        			
	        			agTokens.add(token);
        			}
        			else {
        				error("An tuple of attributes should always be defined as part of a semantic alternative.");
        			}
        		}
        		break;
        	}
            token = lexer.nextToken();
        }
		
        // Then read the same attribute grammar file line-by-line,
        // so the tokens can be related to actual character positions. 
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(agTempFiles.get(agFile))))) {
			try (BufferedReader in = new BufferedReader(new FileReader(agFile))) {
				int pos = 0;
				int endPos = 0;
				int restPos = 0;
				
				int i = 0;
				int tokensLength = agTokens.size();
				
				String line;
				int lineNumber = 0;
				while ((line = in.readLine()) != null) {
					lineNumber++;
					
					// Only prepend an newline if it is not the first line
					// and if the previous token did not contain any newlines.
					if (restPos == 0 && lineNumber > 1) {
						out.println();
					}
					
					// Shift one position due to the normalized newline character.
					// This way it is still clear when we need to resume even if blank lines were present.
					if (restPos > 0) {
						restPos--;
					}
					
					// A token can span over multiple lines, so `restPos` can exceed even the succeeding line.
					endPos = line.length();
					if (restPos > endPos) {
						restPos = restPos - endPos;
						continue;
					}
					
					// The `restPos` is the starting position for the line where the token ended.
					pos = restPos;
					restPos = 0;
					
					// Process the tokens that were on the current line.
					// Keep track of the tokens index, so we do not have to traverse unnecessary tokens.
					for (; i < tokensLength && lineNumber == agTokens.get(i).getLine(); i++) {
						token = agTokens.get(i);
						int tokenPos = token.getCharPositionInLine();
						String tokenText = token.getText().replaceAll("(?:\r?\n|\r)", "\n");
						
						out.print(line.substring(pos, tokenPos));
						
						switch (token.getType()) {
			        		case RewriteAGLexer.INCLUDE: {
			        			File file = agFiles.get(token);
			        			
			        			out.println();
			        			out.print("include ");
			        			out.print('"');
			        			out.print(agTempFiles.get(file).getPath());
			        			out.print('"');
			        		}
			        		break;
			        		case RewriteAGLexer.ATTR: {
			        			AttrUsage attr = attrUsages.get(token);
								
			        			out.println();
								out.print(attr.getScopedName());
								out.print(" = @");
								out.print(attr.getAliasScopedName());
								out.println();
								out.print(attr.getAliasScopedName());
								out.print(" =");
			        		}
			        		break;
			        		case RewriteAGLexer.ATTR_TUPLE: {
			        			AttrUsage[] usages = tupleAttrUsages.get(token);
			        			for (AttrUsage usage: usages) {
			        				if (usage.needsAlias()) {
			        					out.println();
			        					out.print(usage.getScopedName());
			        					out.print(" = @");
			        					out.print(usage.getAliasScopedName());
			        				}
			        			}
			        			
			        			out.println();
			        			boolean first = true;
			        			for (AttrUsage usage: usages) {
			        				out.print(first ? '(' : ',');
			        				out.print(usage.getAliasScopedName());
			        				first = false;
			        			}
			        			out.print(") =");
			        		}
			        		break;
			        	}
						
						// If the combined length of the token position and the length of its text
						// exceeds the length of the line, then the token spans multiple lines.
						pos = tokenPos + tokenText.length();
						if (pos > endPos) {
							restPos = pos - endPos;
							pos = endPos;
						}
					}
					
					if (pos < endPos) {
						out.print(line.substring(pos, endPos));
					}
				}
			}
			
			for (File file: agFiles.values()) {
				processAG(file, false);
			}
			
			if (isRoot) {
				out.println();
				out.println();
				
				ParseTreeWalker walker = new ParseTreeWalker();
				CodeGen gen = new CodeGen(out, castFn, castTy, agInfo, attrUsageFactory.attrUsages, settings);
				walker.walk(gen, prettyTree);
			}
		}
	}

	private void compileAG() throws Exception {
		exec("uuagc " + uuagcOptions + " -o " + agHsFile.getPath() + " " + agTempFile.getPath(), System.out);
		
		for (File file: agTempFiles.values()) {
			Files.delete(file.toPath());
		}
	}
	
	private void exec(String cmd, Appendable... dests) throws Exception {
		System.out.println("Executing: " + cmd);
		
		// Execute the given command and wait until it has terminated.
		String[] args = parseCmd(cmd);
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		Process p = pb.start();
		Thread t = inheritIO(p.getInputStream(), dests);
		t.start();
		p.waitFor();
		
		// Waiting for the process does not mean that we are finished reading the output stream,
		// so we have to join the stream handler thread.
		t.join();
		
		if (p.exitValue() == 0) {
			System.out.println("Exited with success.");
		}
		else {
			System.out.println("Exited with failure.");
			
			// If something went wrong during the execution of the command,
			// we have to terminate this program as well, otherwise it would become unreliable.
			System.exit(1);
		}
	}
	
	private static String[] parseCmd(String cmd) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(new StringReader(cmd));
		CmdArgsLexer lexer = new CmdArgsLexer(input);
		List<String> args = new ArrayList<>();
		Token token = lexer.nextToken();
        while(token.getType() != Recognizer.EOF) {
        	args.add(token.getText());
        	token = lexer.nextToken();
        }
        return args.toArray(new String[args.size()]);
	}
	
	private static Thread inheritIO(final InputStream src, final Appendable[] dests) {
	    return new Thread(new Runnable() {
	        @Override
			public void run() {
	        	BufferedReader br = new BufferedReader(new InputStreamReader(src));
	        	String line;
	    		try {
					while ((line = br.readLine()) != null) {
						for (Appendable dest: dests) {
							dest.append(line).append(System.getProperty("line.separator"));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    });
	}
	
	private File createTempAG(File agFile) throws Exception {
		File agTempFile = File.createTempFile("uuagd", ".ag");
		agTempFiles.put(agFile, agTempFile);
		return agTempFile;
	}
	
	private static String getWithoutExt(String filename) {
		int pos = filename.lastIndexOf('.');
		if (pos == -1) return filename;
		return filename = filename.substring(0, pos);
	}
	
	private static String removeOutsideChars(String s) {
		return s.substring(1, s.length() - 1);
	}
	
	private static String removeLeading(String s, String leading) {
		return s.trim().substring(leading.length()).trim();
	}
	
	private static String removeTrailing(String s, String trailing) {
		s = s.trim();
		return s.substring(0, s.length() - trailing.length()).trim();
	}
	
	private static Set<String> parseTypes(String text) {
		Set<String> types = new HashSet<>();
		Matcher m = ALT_PAT.matcher(text);
		while (m.find()) {
			types.add(m.group(1));
		}
		return types;
	}

	private static void error(String msg) {
		System.err.println(msg);
		System.exit(1);
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			error("Usage: uuagd \"uuagc options\" /path/file.ag printFn --option1 value1 --option2");
		}
		
		String uuagcOptions = args[0];
		
		File agFile = new File(args[1]).getAbsoluteFile();
		if (!agFile.exists()) {
			error("The attribute grammar file could not be found: " + agFile);
		}
		
		String castFn = args[2];
		String castTy = args[3];
		
		Settings settings = new Settings();
		for (int i = 4; i < args.length; i++) {
			switch (args[i]) {
				case "--whitelist": {
					List<AttrId> whitelist = new ArrayList<>();
					for (String id: args[i+1].split(";")) {
						whitelist.add(new AttrId(id));
					}
					settings.whitelist = whitelist;
					i++;
				}
				break;
				case "--blacklist": {
					List<AttrId> blacklist = new ArrayList<>();
					for (String id: args[i+1].split(";")) {
						blacklist.add(new AttrId(id));
					}
					settings.blacklist = blacklist;
					i++;
				}
				break;
				case "--filterKids": {
					settings.filterKids = true;
				}
				break;
				case "--noDefaultImport": {
					settings.noDefaultImport = true;
				}
				break;
				default: {
					error("Invalid option given: " + Strings.toLiteral(args[i]));
				}
				break;
			}
		}
		
		Preprocessor dp = new Preprocessor(uuagcOptions, agFile, castFn, castTy, settings);
		dp.preprocessAndCompile();
	}
}
