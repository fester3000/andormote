package mobi.andromote.am2.functions.phone;

import mobi.andromote.am2.functions.phone.helpers.email.GMailSender;
import mobi.andromote.functionalityFramework.datatypes.ActionResult;
import mobi.andromote.functionalityFramework.datatypes.FunctionParam;
import mobi.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;


public class EmailFunction extends BaseDeviceFunction {
	public enum EMAIL implements FunctionParam {
		/**
		 * Adres(y) odbiorców wiadomości e-mail
		 * Adresy e-mail oddzielane przecinkami:
		 * adres1@domena.tld, adres2@domena.tld
		 */
		TO,
		/**
		 * Treść wiadomości
		 */
		TEXT,
		/**
		 * Temat wiadomości
		 */
		SUBJECT;
	}
	private static final String EMAIL_REGEX = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"; 
	public EmailFunction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		sendEmail();
		return ActionResult.COMPLETED;
	}

	private void sendEmail() {
		String emailAddress = ""; 
		String topic = "";
		String text = "";
		if(params.containsKey(EMAIL.TEXT)) {
			text = (String)params.get(EMAIL.TEXT);
		}
		if(params.containsKey(EMAIL.SUBJECT)) {
			topic = (String)params.get(EMAIL.SUBJECT);
		}
		if(params.containsKey(EMAIL.TO)) {
			emailAddress = (String)params.get(EMAIL.TO);
			if(emailAddress != null && !emailAddress.equals("") && emailAddress.matches(EMAIL_REGEX)) {
				try {   
					//FIXME Only for internal use!!!
					logger.debug("SendMail " + text + " " + topic);
                    GMailSender sender = new GMailSender("andromote.v2@gmail.com", "thisisandromote");
                    sender.sendMail(topic, text, emailAddress);   
                } catch (Exception e) {   
                    logger.error("SendMail " + e.getMessage());   
                } 
			} else {
				logger.warn("email addres not specified or incorrect");
			}
		} else {
			logger.warn("Email not sent. No e-mail address number provided");
		}
	}
	
	@Override
	public void putParam(String propertyName, String value) {
		params.put(EMAIL.valueOf(propertyName), value);
	}
}
