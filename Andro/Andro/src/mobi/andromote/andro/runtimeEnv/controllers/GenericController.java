package mobi.andromote.andro.runtimeEnv.controllers;

public interface GenericController {
	public void add(Object command);
	public long getCommandQueueSize();
	public void cancel();
	public void pause();
	public void resume();
	public ControllerStatus getStatus();
}
