// Generated from CmdArgsLexer.g4 by ANTLR 4.1

  package parser.args;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmdArgsLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SingleQuotedString=1, DoubleQuotedString=2, Word=3, WhiteSpace=4;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"SingleQuotedString", "DoubleQuotedString", "Word", "WhiteSpace"
	};
	public static final String[] ruleNames = {
		"SingleQuotedString", "DoubleQuotedString", "Word", "WhiteSpace"
	};


	public CmdArgsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CmdArgsLexer.g4"; }

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
		case 3: WhiteSpace_action(_localctx, actionIndex); break;
		}
	}
	private void WhiteSpace_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\6+\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\6\2\20\n\2\r\2\16\2\21\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\6\3\32\n\3\r\3\16\3\33\3\3\3\3\3\4\6\4!\n\4\r\4\16\4"+
		"\"\3\5\6\5&\n\5\r\5\16\5\'\3\5\3\5\4\21\33\6\3\3\1\5\4\1\7\5\1\t\6\2\3"+
		"\2\6\4\2))^^\4\2$$^^\5\2\13\13\"\"$$\5\2\13\f\17\17\"\"\60\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\3\13\3\2\2\2\5\25\3\2\2\2\7 \3\2"+
		"\2\2\t%\3\2\2\2\13\17\7)\2\2\f\20\n\2\2\2\r\16\7^\2\2\16\20\13\2\2\2\17"+
		"\f\3\2\2\2\17\r\3\2\2\2\20\21\3\2\2\2\21\22\3\2\2\2\21\17\3\2\2\2\22\23"+
		"\3\2\2\2\23\24\7)\2\2\24\4\3\2\2\2\25\31\7$\2\2\26\32\n\3\2\2\27\30\7"+
		"^\2\2\30\32\13\2\2\2\31\26\3\2\2\2\31\27\3\2\2\2\32\33\3\2\2\2\33\34\3"+
		"\2\2\2\33\31\3\2\2\2\34\35\3\2\2\2\35\36\7$\2\2\36\6\3\2\2\2\37!\n\4\2"+
		"\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\b\3\2\2\2$&\t\5\2\2%"+
		"$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\b\5\2\2*\n\3\2"+
		"\2\2\t\2\17\21\31\33\"\'";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}