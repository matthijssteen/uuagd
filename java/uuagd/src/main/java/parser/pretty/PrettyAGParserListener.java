// Generated from PrettyAGParser.g4 by ANTLR 4.1

  package parser.pretty;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrettyAGParser}.
 */
public interface PrettyAGParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#child}.
	 * @param ctx the parse tree
	 */
	void enterChild(@NotNull PrettyAGParser.ChildContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#child}.
	 * @param ctx the parse tree
	 */
	void exitChild(@NotNull PrettyAGParser.ChildContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#attrKind}.
	 * @param ctx the parse tree
	 */
	void enterAttrKind(@NotNull PrettyAGParser.AttrKindContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#attrKind}.
	 * @param ctx the parse tree
	 */
	void exitAttrKind(@NotNull PrettyAGParser.AttrKindContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull PrettyAGParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull PrettyAGParser.RootContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#alt}.
	 * @param ctx the parse tree
	 */
	void enterAlt(@NotNull PrettyAGParser.AltContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#alt}.
	 * @param ctx the parse tree
	 */
	void exitAlt(@NotNull PrettyAGParser.AltContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#attrs}.
	 * @param ctx the parse tree
	 */
	void enterAttrs(@NotNull PrettyAGParser.AttrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#attrs}.
	 * @param ctx the parse tree
	 */
	void exitAttrs(@NotNull PrettyAGParser.AttrsContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#alternatives}.
	 * @param ctx the parse tree
	 */
	void enterAlternatives(@NotNull PrettyAGParser.AlternativesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#alternatives}.
	 * @param ctx the parse tree
	 */
	void exitAlternatives(@NotNull PrettyAGParser.AlternativesContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(@NotNull PrettyAGParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(@NotNull PrettyAGParser.DataContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#attr}.
	 * @param ctx the parse tree
	 */
	void enterAttr(@NotNull PrettyAGParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#attr}.
	 * @param ctx the parse tree
	 */
	void exitAttr(@NotNull PrettyAGParser.AttrContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#local}.
	 * @param ctx the parse tree
	 */
	void enterLocal(@NotNull PrettyAGParser.LocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#local}.
	 * @param ctx the parse tree
	 */
	void exitLocal(@NotNull PrettyAGParser.LocalContext ctx);

	/**
	 * Enter a parse tree produced by {@link PrettyAGParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(@NotNull PrettyAGParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrettyAGParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(@NotNull PrettyAGParser.AttributesContext ctx);
}