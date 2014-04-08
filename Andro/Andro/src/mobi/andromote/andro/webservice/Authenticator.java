package mobi.andromote.andro.webservice;

import android.util.Log;
import mobi.andromote.andro.androscript.processingTools.Parser;

public class Authenticator {
	private static final String TAG = Authenticator.class.getSimpleName();

	public static AuthenticationStatus isAuthenticated() {
		Log.d(TAG, "isAuthenticated");
		return AuthenticationStatus.OK;
	}
}
