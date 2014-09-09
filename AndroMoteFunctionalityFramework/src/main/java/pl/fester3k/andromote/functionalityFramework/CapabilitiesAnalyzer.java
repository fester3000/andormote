package pl.fester3k.andromote.functionalityFramework;


public interface CapabilitiesAnalyzer {
	public abstract boolean hasFeature(String feature);

	public abstract void checkCurrentCapabilities();

	public abstract String getAvailableFeatures();
}