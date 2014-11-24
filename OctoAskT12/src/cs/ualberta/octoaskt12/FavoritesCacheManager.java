package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class FavoritesCacheManager {
	private static final String FILENAME = "favorites.sav";
	private User user;
	private Context context;
	public ArrayList<Question> flist;

	public FavoritesCacheManager(Context context)
	{
		this.context = context;
	}
	
	public void init()
	{
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.flist);
			Log.i("QCM", "favourites file opened.");
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("FavouritesCacheManager", "Error creating");
			e.printStackTrace();
			File offlineData = new File(context.getFilesDir(), "favorites.sav");
		}
	}
	
	public void loadFavorites()
	{
		ArrayList<Question> favoritesList = new ArrayList<Question>();
		Favorites fav = null;
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			fav = (Favorites) ois.readObject();

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("FavoritesCacheManager", "Error loading");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Question> getFavourites()
	{
		return this.flist;
	}
	
	public void saveFavorites(Favorites fav, User user) {
		this.user = user;
		
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(fav);
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("FavoritesCacheManager", "Error saving");
			e.printStackTrace();
		}
	}
}
