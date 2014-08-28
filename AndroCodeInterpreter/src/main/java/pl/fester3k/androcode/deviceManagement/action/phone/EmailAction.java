package pl.fester3k.androcode.deviceManagement.action.phone;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import pl.fester3k.androcode.deviceManagement.action.phone.helpers.email.GMailSender;
import android.content.Context;

public class EmailAction extends BaseDeviceAction {
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
		if(getParams().containsKey(ActionParams.EMAIL.TOPIC.toString())) {
			topic = (String)getParams().get(ActionParams.EMAIL.TOPIC.toString());
		}
		if(getParams().containsKey(ActionParams.EMAIL.TO.toString())) {
			emailAddress = (String)getParams().get(ActionParams.EMAIL.TO.toString());
			if(emailAddress != null && !emailAddress.equals("")) {
				try {   
					//FIXME Only for internal use!!!
					logger.debug("SendMail " + text + " " + topic);
                    GMailSender sender = new GMailSender("andromote.v2@gmail.com", "thisisandromote");
                    sender.sendMail(topic, text, emailAddress);   
                } catch (Exception e) {   
                    logger.error("SendMail " + e.getMessage());   
                } 
			} 
		} else {
			logger.warn("Email not sent. No e-mail address number provided");
		}
	}
}
