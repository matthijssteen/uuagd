-- | The HTML renderer.
module UUAGD.Html.Renderer where

import qualified Data.List as L
import Text.Blaze.Html5 (Html)
import Text.Blaze.Html.Renderer.String (renderHtml)

import Paths_uuagd

-- | Render the generated HTML as a HTML document.
render :: Html -> IO String
render html = do
	filename <- getDataFileName "resources/template.html"
	contents <- readFile filename
	let replaceBody s@(c:cs) = if L.isPrefixOf "#(body)" s
		then renderHtml html ++ drop (length "#(body)") s
		else c : replaceBody cs
	return $ replaceBody contents