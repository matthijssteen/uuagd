-- | The Html code generator.
{-# LANGUAGE FlexibleInstances #-}
{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE TypeSynonymInstances #-}
module UUAGD.Html.TreeEmbed (Html) where

import Control.Monad (sequence_, forM_)
import qualified Data.List as L
import Data.String (IsString(..))
import Text.Blaze.Html5 (Html, toHtml)
import qualified Text.Blaze.Html5 as H
import qualified Text.Blaze.Html5.Attributes as A
import UUAGD.Embed

instance Embed Html where
  embedAlt dataType alt attrs = do
    H.span H.! A.class_ "alt" $ fromString alt
    " {"
    H.div H.! A.class_ "braced" $ toHtml attrs
    "}"

  embedNonTerminal = markupAttr "non-terminal" "kid"
  embedTerminal    = markupAttr "terminal"     "kid"
  embedAttrUsage   = markupAttr "usage"

  embedAttrUsageCode = markupAttrUsageCode
  embedAttrUsageTupleCode = markupAttrUsageTupleCode

markupAttr :: String -> String -> String -> Html -> Html
markupAttr cls scope name val =
  H.div H.! A.class_ (fromString $ "attr " ++ cls) $ do
    markupAttrName scope name
    " = "
    H.span H.! A.class_ "val" $ val

markupAttrUsageCode :: [(String, Html)] -> String -> String -> String -> Html -> Html
markupAttrUsageCode codeVals code scope name val =
    H.div H.! A.class_ "attr usage" $ do
      markupAttrName scope name
      " = "
      H.span H.! A.class_ "code" $ fromString code
      H.div H.! A.class_ "code-attrs" $
        forM_ codeVals $ \(attr, val) -> H.div H.! H.dataAttribute "attr" (fromString attr) $ val
      H.preEscapedToHtml (" &crarr;" :: String)
      H.div H.! A.class_ "val" $ val

markupAttrUsageTupleCode :: [(String, Html)] -> String -> [(String, String, Maybe Html)] -> Html
markupAttrUsageTupleCode codeVals code usages =
    H.div H.! A.class_ "attr usage" $ do
      "("
      sequence_ $ L.intersperse ", " $ map (\(scope, name, _) -> H.span H.! A.class_ "tuple-attr" $ markupAttrName scope name) usages
      ") = "
      H.span H.! A.class_ "code" $ fromString code
      H.div H.! A.class_ "code-attrs" $
        forM_ codeVals $ \(attr, val) -> H.div H.! H.dataAttribute "attr" (fromString attr) $ val
      H.div H.! A.class_ "tuple-attrs" $
        forM_ usages $ \(scope, name, mval) -> case mval of
          Nothing -> ""
          Just val -> H.div H.! H.dataAttribute "attr" (fromString $ scope ++ "." ++ name) $ val

markupAttrName :: String -> String -> Html
markupAttrName scope name = do
  H.span H.! A.class_ "scope" $ fromString scope
  "."
  H.span H.! A.class_ "name" $ fromString name