package cs.ualberta.octoaskt12;

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
	
	public FavoritesCacheManager(Context context)
	{
		this.context = context;
	}
	
	public ArrayList<Question> loadFavorites()
	{
		ArrayList<Question> favoritesList = new ArrayList<Question>();
		
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			favoritesList = (ArrayList<Question>) ois.readObject();

			fis.close();
			ois.close();
		} catch (Exception e) {
			Log.i("FavoritesCacheManager", "Error loading");
			e.printStackTrace();
		}

		return favoritesList;
	}
	
	public void saveQuestions(QuestionArrayList qal, User user) {
		this.user = user;
		
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(qal);
			fos.close();
			oos.close();
		}
		catch (Exception e) {
			Log.i("FavoritesCacheManager", "Error saving");
			e.printStackTrace();
		}
	}
}
