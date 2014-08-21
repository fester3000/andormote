package mobi.andromote.andro.androscript;

import java.util.List;

import pl.fester3k.androcode.datatypes.Script;

public interface ScriptRepository {
	public List<Script> getScriptList();
	public Script getScript(int id);
	public void save(Script script);
}
