// Generated from DumpAGLexer.g4 by ANTLR 4.1

  package parser.dump;

  import java.util.Stack;
  import java.util.Queue;
  import java.util.LinkedList;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DumpAGLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ANY_CHAR=1, DUMP_START=2, DEDENT=3, INDENT=4, TEXT=5, ATTR_USAGE=6, DATA_CON=7, 
		DATA_CON_VAL_SEP=8, TYPE_SIGS_KEY=9, KEY=10, CON=11, FIELD=12, FILE_LOC=13, 
		LINE_COMMENT=14, LIST_SET=15, LIST_START=16, TUPLE_START=17, BIG_LIST_START=18, 
		BIG_LIST_SEP=19, BIG_LIST_END=20, BIG_LIST_EMPTY=21, EOL=22, DUMP_END=23, 
		ENTRIES_SEP=24, ENTRIES_NAME=25, ENTRIES_STRING=26, LIST_END=27, TUPLE_END=28, 
		TYPE_MAP_START=29, TYPE_MAP_KEY=30, TYPE_MAP_EOL=31, TYPE_MAP_END=32, 
		TYPE_MAP_ANY_CHAR=33, TXT_ANY_CHAR=34, TXT_ATTR_USAGE=35, TXT_BIG_LIST_END=36, 
		TXT_EOL=37, TYPE_SIGS_ANY_CHAR=38, TYPE_SIGS_BIG_LIST_END=39;
	public static final int DUMP = 1;
	public static final int ENTRIES = 2;
	public static final int TYPE_MAP = 3;
	public static final int TXT = 4;
	public static final int TYPE_SIGS = 5;
	public static String[] modeNames = {
		"DEFAULT_MODE", "DUMP", "ENTRIES", "TYPE_MAP", "TXT", "TYPE_SIGS"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"ANY_CHAR", "'{- Dump of grammar without default rules'", "DEDENT", "INDENT", 
		"TEXT", "ATTR_USAGE", "DATA_CON", "' '", "'typeSigs: '", "KEY", "CON", 
		"FIELD", "FILE_LOC", "LINE_COMMENT", "'fromList '", "'['", "'('", "'[ '", 
		"', '", "' ]'", "'[ ]'", "EOL", "'-}'", "','", "ENTRIES_NAME", "ENTRIES_STRING", 
		"']'", "')'", "TYPE_MAP_START", "TYPE_MAP_KEY", "TYPE_MAP_EOL", "TYPE_MAP_END", 
		"TYPE_MAP_ANY_CHAR", "TXT_ANY_CHAR", "TXT_ATTR_USAGE", "TXT_BIG_LIST_END", 
		"TXT_EOL", "TYPE_SIGS_ANY_CHAR", "TYPE_SIGS_BIG_LIST_END"
	};
	public static final String[] ruleNames = {
		"ANY_CHAR", "DUMP_START", "DEDENT", "INDENT", "TEXT", "ATTR_USAGE", "LNAME", 
		"UNAME", "NAME", "NL", "SPACE", "SPACES", "STRING", "DATA_CON", "DATA_CON_VAL_SEP", 
		"TYPE_SIGS_KEY", "KEY", "CON", "FIELD", "FILE_LOC", "LINE_COMMENT", "LIST_SET", 
		"LIST_START", "TUPLE_START", "BIG_LIST_START", "BIG_LIST_SEP", "BIG_LIST_END", 
		"BIG_LIST_EMPTY", "EOL", "DUMP_END", "ENTRIES_SEP", "ENTRIES_NAME", "ENTRIES_STRING", 
		"LIST_END", "TUPLE_END", "TYPE_MAP_START", "TYPE_MAP_KEY", "TYPE_MAP_EOL", 
		"TYPE_MAP_END", "TYPE_MAP_ANY_CHAR", "TXT_ANY_CHAR", "TXT_ATTR_USAGE", 
		"TXT_BIG_LIST_END", "TXT_EOL", "TYPE_SIGS_ANY_CHAR", "TYPE_SIGS_BIG_LIST_END"
	};


	  private Stack<Integer> indents = new Stack<Integer>();
	  private Stack<Integer> indentLevels = new Stack<Integer>();
	  private boolean indentLayout = false;
	  private Queue<Token> tokens = new LinkedList<Token>();

	  private StringBuilder text;

	  @Override
	  public void emit(Token t) {
	    super.emit(t);
	    tokens.offer(t);
	  }

	  public void emit(int type) {
	    emit(type, "");
	  }

	  public void emit(int type, String text) {
	    int _old_type = _type;
	    String _old_text = _text;
	    _type = type;
	    _text = text;
	    emit();
	    _type = _old_type;
	    _text = _old_text;
	  }

	  @Override
	  public Token nextToken() {
	    super.nextToken();
	    return tokens.isEmpty() ? emitEOF() : tokens.poll();
	  }

	  private int getIndentFromText() {
	    return getText().replaceFirst("^.*?(\\r?\\n|\\r)", "").length();
	  }

	  private int getIndentFromLA() {
	    if (_input.LA(1) == '\r' || _input.LA(1) == '\n') {
	      int i = _input.LA(1) == '\r' && _input.LA(2) == '\n' ? 3 : 2;
	      int n = 0;
	      while (true) {
	        if (_input.LA(i) == ' ') {
	          n += 1;
	        } else if (_input.LA(i) == '\t') {
	          n += 4;
	        } else {
	          break;
	        }
	        i++;
	      }
	      return n;
	    } else {
	      return 0;
	    }
	  }

	  private void handleEOL() {
	    skip();

	    int indent = getIndentFromText();
	    int prevIndent = indents.isEmpty() ? 0 : indents.peek();

	    if (indentLayout && indent > prevIndent) {
	      indents.push(indent);
	      emit(INDENT);
	    }
	    else {
	      while (!indents.isEmpty() && indents.peek() > indent) {
	        emit(DEDENT);
	        indents.pop();
	      }
	    }
	  }

	  private void handleBigListEnd() {
	    while (indents.size() > indentLevels.peek()) {
	      emit(DEDENT);
	      indents.pop();
	    }
	    indentLevels.pop();
	    emit(BIG_LIST_END, " ]");
	  }

	  private void newText() {
	    text = new StringBuilder();
	  }

	  private void appendText() {
	    if (text != null) {
	      text.append(getText());
	      skip();
	    }
	  }

	  private void emitText() {
	    if (text != null) {
	      emit(TEXT, text.toString());
	      text = null;
	    }
	  }


	public DumpAGLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DumpAGLexer.g4"; }

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

		case 1: DUMP_START_action((RuleContext)_localctx, actionIndex); break;

		case 11: SPACES_action((RuleContext)_localctx, actionIndex); break;

		case 13: DATA_CON_action((RuleContext)_localctx, actionIndex); break;

		case 14: DATA_CON_VAL_SEP_action((RuleContext)_localctx, actionIndex); break;

		case 15: TYPE_SIGS_KEY_action((RuleContext)_localctx, actionIndex); break;

		case 16: KEY_action((RuleContext)_localctx, actionIndex); break;

		case 20: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 22: LIST_START_action((RuleContext)_localctx, actionIndex); break;

		case 23: TUPLE_START_action((RuleContext)_localctx, actionIndex); break;

		case 24: BIG_LIST_START_action((RuleContext)_localctx, actionIndex); break;

		case 25: BIG_LIST_SEP_action((RuleContext)_localctx, actionIndex); break;

		case 26: BIG_LIST_END_action((RuleContext)_localctx, actionIndex); break;

		case 28: EOL_action((RuleContext)_localctx, actionIndex); break;

		case 29: DUMP_END_action((RuleContext)_localctx, actionIndex); break;

		case 30: ENTRIES_SEP_action((RuleContext)_localctx, actionIndex); break;

		case 33: LIST_END_action((RuleContext)_localctx, actionIndex); break;

		case 34: TUPLE_END_action((RuleContext)_localctx, actionIndex); break;

		case 36: TYPE_MAP_KEY_action((RuleContext)_localctx, actionIndex); break;

		case 37: TYPE_MAP_EOL_action((RuleContext)_localctx, actionIndex); break;

		case 38: TYPE_MAP_END_action((RuleContext)_localctx, actionIndex); break;

		case 39: TYPE_MAP_ANY_CHAR_action((RuleContext)_localctx, actionIndex); break;

		case 40: TXT_ANY_CHAR_action((RuleContext)_localctx, actionIndex); break;

		case 41: TXT_ATTR_USAGE_action((RuleContext)_localctx, actionIndex); break;

		case 42: TXT_BIG_LIST_END_action((RuleContext)_localctx, actionIndex); break;

		case 43: TXT_EOL_action((RuleContext)_localctx, actionIndex); break;

		case 44: TYPE_SIGS_ANY_CHAR_action((RuleContext)_localctx, actionIndex); break;

		case 45: TYPE_SIGS_BIG_LIST_END_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 24: popMode();  break;
		}
	}
	private void TYPE_MAP_EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: emitText(); break;
		}
	}
	private void DATA_CON_VAL_SEP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 17: skip();  break;
		}
	}
	private void TYPE_MAP_KEY_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: newText(); break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:  handleEOL();  break;
		}
	}
	private void TUPLE_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 25: popMode();  break;
		}
	}
	private void KEY_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: 
		    String name = getText();
		    name = name.substring(0, name.length() - 2);
		    setText(name);
		    if (name.equals("txt")) {
		      newText();
		      pushMode(TXT);
		    } else if (name.equals("inh") || name.equals("syn")) {
		      pushMode(TYPE_MAP);
		    }
		    indentLayout = false;
		   break;
		}
	}
	private void TYPE_SIGS_ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 26: skip();  break;
		}
	}
	private void TXT_ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11: appendText(); break;
		}
	}
	private void TYPE_SIGS_BIG_LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 27: skip(); popMode();  break;
		}
	}
	private void TYPE_SIGS_KEY_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 18: skip(); pushMode(TYPE_SIGS);  break;
		}
	}
	private void DUMP_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 22: skip(); popMode();  break;
		}
	}
	private void BIG_LIST_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: indentLevels.push(indents.size()); break;
		}
	}
	private void TXT_ATTR_USAGE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12: 
		    if (text.length() > 0) {
		      emitText();
		      newText();
		    }
		    emit(ATTR_USAGE, getText());
		   break;
		}
	}
	private void DUMP_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 16: skip(); pushMode(DUMP);  break;
		}
	}
	private void ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 15: skip();  break;
		}
	}
	private void EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: handleEOL(); break;
		}
	}
	private void BIG_LIST_SEP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 21: skip();  break;
		}
	}
	private void SPACES_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: setText(getText().replace("\t", "    ")); break;
		}
	}
	private void TYPE_MAP_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9: 
		    if (getIndentFromLA() <= indents.peek()) {
		      if (text != null) {
		        emitText();
		      }
		      emit(TYPE_MAP_END, " ]");
		      popMode();
		    } else {
		      appendText();
		    }
		   break;
		}
	}
	private void TXT_EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 14: 
		    if (getIndentFromText() <= indents.peek()) {
		      if (text != null && text.length() > 0) {
		        emitText();
		      }
		      handleEOL();
		      popMode();
		    } else {
		      appendText();
		    }
		   break;
		}
	}
	private void TYPE_MAP_ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10: appendText(); break;
		}
	}
	private void TXT_BIG_LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 13: 
		    if (getIndentFromLA() == indents.get(indentLevels.peek() - 1)) {
		      if (text.length() > 0) {
		        emitText();
		      }
		      handleBigListEnd();
		    } else {
		      appendText();
		    }
		   break;
		}
	}
	private void TUPLE_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 20: pushMode(ENTRIES);  break;
		}
	}
	private void DATA_CON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: indentLayout = true; break;
		}
	}
	private void BIG_LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: handleBigListEnd(); break;
		}
	}
	private void LIST_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 19: pushMode(ENTRIES);  break;
		}
	}
	private void ENTRIES_SEP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 23: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2)\u0188\b\1\b\1\b"+
		"\1\b\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t"+
		"\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t"+
		"\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t"+
		"(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\7\b\u009e"+
		"\n\b\f\b\16\b\u00a1\13\b\3\t\3\t\7\t\u00a5\n\t\f\t\16\t\u00a8\13\t\3\n"+
		"\3\n\5\n\u00ac\n\n\3\13\5\13\u00af\n\13\3\13\3\13\5\13\u00b3\n\13\3\f"+
		"\3\f\3\r\6\r\u00b8\n\r\r\r\16\r\u00b9\3\r\3\r\3\16\3\16\3\16\3\16\7\16"+
		"\u00c2\n\16\f\16\16\16\u00c5\13\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u00f2\n\25\r\25\16\25\u00f3"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u0101\n\25"+
		"\r\25\16\25\u0102\3\25\3\25\3\26\3\26\3\26\3\26\6\26\u010b\n\26\r\26\16"+
		"\26\u010c\3\26\3\26\5\26\u0111\n\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\5\36\u013c\n\36\3\36\3\36\3\37\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3&"+
		"\3&\3&\3&\3&\3&\3\'\3\'\7\'\u015f\n\'\f\'\16\'\u0162\13\'\3\'\3\'\3\'"+
		"\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3+\3+\5+\u0174\n+\3+\3+\3,\3,\3,\3-"+
		"\3-\5-\u017d\n-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\2\60\b\3\21\n\4\22\f\5\1"+
		"\16\6\1\20\7\1\22\b\1\24\2\1\26\2\1\30\2\1\32\2\1\34\2\1\36\2\2 \2\1\""+
		"\t\3$\n\23&\13\24(\f\4*\r\1,\16\1.\17\1\60\20\5\62\21\1\64\22\25\66\23"+
		"\268\24\6:\25\27<\26\7>\27\1@\30\bB\31\30D\32\31F\33\1H\34\1J\35\32L\36"+
		"\33N\37\1P \tR!\nT\"\13V#\fX$\rZ%\16\\&\17^\'\20`(\34b)\35\b\2\3\4\5\6"+
		"\7\t\3\2c|\7\2))\62;C\\aac|\3\2C\\\4\2\13\13\"\"\4\2$$^^\3\2\62;\4\2\f"+
		"\f\17\17\u018b\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3"+
		"\2\2\2\2\22\3\2\2\2\3\"\3\2\2\2\3$\3\2\2\2\3&\3\2\2\2\3(\3\2\2\2\3*\3"+
		"\2\2\2\3,\3\2\2\2\3.\3\2\2\2\3\60\3\2\2\2\3\62\3\2\2\2\3\64\3\2\2\2\3"+
		"\66\3\2\2\2\38\3\2\2\2\3:\3\2\2\2\3<\3\2\2\2\3>\3\2\2\2\3@\3\2\2\2\3B"+
		"\3\2\2\2\4D\3\2\2\2\4F\3\2\2\2\4H\3\2\2\2\4J\3\2\2\2\4L\3\2\2\2\5N\3\2"+
		"\2\2\5P\3\2\2\2\5R\3\2\2\2\5T\3\2\2\2\5V\3\2\2\2\6X\3\2\2\2\6Z\3\2\2\2"+
		"\6\\\3\2\2\2\6^\3\2\2\2\7`\3\2\2\2\7b\3\2\2\2\bd\3\2\2\2\nh\3\2\2\2\f"+
		"\u0093\3\2\2\2\16\u0095\3\2\2\2\20\u0097\3\2\2\2\22\u0099\3\2\2\2\24\u009b"+
		"\3\2\2\2\26\u00a2\3\2\2\2\30\u00ab\3\2\2\2\32\u00b2\3\2\2\2\34\u00b4\3"+
		"\2\2\2\36\u00b7\3\2\2\2 \u00bd\3\2\2\2\"\u00c8\3\2\2\2$\u00cd\3\2\2\2"+
		"&\u00d1\3\2\2\2(\u00de\3\2\2\2*\u00e4\3\2\2\2,\u00e6\3\2\2\2.\u00e8\3"+
		"\2\2\2\60\u0106\3\2\2\2\62\u0114\3\2\2\2\64\u011e\3\2\2\2\66\u0122\3\2"+
		"\2\28\u0126\3\2\2\2:\u012b\3\2\2\2<\u0130\3\2\2\2>\u0135\3\2\2\2@\u0139"+
		"\3\2\2\2B\u013f\3\2\2\2D\u0144\3\2\2\2F\u0148\3\2\2\2H\u014a\3\2\2\2J"+
		"\u014c\3\2\2\2L\u0150\3\2\2\2N\u0154\3\2\2\2P\u0156\3\2\2\2R\u015c\3\2"+
		"\2\2T\u0166\3\2\2\2V\u0169\3\2\2\2X\u016c\3\2\2\2Z\u016f\3\2\2\2\\\u0177"+
		"\3\2\2\2^\u017a\3\2\2\2`\u0180\3\2\2\2b\u0184\3\2\2\2de\13\2\2\2ef\3\2"+
		"\2\2fg\b\2\21\2g\t\3\2\2\2hi\7}\2\2ij\7/\2\2jk\7\"\2\2kl\7F\2\2lm\7w\2"+
		"\2mn\7o\2\2no\7r\2\2op\7\"\2\2pq\7q\2\2qr\7h\2\2rs\7\"\2\2st\7i\2\2tu"+
		"\7t\2\2uv\7c\2\2vw\7o\2\2wx\7o\2\2xy\7c\2\2yz\7t\2\2z{\7\"\2\2{|\7y\2"+
		"\2|}\7k\2\2}~\7v\2\2~\177\7j\2\2\177\u0080\7q\2\2\u0080\u0081\7w\2\2\u0081"+
		"\u0082\7v\2\2\u0082\u0083\7\"\2\2\u0083\u0084\7f\2\2\u0084\u0085\7g\2"+
		"\2\u0085\u0086\7h\2\2\u0086\u0087\7c\2\2\u0087\u0088\7w\2\2\u0088\u0089"+
		"\7n\2\2\u0089\u008a\7v\2\2\u008a\u008b\7\"\2\2\u008b\u008c\7t\2\2\u008c"+
		"\u008d\7w\2\2\u008d\u008e\7n\2\2\u008e\u008f\7g\2\2\u008f\u0090\7u\2\2"+
		"\u0090\u0091\3\2\2\2\u0091\u0092\b\3\22\2\u0092\13\3\2\2\2\u0093\u0094"+
		"\13\2\2\2\u0094\r\3\2\2\2\u0095\u0096\13\2\2\2\u0096\17\3\2\2\2\u0097"+
		"\u0098\13\2\2\2\u0098\21\3\2\2\2\u0099\u009a\13\2\2\2\u009a\23\3\2\2\2"+
		"\u009b\u009f\t\2\2\2\u009c\u009e\t\3\2\2\u009d\u009c\3\2\2\2\u009e\u00a1"+
		"\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\25\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\u00a6\t\4\2\2\u00a3\u00a5\t\3\2\2\u00a4\u00a3\3\2"+
		"\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\27\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ac\5\24\b\2\u00aa\u00ac\5\26"+
		"\t\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\31\3\2\2\2\u00ad\u00af"+
		"\7\17\2\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2"+
		"\u00b0\u00b3\7\f\2\2\u00b1\u00b3\7\17\2\2\u00b2\u00ae\3\2\2\2\u00b2\u00b1"+
		"\3\2\2\2\u00b3\33\3\2\2\2\u00b4\u00b5\t\5\2\2\u00b5\35\3\2\2\2\u00b6\u00b8"+
		"\5\34\f\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2"+
		"\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\r\2\2\u00bc\37"+
		"\3\2\2\2\u00bd\u00c3\7$\2\2\u00be\u00c2\n\6\2\2\u00bf\u00c0\7^\2\2\u00c0"+
		"\u00c2\13\2\2\2\u00c1\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c5\3"+
		"\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c6\u00c7\7$\2\2\u00c7!\3\2\2\2\u00c8\u00c9\5\26\t\2"+
		"\u00c9\u00ca\7a\2\2\u00ca\u00cb\5\26\t\2\u00cb\u00cc\b\17\3\2\u00cc#\3"+
		"\2\2\2\u00cd\u00ce\7\"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b\20\23\2\u00d0"+
		"%\3\2\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7{\2\2\u00d3\u00d4\7r\2\2\u00d4"+
		"\u00d5\7g\2\2\u00d5\u00d6\7U\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7i\2\2"+
		"\u00d8\u00d9\7u\2\2\u00d9\u00da\7<\2\2\u00da\u00db\7\"\2\2\u00db\u00dc"+
		"\3\2\2\2\u00dc\u00dd\b\21\24\2\u00dd\'\3\2\2\2\u00de\u00df\5\30\n\2\u00df"+
		"\u00e0\7<\2\2\u00e0\u00e1\7\"\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\b\22"+
		"\4\2\u00e3)\3\2\2\2\u00e4\u00e5\5\26\t\2\u00e5+\3\2\2\2\u00e6\u00e7\5"+
		"\24\b\2\u00e7-\3\2\2\2\u00e8\u00e9\5 \16\2\u00e9\u00ea\7*\2\2\u00ea\u00eb"+
		"\7n\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7g\2\2\u00ee"+
		"\u00ef\7\"\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00f2\t\7\2\2\u00f1\u00f0\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u00f6\7.\2\2\u00f6\u00f7\7\"\2\2\u00f7\u00f8\7e\2"+
		"\2\u00f8\u00f9\7q\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7w\2\2\u00fb\u00fc"+
		"\7o\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7\"\2\2\u00fe\u0100\3\2\2\2\u00ff"+
		"\u0101\t\7\2\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\7+\2\2\u0105"+
		"/\3\2\2\2\u0106\u0107\7/\2\2\u0107\u0108\7/\2\2\u0108\u010a\3\2\2\2\u0109"+
		"\u010b\n\b\2\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2"+
		"\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u0110\5\32\13\2\u010f"+
		"\u0111\5\36\r\2\u0110\u010f\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\3"+
		"\2\2\2\u0112\u0113\b\26\5\2\u0113\61\3\2\2\2\u0114\u0115\7h\2\2\u0115"+
		"\u0116\7t\2\2\u0116\u0117\7q\2\2\u0117\u0118\7o\2\2\u0118\u0119\7N\2\2"+
		"\u0119\u011a\7k\2\2\u011a\u011b\7u\2\2\u011b\u011c\7v\2\2\u011c\u011d"+
		"\7\"\2\2\u011d\63\3\2\2\2\u011e\u011f\7]\2\2\u011f\u0120\3\2\2\2\u0120"+
		"\u0121\b\30\25\2\u0121\65\3\2\2\2\u0122\u0123\7*\2\2\u0123\u0124\3\2\2"+
		"\2\u0124\u0125\b\31\26\2\u0125\67\3\2\2\2\u0126\u0127\7]\2\2\u0127\u0128"+
		"\7\"\2\2\u0128\u0129\3\2\2\2\u0129\u012a\b\32\6\2\u012a9\3\2\2\2\u012b"+
		"\u012c\7.\2\2\u012c\u012d\7\"\2\2\u012d\u012e\3\2\2\2\u012e\u012f\b\33"+
		"\27\2\u012f;\3\2\2\2\u0130\u0131\7\"\2\2\u0131\u0132\7_\2\2\u0132\u0133"+
		"\3\2\2\2\u0133\u0134\b\34\7\2\u0134=\3\2\2\2\u0135\u0136\7]\2\2\u0136"+
		"\u0137\7\"\2\2\u0137\u0138\7_\2\2\u0138?\3\2\2\2\u0139\u013b\5\32\13\2"+
		"\u013a\u013c\5\36\r\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d"+
		"\3\2\2\2\u013d\u013e\b\36\b\2\u013eA\3\2\2\2\u013f\u0140\7/\2\2\u0140"+
		"\u0141\7\177\2\2\u0141\u0142\3\2\2\2\u0142\u0143\b\37\30\2\u0143C\3\2"+
		"\2\2\u0144\u0145\7.\2\2\u0145\u0146\3\2\2\2\u0146\u0147\b \31\2\u0147"+
		"E\3\2\2\2\u0148\u0149\5\26\t\2\u0149G\3\2\2\2\u014a\u014b\5 \16\2\u014b"+
		"I\3\2\2\2\u014c\u014d\7_\2\2\u014d\u014e\3\2\2\2\u014e\u014f\b#\32\2\u014f"+
		"K\3\2\2\2\u0150\u0151\7+\2\2\u0151\u0152\3\2\2\2\u0152\u0153\b$\33\2\u0153"+
		"M\3\2\2\2\u0154\u0155\58\32\2\u0155O\3\2\2\2\u0156\u0157\5\24\b\2\u0157"+
		"\u0158\7<\2\2\u0158\u0159\7\"\2\2\u0159\u015a\3\2\2\2\u015a\u015b\b&\t"+
		"\2\u015bQ\3\2\2\2\u015c\u0160\5\32\13\2\u015d\u015f\5\34\f\2\u015e\u015d"+
		"\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161"+
		"\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163\u0164\5:\33\2\u0164\u0165\b\'"+
		"\n\2\u0165S\3\2\2\2\u0166\u0167\5<\34\2\u0167\u0168\b(\13\2\u0168U\3\2"+
		"\2\2\u0169\u016a\5\b\2\2\u016a\u016b\b)\f\2\u016bW\3\2\2\2\u016c\u016d"+
		"\5\b\2\2\u016d\u016e\b*\r\2\u016eY\3\2\2\2\u016f\u0170\7B\2\2\u0170\u0173"+
		"\5\24\b\2\u0171\u0172\7\60\2\2\u0172\u0174\5\24\b\2\u0173\u0171\3\2\2"+
		"\2\u0173\u0174\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176\b+\16\2\u0176["+
		"\3\2\2\2\u0177\u0178\5<\34\2\u0178\u0179\b,\17\2\u0179]\3\2\2\2\u017a"+
		"\u017c\5\32\13\2\u017b\u017d\5\36\r\2\u017c\u017b\3\2\2\2\u017c\u017d"+
		"\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\b-\20\2\u017f_\3\2\2\2\u0180"+
		"\u0181\5\b\2\2\u0181\u0182\3\2\2\2\u0182\u0183\b.\34\2\u0183a\3\2\2\2"+
		"\u0184\u0185\5<\34\2\u0185\u0186\3\2\2\2\u0186\u0187\b/\35\2\u0187c\3"+
		"\2\2\2\30\2\3\4\5\6\7\u009f\u00a6\u00ab\u00ae\u00b2\u00b9\u00c1\u00c3"+
		"\u00f3\u0102\u010c\u0110\u013b\u0160\u0173\u017c";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}