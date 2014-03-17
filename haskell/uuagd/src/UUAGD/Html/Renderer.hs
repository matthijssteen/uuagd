-- | The HTML renderer.
{-# LANGUAGE QuasiQuotes #-}
module UUAGD.Html.Renderer where

import Text.Blaze.Html5 (Html)
import Text.Blaze.Html.Renderer.String (renderHtml)

import UUAGD.Heredoc

-- | Render the generated HTML as a HTML document.
render :: Html -> String
render html = let body = renderHtml html in [here|
<!DOCTYPE html>
<html>
	<head>
		<title>Debugger</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" type="text/css" href="http://necolas.github.io/normalize.css/3.0.0/normalize.css" />
		<style>
		body {
			font-family: monospace;
			font-size: 12px;
			margin: 1em;
		}
		div {
			background-color: inherit;
		}
		.value {
			display: inline;
			cursor: pointer;
		}
		.braced {
			padding-left: 20px;
		}
		.scope {
			color: purple;
		}
		.name {
			color: blue;
		}
		.type {
			color: red;
		}
		</style>
		<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
		<script>
		$(function()
		{
			var $dots = $('<span class="dots">...</span>');
			$('body').on('click', '.value:has(.braced)', function(e)
			{
				var $braced = $(this).find('.braced');
				if ($braced.is(':hidden')) {
					$braced.next().remove();
					$braced.show();
				}
				else {
					$braced.hide();
					$braced.after($dots.clone());
				}
				return false;
			});
		});
		</script>
		<script type="text/x-mathjax-config">
		MathJax.Hub.Register.MessageHook("TeX Jax - parse error", function (data) {
			console.log(data);
		});
		</script>
		<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
	</head>
	<body>
		<div class="value">#(body)</div>
	</body>
</html>
|]