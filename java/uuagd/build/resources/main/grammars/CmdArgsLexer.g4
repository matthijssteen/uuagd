lexer grammar CmdArgsLexer;

@header {
  package parser.args;
}

SingleQuotedString : '\'' (~['\\] | '\\' .)+? '\'';
DoubleQuotedString : '"'  (~["\\] | '\\' .)+? '"';

Word : ~[" \t]+;

WhiteSpace : [ \t\r\n]+ -> skip;