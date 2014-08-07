package pl.fester3k.prot.utils;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import pl.fester3k.antlr.semanticAnalysis.Type;

public class PrintUtils {	
	private final String prefix;	
	
	public PrintUtils(String prefix) {
		super();
		this.prefix = prefix;
	}

	public <T extends ParserRuleContext> String computeMessagePrefix(T ctx) {
		Token startToken = ctx.start;
		return prefix + " [" + startToken.getLine() +","+ startToken.getCharPositionInLine() + "] ";
	}

	public <T extends ParserRuleContext> void print(String message, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		System.out.println(messagePrefix + "-> " + message);
	}
	
	public <T extends ParserRuleContext> void printError(String errorMessage, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		System.err.println(messagePrefix + "-> Error: " + errorMessage + " in " + ctx.getParent().getText());
	} 
	
	public <T extends ParserRuleContext> void printTypeWithContext(Type type, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		if(type != Type.INVALID) {
			System.out.println(messagePrefix + "-> " + type + " " + ctx.getText());
		} else {
			System.err.println(messagePrefix + "-> " + ctx.getText() + " has invalid type in " + ctx.getParent().getText());
		}
	} 
	
	public <T extends ParserRuleContext> void printPromotedTypeWithContext(Type type, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		if(type != null) {
			System.out.println(messagePrefix + "-> PromotedType (" + type + ") " + ctx.getText());
		} else {
			System.out.println(messagePrefix + "-> Promotion not necessary or impossible for " + ctx.getText() + " in " + ctx.getParent().getText());
		}
	} 
	
	public <T extends ParserRuleContext> void printResultTypeWithContext(Type type, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		if(type != null) {
			System.out.println(messagePrefix + "-> ResultType (" + type + ") " + ctx.getText());
		} else {
			System.out.println(messagePrefix + "-> " + ctx.getText() + " doesn't require type promotion in " + ctx.getParent().getText());
		} 
	} 	
	
}
