package pl.fester3k.androcode.deviceManagement.action.phone;

import java.util.Locale;
import pl.fester3k.androcode.datatypes.ActionParams;
import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class TextToSpeechAction extends BaseDeviceAction implements TextToSpeech.OnInitListener {
	private TextToSpeech textToSpeech;
	private boolean readyToTalk = false;
	private UtteranceProgressListener ttsProgressListener;
	private boolean isTalking = false;

	public TextToSpeechAction(Context context) {
		super(context);		
		textToSpeech = new TextToSpeech(context, this);
	}

	@Override
	public Object run() {
		String text = null;
		if(readyToTalk) {
			if(params.containsKey(ActionParams.TTS.TEXT.toString())) {
				text = (String) params.get(ActionParams.TTS.TEXT.toString());
				logger.info("TTS is speaking: " + text);
				textToSpeech.setLanguage(Locale.getDefault());
				textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null);
				return ActionResult.COMPLETED;
			}
		} else {
			logger.warn("TTS not ready to speak yet");
		}
		return ActionResult.FAILED;
	}

	@Override
	public void onInit(int status) {
		if(status == TextToSpeech.SUCCESS) {
			readyToTalk = true;
			ttsProgressListener = new UtteranceProgressListener() {
				
				@Override
				public void onStart(String utteranceId) {
					isTalking = true;					
				}
				
				@Override
				public void onError(String utteranceId) {
					isTalking = false;
				}
				
				@Override
				public void onDone(String utteranceId) {
					isTalking = false;	
				}
			};
			textToSpeech.setOnUtteranceProgressListener(ttsProgressListener);
		}
	}

	@Override
	public void cleanup() {
		if (textToSpeech != null)
		{
			textToSpeech.stop();
			textToSpeech.shutdown();
		}
		super.cleanup();
	}
	
	
}

