package pl.fester3k.antlr.interpreter;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.fester3k.antlr.semanticAnalysis.SemanticAnalysisResult;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.GlobalScope;
import pl.fester3k.antlr.semanticAnalysis.symbols.scopeManagement.Scope;

public class Interpreter {
	InterpreterListener interpreterListener;
	InterpreterVisitor interpreterVisitor;
	
	public Interpreter(SemanticAnalysisResult semanticAnalysisResult) {
//		interpreterListener = new InterpreterListener(semanticAnalysisResult);
		interpreterVisitor = new InterpreterVisitor(semanticAnalysisResult);
	}

	public void interpret(ParseTree tree) {
		interpreterVisitor.visit(tree);
//		ParseTreeWalker walker = new ParseTreeWalker();
//		walker.walk(interpreterListener, tree);
	}
}
