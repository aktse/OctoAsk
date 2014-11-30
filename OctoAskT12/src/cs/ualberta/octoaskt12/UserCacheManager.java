package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class UserCacheManager {
	private static final String FILENAME = "username_cache.sav";
	private User user;
	private Context context;

	public UserCacheManager(Context context)
	{
		this.context = context;
		this.user = new User(null);
	}
	
	public void init()
	{
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);

			this.user = (User) ois.readObject();
			Log.i("UCM", "user cache initialized");
			fis.close();
			ois.close();
		}
		catch (Exception e) {
			Log.i("UCM", "Error initializing");
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
			this.user = (User) ois.readObject();
			
			Log.i("UCM", "Loaded user");

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("UCM", "Error loading user");
			e.printStackTrace();
		}
	}
	
	public User getUser()
	{
		return this.user;
	}
	
	public String getUsername()
	{
		return this.user.getName();
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public void save()
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.user);
			Log.i("UCM", "User saved to cache");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("UCM", "Error saving user");
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
			Log.i("UCM", "Error deleting");
		}
	}
}