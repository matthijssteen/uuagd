package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;

import parser.dump.DumpAGLexer;
import parser.dump.DumpAGParser;

public class TestParser {
	public static void main(String[] args) throws Exception {
		testParser(DumpAGLexer.class, DumpAGParser.class, new File("/sud/Code/Webroot/dump.uuagd.hs"));
	}
	
	public static <L extends Lexer, P extends Parser> void testParser(Class<L> lexerClass, Class<P> parserClass, File input) throws Exception {
		testParser(lexerClass, parserClass, new BufferedReader(new FileReader(input)));
	}

	public static <L extends Lexer, P extends Parser> void testParser(Class<L> lexerClass, Class<P> parserClass, String input) throws Exception {
		testParser(lexerClass, parserClass, new StringReader(input));
	}

	public static <L extends Lexer, P extends Parser> void testParser(Class<L> lexerClass, Class<P> parserClass, Reader in) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(in);
		L lexer = lexerClass.getConstructor(CharStream.class).newInstance(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		P parser = parserClass.getConstructor(TokenStream.class).newInstance(tokens);
		String startRule = ((String[])parserClass.getDeclaredField("ruleNames").get(null))[0];
		ParserRuleContext context = (ParserRuleContext)parserClass.getMethod(startRule).invoke(parser);
		context.inspect(parser);
	}
}
