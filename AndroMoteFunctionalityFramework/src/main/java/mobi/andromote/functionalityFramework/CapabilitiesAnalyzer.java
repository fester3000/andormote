package mobi.andromote.functionalityFramework;



public interface CapabilitiesAnalyzer {
	
	public boolean hasFeature(String feature);

	public void checkCurrentCapabilities();

	public String getAvailableFeatures();
}