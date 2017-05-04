package generated;

import sun.security.krb5.KrbException;

/**
 * Created by ADRIAN on 27/4/2017.
 */
public class AContextual extends PParserBaseVisitor {
    private TablaSimbolos tablaSimbolos;

    public AContextual(){
        tablaSimbolos = new TablaSimbolos();
    }

    @Override
    public Object visitProgrm(PParser.ProgrmContext ctx) {
        visit(ctx.statement(0));
        for (int i = 1; i <= ctx.statement().size() - 1; i++) {
            visit(ctx.statement(i));
        }
        return null;
    }
    @Override
    public Object visitDefStatmm(PParser.DefStatmmContext ctx) {

        return super.visitDefStatmm(ctx);
    }
    @Override
    public Object visitIfStatmm(PParser.IfStatmmContext ctx) {

        return super.visitIfStatmm(ctx);
    }
    @Override
    public Object visitRetunStatmm(PParser.RetunStatmmContext ctx) {

        return super.visitRetunStatmm(ctx);
    }
    @Override
    public Object visitPrintStatmm(PParser.PrintStatmmContext ctx) {

        return super.visitPrintStatmm(ctx);
    }
    @Override
    public Object visitWhileStatmm(PParser.WhileStatmmContext ctx) {
        return super.visitWhileStatmm(ctx);
    }

    @Override
    public Object visitAssignStatmm(PParser.AssignStatmmContext ctx) {
        return super.visitAssignStatmm(ctx);
    }

    @Override
    public Object visitFunctionStatmm(PParser.FunctionStatmmContext ctx) {
        return super.visitFunctionStatmm(ctx);
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        return super.visitDefStatm(ctx);
    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {
        return super.visitArgListt(ctx);
    }
    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
        return super.visitArgVacio(ctx);
    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {
        return super.visitMoreArgss(ctx);
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        return super.visitIfStatm(ctx);
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {
        return super.visitWhileStatm(ctx);
    }

    @Override
    public Object visitRetunStatm(PParser.RetunStatmContext ctx) {
        return super.visitRetunStatm(ctx);
    }

    @Override
    public Object visitPrintStatm(PParser.PrintStatmContext ctx) {
        return super.visitPrintStatm(ctx);
    }

    @Override
    public Object visitAssignStatm(PParser.AssignStatmContext ctx) {

        return null;
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        return super.visitFunctionStatm(ctx);
    }

    @Override
    public Object visitSequencee(PParser.SequenceeContext ctx) {
        return super.visitSequencee(ctx);
    }

    @Override
    public Object visitMoreStatementt(PParser.MoreStatementtContext ctx) {
        return super.visitMoreStatementt(ctx);
    }

    @Override
    public Object visitExpressionn(PParser.ExpressionnContext ctx) {
        return super.visitExpressionn(ctx);
    }

    @Override
    public Object visitComparisonn(PParser.ComparisonnContext ctx) {
        return super.visitComparisonn(ctx);
    }

    @Override
    public Object visitAdditionExpressionn(PParser.AdditionExpressionnContext ctx) {
        return super.visitAdditionExpressionn(ctx);
    }

    @Override
    public Object visitAdditionFactorr(PParser.AdditionFactorrContext ctx) {
        return super.visitAdditionFactorr(ctx);
    }

    @Override
    public Object visitMultiplicationExpressionn(PParser.MultiplicationExpressionnContext ctx) {
        Object retorn  = visit(ctx.elementExpression());
        return retorn;
    }

    @Override
    public Object visitMultiplicationFactorr(PParser.MultiplicationFactorrContext ctx) {
        return super.visitMultiplicationFactorr(ctx);
    }

    @Override
    public Object visitElementExpressions(PParser.ElementExpressionsContext ctx) {
        Object retorno  = visit(ctx.primitiveExpression());
        return retorno;
    }

    @Override
    public Object visitElementAccesss(PParser.ElementAccesssContext ctx) {
        return super.visitElementAccesss(ctx);
    }

    @Override
    public Object visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx) {
        return super.visitFunctionCallStatementt(ctx);
    }

    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        return super.visitElementExpressionn(ctx);
    }

    @Override
    public Object visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx) {
        return super.visitElementExpressionVacio(ctx);
    }

    @Override
    public Object visitMoreExpressionss(PParser.MoreExpressionssContext ctx) {
        return super.visitMoreExpressionss(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionInt(PParser.PrimitiveExpressionIntContext ctx) {
        return super.visitPrimitiveExpressionInt(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        return super.visitPrimitiveExpressionStr(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        return super.visitPrimitiveExpressionIndetifier(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        return super.visitPrimitiveExpressionChar(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx) {
        return super.visitPrimitiveExpressionPIZQPDE(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionListEspresion(PParser.PrimitiveExpressionListEspresionContext ctx) {
        return super.visitPrimitiveExpressionListEspresion(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionLENPIZQPDER(PParser.PrimitiveExpressionLENPIZQPDERContext ctx) {
        return super.visitPrimitiveExpressionLENPIZQPDER(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionfunctionCallExpression(PParser.PrimitiveExpressionfunctionCallExpressionContext ctx) {
        return super.visitPrimitiveExpressionfunctionCallExpression(ctx);
    }

    @Override
    public Object visitListExpressionn(PParser.ListExpressionnContext ctx) {
        return super.visitListExpressionn(ctx);
    }
}
