package mobi.andromote.andro.androscript;

import java.util.List;

import mobi.andromote.andro.androscript.datatypes.Script;

public interface ScriptRepository {
	public void save(Script script);
	public List<Script> getScriptList();
	public Script getScript(int id);
}
