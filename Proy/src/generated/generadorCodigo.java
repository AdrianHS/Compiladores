package generated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADRIAN on 25/5/2017.
 */
public class generadorCodigo extends PParserBaseVisitor {
    List<Instrucion> listaIntruciones = new ArrayList<>();
    private int linea =0;
    private int contador = 0;

    @Override
    public Object visitProgrm(PParser.ProgrmContext ctx) {
        visit(ctx.statement(0));

        for (int i=1; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }

        for(Instrucion i : listaIntruciones){
            i.imprimir();
        }
        return null;
    }

    @Override
    public Object visitDefStatmm(PParser.DefStatmmContext ctx) {
        visit(ctx.defStatement());
        return null;
    }

    @Override
    public Object visitIfStatmm(PParser.IfStatmmContext ctx) {
        visit(ctx.ifStatement());
        return null;
    }

    @Override
    public Object visitRetunStatmm(PParser.RetunStatmmContext ctx) {
        visit(ctx.returnStatement());
        return null;
    }

    @Override
    public Object visitPrintStatmm(PParser.PrintStatmmContext ctx) {
        visit(ctx.printStatement());
        return null;
    }

    @Override
    public Object visitWhileStatmm(PParser.WhileStatmmContext ctx) {
        visit(ctx.whileStatement());//no se
        return null;
    }

    @Override
    public Object visitAssignStatmm(PParser.AssignStatmmContext ctx) {
        visit(ctx.assignStatement());//algo
        return null;
    }

