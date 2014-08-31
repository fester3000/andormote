package pl.fester3k.androcode.deviceManagement.action.phone;

import java.io.File;
import java.io.IOException;

import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer;
import android.net.Uri;

public class AudioOut extends BaseDeviceAction implements OnPreparedListener {
	private MediaPlayer mediaPlayer;
	private boolean notReleased = true;
	public AudioOut(Context context) {
		super(context);		
	}

	@Override
	public Object run() {
//		if(params.containsKey(ActionParams.AUDIO_OUT.PATH)) {
//			String path = params.getProperty(ActionParams.AUDIO_OUT.PATH.toString());
//			File file = new File(path);
//			Uri fileUri = Uri.fromFile(file);
//			logger.debug(fileUri.toString());
//			mediaPlayer = new MediaPlayer();
//			mediaPlayer.setOnPreparedListener(this);
//			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//			try {
//				mediaPlayer.setDataSource(context, fileUri);
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return ActionResult.COMPLETED;
	}

	@Override
	public void cleanup() {
		notReleased = false;
		if(mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
		super.cleanup();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		if(notReleased) {
			mediaPlayer.start();
		}		
	}
}
