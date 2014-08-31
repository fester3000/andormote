package pl.fester3k.androcode.deviceManagement.action;



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
	public void putParam(String propertyName, String value);
}
