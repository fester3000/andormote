package pl.fester3k.prot;

import pl.fester3k.androcode.AndroCodeProcessor;
import pl.fester3k.androcode.logger.AndroLog;

public class Main {
	private static final AndroLog log = new AndroLog(Main.class.getSimpleName());
	public static void main(String[] args) throws Exception {
		AndroCodeProcessor androCode = new AndroCodeProcessor();
		if(args.length>0) {
			androCode.processFile(args[0]);	
		} else {
			log.info("Usage: androcode [filename]");
			log.info("where filename is the path to androcode script file to process");
		}
	}
}
