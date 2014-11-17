package cs.ualberta.octoaskt12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class QuestionsCacheManager {
	private static final String FILENAME = "OfflineQuestions.sav";
	private User user;
	private Context context;
	ArrayList<Question> qlist;
	
	public QuestionsCacheManager()
	{
		//this.context = context;
		qlist = new ArrayList<Question>();
	}
	
	public ArrayList<Question> loadQuestions()
	{		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qlist = (ArrayList<Question>) ois.readObject();

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("QuestionsCacheManager", "Error loading");
			e.printStackTrace();
		}

		return this.qlist;
	}
	
	public void addQuestion(Question question)
	{
		this.qlist.add(question);
	}
	
	public void saveQuestion()
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.qlist);
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("QuestionsCacheManager", "Error saving");
			e.printStackTrace();
		}
	}

}
