package mobi.andromote.andro.logger;

import java.io.File;

import org.apache.log4j.Level;

import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;

public class ConfigureLog4J {
	public static void configure() {
		final LogConfigurator logConfigurator = new LogConfigurator();

		
		logConfigurator.setFileName(Environment.getExternalStorageDirectory() + "/data/mobi.andromote.andro/log/" + File.separator + "andro.log");
		logConfigurator.setMaxFileSize(1000);
		logConfigurator.setMaxBackupSize(10);
		logConfigurator.setRootLevel(Level.DEBUG);
		logConfigurator.setUseLogCatAppender(true);
		logConfigurator.setUseFileAppender(true);
		logConfigurator.setLevel("org.apache", Level.DEBUG);
		logConfigurator.configure();
	}
}
