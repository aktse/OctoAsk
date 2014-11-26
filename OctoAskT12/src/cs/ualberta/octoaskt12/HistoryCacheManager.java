package cs.ualberta.octoaskt12;

import java.io.File;
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
	private QuestionArrayList qal;
	
	public HistoryCacheManager(Context context)
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
			
			Log.i("QAL history", "Loaded history");

			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("HistoryCacheManager", "Error creating");
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), "history.sav");
		}
	}
	public void load()
	{		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.qal = (QuestionArrayList) ois.readObject();
			
			Log.i("QAL history", "Loaded history");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("HistoryCacheManager", "Error loading history");
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
			Log.i("QAL history", "All history saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("HistoryCacheManager", "Error saving history");
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		
	}
}
