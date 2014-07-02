parser grammar DumpAGParser;

@header {
  package parser.dump;
}

options { tokenVocab = DumpAGLexer; }

root : dataCon;

dataCon : DATA_CON dataConVal* (INDENT mapEntry+ DEDENT)?;

dataConVal
  : CON
  | FIELD
  | FILE_LOC
  ;

set : LIST_SET list;

list
  : LIST_START entry* LIST_END
  | BIG_LIST_START val* BIG_LIST_END
  | BIG_LIST_EMPTY
  ;

tuple : TUPLE_START entry* TUPLE_END;

entry : ENTRIES_NAME | ENTRIES_STRING;

typeMap : TYPE_MAP_START typeMapEntry* TYPE_MAP_END;

typeMapEntry : TYPE_MAP_KEY TEXT;

map : BIG_LIST_START mapEntry* BIG_LIST_END;

mapEntry : KEY val;

val
  : dataCon
  | set
  | list
  | tuple
  | typeMap
  | map
  | CON
  | TEXT
  ;