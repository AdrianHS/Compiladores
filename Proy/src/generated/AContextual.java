package generated;

import com.company.Main;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import sun.security.krb5.KrbException;

/**
 * Created by ADRIAN on 27/4/2017.
 */
public class AContextual extends PParserBaseVisitor {
    private TablaSimbolos2 tablaSimbolos;

    public AContextual(){
        tablaSimbolos = new TablaSimbolos2();
    }

    @Override
    public Object visitProgrm(PParser.ProgrmContext ctx) {
        visit(ctx.statement(0));
        for (int i = 1; i <= ctx.statement().size() - 1; i++) {
            visit(ctx.statement(i));
        }
        tablaSimbolos.ultimoElemento().imprimirScope();
        return null;
    }
    @Override
    public Object visitDefStatmm(PParser.DefStatmmContext ctx) {
        visit(ctx.defStatement());
        return 7;
    }
    @Override
    public Object visitIfStatmm(PParser.IfStatmmContext ctx) {
        visit(ctx.ifStatement());
        return 7;
    }
    @Override
    public Object visitRetunStatmm(PParser.RetunStatmmContext ctx) {
        int retorno = (int)visit(ctx.returnStatement());
        return retorno;
    }
    @Override
    public Object visitPrintStatmm(PParser.PrintStatmmContext ctx) {
        visit(ctx.printStatement());
        return 7;
    }
    @Override
    public Object visitWhileStatmm(PParser.WhileStatmmContext ctx) {
        visit(ctx.whileStatement());
        return 7;
    }

    @Override
    public Object visitAssignStatmm(PParser.AssignStatmmContext ctx) {
        visit(ctx.assignStatement());
        return 7;
    }

    @Override
    public Object visitFunctionStatmm(PParser.FunctionStatmmContext ctx) {
        visit(ctx.functionCallStatement());
        return 7;
    }

    //Para encontrar los tipos de los parametros de los Def
    private int [] getTipos(Object [] parametros){
        int [] tipos = new int [parametros.length];

        //Se buscan los parametros en el scope de la funcion, en el momento en que se llama este metodo solo se
        //encuentran los parametros en la tabla de este scope
        for(int i = 0; i < parametros.length; i++){
            Scope.Datos id = tablaSimbolos.ultimoElemento().buscarDato(parametros[i].toString());
            tipos[i] = id.getTipo();
        }
        return tipos;
    }

