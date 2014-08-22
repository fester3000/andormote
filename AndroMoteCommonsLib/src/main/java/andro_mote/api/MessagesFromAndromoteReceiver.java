package andro_mote.api;

import andro_mote.api.exceptions.BroadcastReceiverClientNotSetException;
import andro_mote.api.exceptions.MobilePlatformException;
import andro_mote.commons.IntentsFieldsIdentifiers;
import andro_mote.commons.IntentsIdentifiers;
import andro_mote.commons.Packet;
import andro_mote.logger.AndroMoteLogger;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Podstawowa implementacja interfejsu {@link MessagesFromDeviceReceiver}
 * pozwalająca na odbieranie wiadomości z urzadzenia zewnętrznego w projekcie
 * AndroMote. W przypadku chęci dalszego rozwoju należy dodać kolejne
 * identyfikatory akcji Intencji przy rejestracji filtrów BroadcastReceivera.
 * 
 * @author Maciej Gzik
 * 
 */
public class MessagesFromAndromoteReceiver extends BroadcastReceiver implements MessagesFromDeviceReceiver {
	private static final String TAG = MessagesFromAndromoteReceiver.class.getName().toString();
	AndroMoteLogger logger = new AndroMoteLogger(MessagesFromAndromoteReceiver.class);
	private final IAndroMoteDeviceReceiverClient client;
	private final Application application;

	public MessagesFromAndromoteReceiver(IAndroMoteDeviceReceiverClient client, Application application) {
		this.application = application;
		this.client = client;
	}

	@Override
	public boolean startMessagesListener() throws BroadcastReceiverClientNotSetException {
		if (this.client == null || this.application == null)
			throw new BroadcastReceiverClientNotSetException();
		else {
			IntentFilter filter = new IntentFilter(IntentsIdentifiers.ACTION_ENGINE_STEP);
			filter.addAction(IntentsIdentifiers.ACTION_MESSAGE_FROM_DEVICE);
			LocalBroadcastManager.getInstance(application).registerReceiver(this, filter);
			return true;
		}
	}

	@Override
	public boolean stopMessagesListener() throws BroadcastReceiverClientNotSetException {
		if (this.application == null)
			throw new BroadcastReceiverClientNotSetException();
		else {
			LocalBroadcastManager.getInstance(application).unregisterReceiver(this);
			return true;
		}
	}

	@Override
	public void mobilePlatformMessageReceived(Packet pack) throws BroadcastReceiverClientNotSetException {
		if (client == null) {
			throw new BroadcastReceiverClientNotSetException();
		}
		else {
			try {
				this.client.deviceMessageReceived(pack);
			} catch (MobilePlatformException e) {
				logger.error(TAG, e);
			}
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (this.client != null) {
				Packet packet = (Packet) intent.getSerializableExtra(IntentsFieldsIdentifiers.EXTRA_PACKET);
				mobilePlatformMessageReceived(packet);
			} else {
				throw new BroadcastReceiverClientNotSetException();
			}
		} catch (BroadcastReceiverClientNotSetException e) {
			logger.error(TAG, e);
		}
	}
}
