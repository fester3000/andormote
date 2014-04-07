package mobi.andromote.andro.androscript.datatypes;

import java.util.Date;

public class VerifiedScript extends Script {

	public VerifiedScript(UnverifiedScript unverifiedScript) {
		this.name = unverifiedScript.name;
		this.content = unverifiedScript.content;
		this.creationDate = new Date(unverifiedScript.creationDate.getTime());
	}

}
