// Generated from PrettyAGLexer.g4 by ANTLR 4.1

  package parser.pretty;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrettyAGLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ANY_CHAR=1, AG_DATA_TYPE=2, START_BLOCK_COMMENT=3, END_BLOCK_COMMENT=4, 
		WS=5, VISIT=6, ATTRS=7, ALTS=8, COLON=9, SYN=10, INH=11, CHN=12, LOC=13, 
		ALT=14, KID=15, AG_TYPE=16, IDENT=17, HS_TYPE=18;
	public static final int AG_DATA = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "AG_DATA"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"ANY_CHAR", "AG_DATA_TYPE", "'{-'", "'-}'", "WS", "VISIT", "ATTRS", "'alternatives:'", 
		"':'", "'synthesized'", "'inherited'", "'chained'", "'local'", "'alternative'", 
		"'child'", "AG_TYPE", "IDENT", "HS_TYPE"
	};
	public static final String[] ruleNames = {
		"ANY_CHAR", "AG_DATA_TYPE", "START_BLOCK_COMMENT", "END_BLOCK_COMMENT", 
		"WS", "VISIT", "ATTRS", "ALTS", "COLON", "SYN", "INH", "CHN", "LOC", "ALT", 
		"KID", "AG_TYPE", "IDENT", "HS_TYPE"
	};


	public PrettyAGLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrettyAGLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0: ANY_CHAR_action(_localctx, actionIndex); break;

		case 1: AG_DATA_TYPE_action(_localctx, actionIndex); break;

		case 3: END_BLOCK_COMMENT_action(_localctx, actionIndex); break;

		case 4: WS_action(_localctx, actionIndex); break;

		case 5: VISIT_action(_localctx, actionIndex); break;

		case 6: ATTRS_action(_localctx, actionIndex); break;

		case 7: ALTS_action(_localctx, actionIndex); break;

		case 8: COLON_action(_localctx, actionIndex); break;

		case 17: HS_TYPE_action(_localctx, actionIndex); break;
		}
	}
	private void COLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: skip();  break;
		}
	}
	private void ATTRS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: skip();  break;
		}
	}
	private void ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void ALTS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: skip();  break;
		}
	}
	private void HS_TYPE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: 
		      String text = getText();

		      // Discard the characters used to delimit the token.
		      int to = text.indexOf('\r');
		      if (to == -1) to = text.indexOf('\n');
		      text = text.substring(2, to);

		      // In the case of an escaped type, remove the braces.
		      if (text.charAt(0) == '{' && text.charAt(text.length() - 1) == '}') {
		        text = text.substring(1, text.length() - 1);
		      }

		      setText(text.trim());
		     break;
		}
	}
	private void END_BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: popMode();  break;
		}
	}
	private void VISIT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: skip();  break;
		}
	}
	private void AG_DATA_TYPE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: 
		      // After removing the dashes and whitespace,
		      // what remains will be the data type.
		      setText(getText().replaceAll("[\\-\\s]+", ""));

		      // For the AG data we need to use different lexer rules.
		      pushMode(AG_DATA);
		     break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\24\u00e3\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\63\n\3"+
		"\f\3\16\3\66\13\3\3\3\3\3\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3\3\3\5\3"+
		"C\n\3\3\3\3\3\5\3G\n\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\6\6"+
		"T\n\6\r\6\16\6U\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\6\7b\n\7\r\7\16"+
		"\7c\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bu\n"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\7\21\u00c7\n\21\f\21\16\21\u00ca\13\21\3\22\3\22\7\22\u00ce"+
		"\n\22\f\22\16\22\u00d1\13\22\3\23\3\23\3\23\3\23\6\23\u00d7\n\23\r\23"+
		"\16\23\u00d8\3\23\5\23\u00dc\n\23\3\23\3\23\5\23\u00e0\n\23\3\23\3\23"+
		"\2\24\4\3\4\6\4\2\b\5\1\n\6\5\f\7\6\16\b\7\20\t\b\22\n\t\24\13\n\26\f"+
		"\1\30\r\1\32\16\1\34\17\1\36\20\1 \21\1\"\22\1$\23\1&\24\3\4\2\3\t\3\2"+
		"C\\\7\2))\62;C\\aac|\3\2//\5\2\13\f\17\17\"\"\3\2\62;\3\2c|\4\2\f\f\17"+
		"\17\u00ed\2\4\3\2\2\2\2\6\3\2\2\2\3\b\3\2\2\2\3\n\3\2\2\2\3\f\3\2\2\2"+
		"\3\16\3\2\2\2\3\20\3\2\2\2\3\22\3\2\2\2\3\24\3\2\2\2\3\26\3\2\2\2\3\30"+
		"\3\2\2\2\3\32\3\2\2\2\3\34\3\2\2\2\3\36\3\2\2\2\3 \3\2\2\2\3\"\3\2\2\2"+
		"\3$\3\2\2\2\3&\3\2\2\2\4(\3\2\2\2\6,\3\2\2\2\bJ\3\2\2\2\nM\3\2\2\2\fS"+
		"\3\2\2\2\16Y\3\2\2\2\20i\3\2\2\2\22z\3\2\2\2\24\u008a\3\2\2\2\26\u008e"+
		"\3\2\2\2\30\u009a\3\2\2\2\32\u00a4\3\2\2\2\34\u00ac\3\2\2\2\36\u00b2\3"+
		"\2\2\2 \u00be\3\2\2\2\"\u00c4\3\2\2\2$\u00cb\3\2\2\2&\u00d2\3\2\2\2()"+
		"\13\2\2\2)*\3\2\2\2*+\b\2\4\2+\5\3\2\2\2,-\7/\2\2-.\7/\2\2./\7\"\2\2/"+
		"\60\3\2\2\2\60\64\t\2\2\2\61\63\t\3\2\2\62\61\3\2\2\2\63\66\3\2\2\2\64"+
		"\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7\"\2\289\7"+
		"/\2\29:\7/\2\2:>\3\2\2\2;=\t\4\2\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2"+
		"\2\2?F\3\2\2\2@>\3\2\2\2AC\7\17\2\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2\2DG\7"+
		"\f\2\2EG\7\17\2\2FB\3\2\2\2FE\3\2\2\2GH\3\2\2\2HI\b\3\2\2I\7\3\2\2\2J"+
		"K\7}\2\2KL\7/\2\2L\t\3\2\2\2MN\7/\2\2NO\7\177\2\2OP\3\2\2\2PQ\b\5\5\2"+
		"Q\13\3\2\2\2RT\t\5\2\2SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2"+
		"\2WX\b\6\6\2X\r\3\2\2\2YZ\7x\2\2Z[\7k\2\2[\\\7u\2\2\\]\7k\2\2]^\7v\2\2"+
		"^_\7\"\2\2_a\3\2\2\2`b\t\6\2\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2"+
		"de\3\2\2\2ef\7<\2\2fg\3\2\2\2gh\b\7\7\2h\17\3\2\2\2ij\7c\2\2jk\7v\2\2"+
		"kl\7v\2\2lm\7t\2\2mn\7k\2\2no\7d\2\2op\7w\2\2pq\7v\2\2qr\7g\2\2rt\3\2"+
		"\2\2su\7u\2\2ts\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\7<\2\2wx\3\2\2\2xy\b\b\b"+
		"\2y\21\3\2\2\2z{\7c\2\2{|\7n\2\2|}\7v\2\2}~\7g\2\2~\177\7t\2\2\177\u0080"+
		"\7p\2\2\u0080\u0081\7c\2\2\u0081\u0082\7v\2\2\u0082\u0083\7k\2\2\u0083"+
		"\u0084\7x\2\2\u0084\u0085\7g\2\2\u0085\u0086\7u\2\2\u0086\u0087\7<\2\2"+
		"\u0087\u0088\3\2\2\2\u0088\u0089\b\t\t\2\u0089\23\3\2\2\2\u008a\u008b"+
		"\7<\2\2\u008b\u008c\3\2\2\2\u008c\u008d\b\n\n\2\u008d\25\3\2\2\2\u008e"+
		"\u008f\7u\2\2\u008f\u0090\7{\2\2\u0090\u0091\7p\2\2\u0091\u0092\7v\2\2"+
		"\u0092\u0093\7j\2\2\u0093\u0094\7g\2\2\u0094\u0095\7u\2\2\u0095\u0096"+
		"\7k\2\2\u0096\u0097\7|\2\2\u0097\u0098\7g\2\2\u0098\u0099\7f\2\2\u0099"+
		"\27\3\2\2\2\u009a\u009b\7k\2\2\u009b\u009c\7p\2\2\u009c\u009d\7j\2\2\u009d"+
		"\u009e\7g\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7v\2\2"+
		"\u00a1\u00a2\7g\2\2\u00a2\u00a3\7f\2\2\u00a3\31\3\2\2\2\u00a4\u00a5\7"+
		"e\2\2\u00a5\u00a6\7j\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9"+
		"\7p\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7f\2\2\u00ab\33\3\2\2\2\u00ac\u00ad"+
		"\7n\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7e\2\2\u00af\u00b0\7c\2\2\u00b0"+
		"\u00b1\7n\2\2\u00b1\35\3\2\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7n\2\2\u00b4"+
		"\u00b5\7v\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7p\2\2"+
		"\u00b8\u00b9\7c\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc"+
		"\7x\2\2\u00bc\u00bd\7g\2\2\u00bd\37\3\2\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0"+
		"\7j\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7f\2\2\u00c3"+
		"!\3\2\2\2\u00c4\u00c8\t\2\2\2\u00c5\u00c7\t\3\2\2\u00c6\u00c5\3\2\2\2"+
		"\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9#\3"+
		"\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cf\t\7\2\2\u00cc\u00ce\t\3\2\2\u00cd"+
		"\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0%\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d3\7<\2\2\u00d3\u00d4"+
		"\7\"\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d7\n\b\2\2\u00d6\u00d5\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00df\3\2"+
		"\2\2\u00da\u00dc\7\17\2\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00e0\7\f\2\2\u00de\u00e0\7\17\2\2\u00df\u00db\3"+
		"\2\2\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\b\23\3\2\u00e2"+
		"\'\3\2\2\2\20\2\3\64>BFUct\u00c8\u00cf\u00d8\u00db\u00df";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}