package pl.fester3k.andromote.am2.functions.phone;

import java.util.ArrayList;

import pl.fester3k.andromote.functionalityFramework.datatypes.ActionResult;
import pl.fester3k.andromote.functionalityFramework.datatypes.FunctionParam;
import pl.fester3k.andromote.functionalityFramework.functions.BaseDeviceFunction;
import android.content.Context;
import android.telephony.SmsManager;

public class SMSFunction extends BaseDeviceFunction {
	public enum SMS implements FunctionParam {
		/**
		 * Numer telefonu odbiorcy wiadomości
		 */
		TO,
		/**
		 * Treść wiadomości
		 */
		TEXT;
	}
	public SMSFunction(Context context) {
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
		if(params.containsKey(SMS.TEXT)) {
			text = (String)params.get(SMS.TEXT);
		}
		if(params.containsKey(SMS.TO)) {
			phoneNumber = (String)params.get(SMS.TO);
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
		params.put(SMS.valueOf(propertyName), value);
	}
}
