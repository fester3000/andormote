grammar AndroCode;

@header {
    package pl.fester3k.antlr.grammar;
}

script      : lib_includes* body EOF;         // overal script structure constists of optional header (with library declaration etc) and body (instructions for platform)
lib_includes: 'use' LIBNAME ';';        // library declaration is followed by "use" keyword
body        : 'begin' function* main_function function* 'end';  // expression list has to contain at least main function and has to be enclosed between "begin" and "end" keywords

main_function: 'int' 'main' LP RP block; // main script function with no parameters. "int" type and "main" name are obligatory
function    : (type | 'void') ID LP parameters? RP block;
parameters  : parameter (',' parameter)*;
parameter   : type ID ;

var_declaration: type ID ('=' (math_operation | value))?;
var_attribution: ID '=' (math_operation | value);
math_operation: (INT | ID) (ADD_OP | SUBST_OP) (INT | ID)
              | (INT | ID) (MULT_OP | DEV_OP) (INT | ID)
              | ID'++'
              | ID'--'
              ;

block       : '{' line* ('return' (ID | value)';')? '}' ;

line        : (var_declaration
              | var_attribution
              | math_operation
              | exec_function
              | dev_operation) ';'
            | for_loop
            | while_loop
            | if_condition
            ;

exec_function: ID LP arguments? RP ;
dev_operation: ID'.'( 'setParam' LP STRING ',' (ID | value) RP
                    | 'getDevice' LP STRING RP
                    )
             ;

arguments   : (ID | value) (',' (ID | value))* ;
for_loop    : 'for' LP var_attribution ';' condition ';' math_operation RP block ;
while_loop  : 'while' LP condition RP block ;
if_condition: 'if' LP condition RP block ;
condition   : (INT | ID) (EQ_OP | NOT_EQ_OP | GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) (INT | ID) ;

value       : INT 
            | STRING  
            ;

type        : 'int'
            | 'float'
            | 'String'
            | 'bool'
            | 'dev'
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

INT         : DIGIT+;
STRING      : '"' (ESC | . )*? '"' ;

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