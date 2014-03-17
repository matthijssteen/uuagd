-- | The code generation on the Haskell side.
{-# LANGUAGE QuasiQuotes #-}
module UUAGD.CodeGen where

class CodeGen a where
  markupAlt         :: String -> String -> [a] -> a
  markupNonTerminal :: String -> a      -> a
  markupTerminal    :: String -> a      -> a
  markupAttrUsage   :: String -> String -> a   -> a