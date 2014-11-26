package cs.ualberta.octoaskt12;

import java.io.File;
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
	public ArrayList<Question> qlist;
	
	public QuestionsCacheManager(Context context)
	{
		this.context = context;
		qlist = new ArrayList<Question>();
		
		
	}
	
	public void init()
	{
		// !!!!!!!!!!!!!!!!!!!!! change this!
		// may be the duplicate problem
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.qlist);
			Log.i("QCM", "file created.");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("QuestionsCacheManager", "Error creating");
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), "OfflineQuestions.sav");
		}
	}
	
	public void set(ArrayList<Question> newQuestionList)
	{
		this.qlist = newQuestionList;
	}
	
	public void loadQuestions()
	{		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qlist = (ArrayList<Question>) ois.readObject();
			
			Log.i("QCM", "Loaded questions");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("QuestionsCacheManager", "Error loading");
			e.printStackTrace();
		}

	}
	
	public ArrayList<Question> getQuestions()
	{
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
			Log.i("QCM", "Saved questions to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("QuestionsCacheManager", "Error saving");
			e.printStackTrace();
		}
	}

	public void clear()
	{
		try
		{
			File current_dir = this.context.getFilesDir();
			File current_file = new File(current_dir, FILENAME);
			current_file.delete();
			File offlineData = new File(context.getFilesDir(), FILENAME);
		}
		catch (Exception e)
		{
			Log.i("Clearing part", "clearing a non existent file.");
		}
	}
}
