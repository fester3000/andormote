package mobi.andromote.androcode.interpreter;

import mobi.andromote.androcode.logger.AndroLog;
import mobi.andromote.androcode.symbolManagement.SymbolTable;

import org.antlr.v4.runtime.tree.ParseTree;

import mobi.andromote.functionalityFramework.FunctionManager;

public class AndroCodeInterpreter {
	private static final AndroLog log = new AndroLog(InterpreterVisitor.class.getSimpleName());
	InterpreterVisitor interpreterVisitor;
	
	public void interpret(ParseTree tree, SymbolTable symbolTable) {
		logProcessingStarted();
		FunctionManager.INSTANCE.prepare();
		interpreterVisitor = new InterpreterVisitor(symbolTable);
		interpreterVisitor.visit(tree);
		FunctionManager.INSTANCE.cleanup();
		logProcessingDone();
	}

	private void logProcessingDone() {
		log.info("####################################");
		log.info("## AndroCode interpreting is done ##");
		log.info("####################################");
	}

	private void logProcessingStarted() {
		log.info("####################################");
		log.info("## AndroCode interpreting started ##");
		log.info("####################################");
	}

	private void logProcessingFailed() {
		log.info("##############!!!!!!!!!!#############");
		log.info("!!! AndroCode interpreting  failed !!!");
		log.info("##############!!!!!!!!!!#############");
	}
}
