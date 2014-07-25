grammar AndroCode;

/*
@header {
    package pl.fester3k.antlr.grammar;
}*/

script      : lib_includes* body EOF;         // overal script structure constists of optional header (with library declaration etc) and body (instructions for platform)
lib_includes: 'use' LIBNAME ';';        // library declaration is followed by "use" keyword
body        : 'begin' function* main_function function* 'end';  // expression list has to contain at least main function and has to be enclosed between "begin" and "end" keywords

main_function: 'int' 'main' LP RP block; // main script function with no parameters. "int" type and "main" name are obligatory
function    : (type | 'void') ID LP parameters? RP block;
parameters  : parameter (',' parameter)*;
parameter   : type ID ;

block       : '{' statement* '}' ;

statement   : block                 # stat_block
            | var_declaration ';'   # stat_var_declaration
            | assignment ';'        # stat_assignment
            | for_loop              # stat_for_loop
            | while_loop            # stat_while_loop
            | if_condition          # stat_if_condition
            | 'return' expr? ';'    # stat_return
            | expr ';'              # stat_expr
            ;

expr        : ID
            | value
            | '-' expr
            | '!' expr 
            | expr '^'<assoc=right> expr 
            | expr (MULT_OP | DEV_OP) expr
            | expr (ADD_OP | SUBST_OP) expr
            | ID'++'
            | ID'--'
            | function_call
            | dev_operation;

var_declaration: type ID ('=' expr)?;
assignment: expr '=' expr;
function_call: ID LP arguments? RP ;

arguments   : expr (',' expr)* ;
for_loop    : 'for' LP assignment ';' condition ';' expr RP block ;
while_loop  : 'while' LP condition RP block ;
if_condition: 'if' LP condition RP block ('elseif' LP condition RP block)* ('else' block)?;
condition   : expr (EQ_OP | NOT_EQ_OP | GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) expr;

dev_operation: ID'.'( 'setParam' LP STRING ',' (ID | value) RP
                    | 'getDevice' LP STRING RP
                    )
             ;

value       : INT 
           // FLOAT
            | STRING  
            | BOOLEAN
            | NULL
            ;

type        : 'int'     # type_int
            | 'float'   # type_float
            | 'String'  # type_String
            | 'bool'    # type_boolean
            | 'dev'     # type_device
            ;

LIBNAME     : (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')*'.and' ;
ID          : LOWERCASE_LETTER+ (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')* ;

LP          : '(' ;
RP          : ')' ;
ADD_OP      : '+' ;
SUBST_OP    : '-' ;
MULT_OP     : '*';
DEV_OP      : '/';

EQ_OP       : '==';
NOT_EQ_OP   : '!=';
GT_OP       : '>';
LT_OP       : '<';
GTEQ_OP     : '>=';
LTEQ_OP     : '<=';

INT         : '0' | [1-9] DIGIT*;
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