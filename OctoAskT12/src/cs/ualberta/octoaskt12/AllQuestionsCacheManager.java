package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class AllQuestionsCacheManager {
	private static final String FILENAME = "AllQuestionsOffline.sav";
	private User user;
	private Context context;
	public QuestionArrayList qal;
	
	public AllQuestionsCacheManager(Context context)
	{
		this.context = context;
		qal = new QuestionArrayList();
	}
	
	public void init()
	{
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qal = (QuestionArrayList) ois.readObject();
			
			Log.i("QAL", "Loaded questions");

			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("AllQuestionsCacheManager", "Error creating");
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), "AllQuestionsOffline.sav");
		}
	}
	public void load()
	{		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qal = (QuestionArrayList) ois.readObject();
			
			Log.i("QAL", "Loaded questions");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("AllQuestionsCacheManager", "Error loading");
			e.printStackTrace();
		}

	}
	
	public QuestionArrayList get()
	{
		return this.qal;
	}
	
	public void set(QuestionArrayList qal_in)
	{
		this.qal = qal_in;
	}
	
	/*
	public void addQuestion(Question question)
	{
		this.qlist.add(question);
	}
	*/
	
	public void save()
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.qal);
			Log.i("QAL", "All questions saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("AllQuestionsCacheManager", "Error saving");
			e.printStackTrace();
		}
	}
}