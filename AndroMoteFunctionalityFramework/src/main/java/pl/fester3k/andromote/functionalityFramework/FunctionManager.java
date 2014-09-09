package pl.fester3k.andromote.functionalityFramework;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.fester3k.andromote.functionalityFramework.exceptions.NoSuchActionException;
import pl.fester3k.andromote.functionalityFramework.exceptions.NoSuchFeatureException;
import pl.fester3k.andromote.functionalityFramework.functions.Function;
import pl.fester3k.andromote.functionalityFramework.functions.FunctionFactory;
import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.platform_controller.VehicleController;
import android.app.Application;

public enum FunctionManager {
	INSTANCE;
	private final Logger log = LoggerFactory.getLogger(FunctionManager.class);
	private FunctionFactory functionFactory;
	private final Map<String, Function> reservedActions = new HashMap<String, Function>();
	private CapabilitiesAnalyzer capabilitiesAnalyzer;

	/**
	 * Inicjalizuje singleton FunctionManager. 
	 * Metoda musi zostać wywołana przed korzystaniem z obiektu
	 * @param mobilePlatform 
	 * @param motorDriver 
	 * @param context
	 */
	public void init(Application application, CapabilitiesAnalyzer capabilitiesAnalyzer, FunctionFactory functionFactory, 
			MobilePlatformType mobilePlatform, MotorDriverType motorDriver) {
		VehicleController.INSTANCE.onCreate(application, mobilePlatform, motorDriver);
		this.functionFactory = functionFactory;
		this.capabilitiesAnalyzer = capabilitiesAnalyzer;
		capabilitiesAnalyzer.checkCurrentCapabilities();
	}
	
	public void onDestroy() {
		VehicleController.INSTANCE.destroy();
	}
	
	/**
	 * Przygotowuje {@link FunctionManager} na potrzeby przetwarzania skryptu
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
		for (Map.Entry<String, Function> actionEntry : reservedActions.entrySet()) {
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
	public Function get(String feature, String varName) throws NoSuchFeatureException {
		Function action = null;
		if(capabilitiesAnalyzer.hasFeature(feature)) {
			action = functionFactory.create(feature);
			if(reservedActions.containsKey(varName)) {
				Function oldAction = reservedActions.get(varName);
				oldAction.cleanup();
			}
			reservedActions.put(varName, action);
		} else {
			throw new NoSuchFeatureException(feature.toString());
		}
		return action;
	}
	
	public void setParam(String varName, String propertyName, String value) throws NoSuchActionException {
		Function action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchActionException(varName);
		}
		action.putParam(propertyName, value);
//		Map<ActionParam, Object> params = action.params;
//		params.put(ActionParam.getValueOf(propertyName), value);
//		action.setParams(params);
	}
	
	public Object execute(String varName) throws NoSuchActionException {
		Object result = null;
		Function action = reservedActions.get(varName);
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
		Function action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchActionException(varName);
		}
		log.debug("Releasing action " + action.getClass().getSimpleName());
		action.cleanup();
		reservedActions.remove(varName);
		log.debug("Action released.");
	}
}
