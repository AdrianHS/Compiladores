package generated;


/**
 * Created by ADRIAN on 25/5/2017.
 */
public class Instruccion {
    //Guarda el codigo de la instruccion
    String codigo;
    //Guarla le linea de codigo
    int linea;
    //Para diferenciar las etiquetas de funciones y las lineas de codigo normales
    boolean esFuncion;
    //Para guardar los parametros de las etiquetas
    String parametros;


    //Constructor para las lineas de codigo
    public Instruccion(String codigo, int linea) {
        this.codigo = codigo;
        this.linea = linea;
    }

    //Constructor para las etiquetas de las funciones
    public Instruccion(String codigo, String parametros) {
        this.codigo = codigo;
        this.parametros = parametros;
        this.esFuncion=true;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public boolean getEsFuncion() {
        return esFuncion;
    }


    //Imprimir para las lineas de codigo
    public void imprimir(){
        System.out.println(linea + " " + codigo);
    }

    //Imprimir para las etiquetas de las funciones
    public void imprimir2(){
        System.out.println("\n"+codigo+ "(" + parametros + ")");
    }


    //Debuelve la linea de codigo como un string
    public String getByteCode(){
        if(!esFuncion){
            return (linea + " " + codigo);
        }
        return (codigo+ "(" + parametros + ")");
    }

}
