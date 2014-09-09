grammar AndroCode;

@header {
    package pl.fester3k.androcode.antlr;
    import pl.fester3k.androcode.antlr.enums.Type;
}

script          // overal script structure constists of optional header (with library declaration etc) and body (instructions for platform)     
    : lib_includes* body EOF;         
lib_includes    // library declaration is followed by "use" keyword
    : 'use' LIBNAME ';';        
body            // expression list has to contain at least main function and has to be enclosed between "begin" and "end" keywords
    : function* statement* function*;  

function        // typical function of desired type with opional parameters
    : type ID LP parameters? RP block;
parameters      //at least one parameter
    : parameter (',' parameter)*; 
parameter       //pair of type and ID
    : type ID ; 

block           // list of statements
    : '{' statement* '}' ; 

statement       // statement
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

expr 			        //expression
    : var_or_function_call                              #expr_var_or_fcall		// returns: function type    
    | '(' subExpr=expr ')'                              #expr_parenthesis 	// returns: type of expr
    | MINUS_OP subExpr=expr                             #expr_uminus	// returns: type of expr
    | a=expr op=(MULT_OP | DEV_OP) b=expr               #expr_binop // returns: type of Lexpr
    | a=expr op=(ADD_OP | MINUS_OP) b=expr              #expr_binop // returns: type of Lexpr
    | LP type RP expr                                   #expr_cast // returns: type of "type"
    | id=ID op=(INCR_OP | DECR_OP)                      #expr_incr_decr	// returns: int
    | dev_operation                                     #expr_dev	// returns: boolean
    | v=value                                           #expr_value	// returns: value type
    //| var_call                                          #expr_var	// returns: type of var ID
    ;

var_or_val
    : var_or_function_call
    | value
    ;    

mixed_string
    : var_or_val (ADD_OP var_or_val)*
    ;

sleep
    : 'sleep' LP INT RP ';'
    ;

print
    : 'print' LP mixed_string RP ';'
    ;

return_statement 
    : 'return' expr? ';' 
    ;

var_declaration // declaration of variable 'ID' of type 'type' with optional assignment
    : type ID ('=' expr)?;

assignment 
    : a=ID '=' b=expr;

var_or_function_call
    : ID                    #var_call
    | ID LP arguments? RP   #function_call
    ;

condition  // logical condition
    : '!' LP var_or_function_call RP                        #condition_var_negated
    | '!' var_or_function_call                              #condition_var_negated
    | '!' LP condition RP                                   #condition_negated
    | a=expr op=( EQ_OP | NOT_EQ_OP ) b=expr                #condition_equality
    | a=expr op=( GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) b=expr #condition_relational
    | condition logical_op condition (logical_op condition)* #condition_combined
    | LP condition RP                                       #condition_parenthesis
    ;

arguments   
    : expr (',' expr)* ;
for_loop    
    : 'for' LP assignment ';' condition ';' (newValExpr=expr | newValAssign=assignment) RP block ;
while_loop  
    : 'while' LP condition (',' INT)? RP block ;
if_condition
    : 'if' LP condition RP block ('elseif' LP condition RP block)* ('else' elseBlock=block)?;

dev_operation
    : ID'.' 'setParam' LP STRING ',' mixed_string RP	#dev_setParam
    | ID'.' 'getFeature' LP STRING RP			#dev_get
    | ID'.' 'exec' LP (STRING ',' mixed_string (',' INT)?)? RP  #dev_exec
    | ID'.' 'release' LP RP                             #dev_release
    ;
    
value     
    : CHAR           
    | INT
    | NEGATED_INT
    | FLOAT       
    | NEGATED_FLOAT
    | STRING         
    | BOOLEAN        
    | NULL           
    ;

type        
    : K_INT_TYPE     
    | K_VOID_TYPE    
    | K_FLOAT_TYPE   
    | K_CHAR_TYPE
    | K_STRING_TYPE  
    | K_BOOLEAN_TYPE 
    | K_DEV_TYPE     
    ;

logical_op
    : AND_OP
    | OR_OP
    ;

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
FLOAT
            :   DIGIT* '.' DIGIT*
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