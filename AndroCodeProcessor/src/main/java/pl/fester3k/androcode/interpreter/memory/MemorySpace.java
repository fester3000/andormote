package pl.fester3k.androcode.interpreter.memory;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class MemorySpace {
	@Getter private final String name;
	private Map<String, Object> members = new HashMap<String, Object>();
	
	public MemorySpace(String name) {
		this.name = name;
	}
		
	public Object get(String memberId) {
		return members.get(memberId);
	}
	
	public void put(String memberId, Object object) {
		members.put(memberId, object);
	}
	
	public boolean contains(String memberId) {
		return members.containsKey(memberId);
	}
	
	public int sizeOf() {
		return members.size();
	}
	
	public String toString() {
		return name + ":" + members;
	}
}
