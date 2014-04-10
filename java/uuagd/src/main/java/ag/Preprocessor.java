package ag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;

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
import util.Files;
import util.ReplaceCallback;
import ag.gen.ShallowEmbed;

class Preprocessor {
	final private static Pattern ALT_PAT = Pattern.compile("([A-Z][a-zA-Z0-9_']*|\\*)");
	final private static Pattern LINE_PAT = Pattern.compile("\\{\\-# LINE (\\d+) \"(.*?)\" #\\-\\}");

	final private Settings settings;

	final private File agHsFile;
	final private Map<File, File> agTempFiles;

	private RuleContext prettyTree;
	private PrettyListener agInfo;
	private AttrUsageFactory attrUsageFactory;
	private File agTempFile;

	public Preprocessor(Settings settings) throws Exception {
		this.settings = settings;

		agHsFile = new File(getWithoutExt(settings.agFile.getPath()) + ".hs");
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

		exec("uuagc -H --pretty -o " + hsTempFile.getPath() + " " + settings.agFile.getPath(), System.out);

		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(hsTempFile));
		PrettyAGLexer lexer = new PrettyAGLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PrettyAGParser parser = new PrettyAGParser(tokens);
		prettyTree = parser.root();

		Files.delete(hsTempFile);
	}

	private void gatherInfo() {
		ParseTreeWalker walker = new ParseTreeWalker();
		agInfo = new PrettyListener();
		walker.walk(agInfo, prettyTree);

		attrUsageFactory = new AttrUsageFactory(agInfo, settings);
	}

	private void preprocess() throws Exception {
		agTempFile = createTempAG(settings.agFile);
		processAG(settings.agFile, true);
	}

	private void processAG(File agFile, boolean isRoot) throws FileNotFoundException, IOException {
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
		Set<String> dataTypes = null;
		Set<String> alts = null;

		// First lex the attribute grammar to determine which tokens have to be rewritten
		// for the debugging to work, like creating local aliases for non-local attributes.
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(agFile));
		RewriteAGLexer lexer = new RewriteAGLexer(input);
		Token token = lexer.nextToken();
		while (token.getType() != Recognizer.EOF) {
			switch (token.getType()) {
				case RewriteAGLexer.INCLUDE: {
					String filename = removeOutsideChars(removeLeading(token.getText(), "include"));
					File file = new File(filename);
					if (!file.isAbsolute()) {
						file = new File(settings.agFile.getParent() + File.separator + filename);
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
						System.out.println(dataTypes);
						System.out.println(alts);
						System.out.println(token);
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
					ShallowEmbed gen = new ShallowEmbed(out, agInfo, attrUsageFactory.attrUsages, settings);
					walker.walk(gen, prettyTree);
				}
			} catch (IOException e) {
				inEx = e;
				throw e;
			} finally {
				if (in != null) {
					if (inEx != null) {
						try {
							in.close();
						} catch (Throwable t) {
							inEx.addSuppressed(t);
						}
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
					} catch (Throwable t) {
						outEx.addSuppressed(t);
					}
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
		exec("uuagc " + settings.uuagcOptions + " -o " + agHsFile.getPath() + " " + agTempFile.getPath(), System.out);
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
					} catch (Throwable t) {
						inEx.addSuppressed(t);
					}
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
					} catch (Throwable t) {
						outEx.addSuppressed(t);
					}
				} else {
					out.close();
				}
			}
		}
	}

	private void exec(String cmd, Appendable... dests) throws IOException {
		System.out.println("Executing: " + cmd);

		// Execute the given command and wait until it has terminated.
		String[] args = parseCmd(cmd);
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		Process p = pb.start();
		Thread t = inheritIO(p.getInputStream(), dests);
		t.start();
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		// Waiting for the process does not mean that we are finished reading the output stream,
		// so we have to join the stream handler thread.
		try {
			t.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		if (p.exitValue() == 0) {
			System.out.println("Exited with success.");
		} else {
			System.out.println("Exited with failure.");

			// If something went wrong during the execution of the command,
			// we have to terminate this program as well, otherwise it would become unreliable.
			System.exit(1);
		}
	}

	private static String[] parseCmd(String cmd) throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(new StringReader(cmd));
		CmdArgsLexer lexer = new CmdArgsLexer(input);
		List<String> args = new ArrayList<String>();
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
				try {
					final BufferedReader br = new BufferedReader(new InputStreamReader(src));
					Throwable brEx = null;
					try {
						String line;
						while ((line = br.readLine()) != null) {
							for (Appendable dest: dests) {
								dest.append(line).append(System.getProperty("line.separator"));
							}
						}
					} catch (IOException e) {
						brEx = e;
						throw e;
					} finally {
						if (br != null) {
							if (brEx != null) {
								try {
									br.close();
								} catch (Throwable t) {
									brEx.addSuppressed(t);
								}
							} else {
								br.close();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private File createTempAG(File agFile) throws IOException {
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
		Set<String> types = new HashSet<String>();
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

	public static void main(String[] args) throws Throwable {
		ArgumentParser parser = ArgumentParsers.newArgumentParser("uuagd")
				.defaultHelp(true)
				.description("A debugger for UUAG files.");
		parser.addArgument("uuagcOptions")
				.help("Options to be passed along to the UUAG Compiler");
		parser.addArgument("agFile")
				.type(File.class)
				.help("The UUAG file to debug");
		parser.addArgument("castFn")
				.help("The Haskell function that will cast attribute values to the cast type so that they can be inspected");
		parser.addArgument("castTy")
				.help("The return type of the cast function");
		parser.addArgument("--whitelist")
				.type(AttrId.class)
				.nargs("*")
				.help("A comma-seperated list of which attributes should only be shown");
		parser.addArgument("--blacklist")
				.type(AttrId.class)
				.nargs("*")
				.help("A comma-seperated list of which attributes should only not be shown");
		parser.addArgument("--filterKids")
				.action(Arguments.storeTrue())
				.help("Whether the children/kids/fields of a data type should also be considered when apply filters");
		parser.addArgument("--noDefaultImport")
				.action(Arguments.storeTrue())
				.help("Whether the default code generation module should not be imported");

		if (args.length > 0) {
			// The uuagc options are ambiguous to the argument parser,
			// so we make sure it is recognized as a string.
			args[0] = '"' + args[0] + '"';

			// Apparently the positional arguments are required,
			// even when the help arguments are given.
			List<String> argList = Arrays.asList(args);
			if (argList.contains("-h") || argList.contains("--help")) {
				parser.printHelp();
				System.exit(0);
			}
		}

		Settings settings = new Settings();
		try {
			parser.parseArgs(args, settings);
			settings.uuagcOptions = removeOutsideChars(settings.uuagcOptions);
		} catch (ArgumentParserException e) {
			parser.handleError(e);
			System.exit(0);
		}

		Preprocessor dp = new Preprocessor(settings);
		dp.preprocessAndCompile();
	}
}
