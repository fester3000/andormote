// Generated from AndroCode.g4 by ANTLR 4.2.2

    package pl.fester3k.antlr.androCode;
    import pl.fester3k.antlr.semanticAnalysis.Type;

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
		T__18=1, T__17=2, T__16=3, T__15=4, T__14=5, T__13=6, T__12=7, T__11=8, 
		T__10=9, T__9=10, T__8=11, T__7=12, T__6=13, T__5=14, T__4=15, T__3=16, 
		T__2=17, T__1=18, T__0=19, K_INT_TYPE=20, K_VOID_TYPE=21, K_FLOAT_TYPE=22, 
		K_CHAR_TYPE=23, K_STRING_TYPE=24, K_BOOLEAN_TYPE=25, K_DEV_TYPE=26, LIBNAME=27, 
		ID=28, LP=29, RP=30, ADD_OP=31, SUBST_OP=32, MULT_OP=33, DEV_OP=34, INCR_OP=35, 
		DECR_OP=36, EQ_OP=37, NOT_EQ_OP=38, GT_OP=39, LT_OP=40, GTEQ_OP=41, LTEQ_OP=42, 
		CHAR=43, INT=44, FLOAT=45, STRING=46, BOOLEAN=47, NULL=48, LINE_COMMENT=49, 
		COMMENT=50, WS=51;
	public static final String[] tokenNames = {
		"<INVALID>", "'use'", "'getDevice'", "'.'", "','", "'setParam'", "'while'", 
		"'for'", "'if'", "'return'", "'main'", "'='", "';'", "'{'", "'begin'", 
		"'else'", "'}'", "'end'", "'!'", "'elseif'", "'int'", "'void'", "'float'", 
		"'char'", "'String'", "'bool'", "'device'", "LIBNAME", "ID", "'('", "')'", 
		"'+'", "'-'", "'*'", "'/'", "'++'", "'--'", "'=='", "'!='", "'>'", "'<'", 
		"'>='", "'<='", "CHAR", "INT", "FLOAT", "STRING", "BOOLEAN", "'null'", 
		"LINE_COMMENT", "COMMENT", "WS"
	};
	public static final int
		RULE_script = 0, RULE_lib_includes = 1, RULE_body = 2, RULE_main_function = 3, 
		RULE_function = 4, RULE_parameters = 5, RULE_parameter = 6, RULE_block = 7, 
		RULE_statement = 8, RULE_expr = 9, RULE_return_statement = 10, RULE_var_declaration = 11, 
		RULE_var_call = 12, RULE_assignment = 13, RULE_function_call = 14, RULE_condition = 15, 
		RULE_arguments = 16, RULE_for_loop = 17, RULE_while_loop = 18, RULE_if_condition = 19, 
		RULE_dev_operation = 20, RULE_value = 21, RULE_type = 22;
	public static final String[] ruleNames = {
		"script", "lib_includes", "body", "main_function", "function", "parameters", 
		"parameter", "block", "statement", "expr", "return_statement", "var_declaration", 
		"var_call", "assignment", "function_call", "condition", "arguments", "for_loop", 
		"while_loop", "if_condition", "dev_operation", "value", "type"
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
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==1) {
				{
				{
				setState(46); lib_includes();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52); body();
			setState(53); match(EOF);
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
			setState(55); match(1);
			setState(56); match(LIBNAME);
			setState(57); match(12);
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
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public Main_functionContext main_function() {
			return getRuleContext(Main_functionContext.class,0);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
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
			setState(59); match(14);
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60); function();
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(66); main_function();
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				{
				setState(67); function();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73); match(17);
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

	public static class Main_functionContext extends ParserRuleContext {
		public TerminalNode K_INT_TYPE() { return getToken(AndroCodeParser.K_INT_TYPE, 0); }
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Main_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterMain_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitMain_function(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitMain_function(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Main_functionContext main_function() throws RecognitionException {
		Main_functionContext _localctx = new Main_functionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_main_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(K_INT_TYPE);
			setState(76); match(10);
			setState(77); match(LP);
			setState(78); match(RP);
			setState(79); block();
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
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); type();
			setState(82); match(ID);
			setState(83); match(LP);
			setState(85);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				setState(84); parameters();
				}
			}

			setState(87); match(RP);
			setState(88); block();
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
		enterRule(_localctx, 10, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); parameter();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(91); match(4);
				setState(92); parameter();
				}
				}
				setState(97);
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
		enterRule(_localctx, 12, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); type();
			setState(99); match(ID);
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
		enterRule(_localctx, 14, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(13);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 8) | (1L << 9) | (1L << 13) | (1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				{
				setState(102); statement();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108); match(16);
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
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111); expr(0);
				setState(112); match(12);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114); var_declaration();
				setState(115); match(12);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117); assignment();
				setState(118); match(12);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(120); for_loop();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(121); while_loop();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(122); if_condition();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(123); return_statement();
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
	public static class Expr_varContext extends ExprContext {
		public Var_callContext var_call() {
			return getRuleContext(Var_callContext.class,0);
		}
		public Expr_varContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_var(this);
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
		public TerminalNode SUBST_OP() { return getToken(AndroCodeParser.SUBST_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
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
	public static class Expr_fcallContext extends ExprContext {
		public Function_callContext fcal;
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public Expr_fcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_fcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_fcall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_fcall(this);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_uminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(127); match(SUBST_OP);
				setState(128); ((Expr_uminusContext)_localctx).subExpr = expr(8);
				}
				break;

			case 2:
				{
				_localctx = new Expr_castContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129); match(LP);
				setState(130); type();
				setState(131); match(RP);
				setState(132); expr(5);
				}
				break;

			case 3:
				{
				_localctx = new Expr_fcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134); ((Expr_fcallContext)_localctx).fcal = function_call();
				}
				break;

			case 4:
				{
				_localctx = new Expr_parenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135); match(LP);
				setState(136); ((Expr_parenthesisContext)_localctx).subExpr = expr(0);
				setState(137); match(RP);
				}
				break;

			case 5:
				{
				_localctx = new Expr_incr_decrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139); ((Expr_incr_decrContext)_localctx).id = match(ID);
				setState(140);
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
				setState(141); dev_operation();
				}
				break;

			case 7:
				{
				_localctx = new Expr_valueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142); ((Expr_valueContext)_localctx).v = value();
				}
				break;

			case 8:
				{
				_localctx = new Expr_varContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143); var_call();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(152);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(146);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(147);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT_OP || _la==DEV_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(148); ((Expr_binopContext)_localctx).b = expr(8);
						}
						break;

					case 2:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(150);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD_OP || _la==SUBST_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(151); ((Expr_binopContext)_localctx).b = expr(7);
						}
						break;
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		enterRule(_localctx, 20, RULE_return_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(9);
			setState(159);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(158); expr(0);
				}
			}

			setState(161); match(12);
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
		enterRule(_localctx, 22, RULE_var_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); type();
			setState(164); match(ID);
			setState(167);
			_la = _input.LA(1);
			if (_la==11) {
				{
				setState(165); match(11);
				setState(166); expr(0);
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

	public static class Var_callContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public Var_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_call; }
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

	public final Var_callContext var_call() throws RecognitionException {
		Var_callContext _localctx = new Var_callContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_var_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(ID);
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
		enterRule(_localctx, 26, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); ((AssignmentContext)_localctx).a = match(ID);
			setState(172); match(11);
			setState(173); ((AssignmentContext)_localctx).b = expr(0);
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

	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
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

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(ID);
			setState(176); match(LP);
			setState(178);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(177); arguments();
				}
			}

			setState(180); match(RP);
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
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public Var_callContext var_call() {
			return getRuleContext(Var_callContext.class,0);
		}
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
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_condition);
		int _la;
		try {
			setState(202);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new Condition_var_negatedContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(182); match(18);
				setState(183); match(LP);
				setState(184); var_call();
				setState(185); match(RP);
				}
				break;

			case 2:
				_localctx = new Condition_var_negatedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(187); match(18);
				setState(188); var_call();
				}
				break;

			case 3:
				_localctx = new Condition_negatedContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(189); match(18);
				setState(190); match(LP);
				setState(191); condition();
				setState(192); match(RP);
				}
				break;

			case 4:
				_localctx = new Condition_equalityContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(194); ((Condition_equalityContext)_localctx).a = expr(0);
				setState(195);
				((Condition_equalityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQ_OP || _la==NOT_EQ_OP) ) {
					((Condition_equalityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(196); ((Condition_equalityContext)_localctx).b = expr(0);
				}
				break;

			case 5:
				_localctx = new Condition_relationalContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(198); ((Condition_relationalContext)_localctx).a = expr(0);
				setState(199);
				((Condition_relationalContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT_OP) | (1L << LT_OP) | (1L << GTEQ_OP) | (1L << LTEQ_OP))) != 0)) ) {
					((Condition_relationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(200); ((Condition_relationalContext)_localctx).b = expr(0);
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
		enterRule(_localctx, 32, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); expr(0);
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(205); match(4);
				setState(206); expr(0);
				}
				}
				setState(211);
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
		enterRule(_localctx, 34, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212); match(7);
			setState(213); match(LP);
			setState(214); assignment();
			setState(215); match(12);
			setState(216); condition();
			setState(217); match(12);
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(218); ((For_loopContext)_localctx).newValExpr = expr(0);
				}
				break;

			case 2:
				{
				setState(219); ((For_loopContext)_localctx).newValAssign = assignment();
				}
				break;
			}
			setState(222); match(RP);
			setState(223); block();
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
		enterRule(_localctx, 36, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225); match(6);
			setState(226); match(LP);
			setState(227); condition();
			setState(228); match(RP);
			setState(229); block();
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
		enterRule(_localctx, 38, RULE_if_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(8);
			setState(232); match(LP);
			setState(233); condition();
			setState(234); match(RP);
			setState(235); block();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==19) {
				{
				{
				setState(236); match(19);
				setState(237); match(LP);
				setState(238); condition();
				setState(239); match(RP);
				setState(240); block();
				}
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(249);
			_la = _input.LA(1);
			if (_la==15) {
				{
				setState(247); match(15);
				setState(248); ((If_conditionContext)_localctx).elseBlock = block();
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public TerminalNode STRING() { return getToken(AndroCodeParser.STRING, 0); }
		public Dev_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dev_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterDev_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitDev_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitDev_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dev_operationContext dev_operation() throws RecognitionException {
		Dev_operationContext _localctx = new Dev_operationContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_dev_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251); match(ID);
			setState(252); match(3);
			setState(264);
			switch (_input.LA(1)) {
			case 5:
				{
				setState(253); match(5);
				setState(254); match(LP);
				setState(255); match(STRING);
				setState(256); match(4);
				setState(257); expr(0);
				setState(258); match(RP);
				}
				break;
			case 2:
				{
				setState(260); match(2);
				setState(261); match(LP);
				setState(262); match(STRING);
				setState(263); match(RP);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(AndroCodeParser.BOOLEAN, 0); }
		public TerminalNode FLOAT() { return getToken(AndroCodeParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(AndroCodeParser.INT, 0); }
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
		enterRule(_localctx, 42, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) ) {
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
		enterRule(_localctx, 44, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 7);

		case 1: return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\65\u0111\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\7\4@\n\4\f\4"+
		"\16\4C\13\4\3\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\5\6X\n\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7`\n\7\f\7\16"+
		"\7c\13\7\3\b\3\b\3\b\3\t\3\t\7\tj\n\t\f\t\16\tm\13\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\177\n\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u0093\n\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u009b\n\13"+
		"\f\13\16\13\u009e\13\13\3\f\3\f\5\f\u00a2\n\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\5\r\u00aa\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\5\20\u00b5"+
		"\n\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00cd\n\21\3\22\3\22"+
		"\3\22\7\22\u00d2\n\22\f\22\16\22\u00d5\13\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u00df\n\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00f5"+
		"\n\25\f\25\16\25\u00f8\13\25\3\25\3\25\5\25\u00fc\n\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u010b\n\26\3\27"+
		"\3\27\3\30\3\30\3\30\2\3\24\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\2\t\3\2%&\3\2#$\3\2!\"\3\2\'(\3\2),\3\2-\62\3\2\26\34\u011b"+
		"\2\63\3\2\2\2\49\3\2\2\2\6=\3\2\2\2\bM\3\2\2\2\nS\3\2\2\2\f\\\3\2\2\2"+
		"\16d\3\2\2\2\20g\3\2\2\2\22~\3\2\2\2\24\u0092\3\2\2\2\26\u009f\3\2\2\2"+
		"\30\u00a5\3\2\2\2\32\u00ab\3\2\2\2\34\u00ad\3\2\2\2\36\u00b1\3\2\2\2 "+
		"\u00cc\3\2\2\2\"\u00ce\3\2\2\2$\u00d6\3\2\2\2&\u00e3\3\2\2\2(\u00e9\3"+
		"\2\2\2*\u00fd\3\2\2\2,\u010c\3\2\2\2.\u010e\3\2\2\2\60\62\5\4\3\2\61\60"+
		"\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63"+
		"\3\2\2\2\66\67\5\6\4\2\678\7\2\2\38\3\3\2\2\29:\7\3\2\2:;\7\35\2\2;<\7"+
		"\16\2\2<\5\3\2\2\2=A\7\20\2\2>@\5\n\6\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2"+
		"AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DH\5\b\5\2EG\5\n\6\2FE\3\2\2\2GJ\3\2\2\2"+
		"HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7\23\2\2L\7\3\2\2\2MN\7\26"+
		"\2\2NO\7\f\2\2OP\7\37\2\2PQ\7 \2\2QR\5\20\t\2R\t\3\2\2\2ST\5.\30\2TU\7"+
		"\36\2\2UW\7\37\2\2VX\5\f\7\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7 \2\2Z["+
		"\5\20\t\2[\13\3\2\2\2\\a\5\16\b\2]^\7\6\2\2^`\5\16\b\2_]\3\2\2\2`c\3\2"+
		"\2\2a_\3\2\2\2ab\3\2\2\2b\r\3\2\2\2ca\3\2\2\2de\5.\30\2ef\7\36\2\2f\17"+
		"\3\2\2\2gk\7\17\2\2hj\5\22\n\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2"+
		"ln\3\2\2\2mk\3\2\2\2no\7\22\2\2o\21\3\2\2\2p\177\5\20\t\2qr\5\24\13\2"+
		"rs\7\16\2\2s\177\3\2\2\2tu\5\30\r\2uv\7\16\2\2v\177\3\2\2\2wx\5\34\17"+
		"\2xy\7\16\2\2y\177\3\2\2\2z\177\5$\23\2{\177\5&\24\2|\177\5(\25\2}\177"+
		"\5\26\f\2~p\3\2\2\2~q\3\2\2\2~t\3\2\2\2~w\3\2\2\2~z\3\2\2\2~{\3\2\2\2"+
		"~|\3\2\2\2~}\3\2\2\2\177\23\3\2\2\2\u0080\u0081\b\13\1\2\u0081\u0082\7"+
		"\"\2\2\u0082\u0093\5\24\13\n\u0083\u0084\7\37\2\2\u0084\u0085\5.\30\2"+
		"\u0085\u0086\7 \2\2\u0086\u0087\5\24\13\7\u0087\u0093\3\2\2\2\u0088\u0093"+
		"\5\36\20\2\u0089\u008a\7\37\2\2\u008a\u008b\5\24\13\2\u008b\u008c\7 \2"+
		"\2\u008c\u0093\3\2\2\2\u008d\u008e\7\36\2\2\u008e\u0093\t\2\2\2\u008f"+
		"\u0093\5*\26\2\u0090\u0093\5,\27\2\u0091\u0093\5\32\16\2\u0092\u0080\3"+
		"\2\2\2\u0092\u0083\3\2\2\2\u0092\u0088\3\2\2\2\u0092\u0089\3\2\2\2\u0092"+
		"\u008d\3\2\2\2\u0092\u008f\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2"+
		"\2\2\u0093\u009c\3\2\2\2\u0094\u0095\f\t\2\2\u0095\u0096\t\3\2\2\u0096"+
		"\u009b\5\24\13\n\u0097\u0098\f\b\2\2\u0098\u0099\t\4\2\2\u0099\u009b\5"+
		"\24\13\t\u009a\u0094\3\2\2\2\u009a\u0097\3\2\2\2\u009b\u009e\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\25\3\2\2\2\u009e\u009c\3\2\2"+
		"\2\u009f\u00a1\7\13\2\2\u00a0\u00a2\5\24\13\2\u00a1\u00a0\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7\16\2\2\u00a4\27\3\2\2"+
		"\2\u00a5\u00a6\5.\30\2\u00a6\u00a9\7\36\2\2\u00a7\u00a8\7\r\2\2\u00a8"+
		"\u00aa\5\24\13\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\31\3\2"+
		"\2\2\u00ab\u00ac\7\36\2\2\u00ac\33\3\2\2\2\u00ad\u00ae\7\36\2\2\u00ae"+
		"\u00af\7\r\2\2\u00af\u00b0\5\24\13\2\u00b0\35\3\2\2\2\u00b1\u00b2\7\36"+
		"\2\2\u00b2\u00b4\7\37\2\2\u00b3\u00b5\5\"\22\2\u00b4\u00b3\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\7 \2\2\u00b7\37\3\2\2\2"+
		"\u00b8\u00b9\7\24\2\2\u00b9\u00ba\7\37\2\2\u00ba\u00bb\5\32\16\2\u00bb"+
		"\u00bc\7 \2\2\u00bc\u00cd\3\2\2\2\u00bd\u00be\7\24\2\2\u00be\u00cd\5\32"+
		"\16\2\u00bf\u00c0\7\24\2\2\u00c0\u00c1\7\37\2\2\u00c1\u00c2\5 \21\2\u00c2"+
		"\u00c3\7 \2\2\u00c3\u00cd\3\2\2\2\u00c4\u00c5\5\24\13\2\u00c5\u00c6\t"+
		"\5\2\2\u00c6\u00c7\5\24\13\2\u00c7\u00cd\3\2\2\2\u00c8\u00c9\5\24\13\2"+
		"\u00c9\u00ca\t\6\2\2\u00ca\u00cb\5\24\13\2\u00cb\u00cd\3\2\2\2\u00cc\u00b8"+
		"\3\2\2\2\u00cc\u00bd\3\2\2\2\u00cc\u00bf\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cc"+
		"\u00c8\3\2\2\2\u00cd!\3\2\2\2\u00ce\u00d3\5\24\13\2\u00cf\u00d0\7\6\2"+
		"\2\u00d0\u00d2\5\24\13\2\u00d1\u00cf\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4#\3\2\2\2\u00d5\u00d3\3\2\2\2"+
		"\u00d6\u00d7\7\t\2\2\u00d7\u00d8\7\37\2\2\u00d8\u00d9\5\34\17\2\u00d9"+
		"\u00da\7\16\2\2\u00da\u00db\5 \21\2\u00db\u00de\7\16\2\2\u00dc\u00df\5"+
		"\24\13\2\u00dd\u00df\5\34\17\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2"+
		"\u00df\u00e0\3\2\2\2\u00e0\u00e1\7 \2\2\u00e1\u00e2\5\20\t\2\u00e2%\3"+
		"\2\2\2\u00e3\u00e4\7\b\2\2\u00e4\u00e5\7\37\2\2\u00e5\u00e6\5 \21\2\u00e6"+
		"\u00e7\7 \2\2\u00e7\u00e8\5\20\t\2\u00e8\'\3\2\2\2\u00e9\u00ea\7\n\2\2"+
		"\u00ea\u00eb\7\37\2\2\u00eb\u00ec\5 \21\2\u00ec\u00ed\7 \2\2\u00ed\u00f6"+
		"\5\20\t\2\u00ee\u00ef\7\25\2\2\u00ef\u00f0\7\37\2\2\u00f0\u00f1\5 \21"+
		"\2\u00f1\u00f2\7 \2\2\u00f2\u00f3\5\20\t\2\u00f3\u00f5\3\2\2\2\u00f4\u00ee"+
		"\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00fb\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\7\21\2\2\u00fa\u00fc\5"+
		"\20\t\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc)\3\2\2\2\u00fd\u00fe"+
		"\7\36\2\2\u00fe\u010a\7\5\2\2\u00ff\u0100\7\7\2\2\u0100\u0101\7\37\2\2"+
		"\u0101\u0102\7\60\2\2\u0102\u0103\7\6\2\2\u0103\u0104\5\24\13\2\u0104"+
		"\u0105\7 \2\2\u0105\u010b\3\2\2\2\u0106\u0107\7\4\2\2\u0107\u0108\7\37"+
		"\2\2\u0108\u0109\7\60\2\2\u0109\u010b\7 \2\2\u010a\u00ff\3\2\2\2\u010a"+
		"\u0106\3\2\2\2\u010b+\3\2\2\2\u010c\u010d\t\7\2\2\u010d-\3\2\2\2\u010e"+
		"\u010f\t\b\2\2\u010f/\3\2\2\2\25\63AHWak~\u0092\u009a\u009c\u00a1\u00a9"+
		"\u00b4\u00cc\u00d3\u00de\u00f6\u00fb\u010a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}