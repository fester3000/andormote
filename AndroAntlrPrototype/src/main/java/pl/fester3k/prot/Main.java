package pl.fester3k.prot;

import pl.fester3k.antlr.AndroCodeProcessor;

public class Main {
	
	public static void main(String[] args) throws Exception {
		AndroCodeProcessor androCode = new AndroCodeProcessor(); 
		androCode.process(args);	
	}
}
