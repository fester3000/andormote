grammar AndroCode;

@header {
    package pl.fester3k.antlr.androCode;
    import pl.fester3k.antlr.semanticAnalysis.Type;
}

script          // overal script structure constists of optional header (with library declaration etc) and body (instructions for platform)     
    : lib_includes* body EOF;         
lib_includes    // library declaration is followed by "use" keyword
    : 'use' LIBNAME ';';        
body            // expression list has to contain at least main function and has to be enclosed between "begin" and "end" keywords
    : 'begin' function* main_function function* 'end';  

main_function   // main script function with no parameters. "int" type and "main" name are obligatory
    : K_INT_TYPE 'main' LP RP block; 
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
    ;

expr returns [Type evalType]			        //expression
    : fcal=function_call  	#expr_fcall			// returns: function type    
    | '(' subExpr=expr ')'  #expr_parenthesis 	// returns: type of expr
    | '-' subExpr=expr      #expr_uminus		// returns: type of expr
    | '!' subExpr=expr      #expr_unot			// returns: boolean
    | a=expr op=(MULT_OP | DEV_OP) b=expr   #expr_binop // returns: type of Lexpr
    | a=expr op=(ADD_OP | SUBST_OP) b=expr  #expr_binop // returns: type of Lexpr
    | LP type RP expr           #expr_cast
    | id=ID'++'            	#expr_incr	// returns: int
    | id=ID'--'            	#expr_decr	// returns: int
    | dev_operation        	#expr_dev	// returns: boolean
    | v=value              	#expr_value	// returns: value type
    | ID                   	#expr_var	// returns: type of var ID
    ;

return_statement 
    : 'return' expr? ';' 
    ;

var_declaration returns [Type evalType]// declaration of variable 'ID' of type 'type' with optional assignment
    : type ID ('=' expr)?;
assignment returns [Type evalType]     // assignment
    : a=expr '=' b=expr;
function_call // call of function 'ID' with optional arguments
    : ID LP arguments? RP  
    ;
condition returns [Type evalType]      // logical condition
    : a=expr op=( EQ_OP | NOT_EQ_OP ) b=expr                #condition_equality
    | a=expr op=( GT_OP | LT_OP | GTEQ_OP | LTEQ_OP) b=expr #condition_relational
    ;

arguments   
    : expr (',' expr)* ;
for_loop    
    : 'for' LP assignment ';' condition ';' expr RP block ;
while_loop  
    : 'while' LP condition RP block ;
if_condition
    : 'if' LP condition RP block ('elseif' LP condition RP block)* ('else' block)?;

dev_operation
    : ID'.'( 'setParam' LP STRING ',' expr RP
           | 'getDevice' LP STRING RP)
    ;

value returns [Type evalType]       
    : CHAR     //{$returnType = Type.tCHAR; }      
    | INT       //{$returnType = Type.tINT; }      
    | FLOAT    //{$returnType = Type.tFLOAT; }      
    | STRING    //{$returnType = Type.tSTRING; }      
    | BOOLEAN   //{$returnType = Type.tBOOLEAN; }      
    | NULL      //{$returnType = Type.tNULL; }      
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

K_INT_TYPE  : 'int' ;
K_VOID_TYPE : 'void' ;
K_FLOAT_TYPE: 'float' ;
K_CHAR_TYPE: 'char' ;
K_STRING_TYPE: 'String' ;
K_BOOLEAN_TYPE: 'bool';
K_DEV_TYPE  : 'device' ;


LIBNAME     : (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')*'.and' ;
ID          : LOWERCASE_LETTER+ (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')* ;

LP          : '(' ;
RP          : ')' ;
ADD_OP      : '+' ;
SUBST_OP    : '-' ;
MULT_OP     : '*' ;
DEV_OP      : '/' ;

EQ_OP       : '==' ;
NOT_EQ_OP   : '!=' ;
GT_OP       : '>' ;
LT_OP       : '<' ;
GTEQ_OP     : '>=' ;
LTEQ_OP     : '<=' ;

CHAR        :   '\'' . '\'' ;
INT         : '0' | [1-9] DIGIT* ;
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
WS          : [ \t\r\n]+ -> skip ; //whitespaces that should be ignored in parsing process