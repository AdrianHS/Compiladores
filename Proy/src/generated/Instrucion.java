package generated;

/**
 * Created by ADRIAN on 25/5/2017.
 */
public class Instrucion {
    String codigo;
    int linea;

    boolean esIf;
    int saltoLinea;

    boolean esFuncion;
    String[] parametros;


    //Constructor para las lineas de codigo
    public Instrucion(String codigo, int linea) {
        this.codigo = codigo;
        this.linea = linea;
        this.esIf = false;
    }

    //Constructor para las funciones
    public Instrucion(String codigo, String[]parametros) {
        this.codigo = codigo;
        this.linea = linea;
        this.parametros = parametros;
        this.esFuncion=true;
    }

    public Instrucion() {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public boolean isEsIf() {
        return esIf;
    }

    public void setEsIf(boolean esIf) {
        this.esIf = esIf;
    }

    public int getSaltoLinea() {
        return saltoLinea;
    }

    public void setSaltoLinea(int saltoLinea) {
        this.saltoLinea = saltoLinea;
    }

    public boolean isEsFuncion() {
        return esFuncion;
    }

    public void setEsFuncion(boolean esFuncion) {
        this.esFuncion = esFuncion;
    }

    public String[] getParametros() {
        return parametros;
    }

    public void setParametros(String[] parametros) {
        this.parametros = parametros;
    }

    public void imprimir(){
        System.out.println(linea + " " + codigo);
    }
}