    @Override
    public Object visitDefStatm(PParser.DefStatmContext ctx) {
        tablaSimbolos.abrirScope("DefStatm: " + ctx.IDENTIFIER().getText());
        Object [] parametros = (Object[])visit(ctx.argList());
        int [] tipos = getTipos(parametros);
        Object sec = visit(ctx.sequence());

        int tipo = (int)sec;

        tablaSimbolos.ultimoElemento().imprimirScope();

        tablaSimbolos.cerrarScope();

        //Se agrega la funcion al scope actual
        tablaSimbolos.ultimoElemento().insertarF(ctx.IDENTIFIER().getSymbol(), ctx, parametros, tipos, tipo);

        return null;
    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {

        Scope scopeActual = tablaSimbolos.ultimoElemento();

        Scope.Datos d = tablaSimbolos.buscar(ctx.IDENTIFIER().getText());

        //ToDo: verificar esto ↓
        if(d != null){
            ParserRuleContext ctx2 = d.getDecl();
            scopeActual.insertar(new CommonToken(d.getTipo(), d.getNombre()), ctx);
        }
        else {
            Main.info.mostarErrorConsola("Parametros sin tipo\n");
            System.err.println("Parametros sin tipo");
            return null;
        }
        Object [] parametros = (Object[]) visit(ctx.moreArgs());
        parametros[0] = ctx.IDENTIFIER().getText();
        return parametros;
    }


    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
        return 7;//new Object[0];
    }



    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {
        Scope scopeActual = tablaSimbolos.ultimoElemento();
        Object [] params = new Object[ctx.IDENTIFIER().size() + 1];
        int i = 1;


        for(TerminalNode node : ctx.IDENTIFIER()){
            Scope.Datos d = tablaSimbolos.buscar(node.getText());
            if(d != null){
                String nombre = d.getNombre();
                int tipo = d.getTipo();
                ParserRuleContext cntx = d.getDecl();
                scopeActual.insertar(new CommonToken(tipo, nombre), ctx);
            }
            else {
                Main.info.mostarErrorConsola("Parametros sin tipo\n");
                System.err.println("Parametros sin tipo.");
                return null;
            }
            params[i] = node.getText();
            i++;
        }

        return params;
        //return 7;
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        //abrir scope
        tablaSimbolos.abrirScope("IfStatm: ");
        Object cond = visit(ctx.expression());
        if(cond == null){
            Main.info.mostarErrorConsola("Condicion invalida\n");
            System.err.println("Condición invalida.");
        }

        visit(ctx.sequence(0));
        visit(ctx.sequence(1));

        tablaSimbolos.ultimoElemento().imprimirScope();

        //cierra scope
        tablaSimbolos.cerrarScope();


        return null;
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {
        //abrir scope
        tablaSimbolos.abrirScope("WhileStatm: ");

        visit(ctx.expression());
        visit(ctx.sequence());
        //cierra scope

        tablaSimbolos.ultimoElemento().imprimirScope();
        tablaSimbolos.cerrarScope();


        return null;
    }

    @Override
    public Object visitRetunStatm(PParser.RetunStatmContext ctx) {
        int retorno = (int)visit(ctx.expression());
        return retorno;//falta assinar tipo retorno funcion
    }

    //Todo: revisar
    @Override
    public Object visitPrintStatm(PParser.PrintStatmContext ctx) {
        visit(ctx.expression());
        return 7;
    }

    @Override
    public Object visitAssignStatm(PParser.AssignStatmContext ctx) {
        Object tipo = visit(ctx.expression());
        if(tipo != null) {
            String id = ctx.IDENTIFIER().getText();
            Scope.Datos identificador = tablaSimbolos.ultimoElemento().buscarDato(id);
            if (identificador == null) {
                tablaSimbolos.ultimoElemento().insertar(new CommonToken((int)tipo, id), ctx);
            }
            else {
                if (identificador.getTipo() != (int)tipo) {
                    Main.info.mostarErrorConsola("Tipos incompatibles\n");
                    System.err.println("Tipos incompatibles");
                }
            }
        }
        return null;
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        //int retorno = (int)visit(ctx.primitiveExpression());
        visit(ctx.primitiveExpression());
        visit(ctx.expressionList());

        return null;
    }

    @Override
    public Object visitSequencee(PParser.SequenceeContext ctx) {
        visit(ctx.moreStatement());
        return null;
    }//no


    //Todo revisar
    @Override
    public Object visitMoreStatementt(PParser.MoreStatementtContext ctx) {
        visit(ctx.statement(0));
        for (int i=1; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }
        return null;//retornar null?
    }



    @Override
    public Object visitExpressionn(PParser.ExpressionnContext ctx) {
        Object retorno = null;
        Object expr = visit(ctx.additionExpression());
        Object comp = visit(ctx.comparison());
        if(comp != null){
            if((int)comp == -1){ //-1 = Error
                return null;
            }
            if((int)comp != PParser.INTEGER && (int)comp != PParser.CHAR && (int)expr != PParser.INTEGER && (int)expr != PParser.CHAR){
                System.err.println("Tipos incompatibles");
                Main.info.mostarErrorConsola("Tipos incompatibles\n");
            }
            else if((int)comp != (int)expr){
                System.err.println("Tipos incompatibles");
                Main.info.mostarErrorConsola("Tipos incompatibles\n");
            }
        }
        else {
            retorno = expr;
        }
        return retorno;

        /*
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

        */
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
                Main.info.mostarErrorConsola("Tipos incompatibles\n");

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
            Main.info.mostarErrorConsola("Se esperaba un entero\n");
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
                    Main.info.mostarErrorConsola("Tipos incompatibles\n");
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
            Main.info.mostarErrorConsola("Se esperaba un entero\n");
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
                    Main.info.mostarErrorConsola("Se esperaba un entero\n");
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
            return -1; //error
        }
        else if(ret != 7){
            if(retorno == 5){
                return 5;//Retornar tipo subindice
            }
            return -1;
        }
        else if(retorno == 1){
            return 1;  //int
        }
        else if(retorno == 2){
            return 2; //string
        }
        else if(retorno == 3){
            return 3; //char
        }
        else if(retorno == 5){
            return 5; //lista
        }
        else {
            return 4; //indefinido
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
        return null;
    }
    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        visit(ctx.expression());
        visit(ctx.moreExpressions());

        return null;//no
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
        return null;//no
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
        Token token = null;
        Scope.Datos d = tablaSimbolos.buscar(ctx.IDENTIFIER().getText());
        if(d == null){
            System.err.println("Valor no declarado");
            Main.info.mostarErrorConsola("Valor no declarado\n");
        }
        else {
            token = new CommonToken(d.getTipo(), ctx.IDENTIFIER().getText());
        }

        return token;
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
            Main.info.mostarErrorConsola("No se aceptan CHAR ni ENTEROS\n");
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
