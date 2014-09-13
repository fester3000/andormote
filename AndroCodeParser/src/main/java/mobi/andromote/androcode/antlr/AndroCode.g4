grammar AndroCode;

@header {
    package mobi.andromote.androcode.antlr;
    import mobi.andromote.androcode.antlr.enums.Type;
}

/* regu³y analizatora sk³adniowego */
script          // podstawowa produkcja skryptu w jêzyku AndroCode
    : lib_includes* body EOF;         
lib_includes    // deklaracja u¿ycia biblioteki zewnêtrznej
    : 'use' LIBNAME ';';        
body            // cia³o skryptu
    : function* statement* function*;  
function        // deklaracja funkcji, parametry opcjonalne
    : type ID LP parameters? RP block;
parameters      //lista parametrów, przynajmniej jeden
    : parameter (',' parameter)*; 
parameter       //para typ - identyfikator parametru
    : type ID ; 
block           // zakres = lista instrukcji zawarta w nawiasach klamrowych
    : '{' statement* '}' ; 
statement       // lista produkcji stanowi¹cych instrukcje jêzyka AC
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
expr 			        //lista produkcji stanowi¹cych wyra¿enie w jêzyku AC
    : var_or_function_call                 #expr_var_or_fcall // wywo³anie zmiennej lub funkcji
    | '(' subExpr=expr ')'                 #expr_parenthesis // wyra¿enie w nawiasie
    | MINUS_OP subExpr=expr                #expr_uminus	// operator negacji arytmetycznej
    | a=expr op=(MULT_OP | DEV_OP) b=expr  #expr_binop // mno¿enie i dzielenie arytmetyczne
    | a=expr op=(ADD_OP | MINUS_OP) b=expr #expr_binop // dodawanie i odejmowanie arytmetyczne
    | LP type RP expr                      #expr_cast // rzutowanie - jawna konwersja
    | id=ID op=(INCR_OP | DECR_OP)         #expr_incr_decr //operator (in/de)krementacji
    | dev_operation                        #expr_dev	// dzia³ania zwi¹zane z funkcjami robota
    | v=value                              #expr_value	// wartoœæ typu prostego
    ;
var_or_val  // wartoœæ typu prostego lub wywo³anie zmiennej lub funkcji
    : var_or_function_call
    | value
    ;    
mixed_string // operator konkatenacji ci¹gów znaków
    : var_or_val (ADD_OP var_or_val)*
    ;
sleep // usypianie w¹tku g³ównego
    : 'sleep' LP INT RP ';'
    ;
print // drukowanie wartoœci na ekran
    : 'print' LP mixed_string RP ';'
    ;
return_statement // zwracanie wartoœci z funkcji
    : 'return' expr? ';' 
    ;
var_declaration // deklaracja zmiennej z opcjonalnym przypisaniem wartoœci
    : type ID ('=' expr)?;
assignment // przypisanie wartoœci
    : a=ID '=' b=expr;
var_or_function_call //wo³anie zmiennej lub funkcji
    : ID                    #var_call
    | ID LP arguments? RP   #function_call
    ;
arguments // argumenty wywo³ania funkcji
    : expr (',' expr)* ;
condition  // warunki logiczne ró¿nego typu
    : '!' LP var_or_function_call RP                        #condition_var_negated
    | '!' var_or_function_call                              #condition_var_negated
    | '!' LP condition RP                                   #condition_negated
    | a=expr op=( EQ_OP | NOT_EQ_OP ) b=expr                #condition_equality
    | a=expr op=( GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) b=expr #condition_relational
    | condition logical_op condition (logical_op condition)* #condition_combined
    | LP condition RP                                       #condition_parenthesis
    ;
for_loop  //pêtla for
    : 'for' LP assignment ';' condition ';' (newValExpr=expr | newValAssign=assignment) RP block ;
while_loop //pêtla while 
    : 'while' LP condition (',' INT)? RP block ;
if_condition //warunek if z mo¿liwoœci¹ dodawania alternatyw warunkowych
    : 'if' LP condition RP block ('elseif' LP condition RP block)* ('else' elseBlock=block)?;
dev_operation //dzia³anie na funkcjach robota
    : ID'.' 'setParam' LP STRING ',' mixed_string RP	#dev_setParam //ustawianie parametrów
    | ID'.' 'getFeature' LP STRING RP			#dev_get //pobieranie/rezerwowanie funkcji
    | ID'.' 'exec' LP (STRING ',' mixed_string (',' INT)?)? RP  #dev_exec //uruchamianie funkcji
    | ID'.' 'release' LP RP                             #dev_release //zwalnianie funkcji
    ;
value //wartoœci typów prostych     
    : CHAR          // wartoœæ znakowa
    | INT           // wartoœæ ca³kowita dodatnia
    | NEGATED_INT   // wartoœæ ca³kowita ujemna
    | FLOAT         // wartoœæ zmiennoprzecinkowa
    | NEGATED_FLOAT // wartoœæ zmiennoprzecinkowa ujemna
    | STRING        // ci¹g znaków
    | BOOLEAN       // wartoœæ logiczna
    | NULL          // brak wartoœci 
    ;
type //podstawowe typy zmiennych      
    : K_INT_TYPE    // typ wartoœci ca³kowitej
    | K_VOID_TYPE   // pusty typ danych
    | K_FLOAT_TYPE  // typ wartoœci zmiennoprzecinkowej
    | K_CHAR_TYPE   // typ znakowy
    | K_STRING_TYPE // typ ci¹gu znaków
    | K_BOOLEAN_TYPE// typ wartoœci logicznych algebry Boole'a
    | K_DEV_TYPE    // typ - funkcja robota
    ;
logical_op // operatory logiczne
    : AND_OP
    | OR_OP
    ;

/* regu³y analizatora leksykalnego */
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