    @Override
    public Object visitFunctionStatmm(PParser.FunctionStatmmContext ctx) {
        return null;//algo
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        //falta metodo

        visit(ctx.argList());
        visit(ctx.sequence());
        return null;

    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {

        String info = ctx.IDENTIFIER().getSymbol().getText();
        listaIntruciones.add(new Instrucion("LOAD_FAST " + info, linea++));

        visit(ctx.moreArgs());

        return null;
    }

    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {

        for (int i = 1; i <= ctx.IDENTIFIER().size() - 1; i++) {
            String info = ctx.IDENTIFIER(i).getSymbol().getText();
            listaIntruciones.add(new Instrucion("LOAD_FAST " + info, linea++));
        }
        return null;
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        visit(ctx.expression());

        listaIntruciones.add(new Instrucion("JUMP_IF_TRUE ", linea++));
        int x = listaIntruciones.size();
        visit(ctx.sequence(1));
        listaIntruciones.get(x).setCodigo("JUMP_IF_TRUE " + Integer.toString(linea));

        listaIntruciones.add(new Instrucion("JUMP_ABSOLUTE ", linea++));
        int y = listaIntruciones.size();
        visit(ctx.sequence(0));
        listaIntruciones.get(y).setCodigo("JUMP_ABSOLUTE " + Integer.toString(linea));
        return null;
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {
        visit(ctx.expression());
        listaIntruciones.add(new Instrucion("JUMP_IF_FALSE ", linea++));
        int x = listaIntruciones.size();
        visit(ctx.sequence());

        listaIntruciones.add(new Instrucion("JUMP_ABSOLUTE ", linea++));

        listaIntruciones.get(x).setCodigo("JUMP_IF_FALSE " + Integer.toString(linea+1));

        return null;
    }

    @Override
    public Object visitRetunStatm(PParser.RetunStatmContext ctx) {
        visit(ctx.expression());
        listaIntruciones.add(new Instrucion("RETURN_VALUE ", linea++));
        return null;
    }

    @Override
    public Object visitPrintStatm(PParser.PrintStatmContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_GLOBAL " + ctx.PRINT().getSymbol().getText() , linea++));
        visit(ctx.expression());
        listaIntruciones.add(new Instrucion("CALL_FUNCTION ", linea++));
        return null;
    }

    @Override
    public Object visitAssignStatm(PParser.AssignStatmContext ctx) {

        visit(ctx.expression());
        String info = ctx.IDENTIFIER().getSymbol().getText();
        listaIntruciones.add(new Instrucion("STORE_FAST " + info, linea++));
        return null;
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        //falta
        //visit(ctx.primitiveExpression());
        //visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitSequencee(PParser.SequenceeContext ctx) {
        visit(ctx.moreStatement());
        return null;
    }

    @Override
    public Object visitMoreStatementt(PParser.MoreStatementtContext ctx) {
        for (int i=0; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }
        return null;
    }

    @Override
    public Object visitExpressionn(PParser.ExpressionnContext ctx) {
        visit(ctx.additionExpression());
        visit(ctx.comparison());
        return null;
    }

    @Override
    public Object visitComparisonn(PParser.ComparisonnContext ctx) {

        //Revisar
        if (ctx.additionExpression().size()!=0)
        {
            //deben visitarse todas las additionExpression
            visit(ctx.additionExpression(0));

        //debe elegiste cual de los operadores viene para saber cual instrucción generar

        listaIntruciones.add(new Instrucion("COMPARE_OP " + "<", linea++));
    }
        return null;//Revisar
    }

    @Override
    public Object visitComparisonElementMenor(PParser.ComparisonElementMenorContext ctx) {

        listaIntruciones.add(new Instrucion("COMPARE_OP " + "<", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementMayor(PParser.ComparisonElementMayorContext ctx) {

        listaIntruciones.add(new Instrucion("COMPARE_OP " + ">", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementMenorIgual(PParser.ComparisonElementMenorIgualContext ctx) {
        listaIntruciones.add(new Instrucion("COMPARE_OP " + "<=", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementMayorIgual(PParser.ComparisonElementMayorIgualContext ctx) {

        listaIntruciones.add(new Instrucion("COMPARE_OP " + ">=", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementComparacion(PParser.ComparisonElementComparacionContext ctx) {
        listaIntruciones.add(new Instrucion("COMPARE_OP " + "==", linea++));
        return null;
    }

    @Override
    public Object visitAdditionExpressionn(PParser.AdditionExpressionnContext ctx) {
        visit(ctx.multiplicationExpression());
        //visit(ctx.additionFactor());

        return null;//revisar
    }

    @Override
    public Object visitAdditionFactorr(PParser.AdditionFactorrContext ctx) {
        //deben visitar todos los multiplicationExpression

        visit(ctx.multiplicationExpression(0));

        // genere ADD o SUB dependiendo de cual venga

        return null;//Revisar
    }

    @Override
    public Object visitAdditionElementSuma(PParser.AdditionElementSumaContext ctx) {
        listaIntruciones.add(new Instrucion("BINARY_ADD " + "+", linea++));
        return null;
    }

    @Override
    public Object visitAdditionElementResta(PParser.AdditionElementRestaContext ctx) {
        listaIntruciones.add(new Instrucion("BINARY_SUBSTRACT " + "-", linea++));
        return null;
    }

    @Override
    public Object visitMultiplicationExpressionn(PParser.MultiplicationExpressionnContext ctx) {
        visit(ctx.elementExpression());

        visit(ctx.multiplicationFactor());

        return null;
    }

    @Override
    public Object visitMultiplicationFactorr(PParser.MultiplicationFactorrContext ctx) {
        //deben visitar todos los elementExpression
        if (ctx.elementExpression().size()!=0) {
            visit(ctx.elementExpression(0));
            listaIntruciones.add(new Instrucion("BINARY_MULTIPLY", linea++));
        }
        return null;//revisar
    }

    @Override
    public Object visitMultiplicationElementMul(PParser.MultiplicationElementMulContext ctx) {
        listaIntruciones.add(new Instrucion("BINARY_MULTIPLY", linea++));
        return null;
    }

    @Override
    public Object visitMultiplicationElementDiv(PParser.MultiplicationElementDivContext ctx) {
        listaIntruciones.add(new Instrucion("BINARY_DIVIDE", linea++));
        return null;
    }

    @Override
    public Object visitElementExpressions(PParser.ElementExpressionsContext ctx) {
        visit(ctx.primitiveExpression());
        //recordar visitar el elementAccess

        return null;
    }

    @Override
    public Object visitElementAccesss(PParser.ElementAccesssContext ctx) {
        return null;
    }

    @Override
    public Object visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx) {
        return null;
    }

    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        visit(ctx.expression());
        int info = (int)visit(ctx.moreExpressions());
        listaIntruciones.add(new Instrucion("BUILD_LIST " + String.valueOf(info+1) , linea++));
        return null;
    }

    @Override
    public Object visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreExpressionss(PParser.MoreExpressionssContext ctx)
    {
        for (int i = 0; i < ctx.expression().size(); i++) {
            visit(ctx.expression(i));
        }
        return ctx.expression().size();
    }

    @Override
    public Object visitPrimitiveExpressionInt(PParser.PrimitiveExpressionIntContext ctx) {
        //Generar la instruccion LOAD_CONST
        //System.out.println("LOAD_CONST " + ctx.INTEGER());
        listaIntruciones.add(new Instrucion("LOAD_CONST " + ctx.INTEGER(), linea++));

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_CONST" + ctx.STRING(), linea++));
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_FAST " + ctx.IDENTIFIER(), linea++));

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_CONST" + ctx.CHAR(), linea++));
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionListEspresion(PParser.PrimitiveExpressionListEspresionContext ctx) {
        visit(ctx.listExpression());
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionLENPIZQPDER(PParser.PrimitiveExpressionLENPIZQPDERContext ctx) {
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionfunctionCallExpression(PParser.PrimitiveExpressionfunctionCallExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitListExpressionn(PParser.ListExpressionnContext ctx) {
        visit(ctx.expressionList());
        return null;
    }
}
