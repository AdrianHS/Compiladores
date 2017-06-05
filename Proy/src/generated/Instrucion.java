package generated;

/**
 * Created by ADRIAN on 25/5/2017.
 */
public class Instrucion {
    String codigo;
    int linea;
    String parametro;

    public Instrucion(String codigo, int linea) {
        this.codigo = codigo;
        this.linea = linea;
    }

    public Instrucion(String codigo, int linea, String parametro) {
        this.codigo = codigo;
        this.linea = linea;
        this.parametro = parametro;
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

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    public void imprimir(){
        System.out.println(linea + " " + codigo + " ");
    }
}
