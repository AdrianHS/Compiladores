package generated;

import java.util.Stack;

/**
 * Created by ADRIAN on 4/5/2017.
 */
public class TablaSimbolos2 {

    private Stack<Scope> pila;

    public static final String[] _SYMBOLIC_NAMES = {
            null, "INDENT", "DEDENT", "NEWLINE", "DEF", "IF", "ELSE", "WHILE", "RETURN",
            "PRINT", "LEN", "IDENTIFIER", "INTEGER", "STRING", "CHAR", "COMA", "ASIGN",
            "PIZQ", "PDER", "SUMA", "MUL", "PCIZQ", "PCDER", "MENOR", "MAYOR", "MENORIGUAL",
            "MAYORIGUAL", "COMPARACION", "DOSPUNTOS", "DIV", "RESTA", "SPECIAL_COMMENT",
            "COMENTARIO_LINEA", "WS"
    };

    public TablaSimbolos2() {
        this.pila = new Stack<>();
        this.pila.push(new Scope("Globales"));
    }

    public void abrirScope(String nombre){
        pila.push(new Scope(nombre));
    }

    public void cerrarScope(){
        pila.pop();
    }

    public Scope ultimoElemento(){
        return pila.lastElement();
    }
    public Scope.Datos buscar(String nombre){
        Scope.Datos d = null;
        for (int i=pila.size()-1;i>=0;i--){
            d = pila.get(i).buscarDato(nombre);
            if(d == null){
                break;
            }
        }
        return d;
    }


}
