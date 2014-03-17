package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class TestLexer {
	public static <T extends Lexer> void testLexer(Class<T> cls, File input) throws Exception {
		testLexer(cls, new BufferedReader(new FileReader(input)));
	}

	public static <T extends Lexer> void testLexer(Class<T> cls, String input) throws Exception {
		testLexer(cls, new StringReader(input));
	}

	public static <T extends Lexer> void testLexer(Class<T> cls, Reader in) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(in);
		T lexer = cls.getConstructor(CharStream.class).newInstance(input);

		String[] tokenNames = (String[])cls.getDeclaredField("tokenNames").get(lexer);

		List<String[]> rows = new ArrayList<String[]>();
		Token token = lexer.nextToken();
		while(token.getType() != Recognizer.EOF) {
			rows.add(new String[] { tokenNames[token.getType()], Strings.toLiteral(token.getText()), token.getLine() + ":" + token.getCharPositionInLine() });
			token = lexer.nextToken();
		}
		printRows(rows);
	}

	public static void printRows(List<String[]> rows) {
		int n = rows.get(0).length;

		int[] maxLengths = new int[n];
		for (String[] row: rows) {
			for (int i = 0; i < n; i++) {
				maxLengths[i] = Math.max(row[i].length(), maxLengths[i]);
			}
		}

		int rowLength = n - 1;
		for (int length: maxLengths) {
			rowLength += length;
		}

		for (String[] row: rows) {
			StringBuffer sb = new StringBuffer(rowLength);
			for (int i = 0; i < n; i++) {
				if (i > 0) {
					sb.append('\t');
				}
				sb.append(row[i]);
				int spaces = maxLengths[i] - row[i].length();
				for (int j = 0; j < spaces; j++) {
					sb.append(' ');
				}
			}
			System.out.println(sb.toString());
		}
	}
}
