package mobi.andromote.andro.androscript;

import java.io.IOException;

import mobi.andromote.androcode.AndroCodeCompilerFrontend;
import mobi.andromote.androcode.dataholder.TreeWithSymbolTable;
import mobi.andromote.androcode.datatypes.Script;
import mobi.andromote.androcode.datatypes.ScriptProcessStatus;
import mobi.andromote.androcode.exceptions.SemanticAnalysisException;
import mobi.andromote.androcode.interpreter.AndroCodeInterpreter;

import org.apache.log4j.Logger;

import android.content.Context;

public enum AndroscriptProcessor {
	INSTANCE;

	private final Logger log = Logger.getLogger(AndroscriptProcessor.class);
	AndroCodeCompilerFrontend compilerFrontEnd = new AndroCodeCompilerFrontend(); 
	AndroCodeInterpreter interpreter = new AndroCodeInterpreter();

	public ScriptProcessStatus process(Script script, Context context) {
		ScriptProcessStatus result;
		SDScriptRepository scriptRepository = new SDScriptRepository(context);
		log.debug("processing script " + script.getName());
		result = parse(script);
		scriptRepository.saveExternally(script);
		return result;
	}

	private ScriptProcessStatus parse(Script script) {
		ScriptProcessStatus result;
		log.debug("parsing");
		TreeWithSymbolTable preprocessResult;
		try {
			preprocessResult = compilerFrontEnd.analyseCode(script.getContent());
			interpreter.interpret(preprocessResult.getTree(), preprocessResult.getSymbolTable());
			result = ScriptProcessStatus.OK;
		} catch (SemanticAnalysisException e) {
			log.error(e.getMessage());
			result = ScriptProcessStatus.VALIDATION_FAILED;
		} catch (IOException e) {
			log.error(e.getMessage());
			result = ScriptProcessStatus.RUN_FAILED;
		} catch(Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			result = ScriptProcessStatus.RUN_FAILED;
		}
		return result;
	}
}
