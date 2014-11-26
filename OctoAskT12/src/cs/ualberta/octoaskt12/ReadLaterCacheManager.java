package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.util.Log;

public class ReadLaterCacheManager {
	private static final String FILENAME = "read_later.sav";
	private User user;
	private Context context;
	private QuestionArrayList qal;
	
	public ReadLaterCacheManager(Context context)
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
			Log.i("RLCM", "read later initialized");
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("RLCM", "Error initializing");
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
			
			Log.i("RLCM", "Loaded read later");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("RLCM", "Error loading read later");
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
			Log.i("RLCM", "All read later saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("RLCM", "Error saving read later");
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
			Log.i("RLCM", "Error deleting");
		}
	}
}
