#!/bin/bash
uuagd "-fscwdH" Example5.ag "(toHtml . show)" Html
ghc Example5.hs
./Example5 > Example5.html