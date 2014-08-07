package pl.fester3k.antlr.semanticAnalysis.typeCheck;

import lombok.Getter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import pl.fester3k.antlr.androCode.AndroCodeParser.ExprContext;
import pl.fester3k.antlr.semanticAnalysis.Type;
import pl.fester3k.prot.exceptions.IncompatibleTypeException;
import pl.fester3k.prot.utils.PrintUtils;

public class TypePromoter {
	@Getter private ParseTreeProperty<Type> promotedTypes = new ParseTreeProperty<Type>();
	@Getter private TokenStreamRewriter rewriter;

	public TypePromoter(TokenStream tokens) {
		super();
		this.rewriter = new TokenStreamRewriter(tokens);
	}	
		
	

}
