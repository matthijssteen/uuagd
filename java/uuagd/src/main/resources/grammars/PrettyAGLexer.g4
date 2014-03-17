lexer grammar PrettyAGLexer;

@header {
  package parser.pretty;
}

// Skip everything until we find a data type definition.
ANY_CHAR : . -> skip;

// A data type marked by two prepended dashes
// and trailed by 60 - length(data type) dashes.
AG_DATA_TYPE
  : '-- ' [A-Z][a-zA-Z0-9_]* ' --' [\-]* ('\r'? '\n' | '\r')
    {
      // After removing the dashes and whitespace,
      // what remains will be the data type.
      setText(getText().replaceAll("[\\-\\s]+", ""));

      // For the AG data we need to use different lexer rules.
      pushMode(AG_DATA);
    };

mode AG_DATA;

// The AG data is delimited by Haskell block comments.
START_BLOCK_COMMENT : '{-';
  END_BLOCK_COMMENT : '-}' -> popMode;

// Discard irrelevant pieces of data.
WS    : [ \t\r\n]+           -> skip;
VISIT : 'visit ' [0-9]+ ':'  -> skip;
ATTRS : 'attribute' 's'? ':' -> skip;
ALTS  : 'alternatives:'      -> skip;
COLON : ':'                  -> skip;

// Keywords.
SYN : 'synthesized';
INH : 'inherited';
CHN : 'chained';
LOC : 'local';
ALT : 'alternative';
KID : 'child';

// Names.
AG_TYPE : [A-Z][a-zA-Z0-9_']*;
IDENT   : [a-z][a-zA-Z0-9_']*;

// Haskell types are a bit more complex to lex,
// because we do not want to specify how Haskell types
// can be lexed.
HS_TYPE
  : ': ' ~[\r\n]+ ('\r'? '\n' | '\r')
    {
      String text = getText();

      // Discard the characters used to delimit the token.
      int to = text.indexOf('\r');
      if (to == -1) to = text.indexOf('\n');
      text = text.substring(2, to);

      // In the case of an escaped type, remove the braces.
      if (text.charAt(0) == '{' && text.charAt(text.length() - 1) == '}') {
        text = text.substring(1, text.length() - 1);
      }

      setText(text.trim());
    }
  ;