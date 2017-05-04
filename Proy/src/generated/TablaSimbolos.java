package generated;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

/**
 * Created by ADRIAN on 27/4/2017.
 */
public class TablaSimbolos {
    LinkedList<Object> tabla;
    class Ident{
        Token tok;
        ParserRuleContext decl;

        public Ident(Token t, ParserRuleContext d){
            tok = t;
            decl = d;
        }
    }
    public TablaSimbolos()
    {
        tabla = new LinkedList<Object>();
    }
    public void insertar(String nombre, int tipo, ParserRuleContext declaracion)
    {
        Token token = new CommonToken(tipo,nombre);
        Ident i = new Ident(token,declaracion);
        tabla.add(i);
    }
    public Ident buscar(String nombre)
    {
        Ident temp=null;
        for(Object id : tabla)
            if (((Ident)id).tok.getText().equals(nombre))
                temp = ((Ident)id);
        return temp;
    }
    public void imprimir() {
        for (int i = 0; i < tabla.size(); i++) {
            Token s = (Token) ((Ident) tabla.get(i)).tok;
            System.out.println("Nombre: " + s.getText());
            if (s.getType() == 2) System.out.println("\tTipo: Indefinido");
            else if (s.getType() == 0) System.out.println("\tTipo: Integer\n");
            else if (s.getType() == 1) System.out.println("\tTipo: String\n");
        }
    }
}
