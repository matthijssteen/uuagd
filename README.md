Utrecht University Attribute Grammar Debugger
=============================================

The Utrecht University Attribute Grammar Debugger. It consists of two parts.
A preprocessor for the UUAG Compiler written in Java using Antlr 4 that will
add an extra synthesized attribute named uuagd containing the debug information.
The other part consists of the Haskell code that is called to compute
the generated attribute. Since an AG can have inherited attributes that are
dependent on external values, the programmar still has to supply the necessary
interfacing code that returns the uuagd attribute.

A default HTML code generator and renderer is given, but you are free to
roll out your own.