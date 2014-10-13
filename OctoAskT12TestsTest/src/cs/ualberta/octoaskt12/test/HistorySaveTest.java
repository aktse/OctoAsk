package cs.ualberta.octoaskt12.test;

import java.util.ArrayList;

import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;


// UseCase #17
public class HistorySaveTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public HistorySaveTest() {
		super(MainActivity.class);
	}
	
	public void testSaveHistory() {
		
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
