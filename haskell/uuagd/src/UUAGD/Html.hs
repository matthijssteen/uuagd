-- | Exports a HTML quasi quotor using Hamlet.
module UUAGD.Html (Html, html) where

import Language.Haskell.TH.Quote (QuasiQuoter)
import Text.Blaze.Html5 (Html)
import Text.Hamlet

-- | A Hamlet quasi quotor that does not add unnecessary newlines.
html :: QuasiQuoter
html = hamletWithSettings htmlRules (defaultHamletSettings {hamletNewlines = NoNewlines})