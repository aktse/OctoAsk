package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

// Grabs all of the questions that were viewed by the user and saves it in a save file.
// Contains functions that load and clear the .sav file as well as ones that can be used to save to a .sav file

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
			
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), FILENAME);
			try {
				offlineData.createNewFile();
			} catch (IOException e1) {
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
			
			fis.close();
			ois.close();
		} catch (Exception e) {
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
			fos.close();
			oos.close();
		}
		catch (Exception e) {
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
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
