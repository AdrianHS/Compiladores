package generated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADRIAN on 25/5/2017.
 */
public class generadorCodigo extends PParserBaseVisitor {
    List<Instrucion> listaIntruciones = new ArrayList<>();
    private int linea =0;
    private int numeroArgumentos =0;

    @Override
    public Object visitProgrm(PParser.ProgrmContext ctx) {
        visit(ctx.statement(0));

        for (int i=1; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }

        for(Instrucion i : listaIntruciones){
            if(i.getEsFuncion()){
                i.imprimir2();
            }
            else {
                i.imprimir();
            }
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
        visit(ctx.whileStatement());
        return null;
    }

    @Override
    public Object visitAssignStatmm(PParser.AssignStatmmContext ctx) {
        visit(ctx.assignStatement());
        return null;
    }

    @Override
    public Object visitFunctionStatmm(PParser.FunctionStatmmContext ctx) {
        visit(ctx.functionCallStatement());
        return null;
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        String[] args= (String[])visit(ctx.argList());

        listaIntruciones.add(new Instrucion(ctx.IDENTIFIER().getSymbol().getText(),args));

        visit(ctx.sequence());
        return null;

    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {

        String[] moreArgs =(String[]) visit(ctx.moreArgs());
        String[] args = {ctx.IDENTIFIER().getSymbol().getText()};
        return args;
    }

    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
        String [] n={};
        return n;
    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {

        return null;
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        visit(ctx.expression());
        listaIntruciones.add(new Instrucion("JUMP_IF_FAlSE ", linea++));
        int x = listaIntruciones.size()-1;
        visit(ctx.sequence(0));
        listaIntruciones.get(x).setCodigo("JUMP_IF_FALSE " + Integer.toString(linea+1));

        listaIntruciones.add(new Instrucion("JUMP_ABSOLUTE ", linea++));
        int y = listaIntruciones.size()-1;
        visit(ctx.sequence(1));
        listaIntruciones.get(y).setCodigo("JUMP_ABSOLUTE " + Integer.toString(linea));
        return null;
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {

        int saltoWhile = linea;
        visit(ctx.expression());

        listaIntruciones.add(new Instrucion("JUMP_IF_FALSE ", linea++));
        int x = listaIntruciones.size()-1;
        visit(ctx.sequence());

        listaIntruciones.add(new Instrucion("JUMP_ABSOLUTE " +saltoWhile, linea++));

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
        listaIntruciones.add(new Instrucion("CALL_FUNCTION "+String.valueOf(numeroArgumentos), linea++));
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
        visit(ctx.primitiveExpression());

        int y = listaIntruciones.size()-1;
        String cod= listaIntruciones.get(y).getCodigo();
        String[] c = cod.split("LOAD_FAST");
        listaIntruciones.get(y).setCodigo("LOAD_GLOBAL" + c[1]);
        visit(ctx.expressionList());

        listaIntruciones.add(new Instrucion("CALL_FUNCTION " + numeroArgumentos,linea++));
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

        for (int i = 0; i < ctx.comparisonElement().size() ; i++) {
            visit(ctx.additionExpression(i));
            visit(ctx.comparisonElement(i));
        }
        return null;
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
        visit(ctx.additionFactor());

        return null;
    }

    @Override
    public Object visitAdditionFactorr(PParser.AdditionFactorrContext ctx) {
        for (int i = 0; i < ctx.additionElement().size() ; i++) {
            visit(ctx.multiplicationExpression(i));
            visit(ctx.additionElement(i));
        }
        return null;
    }

    @Override
    public Object visitAdditionElementSuma(PParser.AdditionElementSumaContext ctx) {
        listaIntruciones.add(new Instrucion("BINARY_ADD ", linea++));
        return null;
    }

    @Override
    public Object visitAdditionElementResta(PParser.AdditionElementRestaContext ctx) {
        listaIntruciones.add(new Instrucion("BINARY_SUBSTRACT ", linea++));
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
        for (int i = 0; i <ctx.elementExpression().size() ; i++) {
            visit(ctx.elementExpression(i));
            visit(ctx.multiplicationElement(i));
        }
        return null;
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
        visit(ctx.elementAccess());


        return null;
    }

    @Override
    public Object visitElementAccesss(PParser.ElementAccesssContext ctx) {

        for (int i = 0; i <ctx.expression().size() ; i++) {
            visit(ctx.expression(i));
            listaIntruciones.add(new Instrucion("BINARY_SUBSCR",linea++));
        }



        return null;
    }

    @Override
    public Object visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_GLOBAL " + ctx.IDENTIFIER().getSymbol().getText(),linea++));
        visit(ctx.expressionList());
        listaIntruciones.add(new Instrucion("CALL_FUNCTION " + String.valueOf(numeroArgumentos),linea++));

        return null;
    }

    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        visit(ctx.expression());
        numeroArgumentos = 1;
        numeroArgumentos += (int)visit(ctx.moreExpressions());
        return null;
    }

    @Override
    public Object visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx) {
        numeroArgumentos=0;
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
        listaIntruciones.add(new Instrucion("LOAD_CONST " + ctx.INTEGER(), linea++));

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_CONST " + ctx.STRING(), linea++));
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_FAST " + ctx.IDENTIFIER(), linea++));

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        listaIntruciones.add(new Instrucion("LOAD_CONST " + ctx.CHAR(), linea++));
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
        listaIntruciones.add(new Instrucion("LOAD_GLOBAL " + ctx.LEN().getSymbol().getText(),linea++));
        visit(ctx.expression());
        listaIntruciones.add(new Instrucion("CALL_FUNCTION " + numeroArgumentos,linea++));
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionfunctionCallExpression(PParser.PrimitiveExpressionfunctionCallExpressionContext ctx) {
        visit(ctx.functionCallExpression());
        return null;
    }

    @Override
    public Object visitListExpressionn(PParser.ListExpressionnContext ctx) {

        visit(ctx.expressionList());
        listaIntruciones.add(new Instrucion("BUILD_LIST " + String.valueOf(numeroArgumentos) , linea++));

        return null;
    }
}
