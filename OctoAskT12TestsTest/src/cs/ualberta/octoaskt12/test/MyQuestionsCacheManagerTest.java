package cs.ualberta.octoaskt12.test;

<<<<<<< HEAD
import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
=======
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
>>>>>>> 7b03f49a0af9a43e52a99b6b051e7f78f4865423
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.MyQuestionsCacheManager;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
<<<<<<< HEAD
import junit.framework.TestCase;

public class MyQuestionsCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	public MyQuestionsCacheManagerTest(Class<MainActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

=======

public class MyQuestionsCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public MyQuestionsCacheManagerTest() {
		super(MainActivity.class);
	}
	
>>>>>>> 7b03f49a0af9a43e52a99b6b051e7f78f4865423
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
		
<<<<<<< HEAD
		assertEquals(qal, qal2);
=======
		assert(qal2.getSize() == 10);
		//assertEquals(qal, qal2);
		//assertEquals(qal2.getSize(), 0);
>>>>>>> 7b03f49a0af9a43e52a99b6b051e7f78f4865423
	}
}
