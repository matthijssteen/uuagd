parser grammar PrettyAGParser;

@header {
  package parser.pretty;
}

options { tokenVocab = PrettyAGLexer; }

root : data*;

data : AG_DATA_TYPE START_BLOCK_COMMENT attributes alternatives END_BLOCK_COMMENT;

attributes : attrs+;
attrs : attrKind attr+;
attrKind : SYN | INH | CHN;
attr : IDENT HS_TYPE;

alternatives : alt+;
alt : ALT AG_TYPE child* local*;
child : KID IDENT HS_TYPE;
local : LOC IDENT HS_TYPE;