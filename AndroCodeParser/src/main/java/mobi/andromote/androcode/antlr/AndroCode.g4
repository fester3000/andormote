grammar AndroCode;

@header {
    package mobi.andromote.androcode.antlr;
    import mobi.andromote.androcode.antlr.enums.Type;
}

/* regu�y analizatora sk�adniowego */
script          // podstawowa produkcja skryptu w j�zyku AndroCode
    : lib_includes* body EOF;         
lib_includes    // deklaracja u�ycia biblioteki zewn�trznej
    : 'use' LIBNAME ';';        
body            // cia�o skryptu
    : function* statement* function*;  
function        // deklaracja funkcji, parametry opcjonalne
    : type ID LP parameters? RP block;
parameters      //lista parametr�w, przynajmniej jeden
    : parameter (',' parameter)*; 
parameter       //para typ - identyfikator parametru
    : type ID ; 
block           // zakres = lista instrukcji zawarta w nawiasach klamrowych
    : '{' statement* '}' ; 
statement       // lista produkcji stanowi�cych instrukcje j�zyka AC
    : block                                       
    | expr ';'              
    | var_declaration ';'   
    | assignment ';'        
    | for_loop              
    | while_loop            
    | if_condition          
    | return_statement    
    | sleep
    | print
    ;
expr 			        //lista produkcji stanowi�cych wyra�enie w j�zyku AC
    : var_or_function_call                 #expr_var_or_fcall // wywo�anie zmiennej lub funkcji
    | '(' subExpr=expr ')'                 #expr_parenthesis // wyra�enie w nawiasie
    | MINUS_OP subExpr=expr                #expr_uminus	// operator negacji arytmetycznej
    | a=expr op=(MULT_OP | DEV_OP) b=expr  #expr_binop // mno�enie i dzielenie arytmetyczne
    | a=expr op=(ADD_OP | MINUS_OP) b=expr #expr_binop // dodawanie i odejmowanie arytmetyczne
    | LP type RP expr                      #expr_cast // rzutowanie - jawna konwersja
    | id=ID op=(INCR_OP | DECR_OP)         #expr_incr_decr //operator (in/de)krementacji
    | dev_operation                        #expr_dev	// dzia�ania zwi�zane z funkcjami robota
    | v=value                              #expr_value	// warto�� typu prostego
    ;
var_or_val  // warto�� typu prostego lub wywo�anie zmiennej lub funkcji
    : var_or_function_call
    | value
    ;    
mixed_string // operator konkatenacji ci�g�w znak�w
    : var_or_val (ADD_OP var_or_val)*
    ;
sleep // usypianie w�tku g��wnego
    : 'sleep' LP INT RP ';'
    ;
print // drukowanie warto�ci na ekran
    : 'print' LP mixed_string RP ';'
    ;
return_statement // zwracanie warto�ci z funkcji
    : 'return' expr? ';' 
    ;
var_declaration // deklaracja zmiennej z opcjonalnym przypisaniem warto�ci
    : type ID ('=' expr)?;
assignment // przypisanie warto�ci
    : a=ID '=' b=expr;
var_or_function_call //wo�anie zmiennej lub funkcji
    : ID                    #var_call
    | ID LP arguments? RP   #function_call
    ;
arguments // argumenty wywo�ania funkcji
    : expr (',' expr)* ;
condition  // warunki logiczne r�nego typu
    : '!' LP var_or_function_call RP                        #condition_var_negated
    | '!' var_or_function_call                              #condition_var_negated
    | '!' LP condition RP                                   #condition_negated
    | a=expr op=( EQ_OP | NOT_EQ_OP ) b=expr                #condition_equality
    | a=expr op=( GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) b=expr #condition_relational
    | condition logical_op condition (logical_op condition)* #condition_combined
    | LP condition RP                                       #condition_parenthesis
    ;
for_loop  //p�tla for
    : 'for' LP assignment ';' condition ';' (newValExpr=expr | newValAssign=assignment) RP block ;
