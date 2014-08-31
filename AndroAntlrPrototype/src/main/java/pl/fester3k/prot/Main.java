package pl.fester3k.prot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.AndroCodePreprocessor;
import pl.fester3k.androcode.dataholder.TreeWithSymbolTable;
import pl.fester3k.androcode.exceptions.SemanticAnalysisException;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());
	public static void main(String[] args) throws Exception {
		AndroCodePreprocessor androCode = new AndroCodePreprocessor();
		
		if(args.length>0) {
			try{
				TreeWithSymbolTable treeWithSymbolTable = androCode.analyseCodeFromFile(args[0]);
			} catch (SemanticAnalysisException e) {
				log.error(e.getMessage());
			}
		} else {
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
		}
	}
}
