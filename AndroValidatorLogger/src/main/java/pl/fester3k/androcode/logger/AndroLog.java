package pl.fester3k.androcode.logger;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.fester3k.androcode.logger.AndroCodeLogger;

import pl.fester3k.androcode.antlr.enums.Type;

public class AndroLog implements AndroCodeLogger {		
	private final Logger log;
	
	public AndroLog(String className) {
		super();
		log = LogManager.getLogger(className);
	}

	@Override
	public void trace(String message) {
		log.trace(message);
	}
	@Override
	public void debug(String message) {
		log.debug(message);
	}
	@Override
	public void info(String message) {
		log.info(message);
	}	
	@Override
	public void warn(String message) {
		log.warn(message);
	}	
	@Override
	public void error(String message) {
		log.error(message);
	}
	@Override
	public void fatal(String message) {
		log.fatal(message);
	}
	
	@Override
	public <T extends ParserRuleContext> void info(String message, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		log.info(messagePrefix + message);
	}
	
	@Override
	public <T extends ParserRuleContext> void debug(String message, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		log.debug(messagePrefix + message);
	}
	
	@Override
	public <T extends ParserRuleContext> void error(String errorMessage, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		log.error(messagePrefix + errorMessage + " in "+ ctx.getParent().getText());
	} 
	
	@Override
	public <T extends ParserRuleContext> void printTypeWithContext(Type type, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		if(type != Type.INVALID) {
			log.debug(messagePrefix + "-> " + type + " " + ctx.getText());
		} else {
			log.error(messagePrefix + "-> " + ctx.getText() + " has invalid type in " + ctx.getParent().getText());
		}
	} 
	
	@Override
	public <T extends ParserRuleContext> void printPromotedTypeWithContext(Type type, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		if(type != null) {
			log.debug(messagePrefix + "-> PromotedType (" + type + ") " + ctx.getText());
		} else {
			log.debug(messagePrefix + "-> Promotion not necessary or impossible for " + ctx.getText() + " in " + ctx.getParent().getText());
		}
	} 
	
	@Override
	public <T extends ParserRuleContext> void printResultTypeWithContext(Type type, T ctx) {
		String messagePrefix = computeMessagePrefix(ctx);
		if(type != null) {
			log.debug(messagePrefix + "-> ResultType (" + type + ") " + ctx.getText());
		} else {
			log.debug(messagePrefix + "-> " + ctx.getText() + " doesn't require type promotion in " + ctx.getParent().getText());
		} 
	} 	
	

	private <T extends ParserRuleContext> String computeMessagePrefix(T ctx) {
		Token startToken = ctx.start;
		return " [" + startToken.getLine() +","+ startToken.getCharPositionInLine() + "] : ";
	}
}
