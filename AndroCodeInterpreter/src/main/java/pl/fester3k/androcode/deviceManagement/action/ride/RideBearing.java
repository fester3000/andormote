package pl.fester3k.androcode.deviceManagement.action.ride;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import andro_mote.commons.Packet;
import android.content.Context;


public class RideBearing extends BaseDeviceAction {

	public RideBearing(Context context) {
		super(context);

	}

	@Override
	public ActionResult run() {
		ActionResult result;
		Packet packet;
		logger.debug("Ride action run");
		if(getParams().containsKey(ActionParams.RIDE_BEARING.BEARING.toString())) {
			if(getParams().containsKey(ActionParams.RIDE_BEARING.MODE.toString())) {
				String property = getParams().getProperty(ActionParams.RIDE_BEARING.MODE.toString());
					if(property.equals("ASYNC")) {
						//Do async
					} else {
						//Do sync
//						result = RideController.INSTANCE.execute(packet);
					}
			}
			result = ActionResult.COMPLETED;
		} else {
			result = ActionResult.FAILED;
		}
		return result;
	}

}
