-- | The Html code generator.
{-# LANGUAGE FlexibleInstances #-}
{-# LANGUAGE TypeSynonymInstances #-}
{-# LANGUAGE QuasiQuotes #-}
module UUAGD.Html.CodeGen (Html) where

import UUAGD.CodeGen
import UUAGD.Html

instance CodeGen Html where
  markupAlt dataType alt attrs = [html|
<span .type>#{alt}
\ {
<div .braced>
  $forall attr <- attrs
    ^{attr}
}
|]

  markupNonTerminal = markupAttr "non-terminal" "kid"
  markupTerminal    = markupAttr "terminal"     "kid"
  markupAttrUsage   = markupAttr "usage"

markupAttr :: String -> String -> String -> Html -> Html
markupAttr cls scope name value = [html|
<div .attr>
  <span .scope>#{scope}
  .
  <span .name>#{name}
  \ = #
  <div .value .#{cls}>^{value}
|]