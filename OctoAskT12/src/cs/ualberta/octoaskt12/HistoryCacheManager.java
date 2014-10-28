package cs.ualberta.octoaskt12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class HistoryCacheManager {
	
	private static final String FILENAME = "history.sav";
	private User user;
	private Context context;
	
	public HistoryCacheManager(Context context)
	{
		this.context = context;
	}
	
	public QuestionArrayList loadQuestions()
	{
		QuestionArrayList qal = new QuestionArrayList();
		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			qal = (QuestionArrayList) ois.readObject();
			
			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("HistoryCacheManager", "Error loading");
			e.printStackTrace();
		}
		return qal;
	}
	
	public void saveQuestions(QuestionArrayList qal, User user)
	{
		this.user = user;
		
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(qal);
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("HistoryCacheManager", "Error saving");
			e.printStackTrace();
		}
	}
}
