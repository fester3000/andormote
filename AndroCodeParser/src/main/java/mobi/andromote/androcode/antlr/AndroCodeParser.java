// Generated from AndroCode.g4 by ANTLR 4.2.2

    package mobi.andromote.androcode.antlr;
    import mobi.andromote.androcode.antlr.enums.Type;

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
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, K_INT_TYPE=21, K_VOID_TYPE=22, K_FLOAT_TYPE=23, 
		K_CHAR_TYPE=24, K_STRING_TYPE=25, K_BOOLEAN_TYPE=26, K_DEV_TYPE=27, LIBNAME=28, 
		ID=29, LP=30, RP=31, ADD_OP=32, MINUS_OP=33, MULT_OP=34, DEV_OP=35, INCR_OP=36, 
		DECR_OP=37, EQ_OP=38, NOT_EQ_OP=39, GT_OP=40, LT_OP=41, GTEQ_OP=42, LTEQ_OP=43, 
		AND_OP=44, OR_OP=45, CHAR=46, NEGATED_INT=47, INT=48, NEGATED_FLOAT=49, 
		FLOAT=50, STRING=51, BOOLEAN=52, NULL=53, LINE_COMMENT=54, COMMENT=55, 
		WS=56;
	public static final String[] tokenNames = {
		"<INVALID>", "'use'", "'exec'", "'.'", "','", "'setParam'", "'while'", 
		"'print'", "'for'", "'if'", "'getFeature'", "'='", "'return'", "';'", 
		"'sleep'", "'release'", "'{'", "'else'", "'}'", "'!'", "'elseif'", "'int'", 
		"'void'", "'float'", "'char'", "'String'", "'bool'", "'feature'", "LIBNAME", 
		"ID", "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'++'", "'--'", "'=='", 
		"'!='", "'>'", "'<'", "'>='", "'<='", "'&&'", "'||'", "CHAR", "NEGATED_INT", 
		"INT", "NEGATED_FLOAT", "FLOAT", "STRING", "BOOLEAN", "'null'", "LINE_COMMENT", 
		"COMMENT", "WS"
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
			setState(63); match(13);
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
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(65); function();
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(71); statement();
					}
					} 
				}
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				{
				setState(77); function();
				}
				}
				setState(82);
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
			setState(83); type();
			setState(84); match(ID);
			setState(85); match(LP);
			setState(87);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE))) != 0)) {
				{
				setState(86); parameters();
				}
			}

			setState(89); match(RP);
			setState(90); block();
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
			setState(92); parameter();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(93); match(4);
				setState(94); parameter();
				}
				}
				setState(99);
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
			setState(100); type();
			setState(101); match(ID);
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
			setState(103); match(16);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 8) | (1L << 9) | (1L << 12) | (1L << 14) | (1L << 16) | (1L << K_INT_TYPE) | (1L << K_VOID_TYPE) | (1L << K_FLOAT_TYPE) | (1L << K_CHAR_TYPE) | (1L << K_STRING_TYPE) | (1L << K_BOOLEAN_TYPE) | (1L << K_DEV_TYPE) | (1L << ID) | (1L << LP) | (1L << MINUS_OP) | (1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				{
				setState(104); statement();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110); match(18);
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
			setState(128);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); expr(0);
				setState(114); match(13);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(116); var_declaration();
				setState(117); match(13);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(119); assignment();
				setState(120); match(13);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(122); for_loop();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(123); while_loop();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(124); if_condition();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(125); return_statement();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(126); sleep();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(127); print();
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
			setState(147);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_uminusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(131); match(MINUS_OP);
				setState(132); ((Expr_uminusContext)_localctx).subExpr = expr(7);
				}
				break;

			case 2:
				{
				_localctx = new Expr_castContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133); match(LP);
				setState(134); type();
				setState(135); match(RP);
				setState(136); expr(4);
				}
				break;

			case 3:
				{
				_localctx = new Expr_var_or_fcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138); var_or_function_call();
				}
				break;

			case 4:
				{
				_localctx = new Expr_parenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139); match(LP);
				setState(140); ((Expr_parenthesisContext)_localctx).subExpr = expr(0);
				setState(141); match(RP);
				}
				break;

			case 5:
				{
				_localctx = new Expr_incr_decrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143); ((Expr_incr_decrContext)_localctx).id = match(ID);
				setState(144);
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
				setState(145); dev_operation();
				}
				break;

			case 7:
				{
				_localctx = new Expr_valueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146); ((Expr_valueContext)_localctx).v = value();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(155);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(149);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(150);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT_OP || _la==DEV_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(151); ((Expr_binopContext)_localctx).b = expr(7);
						}
						break;

					case 2:
						{
						_localctx = new Expr_binopContext(new ExprContext(_parentctx, _parentState));
						((Expr_binopContext)_localctx).a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(153);
						((Expr_binopContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD_OP || _la==MINUS_OP) ) {
							((Expr_binopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(154); ((Expr_binopContext)_localctx).b = expr(6);
						}
						break;
					}
					} 
				}
				setState(159);
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
			setState(162);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(160); var_or_function_call();
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
				setState(161); value();
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
			setState(164); var_or_val();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD_OP) {
				{
				{
				setState(165); match(ADD_OP);
				setState(166); var_or_val();
				}
				}
				setState(171);
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
			setState(172); match(14);
			setState(173); match(LP);
			setState(174); match(INT);
			setState(175); match(RP);
			setState(176); match(13);
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
			setState(178); match(7);
			setState(179); match(LP);
			setState(180); mixed_string();
			setState(181); match(RP);
			setState(182); match(13);
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
			setState(184); match(12);
			setState(186);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << LP) | (1L << MINUS_OP) | (1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
				{
				setState(185); expr(0);
				}
			}

			setState(188); match(13);
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
			setState(190); type();
			setState(191); match(ID);
			setState(194);
			_la = _input.LA(1);
			if (_la==11) {
				{
				setState(192); match(11);
				setState(193); expr(0);
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
			setState(196); ((AssignmentContext)_localctx).a = match(ID);
			setState(197); match(11);
			setState(198); ((AssignmentContext)_localctx).b = expr(0);
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
			setState(207);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new Var_callContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200); match(ID);
				}
				break;

			case 2:
				_localctx = new Function_callContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(201); match(ID);
				setState(202); match(LP);
				setState(204);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << LP) | (1L << MINUS_OP) | (1L << CHAR) | (1L << NEGATED_INT) | (1L << INT) | (1L << NEGATED_FLOAT) | (1L << FLOAT) | (1L << STRING) | (1L << BOOLEAN) | (1L << NULL))) != 0)) {
					{
					setState(203); arguments();
					}
				}

				setState(206); match(RP);
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
	public static class Condition_parenthesisContext extends ConditionContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode LP() { return getToken(AndroCodeParser.LP, 0); }
		public TerminalNode RP() { return getToken(AndroCodeParser.RP, 0); }
		public Condition_parenthesisContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).enterCondition_parenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AndroCodeListener ) ((AndroCodeListener)listener).exitCondition_parenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AndroCodeVisitor ) return ((AndroCodeVisitor<? extends T>)visitor).visitCondition_parenthesis(this);
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
			setState(234);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				_localctx = new Condition_var_negatedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(210); match(19);
				setState(211); match(LP);
				setState(212); var_or_function_call();
				setState(213); match(RP);
				}
				break;

			case 2:
				{
				_localctx = new Condition_var_negatedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(215); match(19);
				setState(216); var_or_function_call();
				}
				break;

			case 3:
				{
				_localctx = new Condition_negatedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(217); match(19);
				setState(218); match(LP);
				setState(219); condition(0);
				setState(220); match(RP);
				}
				break;

			case 4:
				{
				_localctx = new Condition_equalityContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(222); ((Condition_equalityContext)_localctx).a = expr(0);
				setState(223);
				((Condition_equalityContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQ_OP || _la==NOT_EQ_OP) ) {
					((Condition_equalityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(224); ((Condition_equalityContext)_localctx).b = expr(0);
				}
				break;

			case 5:
				{
				_localctx = new Condition_relationalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(226); ((Condition_relationalContext)_localctx).a = expr(0);
				setState(227);
				((Condition_relationalContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT_OP) | (1L << LT_OP) | (1L << GTEQ_OP) | (1L << LTEQ_OP))) != 0)) ) {
					((Condition_relationalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				consume();
				setState(228); ((Condition_relationalContext)_localctx).b = expr(0);
				}
				break;

			case 6:
				{
				_localctx = new Condition_parenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(230); match(LP);
				setState(231); condition(0);
				setState(232); match(RP);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(249);
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
					setState(236);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(237); logical_op();
					setState(238); condition(0);
					setState(244);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
					while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(239); logical_op();
							setState(240); condition(0);
							}
							} 
						}
						setState(246);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
					}
					}
					} 
				}
				setState(251);
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
			setState(252); expr(0);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(253); match(4);
				setState(254); expr(0);
				}
				}
				setState(259);
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
			setState(260); match(8);
			setState(261); match(LP);
			setState(262); assignment();
			setState(263); match(13);
			setState(264); condition(0);
			setState(265); match(13);
			setState(268);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(266); ((For_loopContext)_localctx).newValExpr = expr(0);
				}
				break;

			case 2:
				{
				setState(267); ((For_loopContext)_localctx).newValAssign = assignment();
				}
				break;
			}
			setState(270); match(RP);
			setState(271); block();
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
			setState(273); match(6);
			setState(274); match(LP);
			setState(275); condition(0);
			setState(278);
			_la = _input.LA(1);
			if (_la==4) {
				{
				setState(276); match(4);
				setState(277); match(INT);
				}
			}

			setState(280); match(RP);
			setState(281); block();
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
			setState(283); match(9);
			setState(284); match(LP);
			setState(285); condition(0);
			setState(286); match(RP);
			setState(287); block();
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==20) {
				{
				{
				setState(288); match(20);
				setState(289); match(LP);
				setState(290); condition(0);
				setState(291); match(RP);
				setState(292); block();
				}
				}
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(301);
			_la = _input.LA(1);
			if (_la==17) {
				{
				setState(299); match(17);
				setState(300); ((If_conditionContext)_localctx).elseBlock = block();
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
			setState(337);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new Dev_setParamContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(303); match(ID);
				setState(304); match(3);
				setState(305); match(5);
				setState(306); match(LP);
				setState(307); match(STRING);
				setState(308); match(4);
				setState(309); mixed_string();
				setState(310); match(RP);
				}
				break;

			case 2:
				_localctx = new Dev_getContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(312); match(ID);
				setState(313); match(3);
				setState(314); match(10);
				setState(315); match(LP);
				setState(316); match(STRING);
				setState(317); match(RP);
				}
				break;

			case 3:
				_localctx = new Dev_execContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(318); match(ID);
				setState(319); match(3);
				setState(320); match(2);
				setState(321); match(LP);
				setState(329);
				_la = _input.LA(1);
				if (_la==STRING) {
					{
					setState(322); match(STRING);
					setState(323); match(4);
					setState(324); mixed_string();
					setState(327);
					_la = _input.LA(1);
					if (_la==4) {
						{
						setState(325); match(4);
						setState(326); match(INT);
						}
					}

					}
				}

				setState(331); match(RP);
				}
				break;

			case 4:
				_localctx = new Dev_releaseContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(332); match(ID);
				setState(333); match(3);
				setState(334); match(15);
				setState(335); match(LP);
				setState(336); match(RP);
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
			setState(339);
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
			setState(341);
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
			setState(343);
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
		case 2: return precpred(_ctx, 2);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3:\u015c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\7\28\n\2\f\2\16\2;\13\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\4\7\4E\n\4\f\4\16\4H\13\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4\7\4"+
		"Q\n\4\f\4\16\4T\13\4\3\5\3\5\3\5\3\5\5\5Z\n\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\7\6b\n\6\f\6\16\6e\13\6\3\7\3\7\3\7\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\5\t\u0083\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\n\u0096\n\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u009e\n\n\f\n\16"+
		"\n\u00a1\13\n\3\13\3\13\5\13\u00a5\n\13\3\f\3\f\3\f\7\f\u00aa\n\f\f\f"+
		"\16\f\u00ad\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\5\17\u00bd\n\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u00c5\n"+
		"\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\5\22\u00cf\n\22\3\22\5\22"+
		"\u00d2\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u00ed\n\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00f5\n\23\f\23\16\23\u00f8"+
		"\13\23\7\23\u00fa\n\23\f\23\16\23\u00fd\13\23\3\24\3\24\3\24\7\24\u0102"+
		"\n\24\f\24\16\24\u0105\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5"+
		"\25\u010f\n\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u0119\n\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\7\27\u0129\n\27\f\27\16\27\u012c\13\27\3\27\3\27\5\27\u0130\n\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u014a\n\30\5\30\u014c"+
		"\n\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0154\n\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\33\2\4\22$\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$"+
		"&(*,.\60\62\64\2\n\3\2&\'\3\2$%\3\2\"#\3\2()\3\2*-\3\2\60\67\3\2\27\35"+
		"\3\2./\u0170\29\3\2\2\2\4?\3\2\2\2\6F\3\2\2\2\bU\3\2\2\2\n^\3\2\2\2\f"+
		"f\3\2\2\2\16i\3\2\2\2\20\u0082\3\2\2\2\22\u0095\3\2\2\2\24\u00a4\3\2\2"+
		"\2\26\u00a6\3\2\2\2\30\u00ae\3\2\2\2\32\u00b4\3\2\2\2\34\u00ba\3\2\2\2"+
		"\36\u00c0\3\2\2\2 \u00c6\3\2\2\2\"\u00d1\3\2\2\2$\u00ec\3\2\2\2&\u00fe"+
		"\3\2\2\2(\u0106\3\2\2\2*\u0113\3\2\2\2,\u011d\3\2\2\2.\u0153\3\2\2\2\60"+
		"\u0155\3\2\2\2\62\u0157\3\2\2\2\64\u0159\3\2\2\2\668\5\4\3\2\67\66\3\2"+
		"\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\5\6\4\2=>\7"+
		"\2\2\3>\3\3\2\2\2?@\7\3\2\2@A\7\36\2\2AB\7\17\2\2B\5\3\2\2\2CE\5\b\5\2"+
		"DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GL\3\2\2\2HF\3\2\2\2IK\5\20\t"+
		"\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MR\3\2\2\2NL\3\2\2\2OQ\5\b\5"+
		"\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\7\3\2\2\2TR\3\2\2\2UV\5\62"+
		"\32\2VW\7\37\2\2WY\7 \2\2XZ\5\n\6\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7"+
		"!\2\2\\]\5\16\b\2]\t\3\2\2\2^c\5\f\7\2_`\7\6\2\2`b\5\f\7\2a_\3\2\2\2b"+
		"e\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\13\3\2\2\2ec\3\2\2\2fg\5\62\32\2gh\7\37"+
		"\2\2h\r\3\2\2\2im\7\22\2\2jl\5\20\t\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn"+
		"\3\2\2\2np\3\2\2\2om\3\2\2\2pq\7\24\2\2q\17\3\2\2\2r\u0083\5\16\b\2st"+
		"\5\22\n\2tu\7\17\2\2u\u0083\3\2\2\2vw\5\36\20\2wx\7\17\2\2x\u0083\3\2"+
		"\2\2yz\5 \21\2z{\7\17\2\2{\u0083\3\2\2\2|\u0083\5(\25\2}\u0083\5*\26\2"+
		"~\u0083\5,\27\2\177\u0083\5\34\17\2\u0080\u0083\5\30\r\2\u0081\u0083\5"+
		"\32\16\2\u0082r\3\2\2\2\u0082s\3\2\2\2\u0082v\3\2\2\2\u0082y\3\2\2\2\u0082"+
		"|\3\2\2\2\u0082}\3\2\2\2\u0082~\3\2\2\2\u0082\177\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0081\3\2\2\2\u0083\21\3\2\2\2\u0084\u0085\b\n\1\2\u0085"+
		"\u0086\7#\2\2\u0086\u0096\5\22\n\t\u0087\u0088\7 \2\2\u0088\u0089\5\62"+
		"\32\2\u0089\u008a\7!\2\2\u008a\u008b\5\22\n\6\u008b\u0096\3\2\2\2\u008c"+
		"\u0096\5\"\22\2\u008d\u008e\7 \2\2\u008e\u008f\5\22\n\2\u008f\u0090\7"+
		"!\2\2\u0090\u0096\3\2\2\2\u0091\u0092\7\37\2\2\u0092\u0096\t\2\2\2\u0093"+
		"\u0096\5.\30\2\u0094\u0096\5\60\31\2\u0095\u0084\3\2\2\2\u0095\u0087\3"+
		"\2\2\2\u0095\u008c\3\2\2\2\u0095\u008d\3\2\2\2\u0095\u0091\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\u009f\3\2\2\2\u0097\u0098\f\b"+
		"\2\2\u0098\u0099\t\3\2\2\u0099\u009e\5\22\n\t\u009a\u009b\f\7\2\2\u009b"+
		"\u009c\t\4\2\2\u009c\u009e\5\22\n\b\u009d\u0097\3\2\2\2\u009d\u009a\3"+
		"\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\23\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a5\5\"\22\2\u00a3\u00a5\5\60"+
		"\31\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\25\3\2\2\2\u00a6\u00ab"+
		"\5\24\13\2\u00a7\u00a8\7\"\2\2\u00a8\u00aa\5\24\13\2\u00a9\u00a7\3\2\2"+
		"\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\27"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7\20\2\2\u00af\u00b0\7 \2\2\u00b0"+
		"\u00b1\7\62\2\2\u00b1\u00b2\7!\2\2\u00b2\u00b3\7\17\2\2\u00b3\31\3\2\2"+
		"\2\u00b4\u00b5\7\t\2\2\u00b5\u00b6\7 \2\2\u00b6\u00b7\5\26\f\2\u00b7\u00b8"+
		"\7!\2\2\u00b8\u00b9\7\17\2\2\u00b9\33\3\2\2\2\u00ba\u00bc\7\16\2\2\u00bb"+
		"\u00bd\5\22\n\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3"+
		"\2\2\2\u00be\u00bf\7\17\2\2\u00bf\35\3\2\2\2\u00c0\u00c1\5\62\32\2\u00c1"+
		"\u00c4\7\37\2\2\u00c2\u00c3\7\r\2\2\u00c3\u00c5\5\22\n\2\u00c4\u00c2\3"+
		"\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\37\3\2\2\2\u00c6\u00c7\7\37\2\2\u00c7"+
		"\u00c8\7\r\2\2\u00c8\u00c9\5\22\n\2\u00c9!\3\2\2\2\u00ca\u00d2\7\37\2"+
		"\2\u00cb\u00cc\7\37\2\2\u00cc\u00ce\7 \2\2\u00cd\u00cf\5&\24\2\u00ce\u00cd"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\7!\2\2\u00d1"+
		"\u00ca\3\2\2\2\u00d1\u00cb\3\2\2\2\u00d2#\3\2\2\2\u00d3\u00d4\b\23\1\2"+
		"\u00d4\u00d5\7\25\2\2\u00d5\u00d6\7 \2\2\u00d6\u00d7\5\"\22\2\u00d7\u00d8"+
		"\7!\2\2\u00d8\u00ed\3\2\2\2\u00d9\u00da\7\25\2\2\u00da\u00ed\5\"\22\2"+
		"\u00db\u00dc\7\25\2\2\u00dc\u00dd\7 \2\2\u00dd\u00de\5$\23\2\u00de\u00df"+
		"\7!\2\2\u00df\u00ed\3\2\2\2\u00e0\u00e1\5\22\n\2\u00e1\u00e2\t\5\2\2\u00e2"+
		"\u00e3\5\22\n\2\u00e3\u00ed\3\2\2\2\u00e4\u00e5\5\22\n\2\u00e5\u00e6\t"+
		"\6\2\2\u00e6\u00e7\5\22\n\2\u00e7\u00ed\3\2\2\2\u00e8\u00e9\7 \2\2\u00e9"+
		"\u00ea\5$\23\2\u00ea\u00eb\7!\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00d3\3\2"+
		"\2\2\u00ec\u00d9\3\2\2\2\u00ec\u00db\3\2\2\2\u00ec\u00e0\3\2\2\2\u00ec"+
		"\u00e4\3\2\2\2\u00ec\u00e8\3\2\2\2\u00ed\u00fb\3\2\2\2\u00ee\u00ef\f\4"+
		"\2\2\u00ef\u00f0\5\64\33\2\u00f0\u00f6\5$\23\2\u00f1\u00f2\5\64\33\2\u00f2"+
		"\u00f3\5$\23\2\u00f3\u00f5\3\2\2\2\u00f4\u00f1\3\2\2\2\u00f5\u00f8\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8"+
		"\u00f6\3\2\2\2\u00f9\u00ee\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc%\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0103"+
		"\5\22\n\2\u00ff\u0100\7\6\2\2\u0100\u0102\5\22\n\2\u0101\u00ff\3\2\2\2"+
		"\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\'\3"+
		"\2\2\2\u0105\u0103\3\2\2\2\u0106\u0107\7\n\2\2\u0107\u0108\7 \2\2\u0108"+
		"\u0109\5 \21\2\u0109\u010a\7\17\2\2\u010a\u010b\5$\23\2\u010b\u010e\7"+
		"\17\2\2\u010c\u010f\5\22\n\2\u010d\u010f\5 \21\2\u010e\u010c\3\2\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\7!\2\2\u0111\u0112\5\16"+
		"\b\2\u0112)\3\2\2\2\u0113\u0114\7\b\2\2\u0114\u0115\7 \2\2\u0115\u0118"+
		"\5$\23\2\u0116\u0117\7\6\2\2\u0117\u0119\7\62\2\2\u0118\u0116\3\2\2\2"+
		"\u0118\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\7!\2\2\u011b\u011c"+
		"\5\16\b\2\u011c+\3\2\2\2\u011d\u011e\7\13\2\2\u011e\u011f\7 \2\2\u011f"+
		"\u0120\5$\23\2\u0120\u0121\7!\2\2\u0121\u012a\5\16\b\2\u0122\u0123\7\26"+
		"\2\2\u0123\u0124\7 \2\2\u0124\u0125\5$\23\2\u0125\u0126\7!\2\2\u0126\u0127"+
		"\5\16\b\2\u0127\u0129\3\2\2\2\u0128\u0122\3\2\2\2\u0129\u012c\3\2\2\2"+
		"\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012f\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012d\u012e\7\23\2\2\u012e\u0130\5\16\b\2\u012f\u012d\3\2\2\2"+
		"\u012f\u0130\3\2\2\2\u0130-\3\2\2\2\u0131\u0132\7\37\2\2\u0132\u0133\7"+
		"\5\2\2\u0133\u0134\7\7\2\2\u0134\u0135\7 \2\2\u0135\u0136\7\65\2\2\u0136"+
		"\u0137\7\6\2\2\u0137\u0138\5\26\f\2\u0138\u0139\7!\2\2\u0139\u0154\3\2"+
		"\2\2\u013a\u013b\7\37\2\2\u013b\u013c\7\5\2\2\u013c\u013d\7\f\2\2\u013d"+
		"\u013e\7 \2\2\u013e\u013f\7\65\2\2\u013f\u0154\7!\2\2\u0140\u0141\7\37"+
		"\2\2\u0141\u0142\7\5\2\2\u0142\u0143\7\4\2\2\u0143\u014b\7 \2\2\u0144"+
		"\u0145\7\65\2\2\u0145\u0146\7\6\2\2\u0146\u0149\5\26\f\2\u0147\u0148\7"+
		"\6\2\2\u0148\u014a\7\62\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014c\3\2\2\2\u014b\u0144\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3\2"+
		"\2\2\u014d\u0154\7!\2\2\u014e\u014f\7\37\2\2\u014f\u0150\7\5\2\2\u0150"+
		"\u0151\7\21\2\2\u0151\u0152\7 \2\2\u0152\u0154\7!\2\2\u0153\u0131\3\2"+
		"\2\2\u0153\u013a\3\2\2\2\u0153\u0140\3\2\2\2\u0153\u014e\3\2\2\2\u0154"+
		"/\3\2\2\2\u0155\u0156\t\7\2\2\u0156\61\3\2\2\2\u0157\u0158\t\b\2\2\u0158"+
		"\63\3\2\2\2\u0159\u015a\t\t\2\2\u015a\65\3\2\2\2\369FLRYcm\u0082\u0095"+
		"\u009d\u009f\u00a4\u00ab\u00bc\u00c4\u00ce\u00d1\u00ec\u00f6\u00fb\u0103"+
		"\u010e\u0118\u012a\u012f\u0149\u014b\u0153";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}