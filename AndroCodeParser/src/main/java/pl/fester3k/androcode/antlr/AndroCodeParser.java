// Generated from AndroCode.g4 by ANTLR 4.2.2

    package pl.fester3k.androcode.antlr;
    import pl.fester3k.androcode.antlr.enums.Type;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AndroCodeParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__21=1, T__20=2, T__19=3, T__18=4, T__17=5, T__16=6, T__15=7, T__14=8, 
		T__13=9, T__12=10, T__11=11, T__10=12, T__9=13, T__8=14, T__7=15, T__6=16, 
		T__5=17, T__4=18, T__3=19, T__2=20, T__1=21, T__0=22, K_INT_TYPE=23, K_VOID_TYPE=24, 
		K_FLOAT_TYPE=25, K_CHAR_TYPE=26, K_STRING_TYPE=27, K_BOOLEAN_TYPE=28, 
		K_DEV_TYPE=29, LIBNAME=30, ID=31, LP=32, RP=33, ADD_OP=34, MINUS_OP=35, 
		MULT_OP=36, DEV_OP=37, INCR_OP=38, DECR_OP=39, EQ_OP=40, NOT_EQ_OP=41, 
		GT_OP=42, LT_OP=43, GTEQ_OP=44, LTEQ_OP=45, AND_OP=46, OR_OP=47, CHAR=48, 
		NEGATED_INT=49, INT=50, NEGATED_FLOAT=51, FLOAT=52, STRING=53, BOOLEAN=54, 
		NULL=55, LINE_COMMENT=56, COMMENT=57, WS=58;
	public static final String[] tokenNames = {
		"<INVALID>", "'use'", "'exec'", "'.'", "','", "'setParam'", "'while'", 
		"'print'", "'for'", "'if'", "'return'", "'='", "';'", "'sleep'", "'release'", 
		"'{'", "'begin'", "'else'", "'getAction'", "'}'", "'end'", "'!'", "'elseif'", 
		"'int'", "'void'", "'float'", "'char'", "'String'", "'bool'", "'action'", 
		"LIBNAME", "ID", "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'++'", "'--'", 
		"'=='", "'!='", "'>'", "'<'", "'>='", "'<='", "'&&'", "'||'", "CHAR", 
		"NEGATED_INT", "INT", "NEGATED_FLOAT", "FLOAT", "STRING", "BOOLEAN", "'null'", 
		"LINE_COMMENT", "COMMENT", "WS"
	};
	public static final int
		RULE_script = 0, RULE_lib_includes = 1, RULE_body = 2, RULE_function = 3, 
		RULE_parameters = 4, RULE_parameter = 5, RULE_block = 6, RULE_statement = 7, 
		RULE_expr = 8, RULE_var_or_val = 9, RULE_mixed_string = 10, RULE_sleep = 11, 
		RULE_print = 12, RULE_return_statement = 13, RULE_var_declaration = 14, 
		RULE_assignment = 15, RULE_var_or_function_call = 16, RULE_condition = 17, 
		RULE_arguments = 18, RULE_for_loop = 19, RULE_while_loop = 20, RULE_if_condition = 21, 
		RULE_dev_operation = 22, RULE_value = 23, RULE_type = 24, RULE_logical_op = 25;
	public static final String[] ruleNames = {
		"script", "lib_includes", "body", "function", "parameters", "parameter", 
		"block", "statement", "expr", "var_or_val", "mixed_string", "sleep", "print", 
		"return_statement", "var_declaration", "assignment", "var_or_function_call", 
		"condition", "arguments", "for_loop", "while_loop", "if_condition", "dev_operation", 
		"value", "type", "logical_op"
	};

	@Override
	public String getGrammarFileName() { return "AndroCode.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AndroCodeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AndroCodeParser.EOF, 0); }
		public List<Lib_includesContext> lib_includes() {
			return getRuleContexts(Lib_includesContext.class);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Lib_includesContext lib_includes(int i) {
			return getRuleContext(Lib_includesContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==1) {
				{
				{
				setState(52); lib_includes();
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58); body();
			setState(59); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Lib_includesContext extends ParserRuleContext {
		public TerminalNode LIBNAME() { return getToken(AndroCodeParser.LIBNAME, 0); }
		public Lib_includesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lib_includes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterLib_includes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitLib_includes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitLib_includes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lib_includesContext lib_includes() throws RecognitionException {
		Lib_includesContext _localctx = new Lib_includesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_lib_includes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(1);
			setState(62); match(LIBNAME);
			setState(63); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(16);
			setState(69);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(66); function();
					}
					} 
				}
				setState(71);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(75);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(72); statement();
					}
					} 
				}
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				{
				setState(78); function();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84); match(20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); type();
			setState(87); match(ID);
			setState(88); match(LP);
			setState(90);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				setState(89); parameters();
				}
			}

			setState(92); match(RP);
			setState(93); block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); parameter();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(96); match(4);
				setState(97); parameter();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); type();
			setState(104); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(15);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 8) | (1L << 9) | (1L << 10) | (1L << 13) | (1L << 15) | (1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE) | (1L << ID) | (1L << LP) | (1L << MINUS_OP) | (1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				{
				setState(107); statement();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113); match(19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public For_loopContext for_loop() {
			return getRuleContext(For_loopContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public While_loopContext while_loop() {
			return getRuleContext(While_loopContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public If_conditionContext if_condition() {
			return getRuleContext(If_conditionContext.class,0);
		}
		public Return_statementContext return_statement() {
			return getRuleContext(Return_statementContext.class,0);
		}
		public SleepContext sleep() {
			return getRuleContext(SleepContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statement);
		try {
			setState(131);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(116); expr(0);
				setState(117); match(12);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(119); var_declaration();
				setState(120); match(12);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(122); assignment();
				setState(123); match(12);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(125); for_loop();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(126); while_loop();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(127); if_condition();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(128); return_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(129); sleep();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(130); print();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Expr_parenthesisContext extends ExprContext {
		public ExprContext subExpr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_parenthesisContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_parenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_parenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_parenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_devContext extends ExprContext {
		public Dev_operationContext dev_operation() {
			return getRuleContext(Dev_operationContext.class,0);
		}
		public Expr_devContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_dev(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_dev(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_dev(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_var_or_fcallContext extends ExprContext {
		public Var_or_function_callContext var_or_function_call() {
			return getRuleContext(Var_or_function_callContext.class,0);
		}
		public Expr_var_or_fcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_var_or_fcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_var_or_fcall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_var_or_fcall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_binopContext extends ExprContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public TerminalNode ADD_OP() { return getToken(AndroCodeParser.ADD_OP, 0); }
		public TerminalNode DEV_OP() { return getToken(AndroCodeParser.DEV_OP, 0); }
		public TerminalNode MULT_OP() { return getToken(AndroCodeParser.MULT_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS_OP() { return getToken(AndroCodeParser.MINUS_OP, 0); }
		public Expr_binopContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_binop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_binop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_binop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_valueContext extends ExprContext {
		public ValueContext v;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Expr_valueContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_value(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_incr_decrContext extends ExprContext {
		public Token id;
		public Token op;
		public TerminalNode INCR_OP() { return getToken(AndroCodeParser.INCR_OP, 0); }
		public TerminalNode DECR_OP() { return getToken(AndroCodeParser.DECR_OP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public Expr_incr_decrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_incr_decr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_incr_decr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_incr_decr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_castContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Expr_castContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_cast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_cast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_cast(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_uminusContext extends ExprContext {
		public ExprContext subExpr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS_OP() { return getToken(AndroCodeParser.MINUS_OP, 0); }
		public Expr_uminusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_uminus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_uminus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_uminus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_uminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(134); match(MINUS_OP);
				setState(135); ((Expr_uminusContext)_localctx).subExpr = expr(7);
				}
				break;

			case 2:
				{
				_localctx = new Expr_castContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136); match(LP);
				setState(137); type();
				setState(138); match(RP);
				setState(139); expr(4);
				}
				break;

			case 3:
				{
				_localctx = new Expr_var_or_fcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141); var_or_function_call();
				}
				break;

			case 4:
				{
				_localctx = new Expr_parenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142); match(LP);
				setState(143); ((Expr_parenthesisContext)_localctx).subExpr = expr(0);
				setState(144); match(RP);
				}
				break;

			case 5:
				{
				_localctx = new Expr_incr_decrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146); ((Expr_incr_decrContext)_localctx).id = match(ID);
				setState(147);
				((Expr_incr_decrContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==INCR_OP || _la==DECR_OP) ) {
					((Expr_incr_decrContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				}
				break;

			case 6:
				{
				_localctx = new Expr_devContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148); dev_operation();
				}
				break;

			case 7:
				{
				_localctx = new Expr_valueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149); ((Expr_valueContext)_localctx).v = value();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(160);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(158);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(153);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT_OP || _la==DEV_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(154); ((Expr_binopContext)_localctx).b = expr(7);
						}
						break;

					case 2:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(155);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(156);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD_OP || _la==MINUS_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(157); ((Expr_binopContext)_localctx).b = expr(6);
						}
						break;
					}
					} 
				}
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Var_or_valContext extends ParserRuleContext {
		public Var_or_function_callContext var_or_function_call() {
			return getRuleContext(Var_or_function_callContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Var_or_valContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_or_val; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterVar_or_val(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitVar_or_val(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitVar_or_val(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_or_valContext var_or_val() throws RecognitionException {
		Var_or_valContext _localctx = new Var_or_valContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_var_or_val);
		try {
			setState(165);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(163); var_or_function_call();
				}
				break;
			case CHAR:
			case NEGATED_INT:
			case INT:
			case NEGATED_FLOAT:
			case FLOAT:
			case STRING:
			case BOOLEAN:
			case NULL:
				enterOuterAlt(_localctx, 2);
				{
				setState(164); value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mixed_stringContext extends ParserRuleContext {
		public List<TerminalNode> ADD_OP() { return getTokens(AndroCodeParser.ADD_OP); }
		public TerminalNode ADD_OP(int i) {
			return getToken(AndroCodeParser.ADD_OP, i);
		}
		public Var_or_valContext var_or_val(int i) {
			return getRuleContext(Var_or_valContext.class,i);
		}
		public List<Var_or_valContext> var_or_val() {
			return getRuleContexts(Var_or_valContext.class);
		}
		public Mixed_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mixed_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterMixed_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitMixed_string(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitMixed_string(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mixed_stringContext mixed_string() throws RecognitionException {
		Mixed_stringContext _localctx = new Mixed_stringContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mixed_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167); var_or_val();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD_OP) {
				{
				{
				setState(168); match(ADD_OP);
				setState(169); var_or_val();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SleepContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(AndroCodeParser.INT, 0); }
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public SleepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sleep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterSleep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitSleep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitSleep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SleepContext sleep() throws RecognitionException {
		SleepContext _localctx = new SleepContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sleep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(13);
			setState(176); match(LP);
			setState(177); match(INT);
			setState(178); match(RP);
			setState(179); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintContext extends ParserRuleContext {
		public Mixed_stringContext mixed_string() {
			return getRuleContext(Mixed_stringContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(7);
			setState(182); match(LP);
			setState(183); mixed_string();
			setState(184); match(RP);
			setState(185); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_statementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitReturn_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitReturn_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_return_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); match(10);
			setState(189);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << LP) | (1L << MINUS_OP) | (1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(188); expr(0);
				}
			}

			setState(191); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declarationContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterVar_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitVar_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitVar_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_var_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193); type();
			setState(194); match(ID);
			setState(197);
			_la = _input.LA(1);
			if (_la==11) {
				{
				setState(195); match(11);
				setState(196); expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Token a;
		public ExprContext b;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199); ((AssignmentContext)_localctx).a = match(ID);
			setState(200); match(11);
			setState(201); ((AssignmentContext)_localctx).b = expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_or_function_callContext extends ParserRuleContext {
		public Var_or_function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_or_function_call; }
	 
		public Var_or_function_callContext() { }
		public void copyFrom(Var_or_function_callContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Function_callContext extends Var_or_function_callContext {
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Function_callContext(Var_or_function_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Var_callContext extends Var_or_function_callContext {
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public Var_callContext(Var_or_function_callContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterVar_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitVar_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitVar_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_or_function_callContext var_or_function_call() throws RecognitionException {
		Var_or_function_callContext _localctx = new Var_or_function_callContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_var_or_function_call);
		int _la;
		try {
			setState(210);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new Var_callContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(203); match(ID);
				}
				break;

			case 2:
				_localctx = new Function_callContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(204); match(ID);
				setState(205); match(LP);
				setState(207);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << LP) | (1L << MINUS_OP) | (1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
					{
					setState(206); arguments();
					}
				}

				setState(209); match(RP);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Condition_equalityContext extends ConditionContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public TerminalNode NOT_EQ_OP() { return getToken(AndroCodeParser.NOT_EQ_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ_OP() { return getToken(AndroCodeParser.EQ_OP, 0); }
		public Condition_equalityContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition_equality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition_equality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition_equality(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Condition_negatedContext extends ConditionContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public Condition_negatedContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition_negated(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition_negated(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition_negated(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Condition_var_negatedContext extends ConditionContext {
		public Var_or_function_callContext var_or_function_call() {
			return getRuleContext(Var_or_function_callContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public Condition_var_negatedContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition_var_negated(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition_var_negated(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition_var_negated(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Condition_combinedContext extends ConditionContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public List<Logical_opContext> logical_op() {
			return getRuleContexts(Logical_opContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public Logical_opContext logical_op(int i) {
			return getRuleContext(Logical_opContext.class,i);
		}
		public Condition_combinedContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition_combined(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition_combined(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition_combined(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Condition_relationalContext extends ConditionContext {
		public ExprContext a;
		public Token op;
		public ExprContext b;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode GT_OP() { return getToken(AndroCodeParser.GT_OP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LTEQ_OP() { return getToken(AndroCodeParser.LTEQ_OP, 0); }
		public TerminalNode GTEQ_OP() { return getToken(AndroCodeParser.GTEQ_OP, 0); }
		public TerminalNode LT_OP() { return getToken(AndroCodeParser.LT_OP, 0); }
		public Condition_relationalContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition_relational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition_relational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition_relational(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				_localctx = new Condition_var_negatedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(213); match(21);
				setState(214); match(LP);
				setState(215); var_or_function_call();
				setState(216); match(RP);
				}
				break;

			case 2:
				{
				_localctx = new Condition_var_negatedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(218); match(21);
				setState(219); var_or_function_call();
				}
				break;

			case 3:
				{
				_localctx = new Condition_negatedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(220); match(21);
				setState(221); match(LP);
				setState(222); condition(0);
				setState(223); match(RP);
				}
				break;

			case 4:
				{
				_localctx = new Condition_equalityContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(225); ((Condition_equalityContext)_localctx).a = expr(0);
				setState(226);
				((Condition_equalityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQ_OP || _la==NOT_EQ_OP) ) {
					((Condition_equalityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(227); ((Condition_equalityContext)_localctx).b = expr(0);
				}
				break;

			case 5:
				{
				_localctx = new Condition_relationalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(229); ((Condition_relationalContext)_localctx).a = expr(0);
				setState(230);
				((Condition_relationalContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT_OP) | (1L << LT_OP) | (1L << GTEQ_OP) | (1L << LTEQ_OP))) != 0)) ) {
					((Condition_relationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(231); ((Condition_relationalContext)_localctx).b = expr(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Condition_combinedContext(new ConditionContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_condition);
					setState(235);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(236); logical_op();
					setState(237); condition(0);
					setState(243);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
					while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(238); logical_op();
							setState(239); condition(0);
							}
							} 
						}
						setState(245);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
					}
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251); expr(0);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(252); match(4);
				setState(253); expr(0);
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_loopContext extends ParserRuleContext {
		public ExprContext newValExpr;
		public AssignmentContext newValAssign;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public For_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterFor_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitFor_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitFor_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_loopContext for_loop() throws RecognitionException {
		For_loopContext _localctx = new For_loopContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); match(8);
			setState(260); match(LP);
			setState(261); assignment();
			setState(262); match(12);
			setState(263); condition(0);
			setState(264); match(12);
			setState(267);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(265); ((For_loopContext)_localctx).newValExpr = expr(0);
				}
				break;

			case 2:
				{
				setState(266); ((For_loopContext)_localctx).newValAssign = assignment();
				}
				break;
			}
			setState(269); match(RP);
			setState(270); block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_loopContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode INT() { return getToken(AndroCodeParser.INT, 0); }
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public While_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterWhile_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitWhile_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitWhile_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_loopContext while_loop() throws RecognitionException {
		While_loopContext _localctx = new While_loopContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_while_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(6);
			setState(273); match(LP);
			setState(274); condition(0);
			setState(277);
			_la = _input.LA(1);
			if (_la==4) {
				{
				setState(275); match(4);
				setState(276); match(INT);
				}
			}

			setState(279); match(RP);
			setState(280); block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_conditionContext extends ParserRuleContext {
		public BlockContext elseBlock;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public TerminalNode LP(int i) {
			return getToken(AndroCodeParser.LP, i);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> LP() { return getTokens(AndroCodeParser.LP); }
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> RP() { return getTokens(AndroCodeParser.RP); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public TerminalNode RP(int i) {
			return getToken(AndroCodeParser.RP, i);
		}
		public If_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterIf_condition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitIf_condition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitIf_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_conditionContext if_condition() throws RecognitionException {
		If_conditionContext _localctx = new If_conditionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_if_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); match(9);
			setState(283); match(LP);
			setState(284); condition(0);
			setState(285); match(RP);
			setState(286); block();
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==22) {
				{
				{
				setState(287); match(22);
				setState(288); match(LP);
				setState(289); condition(0);
				setState(290); match(RP);
				setState(291); block();
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(300);
			_la = _input.LA(1);
			if (_la==17) {
				{
				setState(298); match(17);
				setState(299); ((If_conditionContext)_localctx).elseBlock = block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dev_operationContext extends ParserRuleContext {
		public Dev_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dev_operation; }
	 
		public Dev_operationContext() { }
		public void copyFrom(Dev_operationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Dev_setParamContext extends Dev_operationContext {
		public Mixed_stringContext mixed_string() {
			return getRuleContext(Mixed_stringContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public TerminalNode STRING() { return getToken(AndroCodeParser.STRING, 0); }
		public Dev_setParamContext(Dev_operationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterDev_setParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitDev_setParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitDev_setParam(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Dev_releaseContext extends Dev_operationContext {
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public Dev_releaseContext(Dev_operationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterDev_release(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitDev_release(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitDev_release(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Dev_execContext extends Dev_operationContext {
		public TerminalNode INT() { return getToken(AndroCodeParser.INT, 0); }
		public Mixed_stringContext mixed_string() {
			return getRuleContext(Mixed_stringContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public TerminalNode STRING() { return getToken(AndroCodeParser.STRING, 0); }
		public Dev_execContext(Dev_operationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterDev_exec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitDev_exec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitDev_exec(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Dev_getContext extends Dev_operationContext {
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public TerminalNode STRING() { return getToken(AndroCodeParser.STRING, 0); }
		public Dev_getContext(Dev_operationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterDev_get(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitDev_get(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitDev_get(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dev_operationContext dev_operation() throws RecognitionException {
		Dev_operationContext _localctx = new Dev_operationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dev_operation);
		int _la;
		try {
			setState(336);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new Dev_setParamContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(302); match(ID);
				setState(303); match(3);
				setState(304); match(5);
				setState(305); match(LP);
				setState(306); match(STRING);
				setState(307); match(4);
				setState(308); mixed_string();
				setState(309); match(RP);
				}
				break;

			case 2:
				_localctx = new Dev_getContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(311); match(ID);
				setState(312); match(3);
				setState(313); match(18);
				setState(314); match(LP);
				setState(315); match(STRING);
				setState(316); match(RP);
				}
				break;

			case 3:
				_localctx = new Dev_execContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(317); match(ID);
				setState(318); match(3);
				setState(319); match(2);
				setState(320); match(LP);
				setState(328);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(321); match(STRING);
					setState(322); match(4);
					setState(323); mixed_string();
					setState(326);
					_la = _input.LA(1);
					if (_la==4) {
						{
						setState(324); match(4);
						setState(325); match(INT);
						}
					}

					}
				}

				setState(330); match(RP);
				}
				break;

			case 4:
				_localctx = new Dev_releaseContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(331); match(ID);
				setState(332); match(3);
				setState(333); match(14);
				setState(334); match(LP);
				setState(335); match(RP);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(AndroCodeParser.BOOLEAN, 0); }
		public TerminalNode FLOAT() { return getToken(AndroCodeParser.FLOAT, 0); }
		public TerminalNode NEGATED_INT() { return getToken(AndroCodeParser.NEGATED_INT, 0); }
		public TerminalNode INT() { return getToken(AndroCodeParser.INT, 0); }
		public TerminalNode NEGATED_FLOAT() { return getToken(AndroCodeParser.NEGATED_FLOAT, 0); }
		public TerminalNode NULL() { return getToken(AndroCodeParser.NULL, 0); }
		public TerminalNode STRING() { return getToken(AndroCodeParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(AndroCodeParser.CHAR, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode K_STRING_TYPE() { return getToken(AndroCodeParser.K_STRING_TYPE, 0); }
		public TerminalNode K_BOOLEAN_TYPE() { return getToken(AndroCodeParser.K_BOOLEAN_TYPE, 0); }
		public TerminalNode K_INT_TYPE() { return getToken(AndroCodeParser.K_INT_TYPE, 0); }
		public TerminalNode K_DEV_TYPE() { return getToken(AndroCodeParser.K_DEV_TYPE, 0); }
		public TerminalNode K_FLOAT_TYPE() { return getToken(AndroCodeParser.K_FLOAT_TYPE, 0); }
		public TerminalNode K_CHAR_TYPE() { return getToken(AndroCodeParser.K_CHAR_TYPE, 0); }
		public TerminalNode K_VOID_TYPE() { return getToken(AndroCodeParser.K_VOID_TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logical_opContext extends ParserRuleContext {
		public TerminalNode AND_OP() { return getToken(AndroCodeParser.AND_OP, 0); }
		public TerminalNode OR_OP() { return getToken(AndroCodeParser.OR_OP, 0); }
		public Logical_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterLogical_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitLogical_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitLogical_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_opContext logical_op() throws RecognitionException {
		Logical_opContext _localctx = new Logical_opContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_logical_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			_la = _input.LA(1);
			if ( !(_la==AND_OP || _la==OR_OP) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8: return expr_sempred((ExprContext)_localctx, predIndex);

		case 17: return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 6);

		case 1: return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3<\u015b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\7\28\n\2\f\2\16\2;\13\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\7\4F\n\4\f\4\16\4I\13\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\4"+
		"\7\4R\n\4\f\4\16\4U\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5]\n\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\7\6e\n\6\f\6\16\6h\13\6\3\7\3\7\3\7\3\b\3\b\7\bo\n\b\f\b"+
		"\16\br\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u0086\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0099\n\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00a1"+
		"\n\n\f\n\16\n\u00a4\13\n\3\13\3\13\5\13\u00a8\n\13\3\f\3\f\3\f\7\f\u00ad"+
		"\n\f\f\f\16\f\u00b0\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\5\17\u00c0\n\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20"+
		"\u00c8\n\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\5\22\u00d2\n\22\3"+
		"\22\5\22\u00d5\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00ec\n\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00f4\n\23\f\23\16\23\u00f7\13\23"+
		"\7\23\u00f9\n\23\f\23\16\23\u00fc\13\23\3\24\3\24\3\24\7\24\u0101\n\24"+
		"\f\24\16\24\u0104\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u010e"+
		"\n\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u0118\n\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0128"+
		"\n\27\f\27\16\27\u012b\13\27\3\27\3\27\5\27\u012f\n\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0149\n\30\5\30\u014b\n\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\5\30\u0153\n\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\33\2\4\22$\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,."+
		"\60\62\64\2\n\3\2()\3\2&\'\3\2$%\3\2*+\3\2,/\3\2\629\3\2\31\37\3\2\60"+
		"\61\u016e\29\3\2\2\2\4?\3\2\2\2\6C\3\2\2\2\bX\3\2\2\2\na\3\2\2\2\fi\3"+
		"\2\2\2\16l\3\2\2\2\20\u0085\3\2\2\2\22\u0098\3\2\2\2\24\u00a7\3\2\2\2"+
		"\26\u00a9\3\2\2\2\30\u00b1\3\2\2\2\32\u00b7\3\2\2\2\34\u00bd\3\2\2\2\36"+
		"\u00c3\3\2\2\2 \u00c9\3\2\2\2\"\u00d4\3\2\2\2$\u00eb\3\2\2\2&\u00fd\3"+
		"\2\2\2(\u0105\3\2\2\2*\u0112\3\2\2\2,\u011c\3\2\2\2.\u0152\3\2\2\2\60"+
		"\u0154\3\2\2\2\62\u0156\3\2\2\2\64\u0158\3\2\2\2\668\5\4\3\2\67\66\3\2"+
		"\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\5\6\4\2=>\7"+
		"\2\2\3>\3\3\2\2\2?@\7\3\2\2@A\7 \2\2AB\7\16\2\2B\5\3\2\2\2CG\7\22\2\2"+
		"DF\5\b\5\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HM\3\2\2\2IG\3\2\2\2"+
		"JL\5\20\t\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NS\3\2\2\2OM\3\2\2"+
		"\2PR\5\b\5\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2"+
		"\2VW\7\26\2\2W\7\3\2\2\2XY\5\62\32\2YZ\7!\2\2Z\\\7\"\2\2[]\5\n\6\2\\["+
		"\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\7#\2\2_`\5\16\b\2`\t\3\2\2\2af\5\f\7\2"+
		"bc\7\6\2\2ce\5\f\7\2db\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\13\3\2\2"+
		"\2hf\3\2\2\2ij\5\62\32\2jk\7!\2\2k\r\3\2\2\2lp\7\21\2\2mo\5\20\t\2nm\3"+
		"\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\7\25\2\2t\17"+
		"\3\2\2\2u\u0086\5\16\b\2vw\5\22\n\2wx\7\16\2\2x\u0086\3\2\2\2yz\5\36\20"+
		"\2z{\7\16\2\2{\u0086\3\2\2\2|}\5 \21\2}~\7\16\2\2~\u0086\3\2\2\2\177\u0086"+
		"\5(\25\2\u0080\u0086\5*\26\2\u0081\u0086\5,\27\2\u0082\u0086\5\34\17\2"+
		"\u0083\u0086\5\30\r\2\u0084\u0086\5\32\16\2\u0085u\3\2\2\2\u0085v\3\2"+
		"\2\2\u0085y\3\2\2\2\u0085|\3\2\2\2\u0085\177\3\2\2\2\u0085\u0080\3\2\2"+
		"\2\u0085\u0081\3\2\2\2\u0085\u0082\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0084"+
		"\3\2\2\2\u0086\21\3\2\2\2\u0087\u0088\b\n\1\2\u0088\u0089\7%\2\2\u0089"+
		"\u0099\5\22\n\t\u008a\u008b\7\"\2\2\u008b\u008c\5\62\32\2\u008c\u008d"+
		"\7#\2\2\u008d\u008e\5\22\n\6\u008e\u0099\3\2\2\2\u008f\u0099\5\"\22\2"+
		"\u0090\u0091\7\"\2\2\u0091\u0092\5\22\n\2\u0092\u0093\7#\2\2\u0093\u0099"+
		"\3\2\2\2\u0094\u0095\7!\2\2\u0095\u0099\t\2\2\2\u0096\u0099\5.\30\2\u0097"+
		"\u0099\5\60\31\2\u0098\u0087\3\2\2\2\u0098\u008a\3\2\2\2\u0098\u008f\3"+
		"\2\2\2\u0098\u0090\3\2\2\2\u0098\u0094\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0097\3\2\2\2\u0099\u00a2\3\2\2\2\u009a\u009b\f\b\2\2\u009b\u009c\t\3"+
		"\2\2\u009c\u00a1\5\22\n\t\u009d\u009e\f\7\2\2\u009e\u009f\t\4\2\2\u009f"+
		"\u00a1\5\22\n\b\u00a0\u009a\3\2\2\2\u00a0\u009d\3\2\2\2\u00a1\u00a4\3"+
		"\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\23\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a5\u00a8\5\"\22\2\u00a6\u00a8\5\60\31\2\u00a7\u00a5"+
		"\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\25\3\2\2\2\u00a9\u00ae\5\24\13\2\u00aa"+
		"\u00ab\7$\2\2\u00ab\u00ad\5\24\13\2\u00ac\u00aa\3\2\2\2\u00ad\u00b0\3"+
		"\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\27\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b1\u00b2\7\17\2\2\u00b2\u00b3\7\"\2\2\u00b3\u00b4\7"+
		"\64\2\2\u00b4\u00b5\7#\2\2\u00b5\u00b6\7\16\2\2\u00b6\31\3\2\2\2\u00b7"+
		"\u00b8\7\t\2\2\u00b8\u00b9\7\"\2\2\u00b9\u00ba\5\26\f\2\u00ba\u00bb\7"+
		"#\2\2\u00bb\u00bc\7\16\2\2\u00bc\33\3\2\2\2\u00bd\u00bf\7\f\2\2\u00be"+
		"\u00c0\5\22\n\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3"+
		"\2\2\2\u00c1\u00c2\7\16\2\2\u00c2\35\3\2\2\2\u00c3\u00c4\5\62\32\2\u00c4"+
		"\u00c7\7!\2\2\u00c5\u00c6\7\r\2\2\u00c6\u00c8\5\22\n\2\u00c7\u00c5\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\37\3\2\2\2\u00c9\u00ca\7!\2\2\u00ca\u00cb"+
		"\7\r\2\2\u00cb\u00cc\5\22\n\2\u00cc!\3\2\2\2\u00cd\u00d5\7!\2\2\u00ce"+
		"\u00cf\7!\2\2\u00cf\u00d1\7\"\2\2\u00d0\u00d2\5&\24\2\u00d1\u00d0\3\2"+
		"\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d5\7#\2\2\u00d4"+
		"\u00cd\3\2\2\2\u00d4\u00ce\3\2\2\2\u00d5#\3\2\2\2\u00d6\u00d7\b\23\1\2"+
		"\u00d7\u00d8\7\27\2\2\u00d8\u00d9\7\"\2\2\u00d9\u00da\5\"\22\2\u00da\u00db"+
		"\7#\2\2\u00db\u00ec\3\2\2\2\u00dc\u00dd\7\27\2\2\u00dd\u00ec\5\"\22\2"+
		"\u00de\u00df\7\27\2\2\u00df\u00e0\7\"\2\2\u00e0\u00e1\5$\23\2\u00e1\u00e2"+
		"\7#\2\2\u00e2\u00ec\3\2\2\2\u00e3\u00e4\5\22\n\2\u00e4\u00e5\t\5\2\2\u00e5"+
		"\u00e6\5\22\n\2\u00e6\u00ec\3\2\2\2\u00e7\u00e8\5\22\n\2\u00e8\u00e9\t"+
		"\6\2\2\u00e9\u00ea\5\22\n\2\u00ea\u00ec\3\2\2\2\u00eb\u00d6\3\2\2\2\u00eb"+
		"\u00dc\3\2\2\2\u00eb\u00de\3\2\2\2\u00eb\u00e3\3\2\2\2\u00eb\u00e7\3\2"+
		"\2\2\u00ec\u00fa\3\2\2\2\u00ed\u00ee\f\3\2\2\u00ee\u00ef\5\64\33\2\u00ef"+
		"\u00f5\5$\23\2\u00f0\u00f1\5\64\33\2\u00f1\u00f2\5$\23\2\u00f2\u00f4\3"+
		"\2\2\2\u00f3\u00f0\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00ed\3\2"+
		"\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"%\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u0102\5\22\n\2\u00fe\u00ff\7\6\2\2"+
		"\u00ff\u0101\5\22\n\2\u0100\u00fe\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100"+
		"\3\2\2\2\u0102\u0103\3\2\2\2\u0103\'\3\2\2\2\u0104\u0102\3\2\2\2\u0105"+
		"\u0106\7\n\2\2\u0106\u0107\7\"\2\2\u0107\u0108\5 \21\2\u0108\u0109\7\16"+
		"\2\2\u0109\u010a\5$\23\2\u010a\u010d\7\16\2\2\u010b\u010e\5\22\n\2\u010c"+
		"\u010e\5 \21\2\u010d\u010b\3\2\2\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0110\7#\2\2\u0110\u0111\5\16\b\2\u0111)\3\2\2\2\u0112\u0113"+
		"\7\b\2\2\u0113\u0114\7\"\2\2\u0114\u0117\5$\23\2\u0115\u0116\7\6\2\2\u0116"+
		"\u0118\7\64\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3"+
		"\2\2\2\u0119\u011a\7#\2\2\u011a\u011b\5\16\b\2\u011b+\3\2\2\2\u011c\u011d"+
		"\7\13\2\2\u011d\u011e\7\"\2\2\u011e\u011f\5$\23\2\u011f\u0120\7#\2\2\u0120"+
		"\u0129\5\16\b\2\u0121\u0122\7\30\2\2\u0122\u0123\7\"\2\2\u0123\u0124\5"+
		"$\23\2\u0124\u0125\7#\2\2\u0125\u0126\5\16\b\2\u0126\u0128\3\2\2\2\u0127"+
		"\u0121\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2"+
		"\2\2\u012a\u012e\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d\7\23\2\2\u012d"+
		"\u012f\5\16\b\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f-\3\2\2\2"+
		"\u0130\u0131\7!\2\2\u0131\u0132\7\5\2\2\u0132\u0133\7\7\2\2\u0133\u0134"+
		"\7\"\2\2\u0134\u0135\7\67\2\2\u0135\u0136\7\6\2\2\u0136\u0137\5\26\f\2"+
		"\u0137\u0138\7#\2\2\u0138\u0153\3\2\2\2\u0139\u013a\7!\2\2\u013a\u013b"+
		"\7\5\2\2\u013b\u013c\7\24\2\2\u013c\u013d\7\"\2\2\u013d\u013e\7\67\2\2"+
		"\u013e\u0153\7#\2\2\u013f\u0140\7!\2\2\u0140\u0141\7\5\2\2\u0141\u0142"+
		"\7\4\2\2\u0142\u014a\7\"\2\2\u0143\u0144\7\67\2\2\u0144\u0145\7\6\2\2"+
		"\u0145\u0148\5\26\f\2\u0146\u0147\7\6\2\2\u0147\u0149\7\64\2\2\u0148\u0146"+
		"\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014b\3\2\2\2\u014a\u0143\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u0153\7#\2\2\u014d\u014e\7!\2"+
		"\2\u014e\u014f\7\5\2\2\u014f\u0150\7\20\2\2\u0150\u0151\7\"\2\2\u0151"+
		"\u0153\7#\2\2\u0152\u0130\3\2\2\2\u0152\u0139\3\2\2\2\u0152\u013f\3\2"+
		"\2\2\u0152\u014d\3\2\2\2\u0153/\3\2\2\2\u0154\u0155\t\7\2\2\u0155\61\3"+
		"\2\2\2\u0156\u0157\t\b\2\2\u0157\63\3\2\2\2\u0158\u0159\t\t\2\2\u0159"+
		"\65\3\2\2\2\369GMS\\fp\u0085\u0098\u00a0\u00a2\u00a7\u00ae\u00bf\u00c7"+
		"\u00d1\u00d4\u00eb\u00f5\u00fa\u0102\u010d\u0117\u0129\u012e\u0148\u014a"+
		"\u0152";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}