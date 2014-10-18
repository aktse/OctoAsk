package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class AuthorPostsOfflinePushTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
		//TestCase 20/21
		//waiting for implementation of other methods

	public AuthorPostsOfflinePushTest(){
		super(MainActivity.class);
	}
	
	public void testAuthorReply(){
		//TestCase 20/21
		//waiting for implementation of other methods
		try {
			MainActivity.AuthorReplyOffline();

		} catch(Exception e) {
			Log.i("AuthorReplyOffline","AuthorReplyOffline");
		}
	}
	
	public void testAuthorQuestion(){
		//TestCase 20/21
		//waiting for implementation of other methods
		try {
			MainActivity.AuthorQuestionOffline();

		} catch(Exception e) {
			Log.i("AuthorQuestionOffline","AuthorQuestionOffline");
		}
	}
	
	public void testAuthorAnswer(){
		//TestCase 20/21
		//waiting for implementation of other methods
		try {
			MainActivity.AuthorAnswerOffline();

		} catch(Exception e) {
			Log.i("AuthorAnswerOffline","AuthorAnswerOffline");
		}
	}
	
	public void testPushStored(){
		//TestCase 20/21
		//waiting for implementation of other methods
		try {
			MainActivity.PushStored();

		} catch(Exception e) {
			Log.i("testPushStored","testPushStored");
		}
	}
	
	
	
}
