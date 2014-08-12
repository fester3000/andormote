package pl.fester3k.androcode.interpreter;

import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.androcode.semanticAnalysis.SymbolTable;
import pl.fester3k.prot.utils.AndroLog;

public class Interpreter {
	InterpreterVisitor interpreterVisitor;
	
	public void interpret(ParseTree tree, SymbolTable semanticAnalysisResult) {
		interpreterVisitor = new InterpreterVisitor(semanticAnalysisResult);
		interpreterVisitor.visit(tree);
	}
}
