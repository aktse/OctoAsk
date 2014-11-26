package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qlist = (ArrayList<Question>) ois.readObject();
			Log.i("QCM", "file initialized");
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("QuestionsCacheManager", "Error initializing");
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), "OfflineQuestions.sav");
			try {
				offlineData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void set(ArrayList<Question> newQuestionsList)
	{
		this.qlist = newQuestionsList;
	}
	
	public ArrayList<Question> get()
	{
		return this.qlist;
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
	
	public void addQuestion(Question question)
	{
		this.qlist.add(question);
	}
	
	public void saveQuestions()
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
			context.deleteFile(FILENAME);
			
			File offlineData = new File(context.getFilesDir(), FILENAME);
			try {
				offlineData.createNewFile();
				Log.i("Created new file", FILENAME);
			} catch (IOException e1) {
				Log.i("Error creating", FILENAME);
				e1.printStackTrace();
			}
		}
		catch (Exception e)
		{
			Log.i("QuestionsCacheManager", "Error deleting");
		}
		
		/*
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
		*/
	}
}
