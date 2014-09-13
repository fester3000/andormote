package mobi.andromote.am2.functions.phone.helpers;
import java.io.File;
import java.io.FileOutputStream;

import mobi.andromote.functionalityFramework.utils.FileUtils;
import mobi.andromote.functionalityFramework.utils.FileUtils.MediaType;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.Log;
import android.widget.Toast;

public class PhotoHandler implements PictureCallback {
	private final Context context;
	private boolean isDone = false;
	private final String TAG = PhotoHandler.class.getSimpleName();

	public PhotoHandler(Context context) {
		this.context = context;
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		File pictureFile = FileUtils.createFileName(MediaType.PICTURE);

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
			Toast.makeText(context, "New Image saved:" + pictureFile, Toast.LENGTH_LONG).show();
			Log.d(TAG, "New Image saved:" + pictureFile);
		} catch (Exception error) {
			Log.d(TAG, "File" + pictureFile + "not saved: " + error.getMessage());
			Toast.makeText(context, "Image could not be saved.",Toast.LENGTH_LONG).show();
		}
		isDone = true;
	}

	public boolean isDone() {
		return isDone;
	}

} 