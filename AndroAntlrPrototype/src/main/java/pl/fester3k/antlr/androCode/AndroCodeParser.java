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
		T__20=1, T__19=2, T__18=3, T__17=4, T__16=5, T__15=6, T__14=7, T__13=8, 
		T__12=9, T__11=10, T__10=11, T__9=12, T__8=13, T__7=14, T__6=15, T__5=16, 
		T__4=17, T__3=18, T__2=19, T__1=20, T__0=21, K_INT_TYPE=22, K_VOID_TYPE=23, 
		K_FLOAT_TYPE=24, K_CHAR_TYPE=25, K_STRING_TYPE=26, K_BOOLEAN_TYPE=27, 
		K_DEV_TYPE=28, LIBNAME=29, ID=30, LP=31, RP=32, ADD_OP=33, SUBST_OP=34, 
		MULT_OP=35, DEV_OP=36, POWER_OP=37, EQ_OP=38, NOT_EQ_OP=39, GT_OP=40, 
		LT_OP=41, GTEQ_OP=42, LTEQ_OP=43, CHAR=44, INT=45, FLOAT=46, STRING=47, 
		BOOLEAN=48, NULL=49, LINE_COMMENT=50, COMMENT=51, WS=52;
	public static final String[] tokenNames = {
		"<INVALID>", "'use'", "'getDevice'", "'.'", "','", "'setParam'", "'while'", 
		"'for'", "'if'", "'--'", "'return'", "'main'", "'='", "';'", "'{'", "'begin'", 
		"'else'", "'}'", "'end'", "'!'", "'++'", "'elseif'", "'int'", "'void'", 
		"'float'", "'char'", "'String'", "'bool'", "'device'", "LIBNAME", "ID", 
		"'('", "')'", "'+'", "'-'", "'*'", "'/'", "'^'", "'=='", "'!='", "'>'", 
		"'<'", "'>='", "'<='", "CHAR", "INT", "FLOAT", "STRING", "BOOLEAN", "'null'", 
		"LINE_COMMENT", "COMMENT", "WS"
	};
	public static final int
		RULE_script = 0, RULE_lib_includes = 1, RULE_body = 2, RULE_main_function = 3, 
		RULE_function = 4, RULE_parameters = 5, RULE_parameter = 6, RULE_block = 7, 
		RULE_statement = 8, RULE_expr = 9, RULE_var_declaration = 10, RULE_assignment = 11, 
		RULE_function_call = 12, RULE_condition = 13, RULE_arguments = 14, RULE_for_loop = 15, 
		RULE_while_loop = 16, RULE_if_condition = 17, RULE_dev_operation = 18, 
		RULE_value = 19, RULE_type = 20;
	public static final String[] ruleNames = {
		"script", "lib_includes", "body", "main_function", "function", "parameters", 
		"parameter", "block", "statement", "expr", "var_declaration", "assignment", 
		"function_call", "condition", "arguments", "for_loop", "while_loop", "if_condition", 
		"dev_operation", "value", "type"
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
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==1) {
				{
				{
				setState(42); lib_includes();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48); body();
			setState(49); match(EOF);
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
			setState(51); match(1);
			setState(52); match(LIBNAME);
			setState(53); match(13);
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
			setState(55); match(15);
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(56); function();
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(62); main_function();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				{
				setState(63); function();
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69); match(18);
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
			setState(71); match(K_INT_TYPE);
			setState(72); match(11);
			setState(73); match(LP);
			setState(74); match(RP);
			setState(75); block();
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
			setState(77); type();
			setState(78); match(ID);
			setState(79); match(LP);
			setState(81);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				setState(80); parameters();
				}
			}

			setState(83); match(RP);
			setState(84); block();
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
			setState(86); parameter();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(87); match(4);
				setState(88); parameter();
				}
				}
				setState(93);
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
			setState(94); type();
			setState(95); match(ID);
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
			setState(97); match(14);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 8) | (1L << 10) | (1L << 14) | (1L << 19) | (1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				{
				setState(98); statement();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104); match(17);
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
		int _la;
		try {
			setState(124);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107); expr(0);
				setState(108); match(13);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(110); var_declaration();
				setState(111); match(13);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113); assignment();
				setState(114); match(13);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(116); for_loop();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(117); while_loop();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(118); if_condition();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(119); match(10);
				setState(121);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 19) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
					{
					setState(120); expr(0);
					}
				}

				setState(123); match(13);
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
		public Type evalType;
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
			this.evalType = ctx.evalType;
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
	public static class Expr_powContext extends ExprContext {
		public ExprContext subExpr;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode POWER_OP() { return getToken(AndroCodeParser.POWER_OP, 0); }
		public Expr_powContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_pow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_pow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_pow(this);
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
	public static class Expr_decrContext extends ExprContext {
		public Token id;
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public Expr_decrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_decr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_decr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_decr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_varContext extends ExprContext {
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
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
	public static class Expr_unotContext extends ExprContext {
		public ExprContext subExpr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_unotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_unot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_unot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_unot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_binopContext extends ExprContext {
		public ExprContext a;
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
	public static class Expr_incrContext extends ExprContext {
		public Token id;
		public TerminalNode ID() { return getToken(AndroCodeParser.ID, 0); }
		public Expr_incrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterExpr_incr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitExpr_incr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitExpr_incr(this);
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
			setState(143);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_uminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(127); match(SUBST_OP);
				setState(128); ((Expr_uminusContext)_localctx).subExpr = expr(10);
				}
				break;

			case 2:
				{
				_localctx = new Expr_unotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129); match(19);
				setState(130); ((Expr_unotContext)_localctx).subExpr = expr(9);
				}
				break;

			case 3:
				{
				_localctx = new Expr_fcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131); ((Expr_fcallContext)_localctx).fcal = function_call();
				}
				break;

			case 4:
				{
				_localctx = new Expr_parenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132); match(LP);
				setState(133); ((Expr_parenthesisContext)_localctx).subExpr = expr(0);
				setState(134); match(RP);
				}
				break;

			case 5:
				{
				_localctx = new Expr_incrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136); ((Expr_incrContext)_localctx).id = match(ID);
				setState(137); match(20);
				}
				break;

			case 6:
				{
				_localctx = new Expr_decrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138); ((Expr_decrContext)_localctx).id = match(ID);
				setState(139); match(9);
				}
				break;

			case 7:
				{
				_localctx = new Expr_devContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140); dev_operation();
				}
				break;

			case 8:
				{
				_localctx = new Expr_valueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141); ((Expr_valueContext)_localctx).v = value();
				}
				break;

			case 9:
				{
				_localctx = new Expr_varContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142); match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(154);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_powContext(new ExprContext(_parentctx, _parentState));
						((Expr_powContext)_localctx).subExpr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(145);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(146); match(POWER_OP);
						setState(147); expr(8);
						}
						break;

					case 2:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(149);
						_la = _input.LA(1);
						if ( !(_la==MULT_OP || _la==DEV_OP) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(150); ((Expr_binopContext)_localctx).b = expr(8);
						}
						break;

					case 3:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(151);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(152);
						_la = _input.LA(1);
						if ( !(_la==ADD_OP || _la==SUBST_OP) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(153); ((Expr_binopContext)_localctx).b = expr(7);
						}
						break;
					}
					} 
				}
				setState(158);
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

	public static class Var_declarationContext extends ParserRuleContext {
		public Type evalType;
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
		enterRule(_localctx, 20, RULE_var_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); type();
			setState(160); match(ID);
			setState(163);
			_la = _input.LA(1);
			if (_la==12) {
				{
				setState(161); match(12);
				setState(162); expr(0);
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
		public Type evalType;
		public ExprContext a;
		public ExprContext b;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
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
		enterRule(_localctx, 22, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); ((AssignmentContext)_localctx).a = expr(0);
			setState(166); match(12);
			setState(167); ((AssignmentContext)_localctx).b = expr(0);
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
		enterRule(_localctx, 24, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(ID);
			setState(170); match(LP);
			setState(172);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 19) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(171); arguments();
				}
			}

			setState(174); match(RP);
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
		public Type evalType;
		public ExprContext a;
		public ExprContext b;
		public TerminalNode NOT_EQ_OP() { return getToken(AndroCodeParser.NOT_EQ_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode GT_OP() { return getToken(AndroCodeParser.GT_OP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ_OP() { return getToken(AndroCodeParser.EQ_OP, 0); }
		public TerminalNode LTEQ_OP() { return getToken(AndroCodeParser.LTEQ_OP, 0); }
		public TerminalNode GTEQ_OP() { return getToken(AndroCodeParser.GTEQ_OP, 0); }
		public TerminalNode LT_OP() { return getToken(AndroCodeParser.LT_OP, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); ((ConditionContext)_localctx).a = expr(0);
			setState(177);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ_OP) | (1L << NOT_EQ_OP) | (1L << GT_OP) | (1L << LT_OP) | (1L << GTEQ_OP) | (1L << LTEQ_OP))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(178); ((ConditionContext)_localctx).b = expr(0);
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
		enterRule(_localctx, 28, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); expr(0);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(181); match(4);
				setState(182); expr(0);
				}
				}
				setState(187);
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
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
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
		enterRule(_localctx, 30, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); match(7);
			setState(189); match(LP);
			setState(190); assignment();
			setState(191); match(13);
			setState(192); condition();
			setState(193); match(13);
			setState(194); expr(0);
			setState(195); match(RP);
			setState(196); block();
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
		enterRule(_localctx, 32, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198); match(6);
			setState(199); match(LP);
			setState(200); condition();
			setState(201); match(RP);
			setState(202); block();
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
		enterRule(_localctx, 34, RULE_if_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); match(8);
			setState(205); match(LP);
			setState(206); condition();
			setState(207); match(RP);
			setState(208); block();
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==21) {
				{
				{
				setState(209); match(21);
				setState(210); match(LP);
				setState(211); condition();
				setState(212); match(RP);
				setState(213); block();
				}
				}
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(222);
			_la = _input.LA(1);
			if (_la==16) {
				{
				setState(220); match(16);
				setState(221); block();
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
		enterRule(_localctx, 36, RULE_dev_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); match(ID);
			setState(225); match(3);
			setState(237);
			switch (_input.LA(1)) {
			case 5:
				{
				setState(226); match(5);
				setState(227); match(LP);
				setState(228); match(STRING);
				setState(229); match(4);
				setState(230); expr(0);
				setState(231); match(RP);
				}
				break;
			case 2:
				{
				setState(233); match(2);
				setState(234); match(LP);
				setState(235); match(STRING);
				setState(236); match(RP);
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
		public Type evalType;
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
		enterRule(_localctx, 38, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
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
		enterRule(_localctx, 40, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
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
		case 0: return precpred(_ctx, 8);

		case 1: return precpred(_ctx, 7);

		case 2: return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\66\u00f6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\7\2.\n\2\f\2\16\2\61\13\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\4\3\4\7"+
		"\4C\n\4\f\4\16\4F\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\5\6T\n\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7\\\n\7\f\7\16\7_\13\7\3\b\3\b\3\b"+
		"\3\t\3\t\7\tf\n\t\f\t\16\ti\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n|\n\n\3\n\5\n\177\n\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u0092\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u009d"+
		"\n\13\f\13\16\13\u00a0\13\13\3\f\3\f\3\f\3\f\5\f\u00a6\n\f\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\5\16\u00af\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\7\20\u00ba\n\20\f\20\16\20\u00bd\13\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00da\n\23\f\23\16"+
		"\23\u00dd\13\23\3\23\3\23\5\23\u00e1\n\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00f0\n\24\3\25\3\25\3\26\3\26"+
		"\3\26\2\3\24\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\7\3\2"+
		"%&\3\2#$\3\2(-\3\2.\63\3\2\30\36\u00ff\2/\3\2\2\2\4\65\3\2\2\2\69\3\2"+
		"\2\2\bI\3\2\2\2\nO\3\2\2\2\fX\3\2\2\2\16`\3\2\2\2\20c\3\2\2\2\22~\3\2"+
		"\2\2\24\u0091\3\2\2\2\26\u00a1\3\2\2\2\30\u00a7\3\2\2\2\32\u00ab\3\2\2"+
		"\2\34\u00b2\3\2\2\2\36\u00b6\3\2\2\2 \u00be\3\2\2\2\"\u00c8\3\2\2\2$\u00ce"+
		"\3\2\2\2&\u00e2\3\2\2\2(\u00f1\3\2\2\2*\u00f3\3\2\2\2,.\5\4\3\2-,\3\2"+
		"\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\63"+
		"\5\6\4\2\63\64\7\2\2\3\64\3\3\2\2\2\65\66\7\3\2\2\66\67\7\37\2\2\678\7"+
		"\17\2\28\5\3\2\2\29=\7\21\2\2:<\5\n\6\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2"+
		"=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@D\5\b\5\2AC\5\n\6\2BA\3\2\2\2CF\3\2\2\2"+
		"DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GH\7\24\2\2H\7\3\2\2\2IJ\7\30"+
		"\2\2JK\7\r\2\2KL\7!\2\2LM\7\"\2\2MN\5\20\t\2N\t\3\2\2\2OP\5*\26\2PQ\7"+
		" \2\2QS\7!\2\2RT\5\f\7\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\"\2\2VW\5\20"+
		"\t\2W\13\3\2\2\2X]\5\16\b\2YZ\7\6\2\2Z\\\5\16\b\2[Y\3\2\2\2\\_\3\2\2\2"+
		"][\3\2\2\2]^\3\2\2\2^\r\3\2\2\2_]\3\2\2\2`a\5*\26\2ab\7 \2\2b\17\3\2\2"+
		"\2cg\7\20\2\2df\5\22\n\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2"+
		"\2\2ig\3\2\2\2jk\7\23\2\2k\21\3\2\2\2l\177\5\20\t\2mn\5\24\13\2no\7\17"+
		"\2\2o\177\3\2\2\2pq\5\26\f\2qr\7\17\2\2r\177\3\2\2\2st\5\30\r\2tu\7\17"+
		"\2\2u\177\3\2\2\2v\177\5 \21\2w\177\5\"\22\2x\177\5$\23\2y{\7\f\2\2z|"+
		"\5\24\13\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\177\7\17\2\2~l\3\2\2\2~m\3\2"+
		"\2\2~p\3\2\2\2~s\3\2\2\2~v\3\2\2\2~w\3\2\2\2~x\3\2\2\2~y\3\2\2\2\177\23"+
		"\3\2\2\2\u0080\u0081\b\13\1\2\u0081\u0082\7$\2\2\u0082\u0092\5\24\13\f"+
		"\u0083\u0084\7\25\2\2\u0084\u0092\5\24\13\13\u0085\u0092\5\32\16\2\u0086"+
		"\u0087\7!\2\2\u0087\u0088\5\24\13\2\u0088\u0089\7\"\2\2\u0089\u0092\3"+
		"\2\2\2\u008a\u008b\7 \2\2\u008b\u0092\7\26\2\2\u008c\u008d\7 \2\2\u008d"+
		"\u0092\7\13\2\2\u008e\u0092\5&\24\2\u008f\u0092\5(\25\2\u0090\u0092\7"+
		" \2\2\u0091\u0080\3\2\2\2\u0091\u0083\3\2\2\2\u0091\u0085\3\2\2\2\u0091"+
		"\u0086\3\2\2\2\u0091\u008a\3\2\2\2\u0091\u008c\3\2\2\2\u0091\u008e\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u009e\3\2\2\2\u0093"+
		"\u0094\f\n\2\2\u0094\u0095\7\'\2\2\u0095\u009d\5\24\13\n\u0096\u0097\f"+
		"\t\2\2\u0097\u0098\t\2\2\2\u0098\u009d\5\24\13\n\u0099\u009a\f\b\2\2\u009a"+
		"\u009b\t\3\2\2\u009b\u009d\5\24\13\t\u009c\u0093\3\2\2\2\u009c\u0096\3"+
		"\2\2\2\u009c\u0099\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\25\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\5*\26"+
		"\2\u00a2\u00a5\7 \2\2\u00a3\u00a4\7\16\2\2\u00a4\u00a6\5\24\13\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\27\3\2\2\2\u00a7\u00a8\5\24\13"+
		"\2\u00a8\u00a9\7\16\2\2\u00a9\u00aa\5\24\13\2\u00aa\31\3\2\2\2\u00ab\u00ac"+
		"\7 \2\2\u00ac\u00ae\7!\2\2\u00ad\u00af\5\36\20\2\u00ae\u00ad\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\7\"\2\2\u00b1\33\3\2\2"+
		"\2\u00b2\u00b3\5\24\13\2\u00b3\u00b4\t\4\2\2\u00b4\u00b5\5\24\13\2\u00b5"+
		"\35\3\2\2\2\u00b6\u00bb\5\24\13\2\u00b7\u00b8\7\6\2\2\u00b8\u00ba\5\24"+
		"\13\2\u00b9\u00b7\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\37\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\7\t\2"+
		"\2\u00bf\u00c0\7!\2\2\u00c0\u00c1\5\30\r\2\u00c1\u00c2\7\17\2\2\u00c2"+
		"\u00c3\5\34\17\2\u00c3\u00c4\7\17\2\2\u00c4\u00c5\5\24\13\2\u00c5\u00c6"+
		"\7\"\2\2\u00c6\u00c7\5\20\t\2\u00c7!\3\2\2\2\u00c8\u00c9\7\b\2\2\u00c9"+
		"\u00ca\7!\2\2\u00ca\u00cb\5\34\17\2\u00cb\u00cc\7\"\2\2\u00cc\u00cd\5"+
		"\20\t\2\u00cd#\3\2\2\2\u00ce\u00cf\7\n\2\2\u00cf\u00d0\7!\2\2\u00d0\u00d1"+
		"\5\34\17\2\u00d1\u00d2\7\"\2\2\u00d2\u00db\5\20\t\2\u00d3\u00d4\7\27\2"+
		"\2\u00d4\u00d5\7!\2\2\u00d5\u00d6\5\34\17\2\u00d6\u00d7\7\"\2\2\u00d7"+
		"\u00d8\5\20\t\2\u00d8\u00da\3\2\2\2\u00d9\u00d3\3\2\2\2\u00da\u00dd\3"+
		"\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00e0\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00de\u00df\7\22\2\2\u00df\u00e1\5\20\t\2\u00e0\u00de\3"+
		"\2\2\2\u00e0\u00e1\3\2\2\2\u00e1%\3\2\2\2\u00e2\u00e3\7 \2\2\u00e3\u00ef"+
		"\7\5\2\2\u00e4\u00e5\7\7\2\2\u00e5\u00e6\7!\2\2\u00e6\u00e7\7\61\2\2\u00e7"+
		"\u00e8\7\6\2\2\u00e8\u00e9\5\24\13\2\u00e9\u00ea\7\"\2\2\u00ea\u00f0\3"+
		"\2\2\2\u00eb\u00ec\7\4\2\2\u00ec\u00ed\7!\2\2\u00ed\u00ee\7\61\2\2\u00ee"+
		"\u00f0\7\"\2\2\u00ef\u00e4\3\2\2\2\u00ef\u00eb\3\2\2\2\u00f0\'\3\2\2\2"+
		"\u00f1\u00f2\t\5\2\2\u00f2)\3\2\2\2\u00f3\u00f4\t\6\2\2\u00f4+\3\2\2\2"+
		"\23/=DS]g{~\u0091\u009c\u009e\u00a5\u00ae\u00bb\u00db\u00e0\u00ef";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}