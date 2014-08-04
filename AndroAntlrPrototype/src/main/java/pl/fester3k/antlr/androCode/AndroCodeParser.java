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
		MULT_OP=35, DEV_OP=36, EQ_OP=37, NOT_EQ_OP=38, GT_OP=39, LT_OP=40, GTEQ_OP=41, 
		LTEQ_OP=42, CHAR=43, INT=44, FLOAT=45, STRING=46, BOOLEAN=47, NULL=48, 
		LINE_COMMENT=49, COMMENT=50, WS=51;
	public static final String[] tokenNames = {
		"<INVALID>", "'use'", "'getDevice'", "'.'", "','", "'setParam'", "'while'", 
		"'for'", "'if'", "'--'", "'main'", "'return'", "'='", "';'", "'{'", "'begin'", 
		"'else'", "'}'", "'end'", "'!'", "'++'", "'elseif'", "'int'", "'void'", 
		"'float'", "'char'", "'String'", "'bool'", "'device'", "LIBNAME", "ID", 
		"'('", "')'", "'+'", "'-'", "'*'", "'/'", "'=='", "'!='", "'>'", "'<'", 
		"'>='", "'<='", "CHAR", "INT", "FLOAT", "STRING", "BOOLEAN", "'null'", 
		"LINE_COMMENT", "COMMENT", "WS"
	};
	public static final int
		RULE_script = 0, RULE_lib_includes = 1, RULE_body = 2, RULE_main_function = 3, 
		RULE_function = 4, RULE_parameters = 5, RULE_parameter = 6, RULE_block = 7, 
		RULE_statement = 8, RULE_expr = 9, RULE_return_statement = 10, RULE_var_declaration = 11, 
		RULE_assignment = 12, RULE_function_call = 13, RULE_condition = 14, RULE_arguments = 15, 
		RULE_for_loop = 16, RULE_while_loop = 17, RULE_if_condition = 18, RULE_dev_operation = 19, 
		RULE_value = 20, RULE_type = 21;
	public static final String[] ruleNames = {
		"script", "lib_includes", "body", "main_function", "function", "parameters", 
		"parameter", "block", "statement", "expr", "return_statement", "var_declaration", 
		"assignment", "function_call", "condition", "arguments", "for_loop", "while_loop", 
		"if_condition", "dev_operation", "value", "type"
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
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==1) {
				{
				{
				setState(44); lib_includes();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50); body();
			setState(51); match(EOF);
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
			setState(53); match(1);
			setState(54); match(LIBNAME);
			setState(55); match(13);
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
			setState(57); match(15);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(58); function();
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(64); main_function();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				{
				setState(65); function();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71); match(18);
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
			setState(73); match(K_INT_TYPE);
			setState(74); match(10);
			setState(75); match(LP);
			setState(76); match(RP);
			setState(77); block();
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
			setState(79); type();
			setState(80); match(ID);
			setState(81); match(LP);
			setState(83);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				setState(82); parameters();
				}
			}

			setState(85); match(RP);
			setState(86); block();
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
			setState(88); parameter();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(89); match(4);
				setState(90); parameter();
				}
				}
				setState(95);
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
			setState(96); type();
			setState(97); match(ID);
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
			setState(99); match(14);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 8) | (1L << 11) | (1L << 14) | (1L << 19) | (1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				{
				setState(100); statement();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106); match(17);
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
			setState(122);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109); expr(0);
				setState(110); match(13);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112); var_declaration();
				setState(113); match(13);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(115); assignment();
				setState(116); match(13);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(118); for_loop();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(119); while_loop();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(120); if_condition();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(121); return_statement();
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
			setState(141);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_uminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(125); match(SUBST_OP);
				setState(126); ((Expr_uminusContext)_localctx).subExpr = expr(9);
				}
				break;

			case 2:
				{
				_localctx = new Expr_unotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127); match(19);
				setState(128); ((Expr_unotContext)_localctx).subExpr = expr(8);
				}
				break;

			case 3:
				{
				_localctx = new Expr_fcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129); ((Expr_fcallContext)_localctx).fcal = function_call();
				}
				break;

			case 4:
				{
				_localctx = new Expr_parenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130); match(LP);
				setState(131); ((Expr_parenthesisContext)_localctx).subExpr = expr(0);
				setState(132); match(RP);
				}
				break;

			case 5:
				{
				_localctx = new Expr_incrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134); ((Expr_incrContext)_localctx).id = match(ID);
				setState(135); match(20);
				}
				break;

			case 6:
				{
				_localctx = new Expr_decrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136); ((Expr_decrContext)_localctx).id = match(ID);
				setState(137); match(9);
				}
				break;

			case 7:
				{
				_localctx = new Expr_devContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138); dev_operation();
				}
				break;

			case 8:
				{
				_localctx = new Expr_valueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139); ((Expr_valueContext)_localctx).v = value();
				}
				break;

			case 9:
				{
				_localctx = new Expr_varContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140); match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(149);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(143);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(144);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT_OP || _la==DEV_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(145); ((Expr_binopContext)_localctx).b = expr(8);
						}
						break;

					case 2:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(146);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(147);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD_OP || _la==SUBST_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(148); ((Expr_binopContext)_localctx).b = expr(7);
						}
						break;
					}
					} 
				}
				setState(153);
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
			setState(154); match(11);
			setState(156);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 19) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(155); expr(0);
				}
			}

			setState(158); match(13);
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
		enterRule(_localctx, 22, RULE_var_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); type();
			setState(161); match(ID);
			setState(164);
			_la = _input.LA(1);
			if (_la==12) {
				{
				setState(162); match(12);
				setState(163); expr(0);
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
		enterRule(_localctx, 24, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); ((AssignmentContext)_localctx).a = expr(0);
			setState(167); match(12);
			setState(168); ((AssignmentContext)_localctx).b = expr(0);
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
		enterRule(_localctx, 26, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170); match(ID);
			setState(171); match(LP);
			setState(173);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 19) | (1L << ID) | (1L << LP) | (1L << SUBST_OP) | (1L << CHAR) | (1L << INT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(172); arguments();
				}
			}

			setState(175); match(RP);
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
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
			this.evalType = ctx.evalType;
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
		enterRule(_localctx, 28, RULE_condition);
		int _la;
		try {
			setState(185);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new Condition_equalityContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(177); ((Condition_equalityContext)_localctx).a = expr(0);
				setState(178);
				((Condition_equalityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQ_OP || _la==NOT_EQ_OP) ) {
					((Condition_equalityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(179); ((Condition_equalityContext)_localctx).b = expr(0);
				}
				break;

			case 2:
				_localctx = new Condition_relationalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(181); ((Condition_relationalContext)_localctx).a = expr(0);
				setState(182);
				((Condition_relationalContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT_OP) | (1L << LT_OP) | (1L << GTEQ_OP) | (1L << LTEQ_OP))) != 0)) ) {
					((Condition_relationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(183); ((Condition_relationalContext)_localctx).b = expr(0);
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
		enterRule(_localctx, 30, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); expr(0);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(188); match(4);
				setState(189); expr(0);
				}
				}
				setState(194);
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
		enterRule(_localctx, 32, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195); match(7);
			setState(196); match(LP);
			setState(197); assignment();
			setState(198); match(13);
			setState(199); condition();
			setState(200); match(13);
			setState(201); expr(0);
			setState(202); match(RP);
			setState(203); block();
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
		enterRule(_localctx, 34, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205); match(6);
			setState(206); match(LP);
			setState(207); condition();
			setState(208); match(RP);
			setState(209); block();
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
		enterRule(_localctx, 36, RULE_if_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); match(8);
			setState(212); match(LP);
			setState(213); condition();
			setState(214); match(RP);
			setState(215); block();
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==21) {
				{
				{
				setState(216); match(21);
				setState(217); match(LP);
				setState(218); condition();
				setState(219); match(RP);
				setState(220); block();
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(229);
			_la = _input.LA(1);
			if (_la==16) {
				{
				setState(227); match(16);
				setState(228); block();
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
		enterRule(_localctx, 38, RULE_dev_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); match(ID);
			setState(232); match(3);
			setState(244);
			switch (_input.LA(1)) {
			case 5:
				{
				setState(233); match(5);
				setState(234); match(LP);
				setState(235); match(STRING);
				setState(236); match(4);
				setState(237); expr(0);
				setState(238); match(RP);
				}
				break;
			case 2:
				{
				setState(240); match(2);
				setState(241); match(LP);
				setState(242); match(STRING);
				setState(243); match(RP);
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
		enterRule(_localctx, 40, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
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
		enterRule(_localctx, 42, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\65\u00fd\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\7\2\60\n\2\f\2"+
		"\16\2\63\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\7\4>\n\4\f\4\16\4A\13"+
		"\4\3\4\3\4\7\4E\n\4\f\4\16\4H\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\5\6V\n\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7^\n\7\f\7\16\7a\13\7"+
		"\3\b\3\b\3\b\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n}\n\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0090"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0098\n\13\f\13\16\13\u009b\13"+
		"\13\3\f\3\f\5\f\u009f\n\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00a7\n\r\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\5\17\u00b0\n\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20\u00bc\n\20\3\21\3\21\3\21\7\21\u00c1\n"+
		"\21\f\21\16\21\u00c4\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\7\24\u00e1\n\24\f\24\16\24\u00e4\13\24\3\24\3\24"+
		"\5\24\u00e8\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\5\25\u00f7\n\25\3\26\3\26\3\27\3\27\3\27\2\3\24\30\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\b\3\2%&\3\2#$\3\2\'(\3\2),\3"+
		"\2-\62\3\2\30\36\u0105\2\61\3\2\2\2\4\67\3\2\2\2\6;\3\2\2\2\bK\3\2\2\2"+
		"\nQ\3\2\2\2\fZ\3\2\2\2\16b\3\2\2\2\20e\3\2\2\2\22|\3\2\2\2\24\u008f\3"+
		"\2\2\2\26\u009c\3\2\2\2\30\u00a2\3\2\2\2\32\u00a8\3\2\2\2\34\u00ac\3\2"+
		"\2\2\36\u00bb\3\2\2\2 \u00bd\3\2\2\2\"\u00c5\3\2\2\2$\u00cf\3\2\2\2&\u00d5"+
		"\3\2\2\2(\u00e9\3\2\2\2*\u00f8\3\2\2\2,\u00fa\3\2\2\2.\60\5\4\3\2/.\3"+
		"\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2"+
		"\2\2\64\65\5\6\4\2\65\66\7\2\2\3\66\3\3\2\2\2\678\7\3\2\289\7\37\2\29"+
		":\7\17\2\2:\5\3\2\2\2;?\7\21\2\2<>\5\n\6\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2"+
		"\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BF\5\b\5\2CE\5\n\6\2DC\3\2\2\2EH\3\2\2"+
		"\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7\24\2\2J\7\3\2\2\2KL\7\30"+
		"\2\2LM\7\f\2\2MN\7!\2\2NO\7\"\2\2OP\5\20\t\2P\t\3\2\2\2QR\5,\27\2RS\7"+
		" \2\2SU\7!\2\2TV\5\f\7\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\"\2\2XY\5\20"+
		"\t\2Y\13\3\2\2\2Z_\5\16\b\2[\\\7\6\2\2\\^\5\16\b\2][\3\2\2\2^a\3\2\2\2"+
		"_]\3\2\2\2_`\3\2\2\2`\r\3\2\2\2a_\3\2\2\2bc\5,\27\2cd\7 \2\2d\17\3\2\2"+
		"\2ei\7\20\2\2fh\5\22\n\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2"+
		"\2\2ki\3\2\2\2lm\7\23\2\2m\21\3\2\2\2n}\5\20\t\2op\5\24\13\2pq\7\17\2"+
		"\2q}\3\2\2\2rs\5\30\r\2st\7\17\2\2t}\3\2\2\2uv\5\32\16\2vw\7\17\2\2w}"+
		"\3\2\2\2x}\5\"\22\2y}\5$\23\2z}\5&\24\2{}\5\26\f\2|n\3\2\2\2|o\3\2\2\2"+
		"|r\3\2\2\2|u\3\2\2\2|x\3\2\2\2|y\3\2\2\2|z\3\2\2\2|{\3\2\2\2}\23\3\2\2"+
		"\2~\177\b\13\1\2\177\u0080\7$\2\2\u0080\u0090\5\24\13\13\u0081\u0082\7"+
		"\25\2\2\u0082\u0090\5\24\13\n\u0083\u0090\5\34\17\2\u0084\u0085\7!\2\2"+
		"\u0085\u0086\5\24\13\2\u0086\u0087\7\"\2\2\u0087\u0090\3\2\2\2\u0088\u0089"+
		"\7 \2\2\u0089\u0090\7\26\2\2\u008a\u008b\7 \2\2\u008b\u0090\7\13\2\2\u008c"+
		"\u0090\5(\25\2\u008d\u0090\5*\26\2\u008e\u0090\7 \2\2\u008f~\3\2\2\2\u008f"+
		"\u0081\3\2\2\2\u008f\u0083\3\2\2\2\u008f\u0084\3\2\2\2\u008f\u0088\3\2"+
		"\2\2\u008f\u008a\3\2\2\2\u008f\u008c\3\2\2\2\u008f\u008d\3\2\2\2\u008f"+
		"\u008e\3\2\2\2\u0090\u0099\3\2\2\2\u0091\u0092\f\t\2\2\u0092\u0093\t\2"+
		"\2\2\u0093\u0098\5\24\13\n\u0094\u0095\f\b\2\2\u0095\u0096\t\3\2\2\u0096"+
		"\u0098\5\24\13\t\u0097\u0091\3\2\2\2\u0097\u0094\3\2\2\2\u0098\u009b\3"+
		"\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\25\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009c\u009e\7\r\2\2\u009d\u009f\5\24\13\2\u009e\u009d\3"+
		"\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\7\17\2\2\u00a1"+
		"\27\3\2\2\2\u00a2\u00a3\5,\27\2\u00a3\u00a6\7 \2\2\u00a4\u00a5\7\16\2"+
		"\2\u00a5\u00a7\5\24\13\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\31\3\2\2\2\u00a8\u00a9\5\24\13\2\u00a9\u00aa\7\16\2\2\u00aa\u00ab\5\24"+
		"\13\2\u00ab\33\3\2\2\2\u00ac\u00ad\7 \2\2\u00ad\u00af\7!\2\2\u00ae\u00b0"+
		"\5 \21\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\7\"\2\2\u00b2\35\3\2\2\2\u00b3\u00b4\5\24\13\2\u00b4\u00b5\t\4"+
		"\2\2\u00b5\u00b6\5\24\13\2\u00b6\u00bc\3\2\2\2\u00b7\u00b8\5\24\13\2\u00b8"+
		"\u00b9\t\5\2\2\u00b9\u00ba\5\24\13\2\u00ba\u00bc\3\2\2\2\u00bb\u00b3\3"+
		"\2\2\2\u00bb\u00b7\3\2\2\2\u00bc\37\3\2\2\2\u00bd\u00c2\5\24\13\2\u00be"+
		"\u00bf\7\6\2\2\u00bf\u00c1\5\24\13\2\u00c0\u00be\3\2\2\2\u00c1\u00c4\3"+
		"\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3!\3\2\2\2\u00c4\u00c2"+
		"\3\2\2\2\u00c5\u00c6\7\t\2\2\u00c6\u00c7\7!\2\2\u00c7\u00c8\5\32\16\2"+
		"\u00c8\u00c9\7\17\2\2\u00c9\u00ca\5\36\20\2\u00ca\u00cb\7\17\2\2\u00cb"+
		"\u00cc\5\24\13\2\u00cc\u00cd\7\"\2\2\u00cd\u00ce\5\20\t\2\u00ce#\3\2\2"+
		"\2\u00cf\u00d0\7\b\2\2\u00d0\u00d1\7!\2\2\u00d1\u00d2\5\36\20\2\u00d2"+
		"\u00d3\7\"\2\2\u00d3\u00d4\5\20\t\2\u00d4%\3\2\2\2\u00d5\u00d6\7\n\2\2"+
		"\u00d6\u00d7\7!\2\2\u00d7\u00d8\5\36\20\2\u00d8\u00d9\7\"\2\2\u00d9\u00e2"+
		"\5\20\t\2\u00da\u00db\7\27\2\2\u00db\u00dc\7!\2\2\u00dc\u00dd\5\36\20"+
		"\2\u00dd\u00de\7\"\2\2\u00de\u00df\5\20\t\2\u00df\u00e1\3\2\2\2\u00e0"+
		"\u00da\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e7\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\7\22\2\2\u00e6"+
		"\u00e8\5\20\t\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\'\3\2\2"+
		"\2\u00e9\u00ea\7 \2\2\u00ea\u00f6\7\5\2\2\u00eb\u00ec\7\7\2\2\u00ec\u00ed"+
		"\7!\2\2\u00ed\u00ee\7\60\2\2\u00ee\u00ef\7\6\2\2\u00ef\u00f0\5\24\13\2"+
		"\u00f0\u00f1\7\"\2\2\u00f1\u00f7\3\2\2\2\u00f2\u00f3\7\4\2\2\u00f3\u00f4"+
		"\7!\2\2\u00f4\u00f5\7\60\2\2\u00f5\u00f7\7\"\2\2\u00f6\u00eb\3\2\2\2\u00f6"+
		"\u00f2\3\2\2\2\u00f7)\3\2\2\2\u00f8\u00f9\t\6\2\2\u00f9+\3\2\2\2\u00fa"+
		"\u00fb\t\7\2\2\u00fb-\3\2\2\2\24\61?FU_i|\u008f\u0097\u0099\u009e\u00a6"+
		"\u00af\u00bb\u00c2\u00e2\u00e7\u00f6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}