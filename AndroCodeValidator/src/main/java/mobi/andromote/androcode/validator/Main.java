package mobi.andromote.androcode.validator;

import mobi.andromote.androcode.AndroCodeCompilerFrontend;
import mobi.andromote.androcode.dataholder.TreeWithSymbolTable;
import mobi.andromote.androcode.exceptions.SemanticAnalysisException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());
	public static void main(String[] args) throws Exception {
		AndroCodeCompilerFrontend androCode = new AndroCodeCompilerFrontend();
		
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
