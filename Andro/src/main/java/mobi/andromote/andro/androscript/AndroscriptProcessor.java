package mobi.andromote.andro.androscript;

import java.io.IOException;

import org.apache.log4j.Logger;

import pl.fester3k.androcode.AndroCodePreprocessor;
import pl.fester3k.androcode.dataholder.TreeWithSymbolTable;
import pl.fester3k.androcode.datatypes.Script;
import pl.fester3k.androcode.datatypes.ScriptProcessStatus;
import pl.fester3k.androcode.deviceManagement.RideController;
import pl.fester3k.androcode.exceptions.SemanticAnalysisException;
import pl.fester3k.androcode.interpreter.AndroCodeInterpreter;
import android.content.Context;

public enum AndroscriptProcessor {
	INSTANCE;

	private final Logger log = Logger.getLogger(AndroscriptProcessor.class);
	AndroCodePreprocessor preprocessor = new AndroCodePreprocessor(); 
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
			preprocessResult = preprocessor.analyseCode(script.getContent());
			interpreter.interpret(preprocessResult.getTree(), preprocessResult.getSymbolTable());
			RideController.INSTANCE.stopRide();
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
