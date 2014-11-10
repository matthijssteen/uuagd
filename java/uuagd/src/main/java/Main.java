import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import util.Strings;
import ag.AttrId;
import ag.Preprocessor;
import ag.Settings;

public class Main {
	public static void main(String[] args) {
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
				.help("A whitespace seperated list of which attributes should only be shown");
		parser.addArgument("--blacklist")
				.type(AttrId.class)
				.nargs("*")
				.help("A whitespace seperated list of which attributes should only not be shown");
		parser.addArgument("--filterKids")
				.action(Arguments.storeTrue())
				.help("Whether the children/kids/fields of a data type should also be considered when apply filters");
		parser.addArgument("--embedImport")
				.setDefault("UUAGD.Html.TreeEmbed")
				.help("Which import to use for the implementation of the embedding");
		parser.addArgument("--showCode")
				.action(Arguments.storeTrue())
				.help("Should the code of the semantic actions be shown");

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
			settings.uuagcOptions = Strings.removeOutsideChars(settings.uuagcOptions);
		} catch (ArgumentParserException e) {
			parser.handleError(e);
			System.exit(0);
		}

		try {
			Preprocessor dp = new Preprocessor(settings);
			dp.preprocessAndCompile();
		} catch (Exception e) {
			System.out.println("Something went wrong:");
			e.printStackTrace(System.out);
		}
	}
}
