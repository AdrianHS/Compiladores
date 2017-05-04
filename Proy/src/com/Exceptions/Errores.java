package com.Exceptions;

import com.company.Main;
import org.antlr.v4.runtime.BaseErrorListener;

/**
 * Created by ADRIAN on 27/3/2017.
 */
//Aquí extendemos de la clase BaseErrorListener para tomar errores
public class Errores extends BaseErrorListener{
    @Override
    public void syntaxError(org.antlr.v4.runtime.Recognizer<?,?> recognizer, java.lang.Object offendingSymbol, int line, int charPositionInLine, java.lang.String msg, org.antlr.v4.runtime.RecognitionException e) {
        Main.info.mostarErrorConsola("Linea: "+line+" columna "+ charPositionInLine+ " " +msg+"\n");
    }
}
