package mobi.andromote.functionalityFramework;

import java.util.HashMap;
import java.util.Map;

import mobi.andromote.functionalityFramework.exceptions.NoSuchFunctionException;
import mobi.andromote.functionalityFramework.exceptions.NoSuchFeatureException;
import mobi.andromote.functionalityFramework.functions.Function;
import mobi.andromote.functionalityFramework.functions.FunctionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andro_mote.commons.DeviceDefinitions.MobilePlatformType;
import andro_mote.commons.DeviceDefinitions.MotorDriverType;
import andro_mote.platform_controller.ElectronicsController;
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
		ElectronicsController.INSTANCE.onCreate(application, mobilePlatform, motorDriver);
		this.functionFactory = functionFactory;
		this.capabilitiesAnalyzer = capabilitiesAnalyzer;
		capabilitiesAnalyzer.checkCurrentCapabilities();
	}
	
	public void onDestroy() {
		ElectronicsController.INSTANCE.destroy();
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
		Function function = null;
		if(capabilitiesAnalyzer.hasFeature(feature)) {
			function = functionFactory.create(feature);
			if(reservedActions.containsKey(varName)) {
				Function oldAction = reservedActions.get(varName);
				oldAction.cleanup();
			}
			reservedActions.put(varName, function);
		} else {
			throw new NoSuchFeatureException(feature.toString());
		}
		return function;
	}
	
	public void setParam(String varName, String propertyName, String value) throws NoSuchFunctionException {
		Function action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchFunctionException(varName);
		}
		action.putParam(propertyName, value);
	}
	
	public Object execute(String varName) throws NoSuchFunctionException {
		Object result = null;
		Function action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchFunctionException(varName);
		}
		log.debug("Running action " + action.getClass().getSimpleName());
		result = action.run();
		log.debug("Result: " + result);			
		log.info("Action is done");
		return result;
	}

	public void release(String varName) throws NoSuchFunctionException {
		Function action = reservedActions.get(varName);
		if(action == null) {
			throw new NoSuchFunctionException(varName);
		}
		log.debug("Releasing action " + action.getClass().getSimpleName());
		action.cleanup();
		reservedActions.remove(varName);
		log.debug("Action released.");
	}
}
