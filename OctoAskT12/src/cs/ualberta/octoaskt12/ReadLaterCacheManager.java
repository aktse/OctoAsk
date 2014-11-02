package cs.ualberta.octoaskt12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.util.Log;

public class ReadLaterCacheManager {
	private static final String FILENAME = "read_later.sav";
	private User user;
	private Context context;
	
	public ReadLaterCacheManager(Context context)
	{
		this.context = context;
	}
	
	public QuestionArrayList loadReadLater()
	{
		QuestionArrayList readLaterList = new QuestionArrayList();
		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			readLaterList = (QuestionArrayList) ois.readObject();

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("ReadLaterCacheManager", "Error loading");
			e.printStackTrace();
		}

		return readLaterList;
	}
	
	public void saveQuestions(ReadLater rl, User user) {
		this.user = user;
		
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(rl);
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("ReadLaterCacheManager", "Error saving");
			e.printStackTrace();
		}
	}
}
