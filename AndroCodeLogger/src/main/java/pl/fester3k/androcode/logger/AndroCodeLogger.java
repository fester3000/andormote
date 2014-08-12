package pl.fester3k.androcode.logger;

import org.antlr.v4.runtime.ParserRuleContext;

import pl.fester3k.androcode.antlr.enums.Type;

public interface AndroCodeLogger {

	public abstract void trace(String message);

	public abstract void debug(String message);

	public abstract void info(String message);

	public abstract void warn(String message);

	public abstract void error(String message);

	public abstract void fatal(String message);

	public abstract <T extends ParserRuleContext> void info(String message,
			T ctx);

	public abstract <T extends ParserRuleContext> void debug(String message,
			T ctx);

	public abstract <T extends ParserRuleContext> void error(
			String errorMessage, T ctx);

	public abstract <T extends ParserRuleContext> void printTypeWithContext(
			Type type, T ctx);

	public abstract <T extends ParserRuleContext> void printPromotedTypeWithContext(
			Type type, T ctx);

	public abstract <T extends ParserRuleContext> void printResultTypeWithContext(
			Type type, T ctx);

}
