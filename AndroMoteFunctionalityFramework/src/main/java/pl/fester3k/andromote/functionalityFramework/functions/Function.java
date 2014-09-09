package pl.fester3k.andromote.functionalityFramework.functions;



public interface Function {
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
