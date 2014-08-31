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
		if(getParams().containsKey(ActionParams.EMAIL.TEXT.toString())) {
			text = (String)getParams().get(ActionParams.EMAIL.TEXT.toString());
		}
		if(getParams().containsKey(ActionParams.EMAIL.SUBJECT.toString())) {
			topic = (String)getParams().get(ActionParams.EMAIL.SUBJECT.toString());
		}
		if(getParams().containsKey(ActionParams.EMAIL.TO.toString())) {
			emailAddress = (String)getParams().get(ActionParams.EMAIL.TO.toString());
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
}
