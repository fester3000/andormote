package mobi.andromote.andro.androscript;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import pl.fester3k.androcode.datatypes.Script;
import android.content.Context;
import android.os.Environment;

public class SDScriptRepository implements ScriptRepository {
	private final Logger log = Logger.getLogger(SDScriptRepository.class);
	private Context context;
	
	public SDScriptRepository(Context context) {
		this.context = context;
	}
	
	@Override
	public void save(Script script) {
		FileOutputStream outputStream;
		try {
			outputStream = context.openFileOutput(script.getName() + script.getCreationDate()+ ".andro", Context.MODE_PRIVATE);
			outputStream.write(script.getContent().getBytes());
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.debug("save to SD");
	}
	
	public boolean saveExternally(Script script) {
		if(isExternalStorageWriteable()) {
			String filename = Environment.getExternalStorageDirectory() + "/data/mobi.andromote.andro/scripts/" + script.getName() + ".andro";
			log.debug(filename);
			File scriptFile = new File(filename);
			FileOutputStream outputStream;
			try {
				scriptFile.getParentFile().mkdirs();
				if(!scriptFile.exists()) {
					scriptFile.createNewFile();
				}
				outputStream = new FileOutputStream(scriptFile);
				outputStream.write(script.getContent().getBytes());
				outputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.debug("save to SD");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Script> getScriptList() {
		log.debug("get script list");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Script getScript(int id) {
		log.debug("get script with id: " + id);
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isExternalStorageWriteable() {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		} else {
			return false;
		}
	}
	
}
