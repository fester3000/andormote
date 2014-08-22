package mobi.andromote.andro;

import mobi.andromote.andro.AndroCodeService.LocalBinder;

import org.apache.log4j.Logger;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class AndroMainActivity extends Activity {
	private final Logger logger = Logger.getLogger(AndroMainActivity.class);
	AndroCodeService androCodeService;
	boolean isBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(this, AndroCodeService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
		LocalBroadcastManager.getInstance(this).registerReceiver(localMessageReceiver, new IntentFilter("message-event"));
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(localMessageReceiver);
		if(isBound) {
			unbindService(connection);
			isBound = false;
		}
	}
	
	private BroadcastReceiver localMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String message = intent.getStringExtra("message");
			Toast.makeText(AndroMainActivity.this, message, Toast.LENGTH_SHORT).show();
		}
	};

	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className,
				IBinder service) {
			// We've bound to LocalService, cast the IBinder and get LocalService instance
			LocalBinder binder = (LocalBinder) service;
			androCodeService = binder.getService();
			isBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			isBound = false;
		}
	};
}
