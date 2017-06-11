package generated;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADRIAN on 25/5/2017.
 */


public class generadorCodigo extends PParserBaseVisitor {
    //Lista para guardar las instrucciones
    List<Instruccion> listaInstrucciones = new ArrayList<>();
    //Contador para las lineas de codigo
    private int linea =0;
    //Contador para contar argumentos
    private int numeroArgumentos =0;

    //Imprime en consola el codigo
    private void imprimirCodigo(){
        for(Instruccion i : listaInstrucciones){
            if(i.getEsFuncion()){
                i.imprimir2();
            }
            else {
                i.imprimir();
            }
        }
    }

    //Metodo para guardar el codigo generado en un archivo .txt
    private void generarArchivo(){
        ArrayList<String> code = new ArrayList<String>();
        for(Instruccion i : listaInstrucciones){
           code.add(i.getByteCode());
        }
        try{
            Path file = Paths.get("ByteCode.txt");
            Files.write(file, code, Charset.forName("UTF-8"));
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null,"Error en el archivo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public Object visitProgrm(PParser.ProgrmContext ctx) {

        visit(ctx.statement(0));
        for (int i=1; i <= ctx.statement().size()-1; i++)
        {
            visit(ctx.statement(i));
        }

        //Al final de la ejecucion: imprimir y generar codigo.
        imprimirCodigo();
        generarArchivo();

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

        //Retorna los argumentos de la funcion declarada.
        String args= (String)visit(ctx.argList());

        listaInstrucciones.add(new Instruccion(ctx.IDENTIFIER().getSymbol().getText(),args));

        visit(ctx.sequence());
        return null;

    }

    @Override
    public Object visitArgListt(PParser.ArgListtContext ctx) {

        //Si tiene mas argumentos los regresa en un string
        String  moreArgs =(String) visit(ctx.moreArgs());
        //Concatena lo que obtuvo en un solo string
        String args = ctx.IDENTIFIER().getSymbol().getText() + moreArgs;
        return args;
    }

    @Override
    public Object visitArgVacio(PParser.ArgVacioContext ctx) {
        //Cuando no hay argumentos.
        return "";
    }

    @Override
    public Object visitMoreArgss(PParser.MoreArgssContext ctx) {

        //Guarda todos los argumentos en un string, separados por coma.
        String param = new String();
        for (int i = 0; i <ctx.IDENTIFIER().size() ; i++) {
            param+= ", "+ ctx.IDENTIFIER(i).getSymbol().getText();
        }
        return param;
    }

    @Override
    public Object visitIfStatm(PParser.IfStatmContext ctx) {
        visit(ctx.expression());
        listaInstrucciones.add(new Instruccion("JUMP_IF_FAlSE ", linea++));
       //Se guarda la posición de la instrucción anterior
        int x = listaInstrucciones.size()-1;
        //Visita lo que hay dentro del if e incrementa la linea
        visit(ctx.sequence(0));
        //Se sobre escribe la intrucción una vez que ya se sabe cuanto incrementó
        listaInstrucciones.get(x).setCodigo("JUMP_IF_FALSE " + Integer.toString(linea+1));
        //Un Jump para que se salte el else si entró en el if
        listaInstrucciones.add(new Instruccion("JUMP_ABSOLUTE ", linea++));
        //Guardar de nuevo la posición de la insstrucción anterior;
        int y = listaInstrucciones.size()-1;
        visit(ctx.sequence(1));
        //Se reescribe la instrución.
        listaInstrucciones.get(y).setCodigo("JUMP_ABSOLUTE " + Integer.toString(linea));
        return null;
    }

    @Override
    public Object visitWhileStatm(PParser.WhileStatmContext ctx) {

        //Se guarda la posición del inicio del while para poder regresar
        int saltoWhile = linea;
        visit(ctx.expression());

        listaInstrucciones.add(new Instruccion("JUMP_IF_FALSE ", linea++));
        //Guardar posision de la linea anterior.
        int x = listaInstrucciones.size()-1;

        visit(ctx.sequence());
        //Regresar al inicio del while.
        listaInstrucciones.add(new Instruccion("JUMP_ABSOLUTE " +saltoWhile, linea++));
        //Se reescribe la instruccion de salto afuera del while.
        listaInstrucciones.get(x).setCodigo("JUMP_IF_FALSE " + Integer.toString(linea));

        return null;
    }

    @Override
    public Object visitRetunStatm(PParser.RetunStatmContext ctx) {
        visit(ctx.expression());
        listaInstrucciones.add(new Instruccion("RETURN_VALUE ", linea++));
        return null;
    }

    @Override
    public Object visitPrintStatm(PParser.PrintStatmContext ctx) {
        listaInstrucciones.add(new Instruccion("LOAD_GLOBAL " + ctx.PRINT().getSymbol().getText() , linea++));
        visit(ctx.expression());
        listaInstrucciones.add(new Instruccion("CALL_FUNCTION "+String.valueOf(numeroArgumentos), linea++));
        return null;
    }

    @Override
    public Object visitAssignStatm(PParser.AssignStatmContext ctx) {

        visit(ctx.expression());
        String info = ctx.IDENTIFIER().getSymbol().getText();
        listaInstrucciones.add(new Instruccion("STORE_FAST " + info, linea++));
        return null;
    }

    @Override
    public Object visitFunctionStatm(PParser.FunctionStatmContext ctx) {
        visit(ctx.primitiveExpression());

        int y = listaInstrucciones.size()-1;
        String cod= listaInstrucciones.get(y).getCodigo();
        String[] c = cod.split("LOAD_FAST");
        listaInstrucciones.get(y).setCodigo("LOAD_GLOBAL" + c[1]);
        visit(ctx.expressionList());

        listaInstrucciones.add(new Instruccion("CALL_FUNCTION " + numeroArgumentos,linea++));
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

        listaInstrucciones.add(new Instruccion("COMPARE_OP " + "<", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementMayor(PParser.ComparisonElementMayorContext ctx) {

        listaInstrucciones.add(new Instruccion("COMPARE_OP " + ">", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementMenorIgual(PParser.ComparisonElementMenorIgualContext ctx) {
        listaInstrucciones.add(new Instruccion("COMPARE_OP " + "<=", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementMayorIgual(PParser.ComparisonElementMayorIgualContext ctx) {

        listaInstrucciones.add(new Instruccion("COMPARE_OP " + ">=", linea++));
        return null;
    }

    @Override
    public Object visitComparisonElementComparacion(PParser.ComparisonElementComparacionContext ctx) {
        listaInstrucciones.add(new Instruccion("COMPARE_OP " + "==", linea++));
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
        listaInstrucciones.add(new Instruccion("BINARY_ADD ", linea++));
        return null;
    }

    @Override
    public Object visitAdditionElementResta(PParser.AdditionElementRestaContext ctx) {
        listaInstrucciones.add(new Instruccion("BINARY_SUBSTRACT ", linea++));
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
        listaInstrucciones.add(new Instruccion("BINARY_MULTIPLY", linea++));
        return null;
    }

    @Override
    public Object visitMultiplicationElementDiv(PParser.MultiplicationElementDivContext ctx) {
        listaInstrucciones.add(new Instruccion("BINARY_DIVIDE", linea++));
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
            listaInstrucciones.add(new Instruccion("BINARY_SUBSCR",linea++));
        }
        return null;
    }

    @Override
    public Object visitFunctionCallStatementt(PParser.FunctionCallStatementtContext ctx) {
        listaInstrucciones.add(new Instruccion("LOAD_GLOBAL " + ctx.IDENTIFIER().getSymbol().getText(),linea++));
        visit(ctx.expressionList());
        listaInstrucciones.add(new Instruccion("CALL_FUNCTION " + String.valueOf(numeroArgumentos),linea++));

        return null;
    }

    @Override
    public Object visitElementExpressionn(PParser.ElementExpressionnContext ctx) {
        visit(ctx.expression());
        //si tiene un argumento
        numeroArgumentos = 1;
        //Si tienen mas de uno, devuelve cuatos argumentos hay
        numeroArgumentos += (int)visit(ctx.moreExpressions());
        return null;
    }

    @Override
    public Object visitElementExpressionVacio(PParser.ElementExpressionVacioContext ctx) {
        //0 si no tienen argumentos
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
        listaInstrucciones.add(new Instruccion("LOAD_CONST " + ctx.INTEGER(), linea++));
        numeroArgumentos=1;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionStr(PParser.PrimitiveExpressionStrContext ctx) {
        listaInstrucciones.add(new Instruccion("LOAD_CONST " + ctx.STRING(), linea++));
        numeroArgumentos=1;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionIndetifier(PParser.PrimitiveExpressionIndetifierContext ctx) {
        listaInstrucciones.add(new Instruccion("LOAD_FAST " + ctx.IDENTIFIER(), linea++));
        numeroArgumentos=1;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionChar(PParser.PrimitiveExpressionCharContext ctx) {
        listaInstrucciones.add(new Instruccion("LOAD_CONST " + ctx.CHAR(), linea++));
        numeroArgumentos=1;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionPIZQPDE(PParser.PrimitiveExpressionPIZQPDEContext ctx) {
        visit(ctx.expression());
        numeroArgumentos=1;
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionListEspresion(PParser.PrimitiveExpressionListEspresionContext ctx) {
        visit(ctx.listExpression());
        return null;
    }

    @Override
    public Object visitPrimitiveExpressionLENPIZQPDER(PParser.PrimitiveExpressionLENPIZQPDERContext ctx) {
        listaInstrucciones.add(new Instruccion("LOAD_GLOBAL " + ctx.LEN().getSymbol().getText(),linea++));
        visit(ctx.expression());
        listaInstrucciones.add(new Instruccion("CALL_FUNCTION " + numeroArgumentos,linea++));
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
        listaInstrucciones.add(new Instruccion("BUILD_LIST " + String.valueOf(numeroArgumentos) , linea++));

        return null;
    }
}
