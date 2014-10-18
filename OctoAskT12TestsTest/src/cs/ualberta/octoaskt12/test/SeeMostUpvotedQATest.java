package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class SeeMostUpvotedQATest extends ActivityInstrumentationTestCase2<MainActivity> {
	public SeeMostUpvotedQATest(){
		super(MainActivity.class);
	}
	
	public void testSeeUpvotedQuestion(){
		try {
			MainActivity.SeeMostUpvotedQuestion();

		} catch(Exception e) {
			Log.i("testSeeUpvotedQuestion","testSeeUpvotedQuestion");
		}
	}
		
	
	
	public void testSeeUpvotedAnswer(){
		try {
			MainActivity.SeeMostUpvotedAnswer();

		} catch(Exception e) {
			Log.i("testSeeUpvotedQuestion","testSeeUpvotedQuestion");
		}
	}
		
	
	
}
