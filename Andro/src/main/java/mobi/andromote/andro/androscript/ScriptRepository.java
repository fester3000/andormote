package mobi.andromote.andro.androscript;

import java.util.List;

import mobi.andromote.androcode.datatypes.Script;

public interface ScriptRepository {
	public List<Script> getScriptList();
	public Script getScript(int id);
	public void save(Script script);
}
