package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.HistoryCacheManager;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class HistoryCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity>{

	public HistoryCacheManagerTest() {
		super(MainActivity.class);
	}
	
	public void testSaveLoadQuestions()
	{
		Context context = MainActivity.CallContext();
		
		User user = new User("Kevin");
		QuestionArrayList qal = new QuestionArrayList();
		
		for (int i = 0; i < 10; i++)
		{
			Question question = new Question("Q " + i, "Body " + i, user);
			qal.addQuestion(question);
		}
		
		HistoryCacheManager hcm = new HistoryCacheManager(context);
		hcm.save();
		// QuestionArrayList qal2 = (QuestionArrayList) hcm.load();
		
		//assert(qal2.getSize() == 10);
	}

}
