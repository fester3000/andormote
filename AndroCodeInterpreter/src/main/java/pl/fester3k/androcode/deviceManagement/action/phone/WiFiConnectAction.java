package pl.fester3k.androcode.deviceManagement.action.phone;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import pl.fester3k.androcode.datatypes.ActionResult;
import pl.fester3k.androcode.datatypes.Feature;
import pl.fester3k.androcode.deviceManagement.action.BaseDeviceAction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

public class WiFiConnectAction extends BaseDeviceAction {
	public WiFiConnectAction(Context context) {
		super(context);		
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
