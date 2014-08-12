package pl.fester3k.androcode.interpreter;

import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.androcode.logger.AndroLog;
import pl.fester3k.androcode.semanticAnalysis.SymbolTable;

public class Interpreter {
	InterpreterVisitor interpreterVisitor;
	
	public void interpret(ParseTree tree, SymbolTable semanticAnalysisResult) {
		interpreterVisitor = new InterpreterVisitor(semanticAnalysisResult);
		interpreterVisitor.visit(tree);
	}
}
