-- | An implementation of here documents string literals.
{-# LANGUAGE LambdaCase #-}
module UUAGD.Heredoc where

import Language.Haskell.TH
import Language.Haskell.TH.Quote

-- | A quasi quoter to define string literals with variable interpolation.
-- | The first and last newline characters are removed from the string literal
-- | to support the pattern of not having any contents after and before the
-- | opening and closing quasi quote braces. For example: [here|\nliteral\n|]
here :: QuasiQuoter
here = QuasiQuoter {
    quoteExp  = recur (litE . stringL) . rmFirst,
    quotePat  = undefined,
    quoteType = undefined,
    quoteDec  = undefined
  }
  where
    rmFirst :: String -> String
    rmFirst = \case
      -- Remove the first newline character, if present.
      '\r':'\n' : cs -> cs
      '\r'      : cs -> cs
      '\n'      : cs -> cs
      cs             -> cs

    recur :: (String -> ExpQ) -> String -> ExpQ
    recur f = \case
      -- Base case.
      []             -> f []

      -- Remove the last newline character, if present.
      '\r':'\n' : [] -> f []
      '\r'      : [] -> f []
      '\n'      : [] -> f []

      -- Normalize newlines to Unix-style newlines.
      '\r':'\n' : cs -> recur (f . ('\n':)) cs
      '\r'      : cs -> recur (f . ('\n':)) cs

      -- Variable interpolation.
      '#':'(':cs ->
        let (var, ')':cs') = break (== ')') cs
         in recur (append (f []) . append (varE $ mkName var) . litE . stringL) cs'

      -- Default case.
      c:cs -> recur (f . (c:)) cs

    -- The AST version of the append operator.
    append :: ExpQ -> ExpQ -> ExpQ
    append x y = infixApp x appendVar y

    -- The reference to the append operator.
    appendVar :: ExpQ
    appendVar = varE $ mkName "++"