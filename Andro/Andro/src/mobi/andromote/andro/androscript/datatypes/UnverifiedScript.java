package mobi.andromote.andro.androscript.datatypes;

import java.util.Date;

public class UnverifiedScript extends Script {

	public UnverifiedScript(String name, String content, Date creationDate) {
		super(name, content, creationDate);
	}

	public static UnverifiedScript nullValue() {
		return new UnverifiedScript("", "", new Date());
	}
}
