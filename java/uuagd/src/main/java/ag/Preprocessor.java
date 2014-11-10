package ag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.dump.DumpAGLexer;
import parser.dump.DumpAGParser;
import parser.dump.DumpAGParser.RootContext;
import parser.pretty.PrettyAGLexer;
import parser.pretty.PrettyAGParser;
import parser.rewrite.RewriteAGLexer;
import util.Cmd;
import util.Files;
import util.ReplaceCallback;
import util.Strings;
import ag.embed.Embed;

public class Preprocessor {
	final private static Pattern LINE_PAT = Pattern.compile("\\{\\-# LINE (\\d+) \"(.*?)\" #\\-\\}");

	final private Settings settings;

	final private File agHsFile;
	final private Map<File, File> agTempFiles;
	final private Set<File> processedFiles = new HashSet<File>();
	final private GrammarInfo agInfo = new GrammarInfo();

	private RuleContext prettyTree;
	private AttrUsageFactory attrUsageFactory;
	private File agTempFile;

	public Preprocessor(Settings settings) throws Exception {
		this.settings = settings;

		agHsFile = new File(Files.getWithoutExt(settings.agFile.getPath()) + ".hs");
		agTempFiles = new HashMap<File, File>();
	}

	public void preprocessAndCompile() throws Exception {
		parsePrettyOutput();
		gatherInfo();
		preprocess();
		compile();
		deleteTempFiles();
		restoreFileReferences();
	}

	private void parsePrettyOutput() throws IOException {
		File hsTempFile = File.createTempFile("uuagd", ".hs");

		Cmd.exec("uuagc -H --pretty -o " + hsTempFile.getPath() + " " + settings.agFile.getPath(), System.out);

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(hsTempFile));
		PrettyAGLexer lexer = new PrettyAGLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PrettyAGParser parser = new PrettyAGParser(tokens);
		prettyTree = parser.root();

