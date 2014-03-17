// Generated from PrettyAGParser.g4 by ANTLR 4.1

  package parser.pretty;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrettyAGParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=9, ANY_CHAR=1, START_BLOCK_COMMENT=3, WS=5, ALTS=8, ALT=14, AG_DATA_TYPE=2, 
		LOC=13, ATTRS=7, IDENT=17, SYN=10, HS_TYPE=18, END_BLOCK_COMMENT=4, INH=11, 
		CHN=12, KID=15, VISIT=6, AG_TYPE=16;
	public static final String[] tokenNames = {
		"<INVALID>", "ANY_CHAR", "AG_DATA_TYPE", "'{-'", "'-}'", "WS", "VISIT", 
		"ATTRS", "'alternatives:'", "':'", "'synthesized'", "'inherited'", "'chained'", 
		"'local'", "'alternative'", "'child'", "AG_TYPE", "IDENT", "HS_TYPE"
	};
	public static final int
		RULE_root = 0, RULE_data = 1, RULE_attributes = 2, RULE_attrs = 3, RULE_attrKind = 4, 
		RULE_attr = 5, RULE_alternatives = 6, RULE_alt = 7, RULE_child = 8, RULE_local = 9;
	public static final String[] ruleNames = {
		"root", "data", "attributes", "attrs", "attrKind", "attr", "alternatives", 
		"alt", "child", "local"
	};

	@Override
	public String getGrammarFileName() { return "PrettyAGParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrettyAGParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public List<DataContext> data() {
			return getRuleContexts(DataContext.class);
		}
		public DataContext data(int i) {
			return getRuleContext(DataContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AG_DATA_TYPE) {
				{
				{
				setState(20); data();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode AG_DATA_TYPE() { return getToken(PrettyAGParser.AG_DATA_TYPE, 0); }
		public TerminalNode START_BLOCK_COMMENT() { return getToken(PrettyAGParser.START_BLOCK_COMMENT, 0); }
		public AlternativesContext alternatives() {
			return getRuleContext(AlternativesContext.class,0);
		}
		public TerminalNode END_BLOCK_COMMENT() { return getToken(PrettyAGParser.END_BLOCK_COMMENT, 0); }
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_data);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); match(AG_DATA_TYPE);
			setState(27); match(START_BLOCK_COMMENT);
			setState(28); attributes();
			setState(29); alternatives();
			setState(30); match(END_BLOCK_COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesContext extends ParserRuleContext {
		public List<AttrsContext> attrs() {
			return getRuleContexts(AttrsContext.class);
		}
		public AttrsContext attrs(int i) {
			return getRuleContext(AttrsContext.class,i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitAttributes(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); attrs();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYN) | (1L << INH) | (1L << CHN))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrsContext extends ParserRuleContext {
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public AttrKindContext attrKind() {
			return getRuleContext(AttrKindContext.class,0);
		}
		public AttrsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterAttrs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitAttrs(this);
		}
	}

	public final AttrsContext attrs() throws RecognitionException {
		AttrsContext _localctx = new AttrsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attrs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); attrKind();
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38); attr();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrKindContext extends ParserRuleContext {
		public TerminalNode CHN() { return getToken(PrettyAGParser.CHN, 0); }
		public TerminalNode INH() { return getToken(PrettyAGParser.INH, 0); }
		public TerminalNode SYN() { return getToken(PrettyAGParser.SYN, 0); }
		public AttrKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterAttrKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitAttrKind(this);
		}
	}

	public final AttrKindContext attrKind() throws RecognitionException {
		AttrKindContext _localctx = new AttrKindContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attrKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SYN) | (1L << INH) | (1L << CHN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttrContext extends ParserRuleContext {
		public TerminalNode HS_TYPE() { return getToken(PrettyAGParser.HS_TYPE, 0); }
		public TerminalNode IDENT() { return getToken(PrettyAGParser.IDENT, 0); }
		public AttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitAttr(this);
		}
	}

	public final AttrContext attr() throws RecognitionException {
		AttrContext _localctx = new AttrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); match(IDENT);
			setState(46); match(HS_TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlternativesContext extends ParserRuleContext {
		public AltContext alt(int i) {
			return getRuleContext(AltContext.class,i);
		}
		public List<AltContext> alt() {
			return getRuleContexts(AltContext.class);
		}
		public AlternativesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternatives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterAlternatives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitAlternatives(this);
		}
	}

	public final AlternativesContext alternatives() throws RecognitionException {
		AlternativesContext _localctx = new AlternativesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_alternatives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48); alt();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ALT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AltContext extends ParserRuleContext {
		public ChildContext child(int i) {
			return getRuleContext(ChildContext.class,i);
		}
		public TerminalNode AG_TYPE() { return getToken(PrettyAGParser.AG_TYPE, 0); }
		public TerminalNode ALT() { return getToken(PrettyAGParser.ALT, 0); }
		public List<ChildContext> child() {
			return getRuleContexts(ChildContext.class);
		}
		public List<LocalContext> local() {
			return getRuleContexts(LocalContext.class);
		}
		public LocalContext local(int i) {
			return getRuleContext(LocalContext.class,i);
		}
		public AltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterAlt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitAlt(this);
		}
	}

	public final AltContext alt() throws RecognitionException {
		AltContext _localctx = new AltContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(ALT);
			setState(54); match(AG_TYPE);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KID) {
				{
				{
				setState(55); child();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LOC) {
				{
				{
				setState(61); local();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChildContext extends ParserRuleContext {
		public TerminalNode HS_TYPE() { return getToken(PrettyAGParser.HS_TYPE, 0); }
		public TerminalNode KID() { return getToken(PrettyAGParser.KID, 0); }
		public TerminalNode IDENT() { return getToken(PrettyAGParser.IDENT, 0); }
		public ChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_child; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitChild(this);
		}
	}

	public final ChildContext child() throws RecognitionException {
		ChildContext _localctx = new ChildContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_child);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(KID);
			setState(68); match(IDENT);
			setState(69); match(HS_TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalContext extends ParserRuleContext {
		public TerminalNode HS_TYPE() { return getToken(PrettyAGParser.HS_TYPE, 0); }
		public TerminalNode LOC() { return getToken(PrettyAGParser.LOC, 0); }
		public TerminalNode IDENT() { return getToken(PrettyAGParser.IDENT, 0); }
		public LocalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).enterLocal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrettyAGParserListener ) ((PrettyAGParserListener)listener).exitLocal(this);
		}
	}

	public final LocalContext local() throws RecognitionException {
		LocalContext _localctx = new LocalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_local);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(LOC);
			setState(72); match(IDENT);
			setState(73); match(HS_TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\24N\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\6\4$\n\4\r"+
		"\4\16\4%\3\5\3\5\6\5*\n\5\r\5\16\5+\3\6\3\6\3\7\3\7\3\7\3\b\6\b\64\n\b"+
		"\r\b\16\b\65\3\t\3\t\3\t\7\t;\n\t\f\t\16\t>\13\t\3\t\7\tA\n\t\f\t\16\t"+
		"D\13\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\2\f\2\4\6\b\n\f\16\20"+
		"\22\24\2\3\3\2\f\16I\2\31\3\2\2\2\4\34\3\2\2\2\6#\3\2\2\2\b\'\3\2\2\2"+
		"\n-\3\2\2\2\f/\3\2\2\2\16\63\3\2\2\2\20\67\3\2\2\2\22E\3\2\2\2\24I\3\2"+
		"\2\2\26\30\5\4\3\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2"+
		"\2\2\32\3\3\2\2\2\33\31\3\2\2\2\34\35\7\4\2\2\35\36\7\5\2\2\36\37\5\6"+
		"\4\2\37 \5\16\b\2 !\7\6\2\2!\5\3\2\2\2\"$\5\b\5\2#\"\3\2\2\2$%\3\2\2\2"+
		"%#\3\2\2\2%&\3\2\2\2&\7\3\2\2\2\')\5\n\6\2(*\5\f\7\2)(\3\2\2\2*+\3\2\2"+
		"\2+)\3\2\2\2+,\3\2\2\2,\t\3\2\2\2-.\t\2\2\2.\13\3\2\2\2/\60\7\23\2\2\60"+
		"\61\7\24\2\2\61\r\3\2\2\2\62\64\5\20\t\2\63\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\63\3\2\2\2\65\66\3\2\2\2\66\17\3\2\2\2\678\7\20\2\28<\7\22\2\29;\5\22"+
		"\n\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=B\3\2\2\2><\3\2\2\2?A\5\24"+
		"\13\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\21\3\2\2\2DB\3\2\2\2EF"+
		"\7\21\2\2FG\7\23\2\2GH\7\24\2\2H\23\3\2\2\2IJ\7\17\2\2JK\7\23\2\2KL\7"+
		"\24\2\2L\25\3\2\2\2\b\31%+\65<B";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}