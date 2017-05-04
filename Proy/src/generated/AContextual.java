package generated;

import com.company.Main;
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
        int retorno = (int)visit(ctx.defStatement());
        return null;
    }
    @Override
    public Object visitIfStatmm(PParser.IfStatmmContext ctx) {
        int retorno = (int)visit(ctx.ifStatement());
        return null;
    }
    @Override
    public Object visitRetunStatmm(PParser.RetunStatmmContext ctx) {
        int retorno = (int)visit(ctx.returnStatement());
        return null;
    }
    @Override
    public Object visitPrintStatmm(PParser.PrintStatmmContext ctx) {
        int retorno = (int)visit(ctx.printStatement());
        return null;
    }
    @Override
    public Object visitWhileStatmm(PParser.WhileStatmmContext ctx) {
        int retorno = (int)visit(ctx.whileStatement());
        return null;
    }

    @Override
    public Object visitAssignStatmm(PParser.AssignStatmmContext ctx) {
        int retorno = (int)visit(ctx.assignStatement());

        return null;
    }

    @Override
    public Object visitFunctionStatmm(PParser.FunctionStatmmContext ctx) {
        int retorno = (int)visit(ctx.functionCallStatement());
        return null;
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        visit(ctx.argList());
        visit(ctx.sequence());
        return null;
    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {
        visit(ctx.moreArgs());
        return null;
    }
    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {
        for (int i=0; i <= ctx.IDENTIFIER().size()-1; i++){
            tablaSimbolos.insertar(ctx.IDENTIFIER(i).getSymbol().getText(),4,ctx);
        }
        return null;
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        visit(ctx.expression());
        visit(ctx.sequence(0));
        visit(ctx.sequence(1));
        return null;
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {
        visit(ctx.expression());
        visit(ctx.sequence());
        return null;
    }

    @Override
    public Object visitRetunStatm(PParser.RetunStatmContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitPrintStatm(PParser.PrintStatmContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Object visitAssignStatm(PParser.AssignStatmContext ctx) {
        int tipo = (int)visit(ctx.expression());
        TablaSimbolos.Ident simbolo = tablaSimbolos.buscar(ctx.IDENTIFIER().getSymbol().getText());
        if(simbolo == null){
            tablaSimbolos.insertar(ctx.IDENTIFIER().getSymbol().getText(),tipo,ctx);
        }
        else if (simbolo.tok.getType() != tipo){
            Main.info.mostarErrorConsola("Tipos incompatibles");
            return -1;
        }
        return tipo;//no se si esta bien
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        visit(ctx.primitiveExpression());
        visit(ctx.expressionList());
        return null;
    }

    @Override
    public Object visitSequencee(PParser.SequenceeContext ctx) {
        visit(ctx.moreStatement());
        return null;
    }

    @Override
    public Object visitMoreStatementt(PParser.MoreStatementtContext ctx) {
        visit(ctx.statement(0));
        for (int i=1; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }
        return null;
    }

    @Override
    public Object visitExpressionn(PParser.ExpressionnContext ctx) {
        int retorno = (int)visit(ctx.additionExpression());
        int ret = (int)visit(ctx.comparison());
        if(ret != 7){
            if(ret == -1){
                return -1;
            }
            else if(retorno == ret){
                return retorno;
            }
            else {
                return -1;
            }
        }
        return retorno;
    }

    @Override
    public Object visitComparisonn(PParser.ComparisonnContext ctx) {
        int retorno = 7;
        int x = -1;
        for (int i=0; i <= ctx.additionExpression().size()-1; i++)
        {
            if(i == 0){
                x = (int)visit(ctx.additionExpression(i));
                if(x != 1 && x != 3){
                    return -1;
                }
            }
            retorno = (int)visit(ctx.additionExpression(i));
            if(retorno != 1 && retorno != 3){
                Main.info.mostarErrorConsola("Tipos incompatibles");
                return -1;
            }
        }
        return retorno;
    }

    @Override
    public Object visitAdditionExpressionn(PParser.AdditionExpressionnContext ctx) {
        int retorno = (int)visit(ctx.multiplicationExpression());
        int ret = (int)visit(ctx.additionFactor());
        if(ret == -1){
            return -1;
        }
        else if(ret == 1){
            if(retorno == 1){
                return 1;
            }
            Main.info.mostarErrorConsola("Se esperaba un entero");
            return -1;
        }
        else if(ret == 7){
            return retorno;
        }
        else {
            return -1;
        }
    }

    @Override
    public Object visitComparisonElementMenor(PParser.ComparisonElementMenorContext ctx) {
        return null;
    }

    @Override
    public Object visitComparisonElementMayor(PParser.ComparisonElementMayorContext ctx) {
        return null;
    }

    @Override
    public Object visitComparisonElementMenorIgual(PParser.ComparisonElementMenorIgualContext ctx) {
        return null;
    }

    @Override
    public Object visitComparisonElementMayorIgual(PParser.ComparisonElementMayorIgualContext ctx) {
        return null;
    }

    @Override
    public Object visitComparisonElementComparacion(PParser.ComparisonElementComparacionContext ctx) {
        return null;
    }

    @Override
    public Object visitAdditionElementSuma(PParser.AdditionElementSumaContext ctx) {
        return true;
    }

    @Override
    public Object visitAdditionElementResta(PParser.AdditionElementRestaContext ctx) {
        return false;
    }

    @Override
    public Object visitMultiplicationElementMul(PParser.MultiplicationElementMulContext ctx) {
        return null;
    }

    @Override
    public Object visitMultiplicationElementDiv(PParser.MultiplicationElementDivContext ctx) {
        return null;
    }

    @Override
    public Object visitAdditionFactorr(PParser.AdditionFactorrContext ctx) {
        if(ctx.multiplicationExpression().size() == 0) {
            int x = -1; // por defecto -1 para declararlo
            for (int i = 0; i <= ctx.multiplicationExpression().size() - 1; i++) {
                if(i == 0){
                    x = (int)visit(ctx.multiplicationExpression(i));
                    if(x != 1 || x != 2){
                        return -1;
                    }
                    if(x == 2 && !(boolean)visit(ctx.additionElement(i))){
                        return -1;
                    }
                }
                int ret = (int) visit(ctx.multiplicationExpression(i));
                if (ret != x) {
                    Main.info.mostarErrorConsola("Tipos incompatibles");
                    return -1;
                }
            }
            return 1;
        }
        return 7;
    }

    @Override
    public Object visitMultiplicationExpressionn(PParser.MultiplicationExpressionnContext ctx) {
        int retorno = (int)visit(ctx.elementExpression());
        int ret = (int)visit(ctx.multiplicationFactor());
        if(ret == -1){
            return -1;
        }
        else if(ret == 1){
            if(retorno == 1){
                return 1;
            }
            Main.info.mostarErrorConsola("Se esperaba un entero");
            return -1;
        }
        else if(ret == 7){
            return retorno;
        }
        else {
            return -1;
        }
    }
    @Override
    public Object visitMultiplicationFactorr(PParser.MultiplicationFactorrContext ctx) {
        if(ctx.elementExpression().size() == 0){
            for (int i=0; i <= ctx.elementExpression().size()-1; i++)
            {
                int ret = (int)visit(ctx.elementExpression(i));
                if(ret != 1){
                    Main.info.mostarErrorConsola("Se esperaba un entero");
                    return -1;
                }
            }
            return 1;
        }
        return 7;
    }

    @Override
    public Object visitElementExpressions(PParser.ElementExpressionsContext ctx) {
        int retorno = (int)visit(ctx.primitiveExpression());
        int ret = (int)visit(ctx.elementAccess());

        if (retorno == -1){
            return -1;
        }
        else if(ret != 7){
            if(retorno == 5){
                return 5;//Retornar tipo subindice
            }
            return -1;
        }
        else if(retorno == 1){
            return 1;
        }
        else if(retorno == 2){
            return 2;
        }
        else if(retorno == 3){
            return 3;
        }
        else if(retorno == 5){
            return 5;
        }
        else {
            return 4;
        }
    }

    @Override
    public Object visitElementAccesss(PParser.ElementAccesssContext ctx) {
        if(ctx.expression().size() > 0){
            int retorno = (int)visit(ctx.expression(0));
            if(retorno == -1){
                return -1;
            }
            else if (retorno == 1){
                return 1;
            }
            return -1;
        }
        return 7;
    }

    @Override
    public Object visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx) {
        visit(ctx.expressionList());
        TablaSimbolos.Ident simbolo = tablaSimbolos.buscar(ctx.IDENTIFIER().getSymbol().getText());
        if(simbolo == null){
            Main.info.mostarErrorConsola("No existe esta función");
            return -1;
        }
        return simbolo.tok.getType();
    }
    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        visit(ctx.expression());
        visit(ctx.moreExpressions());

        return null;
    }
    @Override
    public Object visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx) {
        return null;
    }

    @Override
    public Object visitMoreExpressionss(PParser.MoreExpressionssContext ctx) {
        for (int i=0; i <= ctx.expression().size()-1; i++)
        {
            visit(ctx.expression(i));
        }
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionInt(PParser.PrimitiveExpressionIntContext ctx) {
        return 1;
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        return 2;
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        TablaSimbolos.Ident simbolo = tablaSimbolos.buscar(ctx.IDENTIFIER().getSymbol().getText());
        if(simbolo == null){
            Main.info.mostarErrorConsola("No existe esta función");
            return -1;
        }
        return simbolo.tok.getType();
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        return 3;
    }

    @Override
    public Object visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx) {
        int retorno = (int)visit(ctx.expression());
        if (retorno == -1)
            return -1;
        return retorno;
    }

    @Override
    public Object visitPrimitiveExpressionListEspresion(PParser.PrimitiveExpressionListEspresionContext ctx) {
        //falta recuperar contenido de lista
        return 5;
    }

    @Override
    public Object visitPrimitiveExpressionLENPIZQPDER(PParser.PrimitiveExpressionLENPIZQPDERContext ctx) {
        int retorno = (int)visit(ctx.expression());
        if (retorno == -1){
            return -1;
        }
        else if (retorno == 2){
            return 1;
        }
        else if (retorno == 5){
            return 1;
        }
        else {
            Main.info.mostarErrorConsola("No se aceptan CHAR ni ENTEROS");
            return -1;
        }
    }

    @Override
    public Object visitPrimitiveExpressionfunctionCallExpression(PParser.PrimitiveExpressionfunctionCallExpressionContext ctx) {
        int retorno = (int)visit(ctx.functionCallExpression());
        if (retorno == -1){
            return -1;
        }
        return retorno;
    }

    @Override
    public Object visitListExpressionn(PParser.ListExpressionnContext ctx) {
        int retorno = (int)visit(ctx.expressionList());
        if (retorno == -1){
            return -1;
        }
        return retorno;
    }
}
