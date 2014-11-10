module Main where

import Paths_uuagd
import System.Environment
import System.Process

main :: IO ()
main = do
  filename <- getDataFileName "resources/uuagd-1.0.3.jar"
  args <- getArgs
  callProcess "java" $ ["-jar", filename] ++ args