package pl.fester3k.androcode.deviceManagement.action.phone;

import java.util.ArrayList;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.widget.Toast;

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
		if(getParams().containsKey(ActionParams.SMS.TEXT.toString())) {
			text = (String)getParams().get(ActionParams.SMS.TEXT.toString());
		}
		if(getParams().containsKey(ActionParams.SMS.TO.toString())) {
			phoneNumber = (String)getParams().get(ActionParams.SMS.TO.toString());
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
}
