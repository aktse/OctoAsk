package cs.ualberta.octoaskt12.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.MyQuestionsCacheManager;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.ReadLater;
import cs.ualberta.octoaskt12.ReadLaterCacheManager;
import cs.ualberta.octoaskt12.User;

public class ReadLaterCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public ReadLaterCacheManagerTest() {
		super(MainActivity.class);
	}
	
	public void testSaveLoad()
	{
		Context context = MainActivity.CallContext();

		User user = new User("Kevin");
		ReadLater rl = new ReadLater();
		
		for (int i = 0; i < 10; i++)
		{
			Question question = new Question("Q " + i, "Body " + i, user);
			rl.add(question);
		}
		
		ReadLaterCacheManager rlcm = new ReadLaterCacheManager(context);
		
		rlcm.saveQuestions(rl, user);
		
		ReadLater rl2 = rlcm.loadReadLater();
		
		assert(rl2.count() == 10);
		//assertEquals(qal, qal2);
		//assertEquals(qal2.getSize(), 0);
	}
}
