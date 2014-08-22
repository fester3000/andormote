package pl.fester3k.androcode.datatypes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Script {
	protected final String name;
	protected final String content;
	protected final Date creationDate;
	
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
	
	public static Script nullValue() {
		return new Script("", "", new Date());
	}
	
	public static String generateScriptName() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd__kk_mm_ss_SSS");
		return formatter.format(date);
	}
}
