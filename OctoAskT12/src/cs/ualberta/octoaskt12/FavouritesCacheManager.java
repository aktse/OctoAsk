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

public class FavouritesCacheManager {
	private static final String FILENAME = "favourites.sav";
	private User user;
	private Context context;
	public QuestionArrayList flist;

	public FavouritesCacheManager(Context context)
	{
		this.context = context;
		this.flist = new QuestionArrayList();
	}
	
	public void init()
	{
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.flist = (QuestionArrayList) ois.readObject();
			Log.i("FCM", "favourites initialized");
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("FavouritesCacheManager", "Error initializing");
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
			this.flist = (QuestionArrayList) ois.readObject();
			
			Log.i("Fav list", "Loaded favourites");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("FavouritesCacheManager", "Error loading favourites");
			e.printStackTrace();
		}
	}
	
	public QuestionArrayList get()
	{
		return this.flist;
	}
	
	public void set(QuestionArrayList qal_in)
	{
		this.flist = qal_in;
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
			oos.writeObject(this.flist);
			Log.i("Fav list", "All favourites saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("FavouritesCacheManager", "Error saving favourites");
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
	}
}
