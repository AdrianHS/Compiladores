package com.Exceptions;

import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;

/**
 * Created by ADRIAN on 27/3/2017.
 */
//Extendemos de la clase DefaultErrorStrategy para poner los errores en español
public class ErroresEspañol extends DefaultErrorStrategy{
    @Override
    protected void reportNoViableAlternative(Parser recognizer,
                                             NoViableAltException e)
    {
        TokenStream tokens = recognizer.getInputStream();
        String input;
        if ( tokens!=null ) {
            if ( e.getStartToken().getType()==Token.EOF ) input = "<EOF>";
            else input = tokens.getText(e.getStartToken(), e.getOffendingToken());
        }
        else {
            input = "<Entrada desconocida>";
        }
        String msg = "No hay alternativa viable en la entrada "+escapeWSAndQuote(input);
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }
    @Override
    protected void reportInputMismatch(Parser recognizer,
                                       InputMismatchException e)
    {
        String msg = "No concuerda la entrada "+getTokenErrorDisplay(e.getOffendingToken())+
                " se esperaba "+e.getExpectedTokens().toString(recognizer.getVocabulary());
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }
    @Override
    protected void reportFailedPredicate(Parser recognizer, org.antlr.v4.runtime.FailedPredicateException e){
        if (inErrorRecoveryMode(recognizer))
        {
            return;
        }
        beginErrorCondition(recognizer);
        Token to = recognizer.getCurrentToken();
        IntervalSet expecting = getExpectedTokens(recognizer);
        String msg = "Se esperaba " + expecting.toString(recognizer.getTokenNames()) + " y en su lugar se encontró " + getTokenErrorDisplay(to);
        recognizer.notifyErrorListeners(to,msg,null);
    }
    @Override
    protected void reportUnwantedToken(Parser recognizer) {
        if (inErrorRecoveryMode(recognizer)) {
            return;
        }
        beginErrorCondition(recognizer);
        Token t = recognizer.getCurrentToken();
        String tokenName = getTokenErrorDisplay(t);
        IntervalSet expecting = getExpectedTokens(recognizer);
        String msg = "Entrada incorrecta "+tokenName+" se esperaba "+
                expecting.toString(recognizer.getVocabulary());
        recognizer.notifyErrorListeners(t, msg, null);
    }
    @Override
    protected void reportMissingToken(Parser recognizer) {
        if (inErrorRecoveryMode(recognizer)) {
            return;
        }
        beginErrorCondition(recognizer);
        Token t = recognizer.getCurrentToken();
        IntervalSet expecting = getExpectedTokens(recognizer);
        String msg = "falta "+expecting.toString(recognizer.getVocabulary())+
                " en "+getTokenErrorDisplay(t);
        recognizer.notifyErrorListeners(t, msg, null);
    }

    @Override
    protected Token getMissingSymbol(Parser recognizer) {
        Token currentSymbol = recognizer.getCurrentToken();
        IntervalSet expecting = getExpectedTokens(recognizer);
        int expectedTokenType = expecting.getMinElement(); // get any element
        String tokenText;
        if ( expectedTokenType== Token.EOF ) tokenText = "<falta EOF>";
        else tokenText = "<falta "+recognizer.getVocabulary().getDisplayName(expectedTokenType)+">";
        Token current = currentSymbol;
        Token lookback = recognizer.getInputStream().LT(-1);
        if ( current.getType() == Token.EOF && lookback!=null ) {
            current = lookback;
        }
        return
                recognizer.getTokenFactory().create(new Pair<TokenSource, CharStream>(current.getTokenSource(), current.getTokenSource().getInputStream()), expectedTokenType, tokenText,
                        Token.DEFAULT_CHANNEL,
                        -1, -1,
                        current.getLine(), current.getCharPositionInLine());
    }
}
