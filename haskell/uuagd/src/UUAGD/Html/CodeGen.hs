-- | The Html code generator.
{-# LANGUAGE FlexibleInstances #-}
{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE TypeSynonymInstances #-}
module UUAGD.Html.CodeGen (Html) where

import Data.String (IsString(..))
import Text.Blaze.Html5 (Html, toHtml)
import qualified Text.Blaze.Html5 as H
import qualified Text.Blaze.Html5.Attributes as A

import UUAGD.CodeGen

instance CodeGen Html where
  markupAlt dataType alt attrs = do
    H.span H.! A.class_ "type" $ fromString alt
    " {"
    H.div H.! A.class_ "braced" $ toHtml attrs
    "}"

  markupNonTerminal = markupAttr "non-terminal" "kid"
  markupTerminal    = markupAttr "terminal"     "kid"
  markupAttrUsage   = markupAttr "usage"

markupAttr :: String -> String -> String -> Html -> Html
markupAttr cls scope name value =
  H.div H.! A.class_ "attr" $ do
    H.span H.! A.class_ "scope" $ fromString scope
    "."
    H.span H.! A.class_ "name" $ fromString name
    " = "
    H.div H.! A.class_ "value" H.! A.class_ (fromString cls) $ value