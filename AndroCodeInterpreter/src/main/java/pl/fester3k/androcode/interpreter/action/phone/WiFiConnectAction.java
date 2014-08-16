package pl.fester3k.androcode.interpreter.action.phone;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import pl.fester3k.androcode.interpreter.action.ActionResult;
import pl.fester3k.androcode.interpreter.action.BaseAction;
import pl.fester3k.androcode.interpreter.action.BaseDeviceAction;

public class WiFiConnectAction extends BaseDeviceAction {
	public WiFiConnectAction(Context context, Properties params) {
		super(context, params);
	}

	@Override
	public ActionResult run() {
		final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/"; 
		File newdir = new File(dir); 
		newdir.mkdirs();

		Date date = new Date();
		String file = dir+date.getTime()+".jpg";
		File newfile = new File(file);
		try {
			newfile.createNewFile();
		} catch (IOException e) {}       

		Uri outputFileUri = Uri.fromFile(newfile);

		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		return ActionResult.COMPLETED;
	}

}
