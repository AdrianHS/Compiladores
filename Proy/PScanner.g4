lexer grammar PScanner;
tokens { INDENT, DEDENT }
@lexer::header {
  import com.yuvalshavit.antlr4.DenterHelper;
}
@lexer::members {
  private final DenterHelper denter = DenterHelper.builder()
    .nl(NEWLINE)
    .indent(PParser.INDENT)
    .dedent(PParser.DEDENT)
    .pullToken(PScanner.super::nextToken);

  @Override
  public Token nextToken() {
    return denter.nextToken();
  }

  public void notifyListeners(LexerNoViableAltException e) {
      String text = this._input.getText(Interval.of(this._tokenStartCharIndex, this._input.index()));
      String msg = "Error: No se puede reconocer el token: \'" + this.getErrorDisplay(text)+" en "+
              this._tokenStartLine+" : "+String.valueOf(this._tokenStartCharPositionInLine+1);
      mostarErrorConsola.addElement(msg);
      ANTLRErrorListener listener = this.getErrorListenerDispatch();
      listener.syntaxError(this, (Object)null, this._tokenStartLine, this._tokenStartCharPositionInLine, msg, e);
  }
  public PScanner(CharStream input, TextArea mostarErrorConsola) {
  		super(input);
  		mostarErrorConsola = new TextArea();
  		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  	}
}
NEWLINE: ('\r'? '\n' ('    ' | '\t')*)|('\r\n    ');
// tokens
//Las palabras reservadas que tiene Python
DEF     : 'def';
IF      : 'if';
ELSE    : 'else';
WHILE   : 'while';
RETURN  : 'return';
PRINT   : 'print';
LEN     : 'len';
//Tokens para expresiones regulares
IDENTIFIER  :
    CharInicial CharContenido*;

INTEGER
	:	'0'
	| '1'..'9' ('0'..'9')*
	| '0'..'9'+'.''0'..'9'+
	;
STRING : '"' ('""'|~'"')* '"' ;
CHAR : '\'' ( . | '\\n' | '\\r' | ~'\'' ) '\'';
fragment
CharInicial
    : 'A'..'Z' | 'a'..'z'
    | '\u00C0'..'\u00D6'
    | '\u00D8'..'\u00F6'
    | '\u00F8'..'\u02FF'
    | '\u0370'..'\u037D'
    | '\u037F'..'\u1FFF'
    | '\u200C'..'\u200D'
    | '\u2070'..'\u218F'
    | '\u2C00'..'\u2FEF'
    | '\u3001'..'\uD7FF'
    | '\uF900'..'\uFDCF'
    | '\uFDF0'..'\uFFFD'
    | '_'
    ;
fragment
CharContenido
   : CharInicial
   | '0'..'9'
   | '_'
   | '\u00B7'
   | '\u0300'..'\u036F'
   | '\u203F'..'\u2040'
   ;
//Tokens para simbolos
COMA	:	',' ;
ASIGN	:	'=' ;
PIZQ	:	'(' ;
PDER	:	')' ;
SUMA	:	'+' ;
MUL		:	'*' ;
PCIZQ	:	'[' ;
PCDER	:	']' ;
MENOR   :   '<';
MAYOR   :   '>';
MENORIGUAL : '<=';
MAYORIGUAL : '>=';
COMPARACION : '==';
DOSPUNTOS : ':';
DIV    :   '/';
RESTA   :   '-';
//elementos omitidos
SPECIAL_COMMENT
 : '"""'( SPECIAL_COMMENT | . )*?'"""' -> skip
 ;
COMENTARIO_LINEA: '#'.*?'\r'?'\n' -> skip;
WS:   [ \t\n\r] -> skip ;