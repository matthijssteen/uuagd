-- | The code generation on the Haskell side.
module UUAGD.Embed where

class Embed a where
  embedAlt :: String -> String -> [a] -> a
  embedNonTerminal :: String -> a -> a
  embedTerminal :: String -> a -> a
  embedAttrUsage :: String -> String -> a -> a
  embedAttrUsageCode :: [(String, a)] -> String -> String -> String -> a -> a
  embedAttrUsageTupleCode :: [(String, a)] -> String -> [(String, String, Maybe a)] -> a