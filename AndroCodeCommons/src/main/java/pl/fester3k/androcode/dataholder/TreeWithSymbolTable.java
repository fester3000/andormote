package pl.fester3k.androcode.dataholder;

import org.antlr.v4.runtime.tree.ParseTree;

import pl.fester3k.androcode.symbolManagement.SymbolTable;

public class TreeWithSymbolTable {
	private final ParseTree tree;
	private final SymbolTable symbolTable;
	public TreeWithSymbolTable(ParseTree tree, SymbolTable symbolTable) {
		super();
		this.tree = tree;
		this.symbolTable = symbolTable;
	}
	public ParseTree getTree() {
		return tree;
	}
	public SymbolTable getSymbolTable() {
		return symbolTable;
	}
}
