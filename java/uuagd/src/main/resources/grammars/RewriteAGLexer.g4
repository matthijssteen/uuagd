lexer grammar RewriteAGLexer;

@header {
  package parser.rewrite;

  import java.util.LinkedList;
  import java.util.Queue;
  import java.util.Stack;
  import java.util.regex.Matcher;
  import java.util.regex.Pattern;
}

@members {
  final private static Pattern ATTR_NAME_PAT = Pattern.compile("[a-z][a-zA-Z0-9_]*\\.[a-z][a-zA-Z0-9_]*");

  private Stack<Integer> indents = new Stack<Integer>();
  private Queue<Token> tokens = new LinkedList<Token>();
  private boolean semBlock = false;
  private int lastTokenType = 0;

  @Override
  public void emit(Token token) {
    super.emit(token);
    tokens.offer(token);
    lastTokenType = token.getType();
  }

  public void emit(int type, String text) {
    _type = type;
    _text = text;
    emit();
    lastTokenType = type;
  }

  @Override
  public Token nextToken() {
    super.nextToken();
    return tokens.isEmpty() ? emitEOF() : tokens.poll();
  }
}


ANY_CHAR : . -> skip;

LINE_COMMENT : '--' .*? ('\r'? '\n' | '\r' | EOF) -> skip;
MULTILINE_COMMENT : '{-' (MULTILINE_COMMENT | .*?) '-}' -> skip;

DATA
  : NL 'data' TY+
    { semBlock = false; skip(); }
  ;

SEM
  : NL 'sem' STAR_TY+
    { semBlock = true; }
  ;

ALT
  : NL '|' STAR_TY+
    { if (!semBlock) { skip(); } }
  ;

ATTR
  : ({lastTokenType == ALT}? WS* | NL) ATTR_NAME WS* '='
    { if (!semBlock) { skip(); } }
  ;

ATTR_TUPLE
  : ({lastTokenType == ALT}? WS* | NL) '(' WS* ATTR_NAME (WS* ',' WS* ATTR_NAME)+ WS* ')' WS* '='
    { if (!semBlock) { skip(); } }
  ;

INCLUDE : NL 'include' WS+ '"'  (~["\\] | '\\' .)+? '"';

fragment NAME : [a-z][a-zA-Z0-9_']*;
fragment ATTR_NAME : NAME '.' NAME;
fragment TY : WS+ [A-Z][a-zA-Z0-9_]*;
fragment STAR_TY : (TY | WS+ '*');
fragment NL : ({lastTokenType == 0}? | ('\r'? '\n' | '\r') WS*);
fragment WS
  : [ \t\r\n]
    { setText(getText().replaceAll("\t", "    ")); }
  ;