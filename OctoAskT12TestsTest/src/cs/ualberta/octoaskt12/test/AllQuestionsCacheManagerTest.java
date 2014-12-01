package cs.ualberta.octoaskt12.test;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import cs.ualberta.octoaskt12.AllQuestionsCacheManager;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.QuestionsCacheManager;
import cs.ualberta.octoaskt12.User;

public class AllQuestionsCacheManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public AllQuestionsCacheManagerTest() {
		super(MainActivity.class);
	}
	
	public void loadSaveCount()
	{
		QuestionArrayList qal = new QuestionArrayList();
		Context context = MainActivity.CallContext();

		User user = new User("Chris");
		
		for (int i = 0; i < 10; i++)
		{
			Question question = new Question("Q " + i, "Body " + i, user);
			qal.addQuestion(question);
		}
		
		AllQuestionsCacheManager aqcm = new AllQuestionsCacheManager(context);
		
		aqcm.clear();
		aqcm.load();
		
		Log.i("LOOK HERE 1", Integer.valueOf(aqcm.get().getSize()).toString());
		Log.i("LOOK HERE 1", Integer.valueOf(aqcm.get().getSize()).toString());
		Log.i("LOOK HERE 1", Integer.valueOf(aqcm.get().getSize()).toString());
		Log.i("LOOK HERE 1", Integer.valueOf(aqcm.get().getSize()).toString());
		Log.i("LOOK HERE 1", Integer.valueOf(aqcm.get().getSize()).toString());

		System.out.println(Integer.valueOf(aqcm.get().getSize()).toString());
		
		File current_dir = context.getFilesDir();
		File current_file = new File(current_dir, "testing_file.sav");
		current_file.delete();
		/*
		try {
			current_file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		assert(!current_file.exists());

		//assert(aqcm.get().getSize() == 0);
		aqcm.init();
		aqcm.load();
		aqcm.set(qal);
		
		
		QuestionArrayList qal2 = new QuestionArrayList();
		
		//Log.i("LOOK HERE 2", Integer.valueOf(qal2.getSize()).toString());
		//assert(qal2.getSize() == 10);
	}
}
