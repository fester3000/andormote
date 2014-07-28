grammar AndroCode;

@header {
    package pl.fester3k.antlr.androCode;
}


script      : lib_includes* body EOF;         // overal script structure constists of optional header (with library declaration etc) and body (instructions for platform)
lib_includes: 'use' LIBNAME ';';        // library declaration is followed by "use" keyword
body        : 'begin' function* main_function function* 'end';  // expression list has to contain at least main function and has to be enclosed between "begin" and "end" keywords

main_function: K_INT_TYPE 'main' LP RP block; // main script function with no parameters. "int" type and "main" name are obligatory
function    : type ID LP parameters? RP block;
parameters  : parameter (',' parameter)*;
parameter   : type ID ;

block       : '{' statement* '}' ;

statement   : block                 # stat_block
            | expr ';'              # stat_expr
            | var_declaration ';'   # stat_var_declaration
            | assignment ';'        # stat_assignment
            | for_loop              # stat_for_loop
            | while_loop            # stat_while_loop
            | if_condition          # stat_if_condition
            | 'return' expr? ';'    # stat_return
            ;

expr        : function_call
            | '(' expr ')' 
            | '-' expr
            | '!' expr 
            |<assoc=right> expr POWER_OP expr 
            | expr (MULT_OP | DEV_OP) expr
            | expr (ADD_OP | SUBST_OP) expr
            | ID'++'
            | ID'--'
            | dev_operation
            | ID
            | value
            ;

var_declaration: type ID ('=' expr)?;
assignment: expr '=' expr;
function_call: ID LP arguments? RP ;
condition   : expr (EQ_OP | NOT_EQ_OP | GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) expr;

arguments   : expr (',' expr)* ;
for_loop    : 'for' LP assignment ';' condition ';' expr RP block ;
while_loop  : 'while' LP condition RP block ;
if_condition: 'if' LP condition RP block ('elseif' LP condition RP block)* ('else' block)?;

dev_operation: ID'.'( 'setParam' LP STRING ',' expr RP
                    | 'getDevice' LP STRING RP
                    )
             ;

value       : INT       
           // FLOAT
           // CHAR
            | STRING  
            | BOOLEAN
            | NULL
            ;

//K_CHAR 
K_INT_TYPE  : 'int' ;
//K_FLOAT_TYPE: 'float' ;
//K_CHAR_TYPE: 'char' ;
K_STRING_TYPE: 'String' ;
K_VOID_TYPE : 'void' ;
K_BOOLEAN_TYPE: 'bool';
K_DEV_TYPE  : 'device' ;


type        : K_INT_TYPE     
            | K_VOID_TYPE    
            //| K_FLOAT_TYPE   
            //| K_CHAR_TYPE
            | K_STRING_TYPE  
            | K_BOOLEAN_TYPE 
            | K_DEV_TYPE     
            ;

LIBNAME     : (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')*'.and' ;
ID          : LOWERCASE_LETTER+ (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')* ;

LP          : '(' ;
RP          : ')' ;
ADD_OP      : '+' ;
SUBST_OP    : '-' ;
MULT_OP     : '*' ;
DEV_OP      : '/' ;
POWER_OP     : '^' ;

EQ_OP       : '==' ;
NOT_EQ_OP   : '!=' ;
GT_OP       : '>' ;
LT_OP       : '<' ;
GTEQ_OP     : '>=' ;
LTEQ_OP     : '<=' ;

INT         : '0' | [1-9] DIGIT* ;
STRING      : '"' (ESC | . )*? '"' ;
BOOLEAN     : 'true'
            | 'false'
            ;
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
WS          : [ \t\n\r]+ -> skip ; //whitespaces that should be ignored in parsing process