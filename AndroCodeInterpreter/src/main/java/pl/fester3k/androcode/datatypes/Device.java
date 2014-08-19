package pl.fester3k.androcode.datatypes;

import java.util.Properties;

public class Device {
	private final Feature feature;
	private Properties params;
	
	public Device(String featureName) {
		this.feature = Feature.valueOf(featureName);
		this.params = new Properties();
	}
	
	public Device(Feature feature) {
		this.feature = feature;
		this.params = new Properties();
	}

	public Device(Feature feature, Properties params) {
		this.feature = feature;
		this.params = params;
	}
	
	public Feature getFeature() {
		return feature;
	}

	public Properties getParams() {
		return params;
	}

	public void setParams(Properties params) {
		this.params = params;
	}
	
	
}