while_loop //p�tla while 
    : 'while' LP condition (',' INT)? RP block ;
if_condition //warunek if z mo�liwo�ci� dodawania alternatyw warunkowych
    : 'if' LP condition RP block ('elseif' LP condition RP block)* ('else' elseBlock=block)?;
dev_operation //dzia�anie na funkcjach robota
    : ID'.' 'setParam' LP STRING ',' mixed_string RP	#dev_setParam //ustawianie parametr�w
    | ID'.' 'getFeature' LP STRING RP			#dev_get //pobieranie/rezerwowanie funkcji
    | ID'.' 'exec' LP (STRING ',' mixed_string (',' INT)?)? RP  #dev_exec //uruchamianie funkcji
    | ID'.' 'release' LP RP                             #dev_release //zwalnianie funkcji
    ;
value //warto�ci typ�w prostych     
    : CHAR          // warto�� znakowa
    | INT           // warto�� ca�kowita dodatnia
    | NEGATED_INT   // warto�� ca�kowita ujemna
    | FLOAT         // warto�� zmiennoprzecinkowa
    | NEGATED_FLOAT // warto�� zmiennoprzecinkowa ujemna
    | STRING        // ci�g znak�w
    | BOOLEAN       // warto�� logiczna
    | NULL          // brak warto�ci 
    ;
type //podstawowe typy zmiennych      
    : K_INT_TYPE    // typ warto�ci ca�kowitej
    | K_VOID_TYPE   // pusty typ danych
    | K_FLOAT_TYPE  // typ warto�ci zmiennoprzecinkowej
    | K_CHAR_TYPE   // typ znakowy
    | K_STRING_TYPE // typ ci�gu znak�w
    | K_BOOLEAN_TYPE// typ warto�ci logicznych algebry Boole'a
    | K_DEV_TYPE    // typ - funkcja robota
    ;
logical_op // operatory logiczne
    : AND_OP
    | OR_OP
    ;

/* regu�y analizatora leksykalnego */
K_INT_TYPE  : 'int' ;
K_VOID_TYPE : 'void' ;
K_FLOAT_TYPE: 'float' ;
K_CHAR_TYPE: 'char' ;
K_STRING_TYPE: 'String' ;
K_BOOLEAN_TYPE: 'bool';
K_DEV_TYPE  : 'feature' ;

LIBNAME     : (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')*'.and' ;
ID          : LOWERCASE_LETTER (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')* ;

LP          : '(' ;
RP          : ')' ;
ADD_OP      : '+' ;
MINUS_OP    : '-' ;
MULT_OP     : '*' ;
DEV_OP      : '/' ;
INCR_OP      : '++';
DECR_OP      : '--';

EQ_OP       : '==' ;
NOT_EQ_OP   : '!=' ;
GT_OP       : '>' ;
LT_OP       : '<' ;
GTEQ_OP     : '>=' ;
LTEQ_OP     : '<=' ;

AND_OP      : '&&';
OR_OP       : '||';

CHAR        :   '\'' . '\'' ;

NEGATED_INT : MINUS_OP INT;
INT         : '0' | [1-9] DIGIT* ;

NEGATED_FLOAT : MINUS_OP FLOAT;
FLOAT       :   DIGIT* '.' DIGIT*
            |   '.' DIGIT+
            ;

STRING      : '"' (ESC | . )*? '"' ;
BOOLEAN     : 'TRUE'
            | 'FALSE' ;

NULL        : 'null' ;

fragment 
ESC         : '\\' [btnr"\\] ;
fragment 
LOWERCASE_LETTER : [a-z];
fragment 
UPPERCASE_LETTER : [A-Z];
fragment 
DIGIT       : [0-9];

LINE_COMMENT: '//' .*? '\n' -> skip ; //single line comments marked by "//" prefix
COMMENT     : '/*' .*? '*/' -> skip ; //multi line comments enclosed between "/*" and "*/" tags
WS          : [ \t\r\n]+ -> channel(HIDDEN) ; //whitespaces that should be ignored in parsing process