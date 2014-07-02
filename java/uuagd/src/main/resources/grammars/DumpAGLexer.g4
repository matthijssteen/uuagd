lexer grammar DumpAGLexer;

// How to implement lexer lookahead:
// https://github.com/sharwell/antlr4/blob/84087334d80af684582c9bbb233b62b3efc68183/tool/test/org/antlr/v4/test/PositionAdjustingLexer.g4

@header {
  package parser.dump;

  import java.util.Stack;
  import java.util.Queue;
  import java.util.LinkedList;
}

@lexer::members {
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
}

// Skip everything.
ANY_CHAR : . -> skip;

// Except for the comment containing the grammar dump.
DUMP_START : '{- Dump of grammar without default rules' -> skip, pushMode(DUMP);

DEDENT : .;
INDENT : .;
TEXT : .;

mode DUMP;

fragment LNAME : [a-z][a-zA-Z0-9_]*;
fragment UNAME : [A-Z][a-zA-Z0-9_]*;
fragment NAME : LNAME | UNAME;
fragment NL : '\r'? '\n' | '\r';
fragment SPACE : ' ' | '\t';
fragment SPACES : SPACE+ {setText(getText().replace("\t", "    "));};
fragment STRING : '"'  (~["\\] | '\\' .)* '"';

DATA_CON : UNAME '_' UNAME {indentLayout = true;};
DATA_CON_VAL_SEP : ' ' -> skip;
KEY : NAME ': '
  {
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
  };
CON : UNAME;
FIELD : LNAME;
FILE_LOC : STRING '(line ' [0-9]+ ', column ' [0-9]+ ')';

LIST_SET : 'fromList ';
LIST_START : '[' -> pushMode(ENTRIES);
TUPLE_START : '(' -> pushMode(ENTRIES);

BIG_LIST_START : '[ ' {indentLevels.push(indents.size());};
BIG_LIST_SEP : ', ' -> skip;
BIG_LIST_END : ' ]' {handleBigListEnd();};
BIG_LIST_EMPTY : '[ ]';

EOL : NL SPACES? {handleEOL();};

DUMP_END : '-}' -> skip, popMode;

mode ENTRIES;

ENTRIES_SEP : ',' -> skip;
ENTRIES_NAME : UNAME;
ENTRIES_STRING : STRING;

LIST_END : ']' -> popMode;
TUPLE_END : ')' -> popMode;

mode TYPE_MAP;

TYPE_MAP_START : BIG_LIST_START;
TYPE_MAP_KEY : LNAME ': ' {newText();};
TYPE_MAP_EOL : NL SPACE* BIG_LIST_SEP {emitText();};
TYPE_MAP_END : BIG_LIST_END
  {
    if (getIndentFromLA() <= indents.peek()) {
      emitText();
      emit(TYPE_MAP_END, " ]");
      popMode();
    } else {
      appendText();
    }
  };
TYPE_MAP_ANY_CHAR : ANY_CHAR {appendText();};

mode TXT;

TXT_ANY_CHAR : ANY_CHAR {appendText();};
TXT_BIG_LIST_END : BIG_LIST_END
  {
    if (getIndentFromLA() == indents.get(indentLevels.peek() - 1)) {
      emitText();
      handleBigListEnd();
    } else {
      appendText();
    }
  };
TXT_EOL : NL SPACES?
  {
    if (getIndentFromText() <= indents.peek()) {
      emitText();
      handleEOL();
      popMode();
    } else {
      appendText();
    }
  };