package pl.fester3k.androcode.interpreter;

import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.androcode.deviceManagement.ActionManager;
import pl.fester3k.androcode.deviceManagement.RideController;
import pl.fester3k.androcode.logger.AndroLog;
import pl.fester3k.androcode.symbolManagement.SymbolTable;

public class AndroCodeInterpreter {
	private static final AndroLog log = new AndroLog(InterpreterVisitor.class.getSimpleName());
	InterpreterVisitor interpreterVisitor;
	
	public void interpret(ParseTree tree, SymbolTable symbolTable) {
		logProcessingStarted();
		ActionManager.INSTANCE.prepare();
		interpreterVisitor = new InterpreterVisitor(symbolTable);
		interpreterVisitor.visit(tree);
		ActionManager.INSTANCE.cleanup();
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
