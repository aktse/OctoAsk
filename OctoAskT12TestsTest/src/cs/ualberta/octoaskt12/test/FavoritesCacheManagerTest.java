package cs.ualberta.octoaskt12.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.octoaskt12.Favorites;
import cs.ualberta.octoaskt12.FavoritesCacheManager;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.User;

public class FavoritesCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public FavoritesCacheManagerTest() {
		super(MainActivity.class);
	}
	
	public void testSaveLoadFavorites()
	{
		Context context = MainActivity.CallContext();

		User user = new User("Kevin");
		Favorites fav = new Favorites();
		
		for (int i = 0; i < 10; i++)
		{
			Question question = new Question("Q " + i, "Body " + i, user);
			fav.add(question);
		}
		
		FavoritesCacheManager fcm = new FavoritesCacheManager(context);
		
		fcm.saveFavorites(fav, user);
		fcm.loadFavorites();
		/*
		Favorites fav2 = fcm.getFavourites();
		
		assert(fav2.count() == 10);
		//assertEquals(qal, qal2);
		//assertEquals(qal2.getSize(), 0);
		*/
	}
}
