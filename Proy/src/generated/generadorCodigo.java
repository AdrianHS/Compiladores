package generated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADRIAN on 25/5/2017.
 */
public class generadorCodigo extends PParserBaseVisitor {
    List<Instrucion> listaIntruciones = new ArrayList<>();
    int linea =1;

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

        return super.visitRetunStatmm(ctx);
    }

    @Override
    public Object visitPrintStatmm(PParser.PrintStatmmContext ctx) {

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
        return null;
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        //falta metodo
        visit(ctx.sequence());
        return null;
    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {

        String o = ctx.IDENTIFIER().getSymbol().getText();

        String list = (String)visit(ctx.moreArgs());
        list = o + list;
        return null;// retorno malo
    }

    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {

        return super.visitArgVacio(ctx);
    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {
        String listaParametros = "";
        ctx.IDENTIFIER(0);
        for (int i = 1; i <= ctx.IDENTIFIER().size() - 1; i++) {
            String o = ctx.IDENTIFIER(i).getSymbol().getText();
            listaParametros = listaParametros + "," + o;
        }
        return listaParametros;
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        visit(ctx.expression());
        //System.out.println("JUMP_IF_TRUE " + "xx");
        listaIntruciones.add(new Instrucion("JUMP_IF_TRUE " + "xx", linea++));
        visit(ctx.sequence(1));
        //System.out.println("JUMP_ABSOLUTE " + "yy");
        listaIntruciones.add(new Instrucion("JUMP_ABSOLUTE " + "yy", linea++));
        visit(ctx.sequence(0));
        return null;
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
        visit(ctx.expression());

        //generar instrucción STORE_FAST
        //System.out.println("STORE_FAST " + ctx.IDENTIFIER() );
        listaIntruciones.add(new Instrucion("STORE_FAST " + ctx.IDENTIFIER(), linea++));

        return null;
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        return super.visitFunctionStatm(ctx);
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
        if (ctx.additionExpression().size()!=0)
        {
            //deben visitarse todas las additionExpression
            visit(ctx.additionExpression(0));

            //debe elegiste cual de los operadores viene para saber cual instrucción generar
            //System.out.println("COMPARE_OP " + "<");
            listaIntruciones.add(new Instrucion("COMPARE_OP " + "<", linea++));
        }
        return null;
    }

    @Override
    public Object visitComparisonElementMenor(PParser.ComparisonElementMenorContext ctx) {
        return super.visitComparisonElementMenor(ctx);
    }

    @Override
    public Object visitComparisonElementMayor(PParser.ComparisonElementMayorContext ctx) {
        return super.visitComparisonElementMayor(ctx);
    }

    @Override
    public Object visitComparisonElementMenorIgual(PParser.ComparisonElementMenorIgualContext ctx) {
        return super.visitComparisonElementMenorIgual(ctx);
    }

    @Override
    public Object visitComparisonElementMayorIgual(PParser.ComparisonElementMayorIgualContext ctx) {
        return super.visitComparisonElementMayorIgual(ctx);
    }

    @Override
    public Object visitComparisonElementComparacion(PParser.ComparisonElementComparacionContext ctx) {
        return super.visitComparisonElementComparacion(ctx);
    }

    @Override
    public Object visitAdditionExpressionn(PParser.AdditionExpressionnContext ctx) {
        visit(ctx.multiplicationExpression());
        //visit(ctx.additionFactor());

        return null;
    }

    @Override
    public Object visitAdditionFactorr(PParser.AdditionFactorrContext ctx) {
        //deben visitar todos los multiplicationExpression

        visit(ctx.multiplicationExpression(0));

        // genere ADD o SUB dependiendo de cual venga

        return null;
    }

    @Override
    public Object visitAdditionElementSuma(PParser.AdditionElementSumaContext ctx) {
        return super.visitAdditionElementSuma(ctx);
    }

    @Override
    public Object visitAdditionElementResta(PParser.AdditionElementRestaContext ctx) {
        return super.visitAdditionElementResta(ctx);
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

            // genere MUL o DIV dependiendo de cual venga
            //System.out.println("BINARY_MULTIPLY");
            listaIntruciones.add(new Instrucion("BINARY_MULTIPLY", linea++));
        }
        return null;
    }

    @Override
    public Object visitMultiplicationElementMul(PParser.MultiplicationElementMulContext ctx) {
        return super.visitMultiplicationElementMul(ctx);
    }

    @Override
    public Object visitMultiplicationElementDiv(PParser.MultiplicationElementDivContext ctx) {
        return super.visitMultiplicationElementDiv(ctx);
    }

    @Override
    public Object visitElementExpressions(PParser.ElementExpressionsContext ctx) {
        visit(ctx.primitiveExpression());
        //recordar visitar el elementAccess

        return null;
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
        //Generar la instruccion LOAD_CONST
        //System.out.println("LOAD_CONST " + ctx.INTEGER());
        listaIntruciones.add(new Instrucion("LOAD_CONST " + ctx.INTEGER(), linea++));

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        return super.visitPrimitiveExpressionStr(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        //Generar la instruccion LOAD_FAST
        //System.out.println("LOAD_FAST " + ctx.IDENTIFIER());
        listaIntruciones.add(new Instrucion("LOAD_FAST " + ctx.IDENTIFIER(), linea++));

        return null;
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        return super.visitPrimitiveExpressionChar(ctx);
    }

    @Override
    public Object visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx) {
        visit(ctx.expression());
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
