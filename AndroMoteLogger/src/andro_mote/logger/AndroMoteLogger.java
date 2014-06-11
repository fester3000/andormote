package andro_mote.logger;

import org.apache.log4j.Logger;

/**
 * Działanie: 1. Utworzenie obiektu AndroMoteLogger 2. wywołanie funkcji
 * ConfigureLogger 3. debug, error logging
 * 
 * @author Maciej Gzik
 * 
 */
public class AndroMoteLogger {
	private Logger logger = null;

	public AndroMoteLogger(Class clazz) {
		this.logger = Logger.getLogger(clazz);
	}

	/**
	 * Konfiguracja loggera.
	 * 
	 * @param logFileName
	 *            nazwa pliku z logami
	 * @param path
	 *            opcjonalny. Jeżeli równy null - logi zapisywane do katalogu:
	 *            sd/AndroMote/logs/
	 */
	public static void ConfigureLogger(String logFileName, String path) {
		ConfigureLog4J.configure(logFileName, path);
	}

	/**
	 * Konfiguracja loggera.
	 * 
	 * @param logFileName
	 */
	public static void ConfigureLogger(String logFileName) {

		ConfigureLog4J.configure(logFileName, null);
	}

	/**
	 * Debug do logCat i log4j
	 * 
	 * @param TAG
	 *            tag (LogCat)
	 * @param message
	 *            wiadomość
	 */
	public void debug(String TAG, String message) {
		// Log.d(TAG, message);
		logger.debug(message);

	}

	/**
	 * log error
	 * 
	 * @param TAG
	 *            tag
	 * @param e
	 *            błąd
	 */
	public void error(String TAG, Throwable e) {
		logger.error(e.getMessage(), e);
		// Log.e(TAG, e.getMessage(), e);
	}
}
