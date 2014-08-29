package pl.fester3k.androcode.deviceManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.datatypes.ServiceWithHandler;
import pl.fester3k.androcode.deviceManagement.action.Action;
import pl.fester3k.androcode.deviceManagement.action.ActionFactory;
import pl.fester3k.androcode.interpreter.exceptions.NoSuchActionException;
import pl.fester3k.androcode.interpreter.exceptions.NoSuchFeatureException;
import android.content.Context;

public enum ActionManager {
	INSTANCE;
	private final Logger log = LoggerFactory.getLogger(ActionManager.class);
	private ActionFactory actionFactory;
	private final Map<String, Action> reservedActions = new HashMap<String, Action>();
	private CapabilitiesAnalyzer capabilitiesAnalyzer;

	/**
	 * Inicjalizuje singleton ActionManager. 
	 * Metoda musi zostać wywołana przed korzystaniem z obiektu
	 * @param context
	 */
	public void init(ServiceWithHandler service) {
		actionFactory = new ActionFactory((Context)service, service.getHandler());
		capabilitiesAnalyzer = new CapabilitiesAnalyzer((Context)service);
	}
	
	/**
	 * Przygotowuje {@link ActionManager} na potrzeby przetwarzania skryptu
	 * Metoda powinna zostać uruchomiona na początku pzetwarzania skryptu 
	 */
	public void prepare() {
		capabilitiesAnalyzer.checkCurrentCapabilities();
		log.info(capabilitiesAnalyzer.getAvailableFeatures());
	}
	
	/**
	 * Sprząta po przetworzeniu skryptu
	 * Metoda powinna zostać uruchomiona na końcu pzetwarzania skryptu 
	 */
	public void cleanup() {
		for (Map.Entry<String, Action> actionEntry : reservedActions.entrySet()) {
			actionEntry.getValue().cleanup();
		}
		reservedActions.clear();
	}
	
	/**
	 * Zwraca akcję danego typu {@link Feature} i dodaje ją do mapy akcji z kluczem varName
	 * @param feature typ akcji
	 * @param varName nazwa zmiennej androCode
	 * @return skonfigurowana akcja lub null jeśli akcja danego typu nie jest dostępna na urządzeniu 
	 * @throws NoSuchFeatureException 
	 */
	public Action get(Feature feature, String varName) throws NoSuchFeatureException {
		Action action = null;
		if(capabilitiesAnalyzer.hasFeature(feature)) {
			action = actionFactory.createAction(feature);
			reservedActions.put(varName, action);
		} else {
			throw new NoSuchFeatureException(feature.toString());
		}
		return action;
	}
	
	public void setParam(String varName, String propertyName, String value) throws NoSuchActionException {
		Action action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchActionException(varName);
		}
		Properties params = action.getParams();
		params.put(propertyName, value);
		action.setParams(params);
	}
	
	public Object execute(String varName) throws NoSuchActionException {
		Object result = null;
		Action action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchActionException(varName);
		}
		log.debug("Running action " + action.getClass().getSimpleName());
		result = action.run();
		log.debug("Result: " + result);			
		log.info("Action is done");
		return result;
	}

	public void release(String varName) throws NoSuchActionException {
		Action action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchActionException(varName);
		}
		log.debug("Releasing action " + action.getClass().getSimpleName());
		action.cleanup();
		reservedActions.remove(varName);
		log.debug("Action released.");
	}
}
