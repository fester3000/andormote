package andro_mote.api;

import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.Packet;
import andro_mote.logger.AndroMoteLogger;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

public enum LocalBroadcastDispatcher {
	INSTANCE;

	private Context context;
	private String TAG = LocalBroadcastDispatcher.class.getSimpleName();
	private AndroMoteLogger log = new AndroMoteLogger(getClass());
	
	public void init(Context context) {
		this.context = context;
	}
	
	/**
	 * Pozwala na przesłanie pakietu zawierającego wiadomość do kontrolera
	 * @param pack
	 */
	public void sendPacketViaLocalBroadcast(Packet pack, String intentAction) {
		if(context == null) {
			log.error(TAG, "ReturnToControllerPacketDispatcher was not initialized!");
		} else {
			Intent intentBroadcast = new Intent(intentAction);
			intentBroadcast.putExtra(IntentsFieldsIdentifiers.EXTRA_PACKET, (Parcelable) pack);
			LocalBroadcastManager.getInstance(context).sendBroadcast(intentBroadcast);
		}
	}
}

