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
		ANY_CHAR=1, DUMP_START=2, DEDENT=3, INDENT=4, TEXT=5, DATA_CON=6, DATA_CON_VAL_SEP=7, 
		KEY=8, CON=9, FIELD=10, FILE_LOC=11, LIST_SET=12, LIST_START=13, TUPLE_START=14, 
		BIG_LIST_START=15, BIG_LIST_SEP=16, BIG_LIST_END=17, BIG_LIST_EMPTY=18, 
		EOL=19, DUMP_END=20, ENTRIES_SEP=21, ENTRIES_NAME=22, ENTRIES_STRING=23, 
		LIST_END=24, TUPLE_END=25, TYPE_MAP_START=26, TYPE_MAP_KEY=27, TYPE_MAP_EOL=28, 
		TYPE_MAP_END=29, TYPE_MAP_ANY_CHAR=30, TXT_ANY_CHAR=31, TXT_BIG_LIST_END=32, 
		TXT_EOL=33;
	public static final int DUMP = 1;
	public static final int ENTRIES = 2;
	public static final int TYPE_MAP = 3;
	public static final int TXT = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "DUMP", "ENTRIES", "TYPE_MAP", "TXT"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"ANY_CHAR", "'{- Dump of grammar without default rules'", "DEDENT", "INDENT", 
		"TEXT", "DATA_CON", "' '", "KEY", "CON", "FIELD", "FILE_LOC", "'fromList '", 
		"'['", "'('", "'[ '", "', '", "' ]'", "'[ ]'", "EOL", "'-}'", "','", "ENTRIES_NAME", 
		"ENTRIES_STRING", "']'", "')'", "TYPE_MAP_START", "TYPE_MAP_KEY", "TYPE_MAP_EOL", 
		"TYPE_MAP_END", "TYPE_MAP_ANY_CHAR", "TXT_ANY_CHAR", "TXT_BIG_LIST_END", 
		"TXT_EOL"
	};
	public static final String[] ruleNames = {
		"ANY_CHAR", "DUMP_START", "DEDENT", "INDENT", "TEXT", "LNAME", "UNAME", 
		"NAME", "NL", "SPACE", "SPACES", "STRING", "DATA_CON", "DATA_CON_VAL_SEP", 
		"KEY", "CON", "FIELD", "FILE_LOC", "LIST_SET", "LIST_START", "TUPLE_START", 
		"BIG_LIST_START", "BIG_LIST_SEP", "BIG_LIST_END", "BIG_LIST_EMPTY", "EOL", 
		"DUMP_END", "ENTRIES_SEP", "ENTRIES_NAME", "ENTRIES_STRING", "LIST_END", 
		"TUPLE_END", "TYPE_MAP_START", "TYPE_MAP_KEY", "TYPE_MAP_EOL", "TYPE_MAP_END", 
		"TYPE_MAP_ANY_CHAR", "TXT_ANY_CHAR", "TXT_BIG_LIST_END", "TXT_EOL"
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
	    return getText().replaceFirst("^(\\r?\\n|\\r)", "").length();
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
	    text.append(getText());
	    skip();
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

		case 10: SPACES_action((RuleContext)_localctx, actionIndex); break;

		case 12: DATA_CON_action((RuleContext)_localctx, actionIndex); break;

		case 13: DATA_CON_VAL_SEP_action((RuleContext)_localctx, actionIndex); break;

		case 14: KEY_action((RuleContext)_localctx, actionIndex); break;

		case 19: LIST_START_action((RuleContext)_localctx, actionIndex); break;

		case 20: TUPLE_START_action((RuleContext)_localctx, actionIndex); break;

		case 21: BIG_LIST_START_action((RuleContext)_localctx, actionIndex); break;

		case 22: BIG_LIST_SEP_action((RuleContext)_localctx, actionIndex); break;

		case 23: BIG_LIST_END_action((RuleContext)_localctx, actionIndex); break;

		case 25: EOL_action((RuleContext)_localctx, actionIndex); break;

		case 26: DUMP_END_action((RuleContext)_localctx, actionIndex); break;

		case 27: ENTRIES_SEP_action((RuleContext)_localctx, actionIndex); break;

		case 30: LIST_END_action((RuleContext)_localctx, actionIndex); break;

		case 31: TUPLE_END_action((RuleContext)_localctx, actionIndex); break;

		case 33: TYPE_MAP_KEY_action((RuleContext)_localctx, actionIndex); break;

		case 34: TYPE_MAP_EOL_action((RuleContext)_localctx, actionIndex); break;

		case 35: TYPE_MAP_END_action((RuleContext)_localctx, actionIndex); break;

		case 36: TYPE_MAP_ANY_CHAR_action((RuleContext)_localctx, actionIndex); break;

		case 37: TXT_ANY_CHAR_action((RuleContext)_localctx, actionIndex); break;

		case 38: TXT_BIG_LIST_END_action((RuleContext)_localctx, actionIndex); break;

		case 39: TXT_EOL_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 13: skip();  break;
		}
	}
	private void LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 21: popMode();  break;
		}
	}
	private void EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: handleEOL(); break;
		}
	}
	private void TYPE_MAP_EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: emitText(); break;
		}
	}
	private void DATA_CON_VAL_SEP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 15: skip();  break;
		}
	}
	private void TYPE_MAP_KEY_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: newText(); break;
		}
	}
	private void TUPLE_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 22: popMode();  break;
		}
	}
	private void BIG_LIST_SEP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 18: skip();  break;
		}
	}
	private void TYPE_MAP_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: 
		    if (getIndentFromLA() <= indents.peek()) {
		      emitText();
		      emit(TYPE_MAP_END, " ]");
		      popMode();
		    } else {
		      appendText();
		    }
		   break;
		}
	}
	private void SPACES_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: setText(getText().replace("\t", "    ")); break;
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
	private void TXT_EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12: 
		    if (getIndentFromText() <= indents.peek()) {
		      emitText();
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
		case 9: appendText(); break;
		}
	}
	private void TXT_BIG_LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11: 
		    if (getIndentFromLA() == indents.get(indentLevels.peek() - 1)) {
		      emitText();
		      handleBigListEnd();
		    } else {
		      appendText();
		    }
		   break;
		}
	}
	private void TXT_ANY_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10: appendText(); break;
		}
	}
	private void DUMP_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 19: skip(); popMode();  break;
		}
	}
	private void BIG_LIST_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: indentLevels.push(indents.size()); break;
		}
	}
	private void DUMP_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 14: skip(); pushMode(DUMP);  break;
		}
	}
	private void TUPLE_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 17: pushMode(ENTRIES);  break;
		}
	}
	private void DATA_CON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: indentLayout = true; break;
		}
	}
	private void BIG_LIST_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: handleBigListEnd(); break;
		}
	}
	private void ENTRIES_SEP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 20: skip();  break;
		}
	}
	private void LIST_START_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 16: pushMode(ENTRIES);  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2#\u014e\b\1\b\1\b"+
		"\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t"+
		"\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4"+
		"\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4"+
		"\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4"+
		"\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)"+
		"\t)\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\7\7\u008f\n\7\f\7\16\7\u0092\13\7\3\b\3\b\7\b\u0096\n\b"+
		"\f\b\16\b\u0099\13\b\3\t\3\t\5\t\u009d\n\t\3\n\5\n\u00a0\n\n\3\n\3\n\5"+
		"\n\u00a4\n\n\3\13\3\13\3\f\6\f\u00a9\n\f\r\f\16\f\u00aa\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\7\r\u00b3\n\r\f\r\16\r\u00b6\13\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\6\23\u00d6\n\23"+
		"\r\23\16\23\u00d7\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\6\23\u00e5\n\23\r\23\16\23\u00e6\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\5\33\u0112\n\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\7$\u0135\n$\f$\16$\u0138\13$\3$\3"+
		"$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\5)\u014b\n)\3)\3)\2"+
		"*\7\3\17\t\4\20\13\5\1\r\6\1\17\7\1\21\2\1\23\2\1\25\2\1\27\2\1\31\2\1"+
		"\33\2\2\35\2\1\37\b\3!\t\21#\n\4%\13\1\'\f\1)\r\1+\16\1-\17\22/\20\23"+
		"\61\21\5\63\22\24\65\23\6\67\24\19\25\7;\26\25=\27\26?\30\1A\31\1C\32"+
		"\27E\33\30G\34\1I\35\bK\36\tM\37\nO \13Q!\fS\"\rU#\16\7\2\3\4\5\6\b\3"+
		"\2c|\6\2\62;C\\aac|\3\2C\\\4\2\13\13\"\"\4\2$$^^\3\2\62;\u014f\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\3\37\3\2\2\2\3"+
		"!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2\2\3)\3\2\2\2\3+\3\2\2\2\3-\3"+
		"\2\2\2\3/\3\2\2\2\3\61\3\2\2\2\3\63\3\2\2\2\3\65\3\2\2\2\3\67\3\2\2\2"+
		"\39\3\2\2\2\3;\3\2\2\2\4=\3\2\2\2\4?\3\2\2\2\4A\3\2\2\2\4C\3\2\2\2\4E"+
		"\3\2\2\2\5G\3\2\2\2\5I\3\2\2\2\5K\3\2\2\2\5M\3\2\2\2\5O\3\2\2\2\6Q\3\2"+
		"\2\2\6S\3\2\2\2\6U\3\2\2\2\7W\3\2\2\2\t[\3\2\2\2\13\u0086\3\2\2\2\r\u0088"+
		"\3\2\2\2\17\u008a\3\2\2\2\21\u008c\3\2\2\2\23\u0093\3\2\2\2\25\u009c\3"+
		"\2\2\2\27\u00a3\3\2\2\2\31\u00a5\3\2\2\2\33\u00a8\3\2\2\2\35\u00ae\3\2"+
		"\2\2\37\u00b9\3\2\2\2!\u00be\3\2\2\2#\u00c2\3\2\2\2%\u00c8\3\2\2\2\'\u00ca"+
		"\3\2\2\2)\u00cc\3\2\2\2+\u00ea\3\2\2\2-\u00f4\3\2\2\2/\u00f8\3\2\2\2\61"+
		"\u00fc\3\2\2\2\63\u0101\3\2\2\2\65\u0106\3\2\2\2\67\u010b\3\2\2\29\u010f"+
		"\3\2\2\2;\u0115\3\2\2\2=\u011a\3\2\2\2?\u011e\3\2\2\2A\u0120\3\2\2\2C"+
		"\u0122\3\2\2\2E\u0126\3\2\2\2G\u012a\3\2\2\2I\u012c\3\2\2\2K\u0132\3\2"+
		"\2\2M\u013c\3\2\2\2O\u013f\3\2\2\2Q\u0142\3\2\2\2S\u0145\3\2\2\2U\u0148"+
		"\3\2\2\2WX\13\2\2\2XY\3\2\2\2YZ\b\2\17\2Z\b\3\2\2\2[\\\7}\2\2\\]\7/\2"+
		"\2]^\7\"\2\2^_\7F\2\2_`\7w\2\2`a\7o\2\2ab\7r\2\2bc\7\"\2\2cd\7q\2\2de"+
		"\7h\2\2ef\7\"\2\2fg\7i\2\2gh\7t\2\2hi\7c\2\2ij\7o\2\2jk\7o\2\2kl\7c\2"+
		"\2lm\7t\2\2mn\7\"\2\2no\7y\2\2op\7k\2\2pq\7v\2\2qr\7j\2\2rs\7q\2\2st\7"+
		"w\2\2tu\7v\2\2uv\7\"\2\2vw\7f\2\2wx\7g\2\2xy\7h\2\2yz\7c\2\2z{\7w\2\2"+
		"{|\7n\2\2|}\7v\2\2}~\7\"\2\2~\177\7t\2\2\177\u0080\7w\2\2\u0080\u0081"+
		"\7n\2\2\u0081\u0082\7g\2\2\u0082\u0083\7u\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0085\b\3\20\2\u0085\n\3\2\2\2\u0086\u0087\13\2\2\2\u0087\f\3\2\2\2\u0088"+
		"\u0089\13\2\2\2\u0089\16\3\2\2\2\u008a\u008b\13\2\2\2\u008b\20\3\2\2\2"+
		"\u008c\u0090\t\2\2\2\u008d\u008f\t\3\2\2\u008e\u008d\3\2\2\2\u008f\u0092"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\22\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0093\u0097\t\4\2\2\u0094\u0096\t\3\2\2\u0095\u0094\3\2"+
		"\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\24\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009d\5\21\7\2\u009b\u009d\5\23"+
		"\b\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\26\3\2\2\2\u009e\u00a0"+
		"\7\17\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2"+
		"\u00a1\u00a4\7\f\2\2\u00a2\u00a4\7\17\2\2\u00a3\u009f\3\2\2\2\u00a3\u00a2"+
		"\3\2\2\2\u00a4\30\3\2\2\2\u00a5\u00a6\t\5\2\2\u00a6\32\3\2\2\2\u00a7\u00a9"+
		"\5\31\13\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2"+
		"\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\f\2\2\u00ad\34"+
		"\3\2\2\2\u00ae\u00b4\7$\2\2\u00af\u00b3\n\6\2\2\u00b0\u00b1\7^\2\2\u00b1"+
		"\u00b3\13\2\2\2\u00b2\u00af\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3"+
		"\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b7\u00b8\7$\2\2\u00b8\36\3\2\2\2\u00b9\u00ba\5\23\b"+
		"\2\u00ba\u00bb\7a\2\2\u00bb\u00bc\5\23\b\2\u00bc\u00bd\b\16\3\2\u00bd"+
		" \3\2\2\2\u00be\u00bf\7\"\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\b\17\21"+
		"\2\u00c1\"\3\2\2\2\u00c2\u00c3\5\25\t\2\u00c3\u00c4\7<\2\2\u00c4\u00c5"+
		"\7\"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\b\20\4\2\u00c7$\3\2\2\2\u00c8"+
		"\u00c9\5\23\b\2\u00c9&\3\2\2\2\u00ca\u00cb\5\21\7\2\u00cb(\3\2\2\2\u00cc"+
		"\u00cd\5\35\r\2\u00cd\u00ce\7*\2\2\u00ce\u00cf\7n\2\2\u00cf\u00d0\7k\2"+
		"\2\u00d0\u00d1\7p\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7\"\2\2\u00d3\u00d5"+
		"\3\2\2\2\u00d4\u00d6\t\7\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\7."+
		"\2\2\u00da\u00db\7\"\2\2\u00db\u00dc\7e\2\2\u00dc\u00dd\7q\2\2\u00dd\u00de"+
		"\7n\2\2\u00de\u00df\7w\2\2\u00df\u00e0\7o\2\2\u00e0\u00e1\7p\2\2\u00e1"+
		"\u00e2\7\"\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e5\t\7\2\2\u00e4\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\u00e9\7+\2\2\u00e9*\3\2\2\2\u00ea\u00eb\7h\2\2\u00eb"+
		"\u00ec\7t\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee\7o\2\2\u00ee\u00ef\7N\2\2"+
		"\u00ef\u00f0\7k\2\2\u00f0\u00f1\7u\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3"+
		"\7\"\2\2\u00f3,\3\2\2\2\u00f4\u00f5\7]\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7"+
		"\b\25\22\2\u00f7.\3\2\2\2\u00f8\u00f9\7*\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\u00fb\b\26\23\2\u00fb\60\3\2\2\2\u00fc\u00fd\7]\2\2\u00fd\u00fe\7\"\2"+
		"\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\b\27\5\2\u0100\62\3\2\2\2\u0101\u0102"+
		"\7.\2\2\u0102\u0103\7\"\2\2\u0103\u0104\3\2\2\2\u0104\u0105\b\30\24\2"+
		"\u0105\64\3\2\2\2\u0106\u0107\7\"\2\2\u0107\u0108\7_\2\2\u0108\u0109\3"+
		"\2\2\2\u0109\u010a\b\31\6\2\u010a\66\3\2\2\2\u010b\u010c\7]\2\2\u010c"+
		"\u010d\7\"\2\2\u010d\u010e\7_\2\2\u010e8\3\2\2\2\u010f\u0111\5\27\n\2"+
		"\u0110\u0112\5\33\f\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113"+
		"\3\2\2\2\u0113\u0114\b\33\7\2\u0114:\3\2\2\2\u0115\u0116\7/\2\2\u0116"+
		"\u0117\7\177\2\2\u0117\u0118\3\2\2\2\u0118\u0119\b\34\25\2\u0119<\3\2"+
		"\2\2\u011a\u011b\7.\2\2\u011b\u011c\3\2\2\2\u011c\u011d\b\35\26\2\u011d"+
		">\3\2\2\2\u011e\u011f\5\23\b\2\u011f@\3\2\2\2\u0120\u0121\5\35\r\2\u0121"+
		"B\3\2\2\2\u0122\u0123\7_\2\2\u0123\u0124\3\2\2\2\u0124\u0125\b \27\2\u0125"+
		"D\3\2\2\2\u0126\u0127\7+\2\2\u0127\u0128\3\2\2\2\u0128\u0129\b!\30\2\u0129"+
		"F\3\2\2\2\u012a\u012b\5\61\27\2\u012bH\3\2\2\2\u012c\u012d\5\21\7\2\u012d"+
		"\u012e\7<\2\2\u012e\u012f\7\"\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b#\b"+
		"\2\u0131J\3\2\2\2\u0132\u0136\5\27\n\2\u0133\u0135\5\31\13\2\u0134\u0133"+
		"\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137"+
		"\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\5\63\30\2\u013a\u013b\b"+
		"$\t\2\u013bL\3\2\2\2\u013c\u013d\5\65\31\2\u013d\u013e\b%\n\2\u013eN\3"+
		"\2\2\2\u013f\u0140\5\7\2\2\u0140\u0141\b&\13\2\u0141P\3\2\2\2\u0142\u0143"+
		"\5\7\2\2\u0143\u0144\b\'\f\2\u0144R\3\2\2\2\u0145\u0146\5\65\31\2\u0146"+
		"\u0147\b(\r\2\u0147T\3\2\2\2\u0148\u014a\5\27\n\2\u0149\u014b\5\33\f\2"+
		"\u014a\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d"+
		"\b)\16\2\u014dV\3\2\2\2\24\2\3\4\5\6\u0090\u0097\u009c\u009f\u00a3\u00aa"+
		"\u00b2\u00b4\u00d7\u00e6\u0111\u0136\u014a";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}