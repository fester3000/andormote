package mobi.andromote.andro.androscript.datatypes;

import java.util.Date;

public abstract class Script {
	protected String name;
	protected String content;
	protected Date creationDate;
	
	public Script() {
		super();
	}
	public Script(String name, String content, Date creationDate) {
		super();
		this.name = name;
		this.content = content;
		this.creationDate = creationDate;
	}
	public String getName() {
		return name;
	}
	public String getContent() {
		return content;
	}
	public Date getCreationDate() {
		return creationDate;
	}
}
