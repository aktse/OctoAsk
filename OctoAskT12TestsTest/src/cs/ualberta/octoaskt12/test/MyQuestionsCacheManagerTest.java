package cs.ualberta.octoaskt12.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.MyQuestionsCacheManager;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;

public class MyQuestionsCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public MyQuestionsCacheManagerTest() {
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
		
		MyQuestionsCacheManager mqcm = new MyQuestionsCacheManager(context);
		
		mqcm.saveQuestions(qal, user);
		
		QuestionArrayList qal2 = mqcm.loadQuestions();
		
		assert(qal2.getSize() == 10);
		//assertEquals(qal, qal2);
		//assertEquals(qal2.getSize(), 0);
	}
}
