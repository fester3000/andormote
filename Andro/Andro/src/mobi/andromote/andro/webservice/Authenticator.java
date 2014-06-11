package mobi.andromote.andro.webservice;

import org.apache.log4j.Logger;

public class Authenticator {
	private final Logger log = Logger.getLogger(Authenticator.class);

	public AuthenticationStatus isAuthenticated() {
		log.debug("isAuthenticated");
		return AuthenticationStatus.OK;
	}
}
