package mobi.andromote.andro.androscript.processingTools;

import java.io.IOException;

import org.apache.log4j.Logger;

import pl.fester3k.androcode.AndroCodePreprocessor;
import pl.fester3k.androcode.dataholder.TreeWithSymbolTable;
import pl.fester3k.androcode.datatypes.Script;
import pl.fester3k.androcode.datatypes.ScriptProcessStatus;
import pl.fester3k.androcode.exceptions.SemanticAnalysisException;
import pl.fester3k.androcode.interpreter.AndroCodeInterpreter;

public class Parser {
	private final Logger log = Logger.getLogger(Parser.class);
	AndroCodePreprocessor preprocess = new AndroCodePreprocessor(); 
	AndroCodeInterpreter interpreter = new AndroCodeInterpreter();
	
	public ScriptProcessStatus parse(Script script) {
		ScriptProcessStatus result;
		log.debug("parsing");
		TreeWithSymbolTable preprocessResult;
		try {
			preprocessResult = preprocess.analyseCode(script.getContent());
			interpreter.interpret(preprocessResult.getTree(), preprocessResult.getSymbolTable());
			result = ScriptProcessStatus.OK;
		} catch (IOException e) {
			log.error(e.getMessage());
			result = ScriptProcessStatus.RUN_FAILED;
		} catch (SemanticAnalysisException e) {
			log.error(e.getMessage());
			result = ScriptProcessStatus.RUN_FAILED;
		}
		return result;
	}
}
