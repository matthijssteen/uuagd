// Generated from DumpAGParser.g4 by ANTLR 4.1

  package parser.dump;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DumpAGParser}.
 */
public interface DumpAGParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DumpAGParser#val}.
	 * @param ctx the parse tree
	 */
	void enterVal(@NotNull DumpAGParser.ValContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#val}.
	 * @param ctx the parse tree
	 */
	void exitVal(@NotNull DumpAGParser.ValContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#dataConVals}.
	 * @param ctx the parse tree
	 */
	void enterDataConVals(@NotNull DumpAGParser.DataConValsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#dataConVals}.
	 * @param ctx the parse tree
	 */
	void exitDataConVals(@NotNull DumpAGParser.DataConValsContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#typeMapEntry}.
	 * @param ctx the parse tree
	 */
	void enterTypeMapEntry(@NotNull DumpAGParser.TypeMapEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#typeMapEntry}.
	 * @param ctx the parse tree
	 */
	void exitTypeMapEntry(@NotNull DumpAGParser.TypeMapEntryContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#typeMap}.
	 * @param ctx the parse tree
	 */
	void enterTypeMap(@NotNull DumpAGParser.TypeMapContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#typeMap}.
	 * @param ctx the parse tree
	 */
	void exitTypeMap(@NotNull DumpAGParser.TypeMapContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#dataCon}.
	 * @param ctx the parse tree
	 */
	void enterDataCon(@NotNull DumpAGParser.DataConContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#dataCon}.
	 * @param ctx the parse tree
	 */
	void exitDataCon(@NotNull DumpAGParser.DataConContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull DumpAGParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull DumpAGParser.RootContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(@NotNull DumpAGParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(@NotNull DumpAGParser.TupleContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(@NotNull DumpAGParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(@NotNull DumpAGParser.SetContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(@NotNull DumpAGParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(@NotNull DumpAGParser.EntryContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#mapEntry}.
	 * @param ctx the parse tree
	 */
	void enterMapEntry(@NotNull DumpAGParser.MapEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#mapEntry}.
	 * @param ctx the parse tree
	 */
	void exitMapEntry(@NotNull DumpAGParser.MapEntryContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(@NotNull DumpAGParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(@NotNull DumpAGParser.MapContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull DumpAGParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull DumpAGParser.ListContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(@NotNull DumpAGParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(@NotNull DumpAGParser.CodeContext ctx);

	/**
	 * Enter a parse tree produced by {@link DumpAGParser#dataConVal}.
	 * @param ctx the parse tree
	 */
	void enterDataConVal(@NotNull DumpAGParser.DataConValContext ctx);
	/**
	 * Exit a parse tree produced by {@link DumpAGParser#dataConVal}.
	 * @param ctx the parse tree
	 */
	void exitDataConVal(@NotNull DumpAGParser.DataConValContext ctx);
}