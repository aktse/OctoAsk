package cs.ualberta.octoaskt12.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.UserText;
import junit.framework.TestCase;

/* Usecase #16 JUnit Test 
 * to be continued, mock at the moment
 * waiting for add question functionality to be added*/
public class QuestionAskedSavedTest extends ActivityInstrumentationTestCase2<MainActivity> {
	
	
	public QuestionAskedSavedTest() {
		super(MainActivity.class);
	}
	
	public void testQuestionAskedSaved() {
		
		ArrayList<UserText> userText = new ArrayList<UserText>();
		/*ArrayList<UserText> myQuestions = new ArrayList<UserText>();*/
		
		userText.add(new UserText("KevinChrisTest"));
		userText.add(new UserText("KevinChrisTest"));
		
		try {
			userText.add(new UserText("KevinChrisTest"));
			SaveMyQuestions();
			LoadMyQuestions();
		} catch (Exception e) {
			assertEquals(e.getClass(), Exception.class);
		}		
	}
	
	
}
