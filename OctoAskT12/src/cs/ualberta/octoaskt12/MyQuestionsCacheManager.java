package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class MyQuestionsCacheManager {
	private static final String FILENAME = "myQuestions.sav";
	private User user;
	private Context context;
	private QuestionArrayList qal;
	
	public MyQuestionsCacheManager(Context context)
	{
		this.context = context;
		this.qal = new QuestionArrayList();
	}
	
	public void init()
	{
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qal = (QuestionArrayList) ois.readObject();
			Log.i("MQCM", "My Questions initialized");
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("MQCM", "Error initializing");
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
			
			Log.i("MQCM", "Loaded my questions");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("MQCM", "Error loading my questions");
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
	
	public void add(Question question)
	{
		this.qal.addQuestion(question);
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
			Log.i("MQCM", "All my questions saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("MQCM", "Error saving my questions");
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
			Log.i("MQCM", "Error deleting");
		}
	}
}