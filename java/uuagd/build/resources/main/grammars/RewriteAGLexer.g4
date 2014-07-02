lexer grammar RewriteAGLexer;

@header {
  package parser.rewrite;
}

@members {
  private boolean semBlock = false;
  private int lastTokenType = 0;

  @Override
  public void emit(Token token) {
    super.emit(token);
    lastTokenType = token.getType();
  }

  @Override
  public void reset() {
    super.reset();
    semBlock = false;
    lastTokenType = 0;
  }
}


ANY_CHAR : . -> skip;

LINE_COMMENT : '--' .*? ({_input.LA(1) == '\r' || _input.LA(1) == '\n'}? | EOF) -> skip;
MULTILINE_COMMENT : '{-' (MULTILINE_COMMENT | .*?) '-}' -> skip;

SET : NL 'set' WS+ TY WS* '=' WS* EXCL_STAR_TY_SET;

DATA
  : NL 'data' (WS+ TY)+
    { semBlock = false; skip(); }
  ;

SEM
  : NL 'sem' WS+ EXCL_STAR_TY_SET
    { semBlock = true; }
  ;

ALT
  : NL '|' WS* EXCL_STAR_TY_SET
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

INCLUDE : NL 'include' WS* '"'  (~["\\] | '\\' .)+? '"';

fragment NAME : [a-z][a-zA-Z0-9_\']*;
fragment ATTR_NAME : NAME '.' NAME;
fragment TY : [A-Z][a-zA-Z0-9_\']*;
fragment STAR_TY : (TY | '*');
fragment STAR_TY_SET : STAR_TY (WS+ STAR_TY)*;
fragment EXCL_STAR_TY_SET : STAR_TY_SET (WS* '-' WS* STAR_TY_SET)*;
fragment NL : ({lastTokenType == 0}? | ('\r'? '\n' | '\r') WS*);
fragment WS
  : [ \t\r\n]
    { setText(getText().replaceAll("\t", "    ")); }
  ;