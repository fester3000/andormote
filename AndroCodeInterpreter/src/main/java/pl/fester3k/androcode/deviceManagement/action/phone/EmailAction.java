package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import pl.fester3k.androcode.deviceManagement.action.phone.helpers.email.GMailSender;
import android.content.Context;

public class EmailAction extends BaseDeviceAction {
	private static final String EMAIL_REGEX = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"; 
	public EmailAction(Context context) {
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
		if(params.containsKey(ActionParams.EMAIL.TEXT)) {
			text = (String)params.get(ActionParams.EMAIL.TEXT);
		}
		if(params.containsKey(ActionParams.EMAIL.SUBJECT)) {
			topic = (String)params.get(ActionParams.EMAIL.SUBJECT);
		}
		if(params.containsKey(ActionParams.EMAIL.TO)) {
			emailAddress = (String)params.get(ActionParams.EMAIL.TO);
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
		params.put(ActionParams.EMAIL.valueOf(propertyName), value);
	}
}
