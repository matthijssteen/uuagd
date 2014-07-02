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
		LIST_END=24, LIST_SET=12, TYPE_MAP_EOL=28, DATA_CON_VAL_SEP=7, TYPE_MAP_KEY=27, 
		TUPLE_END=25, KEY=8, TXT_ANY_CHAR=31, DEDENT=3, DUMP_END=20, BIG_LIST_START=15, 
		TEXT=5, ENTRIES_STRING=23, DUMP_START=2, INDENT=4, ENTRIES_NAME=22, ANY_CHAR=1, 
		EOL=19, BIG_LIST_SEP=16, TYPE_MAP_END=29, TXT_EOL=33, TYPE_MAP_ANY_CHAR=30, 
		TXT_BIG_LIST_END=32, CON=9, BIG_LIST_EMPTY=18, FIELD=10, TYPE_MAP_START=26, 
		TUPLE_START=14, FILE_LOC=11, DATA_CON=6, BIG_LIST_END=17, LIST_START=13, 
		ENTRIES_SEP=21;
	public static final String[] tokenNames = {
		"<INVALID>", "ANY_CHAR", "'{- Dump of grammar without default rules'", 
		"DEDENT", "INDENT", "TEXT", "DATA_CON", "' '", "KEY", "CON", "FIELD", 
		"FILE_LOC", "'fromList '", "'['", "'('", "'[ '", "', '", "' ]'", "'[ ]'", 
		"EOL", "'-}'", "','", "ENTRIES_NAME", "ENTRIES_STRING", "']'", "')'", 
		"TYPE_MAP_START", "TYPE_MAP_KEY", "TYPE_MAP_EOL", "TYPE_MAP_END", "TYPE_MAP_ANY_CHAR", 
		"TXT_ANY_CHAR", "TXT_BIG_LIST_END", "TXT_EOL"
	};
	public static final int
		RULE_root = 0, RULE_dataCon = 1, RULE_dataConVal = 2, RULE_set = 3, RULE_list = 4, 
		RULE_tuple = 5, RULE_entry = 6, RULE_typeMap = 7, RULE_typeMapEntry = 8, 
		RULE_map = 9, RULE_mapEntry = 10, RULE_val = 11;
	public static final String[] ruleNames = {
		"root", "dataCon", "dataConVal", "set", "list", "tuple", "entry", "typeMap", 
		"typeMapEntry", "map", "mapEntry", "val"
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
			setState(24); dataCon();
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
		public List<DataConValContext> dataConVal() {
			return getRuleContexts(DataConValContext.class);
		}
		public TerminalNode DEDENT() { return getToken(DumpAGParser.DEDENT, 0); }
		public DataConValContext dataConVal(int i) {
			return getRuleContext(DataConValContext.class,i);
		}
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(26); match(DATA_CON);
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(27); dataConVal();
					}
					} 
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(41);
			_la = _input.LA(1);
			if (_la==INDENT) {
				{
				setState(33); match(INDENT);
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(34); mapEntry();
					}
					}
					setState(37); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==KEY );
				setState(39); match(DEDENT);
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
		enterRule(_localctx, 4, RULE_dataConVal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
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
		enterRule(_localctx, 6, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); match(LIST_SET);
			setState(46); list();
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
		enterRule(_localctx, 8, RULE_list);
		int _la;
		try {
			setState(65);
			switch (_input.LA(1)) {
			case LIST_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(48); match(LIST_START);
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ENTRIES_NAME || _la==ENTRIES_STRING) {
					{
					{
					setState(49); entry();
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(55); match(LIST_END);
				}
				break;
			case BIG_LIST_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(56); match(BIG_LIST_START);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEXT) | (1L << DATA_CON) | (1L << CON) | (1L << LIST_SET) | (1L << LIST_START) | (1L << TUPLE_START) | (1L << BIG_LIST_START) | (1L << BIG_LIST_EMPTY) | (1L << TYPE_MAP_START))) != 0)) {
					{
					{
					setState(57); val();
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(63); match(BIG_LIST_END);
				}
				break;
			case BIG_LIST_EMPTY:
				enterOuterAlt(_localctx, 3);
				{
				setState(64); match(BIG_LIST_EMPTY);
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
		enterRule(_localctx, 10, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(TUPLE_START);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENTRIES_NAME || _la==ENTRIES_STRING) {
				{
				{
				setState(68); entry();
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74); match(TUPLE_END);
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
		enterRule(_localctx, 12, RULE_entry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
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
		enterRule(_localctx, 14, RULE_typeMap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); match(TYPE_MAP_START);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE_MAP_KEY) {
				{
				{
				setState(79); typeMapEntry();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85); match(TYPE_MAP_END);
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
		enterRule(_localctx, 16, RULE_typeMapEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); match(TYPE_MAP_KEY);
			setState(88); match(TEXT);
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
		enterRule(_localctx, 18, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(BIG_LIST_START);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEY) {
				{
				{
				setState(91); mapEntry();
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97); match(BIG_LIST_END);
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
		enterRule(_localctx, 20, RULE_mapEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); match(KEY);
			setState(100); val();
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
		public TerminalNode TEXT() { return getToken(DumpAGParser.TEXT, 0); }
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public DataConContext dataCon() {
			return getRuleContext(DataConContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
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
		enterRule(_localctx, 22, RULE_val);
		try {
			setState(110);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102); dataCon();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103); set();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104); list();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(105); tuple();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(106); typeMap();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(107); map();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(108); match(CON);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(109); match(TEXT);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3#s\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\3\2\3\2\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\3\3\3\6\3&\n"+
		"\3\r\3\16\3\'\3\3\3\3\5\3,\n\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\7\6\65\n\6"+
		"\f\6\16\68\13\6\3\6\3\6\3\6\7\6=\n\6\f\6\16\6@\13\6\3\6\3\6\5\6D\n\6\3"+
		"\7\3\7\7\7H\n\7\f\7\16\7K\13\7\3\7\3\7\3\b\3\b\3\t\3\t\7\tS\n\t\f\t\16"+
		"\tV\13\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\7\13_\n\13\f\13\16\13b\13\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rq\n\r\3\r\2\16"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\2\4\3\2\13\r\3\2\30\31w\2\32\3\2\2\2\4"+
		"\34\3\2\2\2\6-\3\2\2\2\b/\3\2\2\2\nC\3\2\2\2\fE\3\2\2\2\16N\3\2\2\2\20"+
		"P\3\2\2\2\22Y\3\2\2\2\24\\\3\2\2\2\26e\3\2\2\2\30p\3\2\2\2\32\33\5\4\3"+
		"\2\33\3\3\2\2\2\34 \7\b\2\2\35\37\5\6\4\2\36\35\3\2\2\2\37\"\3\2\2\2 "+
		"\36\3\2\2\2 !\3\2\2\2!+\3\2\2\2\" \3\2\2\2#%\7\6\2\2$&\5\26\f\2%$\3\2"+
		"\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\7\5\2\2*,\3\2\2\2+#"+
		"\3\2\2\2+,\3\2\2\2,\5\3\2\2\2-.\t\2\2\2.\7\3\2\2\2/\60\7\16\2\2\60\61"+
		"\5\n\6\2\61\t\3\2\2\2\62\66\7\17\2\2\63\65\5\16\b\2\64\63\3\2\2\2\658"+
		"\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29D\7\32\2"+
		"\2:>\7\21\2\2;=\5\30\r\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2"+
		"\2\2@>\3\2\2\2AD\7\23\2\2BD\7\24\2\2C\62\3\2\2\2C:\3\2\2\2CB\3\2\2\2D"+
		"\13\3\2\2\2EI\7\20\2\2FH\5\16\b\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2"+
		"\2\2JL\3\2\2\2KI\3\2\2\2LM\7\33\2\2M\r\3\2\2\2NO\t\3\2\2O\17\3\2\2\2P"+
		"T\7\34\2\2QS\5\22\n\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2"+
		"\2VT\3\2\2\2WX\7\37\2\2X\21\3\2\2\2YZ\7\35\2\2Z[\7\7\2\2[\23\3\2\2\2\\"+
		"`\7\21\2\2]_\5\26\f\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2ac\3\2\2"+
		"\2b`\3\2\2\2cd\7\23\2\2d\25\3\2\2\2ef\7\n\2\2fg\5\30\r\2g\27\3\2\2\2h"+
		"q\5\4\3\2iq\5\b\5\2jq\5\n\6\2kq\5\f\7\2lq\5\20\t\2mq\5\24\13\2nq\7\13"+
		"\2\2oq\7\7\2\2ph\3\2\2\2pi\3\2\2\2pj\3\2\2\2pk\3\2\2\2pl\3\2\2\2pm\3\2"+
		"\2\2pn\3\2\2\2po\3\2\2\2q\31\3\2\2\2\f \'+\66>CIT`p";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}