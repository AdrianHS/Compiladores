package generated;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADRIAN on 4/5/2017.
 */
public class Scope {
    String nombre;
    List<Datos> datos;

    public class Datos{
        private String nombre;
        private int tipo;
        private ParserRuleContext decl;

        private Object[] parametros;
        private int[] tipos;
        private boolean funcion;

        //Declaraciones normales
        public Datos(Token t, ParserRuleContext decl) {
            this.nombre = t.getText();
            this.tipo = t.getType();
            this.decl = decl;
            this.funcion = false;
        }

        //Para funciones
        public Datos(Token t, ParserRuleContext decl, Object[] parametros, int[] tipos, int tipo) {
            this.nombre = t.getText();
            this.tipo = tipo;
            this.decl = decl;
            this.parametros = parametros;
            this.tipos = tipos;
            this.funcion = true;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        public ParserRuleContext getDecl() {
            return decl;
        }

        public void setDecl(ParserRuleContext decl) {
            this.decl = decl;
        }

        public Object[] getParametros() {
            return parametros;
        }

        public void setParametros(Object[] parametros) {
            this.parametros = parametros;
        }

        public int[] getTipos() {
            return tipos;
        }

        public void setTipos(int[] tipos) {
            this.tipos = tipos;
        }

        public boolean isFuncion() {
            return funcion;
        }

        public void setFuncion(boolean funcion) {
            this.funcion = funcion;
        }

        private String parametrosStr(){
            String p = "";
            for(Object obj : parametros){
                p += obj.toString() + ",";
            }
            return p;
        }

        private String tiposStr(){
            String p = "";
            for(int t : tipos){
                p += TablaSimbolos2._SYMBOLIC_NAMES[t] + ",";
            }
            return p;
        }

        public void info(){
            if(funcion){
                System.out.println("\tFuncion: " + getNombre() + "→" + String.valueOf(getTipo()) +
                        "\n\t"+parametrosStr()+"\n"+tiposStr());
            }
            else {
                System.out.println("\t" + getNombre() + "→" + String.valueOf(getTipo()));
            }
        }
    }

    public Scope(String nombre) {
        this.nombre = nombre;
        this.datos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void insertar(Token t, ParserRuleContext decl){
        Datos d = new Datos(t,decl);
        datos.add(d);
    }

    public void insertarF(Token t, ParserRuleContext decl, Object[] param, int [] tipos, int tipo){
        Datos d = new Datos(t,decl,param,tipos,tipo);
        datos.add(d);
    }

    public Datos buscarDato(String nombre){
        for(Datos d: datos){
            if(d.getNombre().equals(nombre)){
                return d;
            }
        }
        return null;
    }


    public void imprimirScope(){
        System.out.println(this.nombre);
        for(Datos d : datos){
            d.info();
        }
    }




}

