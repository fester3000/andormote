package pl.fester3k.androcode.deviceManagement.action;

import java.util.Properties;

public interface Action {
	/**
	 * Uruchamia wykonanie danej akcji
	 * @return
	 */
	public Object run();
	
	/**
	 * Czyści i zwalnia zasoby systemowe po zakończenu korzystania z akcji 
	 */
	public void cleanup();
	
	public Properties getParams();

	public void setParams(Properties params);
}