		Files.delete(hsTempFile);
	}
	
	private void gatherInfo() throws IOException {
		gatherPrettyInfo();
		if (settings.showCode) {
			gatherDumpInfo();
		}
	}

	private void gatherPrettyInfo() {
		ParseTreeWalker walker = new ParseTreeWalker();
		PrettyInfoGatherer gatherer = new PrettyInfoGatherer(agInfo);
		walker.walk(gatherer, prettyTree);

		attrUsageFactory = new AttrUsageFactory(agInfo, settings);
	}
	
	private void gatherDumpInfo() throws IOException {
		File hsTempFile = File.createTempFile("uuagd", ".hs");

		Cmd.exec("uuagc -H --dumpgrammar -o " + hsTempFile.getPath() + " " + settings.agFile.getPath(), System.out);

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(hsTempFile));
		DumpAGLexer lexer = new DumpAGLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DumpAGParser parser = new DumpAGParser(tokens);
		RootContext dump = parser.root();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		DumpInfoGatherer gatherer = new DumpInfoGatherer(agInfo);
		walker.walk(gatherer, dump);
		
		Files.delete(hsTempFile);
	}
	
	private void preprocess() throws Exception {
		agTempFile = createTempAG(settings.agFile);
		processAG(settings.agFile, true);
	}

	private void processAG(File agFile, boolean isRoot) throws FileNotFoundException, IOException {
		if (processedFiles.contains(agFile)) {
			return;
		}
		processedFiles.add(agFile);

		Map<Token, File> agFiles = new HashMap<Token, File>();
		Map<Token, AttrUsage> attrUsages = new HashMap<Token, AttrUsage>();
		Map<Token, AttrUsage[]> tupleAttrUsages = new HashMap<Token, AttrUsage[]>();
		List<Token> agTokens = new ArrayList<Token>();

		tokenizeAG(agFile, agFiles, attrUsages, tupleAttrUsages, agTokens);
		updateAG(agFile, isRoot, agFiles, attrUsages, tupleAttrUsages, agTokens);
	}

	private void tokenizeAG(
			File agFile,
			Map<Token, File> agFiles,
			Map<Token, AttrUsage> attrUsages,
			Map<Token, AttrUsage[]> tupleAttrUsages,
			List<Token> agTokens) throws FileNotFoundException, IOException {
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(agFile));
		RewriteAGLexer lexer = new RewriteAGLexer(input);

		Map<String, String> setDecls = new HashMap<String, String>();
		Token token = lexer.nextToken();
		while (token.getType() != Recognizer.EOF) {
			if (token.getType() == RewriteAGLexer.SET) {
				String text = Strings.removeLeading(token.getText(), "set");
				String[] parts = text.split("\\s*=\\s*");
				setDecls.put(parts[0], parts[1]);
			}
			token = lexer.nextToken();
		}
		TypesFactory typesFactory = new TypesFactory(setDecls);

		Types dataTypes = null;
		Types alts = null;

		// First lex the attribute grammar to determine which tokens have to be rewritten
		// for the debugging to work, like creating local aliases for non-local attributes.
		lexer.reset();
		token = lexer.nextToken();
		while (token.getType() != Recognizer.EOF) {
			switch (token.getType()) {
				case RewriteAGLexer.INCLUDE: {
					String filename = Strings.removeOutsideChars(Strings.removeLeading(token.getText(), "include"));
					File file = new File(filename);
					if (!file.exists()) {
						file = new File(settings.agFile.getParent() + File.separator + filename);
					}
					file = file.getCanonicalFile();

					if (!agTempFiles.containsKey(file)) {
						createTempAG(file);
					}
					agFiles.put(token, file);
					agTokens.add(token);
				}
				break;
				case RewriteAGLexer.SEM: {
					dataTypes = typesFactory.create(Strings.removeLeading(token.getText(), "sem"));
				}
				break;
				case RewriteAGLexer.ALT: {
					alts = Types.fromString(Strings.removeLeading(token.getText(), "|"));
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
						System.out.println(dataTypes);
						System.out.println(alts);
						System.out.println(token);
						error("An attribute should always be defined as part of a semantic alternative.");
					}
				}
				break;
				case RewriteAGLexer.ATTR_TUPLE: {
					if (dataTypes != null && alts != null) {
						String[] tupleTexts = Strings.removeOutsideChars(Strings.removeTrailing(token.getText(), "=")).split(",");
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
			}
			token = lexer.nextToken();
		}
	}

	private void updateAG(
			File agFile,
			boolean isRoot,
			Map<Token, File> agFiles,
			Map<Token, AttrUsage> attrUsages,
			Map<Token, AttrUsage[]> tupleAttrUsages,
			List<Token> agTokens) throws IOException {
		// Then read the same attribute grammar file line-by-line,
		// so the tokens can be related to actual character positions.
		final PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(agTempFiles.get(agFile))));
		Throwable outEx = null;
		try {
			final BufferedReader in = new BufferedReader(new FileReader(agFile));
			Throwable inEx = null;
			try {
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
						Token token = agTokens.get(i);
						int tokenPos = token.getCharPositionInLine();
						String tokenText = token.getText().replaceAll("(?:\r?\n|\r)", "\n");

						out.print(line.substring(pos, tokenPos));

						replaceToken(token, out, attrUsages, tupleAttrUsages, agFiles);

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

				for (File file: agFiles.values()) {
					processAG(file, false);
				}

				if (isRoot) {
					out.println();
					out.println();

					ParseTreeWalker walker = new ParseTreeWalker();
					walker.walk(new Embed(out, agInfo, settings), prettyTree);
				}
			} catch (IOException e) {
				inEx = e;
				throw e;
			} finally {
				if (in != null) {
					if (inEx != null) {
						try {
							in.close();
						} catch (Throwable t) {}
					} else {
						in.close();
					}
				}
			}
		} catch (IOException e) {
			outEx = e;
			throw e;
		} finally {
			if (out != null) {
				if (outEx != null) {
					try {
						out.close();
					} catch (Throwable t) {}
				} else {
					out.close();
				}
			}
		}
	}

	private void replaceToken(
			Token token,
			PrintWriter out,
			Map<Token, AttrUsage> attrUsages,
			Map<Token, AttrUsage[]> tupleAttrUsages,
			Map<Token, File> agFiles) {
		switch (token.getType()) {
			case RewriteAGLexer.INCLUDE:
				File file = agFiles.get(token);
				out.println();
				out.print("include ");
				out.print('"');
				out.print(agTempFiles.get(file).getPath());
				out.print('"');
				break;
			case RewriteAGLexer.ATTR:
				AttrUsage attr = attrUsages.get(token);

				out.println();
				out.print(attr.getScopedName());
				out.print(" = @");
				out.print(attr.getAliasScopedName());
				out.println();
				out.print(attr.getAliasScopedName());
				out.print(" =");
				break;
			case RewriteAGLexer.ATTR_TUPLE:
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
	}

	private void deleteTempFiles() throws IOException {
		for (File tempFile: agTempFiles.values()) {
			Files.delete(tempFile);
		}
	}

	private void compile() throws IOException {
		Cmd.exec("uuagc " + settings.uuagcOptions + " -o " + agHsFile.getPath() + " " + agTempFile.getPath(), System.out);
	}
	private void restoreFileReferences() throws IOException {
		final Map<File, File> agFiles = new HashMap<File, File>();
		for (Entry<File, File> entry: agTempFiles.entrySet()) {
			agFiles.put(entry.getValue(), entry.getKey());
		}

		StringBuffer sb = new StringBuffer();
		final BufferedReader in = new BufferedReader(new FileReader(agHsFile));
		Throwable inEx = null;
		try {
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(ReplaceCallback.replace(LINE_PAT, line, new ReplaceCallback.Callback() {
					@Override
					public String replaceMatch(MatchResult match) {
						File file = new File(match.group(2));
						if (agFiles.containsKey(file)) {
							file = agFiles.get(file);
						}
						return "{-# LINE " + match.group(1) + " \"" + file.getPath() + "\" #-}";
					}
				}))
				.append('\n');
			}
		} catch (IOException e) {
			inEx = e;
			throw e;
		} finally {
			if (in != null) {
				if (inEx != null) {
					try {
						in.close();
					} catch (Throwable t) {}
				} else {
					in.close();
				}
			}
		}

		final BufferedWriter out = new BufferedWriter(new FileWriter(agHsFile));
		Throwable outEx = null;
		try {
			out.write(sb.toString());
		} catch (IOException e) {
			outEx = e;
			throw e;
		} finally {
			if (out != null) {
				if (outEx != null) {
					try {
						out.close();
					} catch (Throwable t) {}
				} else {
					out.close();
				}
			}
		}
	}

	private File createTempAG(File agFile) throws IOException {
		File agTempFile = File.createTempFile("uuagd", ".ag");
		agTempFiles.put(agFile, agTempFile);
		return agTempFile;
	}

	private static void error(String msg) {
		System.err.println(msg);
		System.exit(0);
	}
}
