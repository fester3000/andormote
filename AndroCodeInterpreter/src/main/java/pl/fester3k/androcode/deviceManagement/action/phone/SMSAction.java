package pl.fester3k.androcode.deviceManagement.action.phone;

import java.util.ArrayList;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.telephony.SmsManager;

public class SMSAction extends BaseDeviceAction {
	public SMSAction(Context context) {
		super(context);		
	}

	@Override
	public ActionResult run() {
		sendSMS();
		return ActionResult.COMPLETED;
	}

	private void sendSMS() {
		String phoneNumber = ""; 
		String text = "";
		if(params.containsKey(ActionParams.SMS.TEXT)) {
			text = (String)params.get(ActionParams.SMS.TEXT);
		}
		if(params.containsKey(ActionParams.SMS.TO)) {
			phoneNumber = (String)params.get(ActionParams.SMS.TO);
			if(phoneNumber != null && !phoneNumber.equals("")) {
				SmsManager smsManager = SmsManager.getDefault();
				ArrayList<String> parts = smsManager.divideMessage(text);
				logger.debug("SMS sent to: " + phoneNumber + " text: " + parts.get(0));
				smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
			} 
		} else {
			logger.warn("SMS not sent. No phone number provided");
		}
	}
	

	@Override
	public void putParam(String propertyName, String value) {
		params.put(ActionParams.SMS.valueOf(propertyName), value);
	}
}
