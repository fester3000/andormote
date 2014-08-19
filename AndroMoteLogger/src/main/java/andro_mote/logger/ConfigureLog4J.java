package andro_mote.logger;

import java.io.File;

import org.apache.log4j.Level;

import android.os.Environment;
import android.util.Log;
import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Call {@link #configure()} from your application's activity.
 */
public class ConfigureLog4J {
	private static final String TAG = ConfigureLog4J.class.getName();

	public static void configure(String logFileName, String directory) {
		final LogConfigurator logConfigurator = new LogConfigurator();

		if (!logFileName.endsWith(".log"))
			logFileName = logFileName + ".log";

		if (directory == null) {
			directory = "AndroMote" + File.separator + "logs";
		}

		logConfigurator.setFileName(Environment.getExternalStorageDirectory()
				+ File.separator + directory + File.separator
				+ logFileName);

		Log.d(TAG, "logging file name: " + logConfigurator.getFileName());
		logConfigurator.setRootLevel(Level.DEBUG);
		
		logConfigurator.configure();
	}

}