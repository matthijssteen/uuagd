module Main where

import System.Environment
import System.Process

import Paths_uuagd

main :: IO ()
main = do
  filename <- getDataFileName "resources/uuagd-1.0.3.jar"
  args <- getArgs
  callProcess "java" $ ["-jar", filename] ++ args