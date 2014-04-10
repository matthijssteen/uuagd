// Generated from RewriteAGLexer.g4 by ANTLR 4.1

  package parser.rewrite;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RewriteAGLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ANY_CHAR=1, LINE_COMMENT=2, MULTILINE_COMMENT=3, DATA=4, SEM=5, ALT=6, 
		ATTR=7, ATTR_TUPLE=8, INCLUDE=9;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"ANY_CHAR", "LINE_COMMENT", "MULTILINE_COMMENT", "DATA", "SEM", "ALT", 
		"ATTR", "ATTR_TUPLE", "INCLUDE"
	};
	public static final String[] ruleNames = {
		"ANY_CHAR", "LINE_COMMENT", "MULTILINE_COMMENT", "DATA", "SEM", "ALT", 
		"ATTR", "ATTR_TUPLE", "INCLUDE", "NAME", "ATTR_NAME", "TY", "STAR_TY", 
		"NL", "WS"
	};


	  private boolean semBlock = false;
	  private int lastTokenType = 0;

	  @Override
	  public void emit(Token token) {
	    super.emit(token);
	    lastTokenType = token.getType();
	  }


	public RewriteAGLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RewriteAGLexer.g4"; }

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
		case 0: ANY_CHAR_action((RuleContext)_localctx, actionIndex); break;

		case 1: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 2: MULTILINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 3: DATA_action((RuleContext)_localctx, actionIndex); break;

		case 4: SEM_action((RuleContext)_localctx, actionIndex); break;

		case 5: ALT_action((RuleContext)_localctx, actionIndex); break;

		case 6: ATTR_action((RuleContext)_localctx, actionIndex); break;

		case 7: ATTR_TUPLE_action((RuleContext)_localctx, actionIndex); break;

		case 14: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: skip();  break;
		}
	}
	private void MULTILINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:  setText(getText().replaceAll("\t", "    "));  break;
		}
	}
	private void ALT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:  if (!semBlock) { skip(); }  break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: skip();  break;
		}
	}
	private void ATTR_TUPLE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:  if (!semBlock) { skip(); }  break;
		}
	}
	private void ATTR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:  if (!semBlock) { skip(); }  break;
		}
	}
	private void DATA_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:  semBlock = false; skip();  break;
		}
	}
	private void SEM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:  semBlock = true;  break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6: return ATTR_sempred((RuleContext)_localctx, predIndex);

		case 7: return ATTR_TUPLE_sempred((RuleContext)_localctx, predIndex);

		case 13: return NL_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean NL_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return lastTokenType == 0;
		}
		return true;
	}
	private boolean ATTR_TUPLE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return lastTokenType == ALT;
		}
		return true;
	}
	private boolean ATTR_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return lastTokenType == ALT;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\13\u00fe\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\3\5\3\60\n\3\3\3\3\3\5\3\64"+
		"\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\7\4=\n\4\f\4\16\4@\13\4\5\4B\n\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5P\n\5\r\5\16\5Q\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\6\6\\\n\6\r\6\16\6]\3\6\3\6\3\7\3\7\3\7\6\7"+
		"e\n\7\r\7\16\7f\3\7\3\7\3\b\3\b\7\bm\n\b\f\b\16\bp\13\b\3\b\5\bs\n\b\3"+
		"\b\3\b\7\bw\n\b\f\b\16\bz\13\b\3\b\3\b\3\b\3\t\3\t\7\t\u0081\n\t\f\t\16"+
		"\t\u0084\13\t\3\t\5\t\u0087\n\t\3\t\3\t\7\t\u008b\n\t\f\t\16\t\u008e\13"+
		"\t\3\t\3\t\7\t\u0092\n\t\f\t\16\t\u0095\13\t\3\t\3\t\7\t\u0099\n\t\f\t"+
		"\16\t\u009c\13\t\3\t\6\t\u009f\n\t\r\t\16\t\u00a0\3\t\7\t\u00a4\n\t\f"+
		"\t\16\t\u00a7\13\t\3\t\3\t\7\t\u00ab\n\t\f\t\16\t\u00ae\13\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00bd\n\n\r\n\16\n\u00be"+
		"\3\n\3\n\3\n\3\n\6\n\u00c5\n\n\r\n\16\n\u00c6\3\n\3\n\3\13\3\13\7\13\u00cd"+
		"\n\13\f\13\16\13\u00d0\13\13\3\f\3\f\3\f\3\f\3\r\6\r\u00d7\n\r\r\r\16"+
		"\r\u00d8\3\r\3\r\7\r\u00dd\n\r\f\r\16\r\u00e0\13\r\3\16\3\16\6\16\u00e4"+
		"\n\16\r\16\16\16\u00e5\3\16\3\16\5\16\u00ea\n\16\3\17\3\17\5\17\u00ee"+
		"\n\17\3\17\3\17\5\17\u00f2\n\17\3\17\7\17\u00f5\n\17\f\17\16\17\u00f8"+
		"\13\17\5\17\u00fa\n\17\3\20\3\20\3\20\5+>\u00c6\21\3\3\b\5\4\t\7\5\n\t"+
		"\6\2\13\7\3\r\b\4\17\t\5\21\n\6\23\13\1\25\2\1\27\2\1\31\2\1\33\2\1\35"+
		"\2\1\37\2\7\3\2\b\3\3\17\17\4\2$$^^\3\2c|\7\2))\62;C\\aac|\3\2C\\\5\2"+
		"\13\f\17\17\"\"\u0116\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3!\3"+
		"\2\2\2\5%\3\2\2\2\7\67\3\2\2\2\tH\3\2\2\2\13U\3\2\2\2\ra\3\2\2\2\17r\3"+
		"\2\2\2\21\u0086\3\2\2\2\23\u00b2\3\2\2\2\25\u00ca\3\2\2\2\27\u00d1\3\2"+
		"\2\2\31\u00d6\3\2\2\2\33\u00e9\3\2\2\2\35\u00f9\3\2\2\2\37\u00fb\3\2\2"+
		"\2!\"\13\2\2\2\"#\3\2\2\2#$\b\2\b\2$\4\3\2\2\2%&\7/\2\2&\'\7/\2\2\'+\3"+
		"\2\2\2(*\13\2\2\2)(\3\2\2\2*-\3\2\2\2+,\3\2\2\2+)\3\2\2\2,\63\3\2\2\2"+
		"-+\3\2\2\2.\60\7\17\2\2/.\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\64\7\f"+
		"\2\2\62\64\t\2\2\2\63/\3\2\2\2\63\62\3\2\2\2\64\65\3\2\2\2\65\66\b\3\t"+
		"\2\66\6\3\2\2\2\678\7}\2\289\7/\2\29A\3\2\2\2:B\5\7\4\2;=\13\2\2\2<;\3"+
		"\2\2\2=@\3\2\2\2>?\3\2\2\2><\3\2\2\2?B\3\2\2\2@>\3\2\2\2A:\3\2\2\2A>\3"+
		"\2\2\2BC\3\2\2\2CD\7/\2\2DE\7\177\2\2EF\3\2\2\2FG\b\4\n\2G\b\3\2\2\2H"+
		"I\5\35\17\2IJ\7f\2\2JK\7c\2\2KL\7v\2\2LM\7c\2\2MO\3\2\2\2NP\5\31\r\2O"+
		"N\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\b\5\2\2T\n\3\2\2\2"+
		"UV\5\35\17\2VW\7u\2\2WX\7g\2\2XY\7o\2\2Y[\3\2\2\2Z\\\5\33\16\2[Z\3\2\2"+
		"\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\b\6\3\2`\f\3\2\2\2ab\5\35"+
		"\17\2bd\7~\2\2ce\5\33\16\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gh\3"+
		"\2\2\2hi\b\7\4\2i\16\3\2\2\2jn\6\b\2\2km\5\37\20\2lk\3\2\2\2mp\3\2\2\2"+
		"nl\3\2\2\2no\3\2\2\2os\3\2\2\2pn\3\2\2\2qs\5\35\17\2rj\3\2\2\2rq\3\2\2"+
		"\2st\3\2\2\2tx\5\27\f\2uw\5\37\20\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3"+
		"\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7?\2\2|}\b\b\5\2}\20\3\2\2\2~\u0082\6\t\3"+
		"\2\177\u0081\5\37\20\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0087\3\2\2\2\u0084\u0082\3\2\2\2\u0085"+
		"\u0087\5\35\17\2\u0086~\3\2\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2"+
		"\2\u0088\u008c\7*\2\2\u0089\u008b\5\37\20\2\u008a\u0089\3\2\2\2\u008b"+
		"\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2"+
		"\2\2\u008e\u008c\3\2\2\2\u008f\u009e\5\27\f\2\u0090\u0092\5\37\20\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u009a\7.\2\2\u0097"+
		"\u0099\5\37\20\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3"+
		"\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d"+
		"\u009f\5\27\f\2\u009e\u0093\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3"+
		"\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a5\3\2\2\2\u00a2\u00a4\5\37\20\2\u00a3"+
		"\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00ac\7+\2\2\u00a9"+
		"\u00ab\5\37\20\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3"+
		"\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00b0\7?\2\2\u00b0\u00b1\b\t\6\2\u00b1\22\3\2\2\2\u00b2\u00b3\5\35\17"+
		"\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7e\2\2\u00b6\u00b7"+
		"\7n\2\2\u00b7\u00b8\7w\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7g\2\2\u00ba"+
		"\u00bc\3\2\2\2\u00bb\u00bd\5\37\20\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3"+
		"\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c4\7$\2\2\u00c1\u00c5\n\3\2\2\u00c2\u00c3\7^\2\2\u00c3\u00c5\13\2"+
		"\2\2\u00c4\u00c1\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\7$"+
		"\2\2\u00c9\24\3\2\2\2\u00ca\u00ce\t\4\2\2\u00cb\u00cd\t\5\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\26\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\5\25\13\2\u00d2\u00d3\7\60"+
		"\2\2\u00d3\u00d4\5\25\13\2\u00d4\30\3\2\2\2\u00d5\u00d7\5\37\20\2\u00d6"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2"+
		"\2\2\u00d9\u00da\3\2\2\2\u00da\u00de\t\6\2\2\u00db\u00dd\t\5\2\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\32\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00ea\5\31\r\2\u00e2\u00e4"+
		"\5\37\20\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2"+
		"\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\7,\2\2\u00e8\u00ea"+
		"\3\2\2\2\u00e9\u00e1\3\2\2\2\u00e9\u00e3\3\2\2\2\u00ea\34\3\2\2\2\u00eb"+
		"\u00fa\6\17\4\2\u00ec\u00ee\7\17\2\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3"+
		"\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f2\7\f\2\2\u00f0\u00f2\7\17\2\2\u00f1"+
		"\u00ed\3\2\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f6\3\2\2\2\u00f3\u00f5\5\37"+
		"\20\2\u00f4\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00eb\3\2"+
		"\2\2\u00f9\u00f1\3\2\2\2\u00fa\36\3\2\2\2\u00fb\u00fc\t\7\2\2\u00fc\u00fd"+
		"\b\20\7\2\u00fd \3\2\2\2\"\2+/\63>AQ]fnrx\u0082\u0086\u008c\u0093\u009a"+
		"\u00a0\u00a5\u00ac\u00be\u00c4\u00c6\u00ce\u00d8\u00de\u00e5\u00e9\u00ed"+
		"\u00f1\u00f6\u00f9";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}