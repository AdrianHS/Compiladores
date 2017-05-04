parser grammar PParser;
options{
    tokenVocab = PScanner;
}
//Aquí vamos a tener las reglas y cada una con sus respectivos nombres
program
    : statement (statement)* EOF                                #progrm
    ;
statement
    : defStatement                                              #defStatmm
    | ifStatement                                               #ifStatmm
    | returnStatement                                           #retunStatmm
    | printStatement                                            #printStatmm
    | whileStatement                                            #whileStatmm
    | assignStatement                                           #assignStatmm
    | functionCallStatement                                     #functionStatmm
    ;
defStatement
    : DEF IDENTIFIER PIZQ argList PDER DOSPUNTOS sequence       #defStatm
    ;
argList
    : IDENTIFIER moreArgs                                       #argListt
    |                                                           #argVacio
    ;
moreArgs
    : (COMA IDENTIFIER)*                                        #moreArgss
    ;
ifStatement
    : IF expression DOSPUNTOS sequence ELSE DOSPUNTOS sequence  #ifStatm
    ;
whileStatement
    : WHILE expression DOSPUNTOS sequence                       #whileStatm
    ;
returnStatement
    : RETURN expression NEWLINE                                 #retunStatm
    ;
printStatement
    : PRINT PIZQ expression PDER NEWLINE                        #printStatm
    ;
assignStatement
    : IDENTIFIER ASIGN expression NEWLINE                       #assignStatm
    ;
functionCallStatement
    : primitiveExpression PIZQ expressionList PDER NEWLINE               #functionStatm
    ;
sequence
    : INDENT moreStatement DEDENT                               #sequencee
    ;
moreStatement
    : statement (statement)*                                    #moreStatementt
    ;
expression
    : additionExpression comparison                             #expressionn
    ;
comparison
    : (comparisonElement additionExpression)*                   #comparisonn
    ;
comparisonElement
    :MENOR                                                      #comparisonElementMenor
    |MAYOR                                                      #comparisonElementMayor
    |MENORIGUAL                                                 #comparisonElementMenorIgual
    |MAYORIGUAL                                                 #comparisonElementMayorIgual
    |COMPARACION                                                #comparisonElementComparacion
    ;
additionExpression
    : multiplicationExpression additionFactor                   #additionExpressionn
    ;
additionFactor
: (additionElement multiplicationExpression)*                   #additionFactorr
    ;
additionElement
    :SUMA                                                       #additionElementSuma
    |RESTA                                                      #additionElementResta
    ;
multiplicationExpression
    : elementExpression multiplicationFactor                    #multiplicationExpressionn
    ;
multiplicationFactor
    : ( multiplicationElement elementExpression)*               #multiplicationFactorr
    ;
multiplicationElement
    :MUL                                                        #multiplicationElementMul
    |DIV                                                        #multiplicationElementDiv
    ;
elementExpression
    : primitiveExpression elementAccess                         #elementExpressions
    ;
elementAccess
    : (PCIZQ expression PCDER)*                                 #elementAccesss
    ;
functionCallExpression
    : IDENTIFIER PIZQ expressionList PDER                       #functionCallStatementt
    ;
expressionList
    : expression moreExpressions                                #elementExpressionn
    |                                                           #elementExpressionVacio
    ;
moreExpressions
    : (COMA expression)*                                        #moreExpressionss
    ;
primitiveExpression
    : INTEGER                                                   #primitiveExpressionInt
    | STRING                                                    #primitiveExpressionStr
    | IDENTIFIER                                                #primitiveExpressionIndetifier
    | CHAR                                                      #primitiveExpressionChar
    | PIZQ expression PDER                                      #primitiveExpressionPIZQPDE
    | listExpression                                            #primitiveExpressionListEspresion
    | LEN PIZQ expression PDER                                  #primitiveExpressionLENPIZQPDER
    | functionCallExpression                                    #primitiveExpressionfunctionCallExpression
    ;
listExpression
    : PCIZQ expressionList PCDER                                #listExpressionn
    ;