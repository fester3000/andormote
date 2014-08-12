package pl.fester3k.prot;

import pl.fester3k.androcode.AndroCodeProcessor;

public class Main {
	
	public static void main(String[] args) throws Exception {
		AndroCodeProcessor androCode = new AndroCodeProcessor(); 
		androCode.process(args);	
	}
}
