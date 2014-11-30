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
			
			Log.i("AllQuestionsCacheManager", "Initialized all questions");

			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("AllQuestionsCacheManager", "Error initializing");
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), FILENAME);
			try {
				offlineData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void load()
	{		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qal = (QuestionArrayList) ois.readObject();
			
			Log.i("AllQuestionsCacheManager", "Loaded all questions");

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
	
	public void save()
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.qal);
			Log.i("AllQuestionsCacheManager", "All questions saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("AllQuestionsCacheManager", "Error saving");
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		try
		{
			context.deleteFile(FILENAME);
			Log.i("AllQuestionsCacheManager", "file deleted");
			Log.i("AllQuestionsCacheManager", "file deleted");
			Log.i("AllQuestionsCacheManager", "file deleted");
			Log.i("AllQuestionsCacheManager", "file deleted");
			
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
			Log.i("AllQuestionsCacheManager", "Error deleting");
			Log.i("AllQuestionsCacheManager", "Error deleting");
			Log.i("AllQuestionsCacheManager", "Error deleting");
			Log.i("AllQuestionsCacheManager", "Error deleting");
		}
		/*
		try
		{
			File current_dir = this.context.getFilesDir();
			File current_file = new File(current_dir, FILENAME);
			current_file.delete();
			File offlineData = new File(context.getFilesDir(), FILENAME);
			offlineData.createNewFile();
		}
		catch (Exception e)
		{
			Log.i("Clearing part", "clearing a non existent file.");
		}
		*/
	}
}