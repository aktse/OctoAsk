package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class FreshestCommentTest extends ActivityInstrumentationTestCase2<MainActivity> {
	//waiting for implementation of other methods
	public FreshestCommentTest(){
		super(MainActivity.class);
	}
	
	public void testFreshestComment(){
		
		
		try {
			MainActivity.SeeFreshestComment();

		} catch(Exception e) {
			Log.i("SeeFreshestComment","SeeFreshestComment");
		}
	}
		
	
}
