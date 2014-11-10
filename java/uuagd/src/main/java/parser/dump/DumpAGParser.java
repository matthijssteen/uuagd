// Generated from DumpAGParser.g4 by ANTLR 4.1

  package parser.dump;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DumpAGParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LIST_END=27, LIST_SET=15, TYPE_MAP_EOL=31, DATA_CON_VAL_SEP=8, TYPE_MAP_KEY=30, 
		LINE_COMMENT=14, TUPLE_END=28, KEY=10, TYPE_SIGS_ANY_CHAR=38, TXT_ANY_CHAR=34, 
		TYPE_SIGS_BIG_LIST_END=39, ATTR_USAGE=6, DEDENT=3, TYPE_SIGS_KEY=9, DUMP_END=23, 
		BIG_LIST_START=18, TXT_ATTR_USAGE=35, TEXT=5, ENTRIES_STRING=26, DUMP_START=2, 
		INDENT=4, ENTRIES_NAME=25, ANY_CHAR=1, EOL=22, BIG_LIST_SEP=19, TYPE_MAP_END=32, 
		TXT_EOL=37, TYPE_MAP_ANY_CHAR=33, TXT_BIG_LIST_END=36, CON=11, BIG_LIST_EMPTY=21, 
		FIELD=12, TYPE_MAP_START=29, TUPLE_START=17, FILE_LOC=13, DATA_CON=7, 
		BIG_LIST_END=20, LIST_START=16, ENTRIES_SEP=24;
	public static final String[] tokenNames = {
		"<INVALID>", "ANY_CHAR", "'{- Dump of grammar without default rules'", 
		"DEDENT", "INDENT", "TEXT", "ATTR_USAGE", "DATA_CON", "' '", "'typeSigs: '", 
		"KEY", "CON", "FIELD", "FILE_LOC", "LINE_COMMENT", "'fromList '", "'['", 
		"'('", "'[ '", "', '", "' ]'", "'[ ]'", "EOL", "'-}'", "','", "ENTRIES_NAME", 
		"ENTRIES_STRING", "']'", "')'", "TYPE_MAP_START", "TYPE_MAP_KEY", "TYPE_MAP_EOL", 
		"TYPE_MAP_END", "TYPE_MAP_ANY_CHAR", "TXT_ANY_CHAR", "TXT_ATTR_USAGE", 
		"TXT_BIG_LIST_END", "TXT_EOL", "TYPE_SIGS_ANY_CHAR", "TYPE_SIGS_BIG_LIST_END"
	};
	public static final int
		RULE_root = 0, RULE_dataCon = 1, RULE_dataConVals = 2, RULE_dataConVal = 3, 
		RULE_set = 4, RULE_list = 5, RULE_tuple = 6, RULE_entry = 7, RULE_typeMap = 8, 
		RULE_typeMapEntry = 9, RULE_map = 10, RULE_mapEntry = 11, RULE_code = 12, 
		RULE_val = 13;
	public static final String[] ruleNames = {
		"root", "dataCon", "dataConVals", "dataConVal", "set", "list", "tuple", 
		"entry", "typeMap", "typeMapEntry", "map", "mapEntry", "code", "val"
	};

	@Override
	public String getGrammarFileName() { return "DumpAGParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public DumpAGParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public DataConContext dataCon() {
			return getRuleContext(DataConContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); dataCon();
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

	public static class DataConContext extends ParserRuleContext {
		public MapEntryContext mapEntry(int i) {
			return getRuleContext(MapEntryContext.class,i);
		}
		public TerminalNode INDENT() { return getToken(DumpAGParser.INDENT, 0); }
		public DataConValsContext dataConVals() {
			return getRuleContext(DataConValsContext.class,0);
		}
		public TerminalNode DEDENT() { return getToken(DumpAGParser.DEDENT, 0); }
		public List<MapEntryContext> mapEntry() {
			return getRuleContexts(MapEntryContext.class);
		}
		public TerminalNode DATA_CON() { return getToken(DumpAGParser.DATA_CON, 0); }
		public DataConContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataCon; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterDataCon(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitDataCon(this);
		}
	}

	public final DataConContext dataCon() throws RecognitionException {
		DataConContext _localctx = new DataConContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dataCon);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(DATA_CON);
			setState(31); dataConVals();
			setState(40);
			_la = _input.LA(1);
			if (_la==INDENT) {
				{
				setState(32); match(INDENT);
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(33); mapEntry();
					}
					}
					setState(36); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==KEY );
				setState(38); match(DEDENT);
				}
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

	public static class DataConValsContext extends ParserRuleContext {
		public List<DataConValContext> dataConVal() {
			return getRuleContexts(DataConValContext.class);
		}
		public DataConValContext dataConVal(int i) {
			return getRuleContext(DataConValContext.class,i);
		}
		public DataConValsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataConVals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterDataConVals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitDataConVals(this);
		}
	}

	public final DataConValsContext dataConVals() throws RecognitionException {
		DataConValsContext _localctx = new DataConValsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dataConVals);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(42); dataConVal();
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class DataConValContext extends ParserRuleContext {
		public TerminalNode CON() { return getToken(DumpAGParser.CON, 0); }
		public TerminalNode FILE_LOC() { return getToken(DumpAGParser.FILE_LOC, 0); }
		public TerminalNode FIELD() { return getToken(DumpAGParser.FIELD, 0); }
		public DataConValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataConVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterDataConVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitDataConVal(this);
		}
	}

	public final DataConValContext dataConVal() throws RecognitionException {
		DataConValContext _localctx = new DataConValContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dataConVal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CON) | (1L << FIELD) | (1L << FILE_LOC))) != 0)) ) {
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

	public static class SetContext extends ParserRuleContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public TerminalNode LIST_SET() { return getToken(DumpAGParser.LIST_SET, 0); }
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitSet(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(LIST_SET);
			setState(51); list();
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

	public static class ListContext extends ParserRuleContext {
		public List<ValContext> val() {
			return getRuleContexts(ValContext.class);
		}
		public TerminalNode BIG_LIST_EMPTY() { return getToken(DumpAGParser.BIG_LIST_EMPTY, 0); }
		public ValContext val(int i) {
			return getRuleContext(ValContext.class,i);
		}
		public TerminalNode BIG_LIST_END() { return getToken(DumpAGParser.BIG_LIST_END, 0); }
		public TerminalNode BIG_LIST_START() { return getToken(DumpAGParser.BIG_LIST_START, 0); }
		public EntryContext entry(int i) {
			return getRuleContext(EntryContext.class,i);
		}
		public List<EntryContext> entry() {
			return getRuleContexts(EntryContext.class);
		}
		public TerminalNode LIST_START() { return getToken(DumpAGParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(DumpAGParser.LIST_END, 0); }
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitList(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_list);
		int _la;
		try {
			setState(70);
			switch (_input.LA(1)) {
			case LIST_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(53); match(LIST_START);
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ENTRIES_NAME || _la==ENTRIES_STRING) {
					{
					{
					setState(54); entry();
					}
					}
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(60); match(LIST_END);
				}
				break;
			case BIG_LIST_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(61); match(BIG_LIST_START);
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << ATTR_USAGE) | (1L << DATA_CON) | (1L << CON) | (1L << LIST_SET) | (1L << LIST_START) | (1L << TUPLE_START) | (1L << BIG_LIST_START) | (1L << BIG_LIST_EMPTY) | (1L << TYPE_MAP_START))) != 0)) {
					{
					{
					setState(62); val();
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(68); match(BIG_LIST_END);
				}
				break;
			case BIG_LIST_EMPTY:
				enterOuterAlt(_localctx, 3);
				{
				setState(69); match(BIG_LIST_EMPTY);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode TUPLE_START() { return getToken(DumpAGParser.TUPLE_START, 0); }
		public TerminalNode TUPLE_END() { return getToken(DumpAGParser.TUPLE_END, 0); }
		public EntryContext entry(int i) {
			return getRuleContext(EntryContext.class,i);
		}
		public List<EntryContext> entry() {
			return getRuleContexts(EntryContext.class);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitTuple(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(TUPLE_START);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENTRIES_NAME || _la==ENTRIES_STRING) {
				{
				{
				setState(73); entry();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79); match(TUPLE_END);
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

	public static class EntryContext extends ParserRuleContext {
		public TerminalNode ENTRIES_NAME() { return getToken(DumpAGParser.ENTRIES_NAME, 0); }
		public TerminalNode ENTRIES_STRING() { return getToken(DumpAGParser.ENTRIES_STRING, 0); }
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitEntry(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_entry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_la = _input.LA(1);
			if ( !(_la==ENTRIES_NAME || _la==ENTRIES_STRING) ) {
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

	public static class TypeMapContext extends ParserRuleContext {
		public List<TypeMapEntryContext> typeMapEntry() {
			return getRuleContexts(TypeMapEntryContext.class);
		}
		public TerminalNode TYPE_MAP_START() { return getToken(DumpAGParser.TYPE_MAP_START, 0); }
		public TerminalNode TYPE_MAP_END() { return getToken(DumpAGParser.TYPE_MAP_END, 0); }
		public TypeMapEntryContext typeMapEntry(int i) {
			return getRuleContext(TypeMapEntryContext.class,i);
		}
		public TypeMapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeMap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterTypeMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitTypeMap(this);
		}
	}

	public final TypeMapContext typeMap() throws RecognitionException {
		TypeMapContext _localctx = new TypeMapContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeMap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); match(TYPE_MAP_START);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE_MAP_KEY) {
				{
				{
				setState(84); typeMapEntry();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90); match(TYPE_MAP_END);
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

	public static class TypeMapEntryContext extends ParserRuleContext {
		public TerminalNode TYPE_MAP_KEY() { return getToken(DumpAGParser.TYPE_MAP_KEY, 0); }
		public TerminalNode TEXT() { return getToken(DumpAGParser.TEXT, 0); }
		public TypeMapEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeMapEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterTypeMapEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitTypeMapEntry(this);
		}
	}

	public final TypeMapEntryContext typeMapEntry() throws RecognitionException {
		TypeMapEntryContext _localctx = new TypeMapEntryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeMapEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(TYPE_MAP_KEY);
			setState(93); match(TEXT);
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

	public static class MapContext extends ParserRuleContext {
		public MapEntryContext mapEntry(int i) {
			return getRuleContext(MapEntryContext.class,i);
		}
		public TerminalNode BIG_LIST_END() { return getToken(DumpAGParser.BIG_LIST_END, 0); }
		public TerminalNode BIG_LIST_START() { return getToken(DumpAGParser.BIG_LIST_START, 0); }
		public List<MapEntryContext> mapEntry() {
			return getRuleContexts(MapEntryContext.class);
		}
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitMap(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); match(BIG_LIST_START);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEY) {
				{
				{
				setState(96); mapEntry();
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102); match(BIG_LIST_END);
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

	public static class MapEntryContext extends ParserRuleContext {
		public ValContext val() {
			return getRuleContext(ValContext.class,0);
		}
		public TerminalNode KEY() { return getToken(DumpAGParser.KEY, 0); }
		public MapEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterMapEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitMapEntry(this);
		}
	}

	public final MapEntryContext mapEntry() throws RecognitionException {
		MapEntryContext _localctx = new MapEntryContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_mapEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); match(KEY);
			setState(105); val();
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

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode ATTR_USAGE(int i) {
			return getToken(DumpAGParser.ATTR_USAGE, i);
		}
		public TerminalNode TEXT(int i) {
			return getToken(DumpAGParser.TEXT, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(DumpAGParser.TEXT); }
		public List<TerminalNode> ATTR_USAGE() { return getTokens(DumpAGParser.ATTR_USAGE); }
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_code);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(108); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(107);
					_la = _input.LA(1);
					if ( !(_la==TEXT || _la==ATTR_USAGE) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(110); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
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

	public static class ValContext extends ParserRuleContext {
		public TerminalNode CON() { return getToken(DumpAGParser.CON, 0); }
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public DataConContext dataCon() {
			return getRuleContext(DataConContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TypeMapContext typeMap() {
			return getRuleContext(TypeMapContext.class,0);
		}
		public ValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).enterVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DumpAGParserListener ) ((DumpAGParserListener)listener).exitVal(this);
		}
	}

	public final ValContext val() throws RecognitionException {
		ValContext _localctx = new ValContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_val);
		try {
			setState(120);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); dataCon();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); set();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114); list();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(115); tuple();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(116); typeMap();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(117); map();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(118); match(CON);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(119); code();
				}
				break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3)}\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\3\3\6\3%\n\3\r\3"+
		"\16\3&\3\3\3\3\5\3+\n\3\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\7\7:\n\7\f\7\16\7=\13\7\3\7\3\7\3\7\7\7B\n\7\f\7\16\7E\13"+
		"\7\3\7\3\7\5\7I\n\7\3\b\3\b\7\bM\n\b\f\b\16\bP\13\b\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\7\nX\n\n\f\n\16\n[\13\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\7\fd\n\f"+
		"\f\f\16\fg\13\f\3\f\3\f\3\r\3\r\3\r\3\16\6\16o\n\16\r\16\16\16p\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17{\n\17\3\17\2\20\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\2\5\3\2\r\17\3\2\33\34\3\2\7\b\u0080\2\36\3\2\2"+
		"\2\4 \3\2\2\2\6/\3\2\2\2\b\62\3\2\2\2\n\64\3\2\2\2\fH\3\2\2\2\16J\3\2"+
		"\2\2\20S\3\2\2\2\22U\3\2\2\2\24^\3\2\2\2\26a\3\2\2\2\30j\3\2\2\2\32n\3"+
		"\2\2\2\34z\3\2\2\2\36\37\5\4\3\2\37\3\3\2\2\2 !\7\t\2\2!*\5\6\4\2\"$\7"+
		"\6\2\2#%\5\30\r\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'(\3\2\2\2"+
		"()\7\5\2\2)+\3\2\2\2*\"\3\2\2\2*+\3\2\2\2+\5\3\2\2\2,.\5\b\5\2-,\3\2\2"+
		"\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\7\3\2\2\2\61/\3\2\2\2\62\63\t"+
		"\2\2\2\63\t\3\2\2\2\64\65\7\21\2\2\65\66\5\f\7\2\66\13\3\2\2\2\67;\7\22"+
		"\2\28:\5\20\t\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3"+
		"\2\2\2>I\7\35\2\2?C\7\24\2\2@B\5\34\17\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2"+
		"CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FI\7\26\2\2GI\7\27\2\2H\67\3\2\2\2H?\3\2"+
		"\2\2HG\3\2\2\2I\r\3\2\2\2JN\7\23\2\2KM\5\20\t\2LK\3\2\2\2MP\3\2\2\2NL"+
		"\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\36\2\2R\17\3\2\2\2ST\t\3\2"+
		"\2T\21\3\2\2\2UY\7\37\2\2VX\5\24\13\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ"+
		"\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7\"\2\2]\23\3\2\2\2^_\7 \2\2_`\7\7\2"+
		"\2`\25\3\2\2\2ae\7\24\2\2bd\5\30\r\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3"+
		"\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7\26\2\2i\27\3\2\2\2jk\7\f\2\2kl\5\34\17"+
		"\2l\31\3\2\2\2mo\t\4\2\2nm\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\33\3"+
		"\2\2\2r{\5\4\3\2s{\5\n\6\2t{\5\f\7\2u{\5\16\b\2v{\5\22\n\2w{\5\26\f\2"+
		"x{\7\r\2\2y{\5\32\16\2zr\3\2\2\2zs\3\2\2\2zt\3\2\2\2zu\3\2\2\2zv\3\2\2"+
		"\2zw\3\2\2\2zx\3\2\2\2zy\3\2\2\2{\35\3\2\2\2\r&*/;CHNYepz";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}