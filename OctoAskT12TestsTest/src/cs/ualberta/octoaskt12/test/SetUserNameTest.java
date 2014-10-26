package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.MainActivity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class SetUserNameTest extends ActivityInstrumentationTestCase2<MainActivity> {
	//waiting for implementation of other methods
	public SetUserNameTest(){
		super(MainActivity.class);
	}
	
	public void testEditUsername(){
		
		try {
			MainActivity.EditUsername();

		} catch(Exception e) {
			Log.i("testEditUsername","testEditUsername");
		}
	}
}
