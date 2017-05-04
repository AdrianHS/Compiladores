// Generated from C:/Users/ADRIAN/Desktop/Compiladores/Proy\PParser.g4 by ANTLR 4.6
package generated;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code progrm}
	 * labeled alternative in {@link PParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgrm(PParser.ProgrmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefStatmm(PParser.DefStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatmm(PParser.IfStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code retunStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetunStatmm(PParser.RetunStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatmm(PParser.PrintStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatmm(PParser.WhileStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatmm(PParser.AssignStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionStatmm}
	 * labeled alternative in {@link PParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionStatmm(PParser.FunctionStatmmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defStatm}
	 * labeled alternative in {@link PParser#defStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefStatm(PParser.DefStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code argListt}
	 * labeled alternative in {@link PParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgListt(PParser.ArgListtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code argVacio}
	 * labeled alternative in {@link PParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgVacio(PParser.ArgVacioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreArgss}
	 * labeled alternative in {@link PParser#moreArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreArgss(PParser.MoreArgssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatm}
	 * labeled alternative in {@link PParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatm(PParser.IfStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStatm}
	 * labeled alternative in {@link PParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatm(PParser.WhileStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code retunStatm}
	 * labeled alternative in {@link PParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetunStatm(PParser.RetunStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStatm}
	 * labeled alternative in {@link PParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatm(PParser.PrintStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStatm}
	 * labeled alternative in {@link PParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatm(PParser.AssignStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionStatm}
	 * labeled alternative in {@link PParser#functionCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionStatm(PParser.FunctionStatmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sequencee}
	 * labeled alternative in {@link PParser#sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequencee(PParser.SequenceeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreStatementt}
	 * labeled alternative in {@link PParser#moreStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreStatementt(PParser.MoreStatementtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionn}
	 * labeled alternative in {@link PParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionn(PParser.ExpressionnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonn}
	 * labeled alternative in {@link PParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonn(PParser.ComparisonnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonElementMenor}
	 * labeled alternative in {@link PParser#comparisonElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonElementMenor(PParser.ComparisonElementMenorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonElementMayor}
	 * labeled alternative in {@link PParser#comparisonElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonElementMayor(PParser.ComparisonElementMayorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonElementMenorIgual}
	 * labeled alternative in {@link PParser#comparisonElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonElementMenorIgual(PParser.ComparisonElementMenorIgualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonElementMayorIgual}
	 * labeled alternative in {@link PParser#comparisonElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonElementMayorIgual(PParser.ComparisonElementMayorIgualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonElementComparacion}
	 * labeled alternative in {@link PParser#comparisonElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonElementComparacion(PParser.ComparisonElementComparacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additionExpressionn}
	 * labeled alternative in {@link PParser#additionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpressionn(PParser.AdditionExpressionnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additionFactorr}
	 * labeled alternative in {@link PParser#additionFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionFactorr(PParser.AdditionFactorrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additionElementSuma}
	 * labeled alternative in {@link PParser#additionElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionElementSuma(PParser.AdditionElementSumaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additionElementResta}
	 * labeled alternative in {@link PParser#additionElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionElementResta(PParser.AdditionElementRestaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationExpressionn}
	 * labeled alternative in {@link PParser#multiplicationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpressionn(PParser.MultiplicationExpressionnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationFactorr}
	 * labeled alternative in {@link PParser#multiplicationFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationFactorr(PParser.MultiplicationFactorrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationElementMul}
	 * labeled alternative in {@link PParser#multiplicationElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationElementMul(PParser.MultiplicationElementMulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationElementDiv}
	 * labeled alternative in {@link PParser#multiplicationElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationElementDiv(PParser.MultiplicationElementDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elementExpressions}
	 * labeled alternative in {@link PParser#elementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementExpressions(PParser.ElementExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elementAccesss}
	 * labeled alternative in {@link PParser#elementAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementAccesss(PParser.ElementAccesssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallStatementt}
	 * labeled alternative in {@link PParser#functionCallExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elementExpressionn}
	 * labeled alternative in {@link PParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementExpressionn(PParser.ElementExpressionnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elementExpressionVacio}
	 * labeled alternative in {@link PParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moreExpressionss}
	 * labeled alternative in {@link PParser#moreExpressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreExpressionss(PParser.MoreExpressionssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionInt}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionInt(PParser.PrimitiveExpressionIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionStr}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionIndetifier}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionChar}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionPIZQPDE}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionListEspresion}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionListEspresion(PParser.PrimitiveExpressionListEspresionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionLENPIZQPDER}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionLENPIZQPDER(PParser.PrimitiveExpressionLENPIZQPDERContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveExpressionfunctionCallExpression}
	 * labeled alternative in {@link PParser#primitiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveExpressionfunctionCallExpression(PParser.PrimitiveExpressionfunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listExpressionn}
	 * labeled alternative in {@link PParser#listExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExpressionn(PParser.ListExpressionnContext ctx);
}