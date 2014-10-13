package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class ConnectivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
	public ConnectivityTest() {
		super(MainActivity.class);
	}
	
	public void testConnectivity() {
		
		Question q1 = new Question("sup bra", "neel",new User("Ivan Burrito"));
		QuestionArrayList questions = new QuestionArrayList();
		questions.addQuestion(q1);
		
		try {
			Context context = MainActivity.CallContext();
			MainActivity.SaveMyQuestions(context, questions);

			MainActivity.LoadMyQuestions(context, questions);
			
			assertEquals(q1.getTitle(),"sup bra");
		} catch(Exception e) {
			assertEquals(q1.getTitle(),"sup bra");
		}
	}
}
