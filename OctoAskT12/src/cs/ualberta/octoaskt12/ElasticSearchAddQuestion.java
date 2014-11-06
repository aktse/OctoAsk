package cs.ualberta.octoaskt12;

import android.app.Activity;

public class ElasticSearchAddQuestion extends Activity{
	
	
	// Thread that close the activity after finishing add
	//passed from intent
	

	private Runnable doFinishAdd = new Runnable(){
		
		public void run(){
			finish();
		}
	};
	
	

	
	public static void AddToDatabase(){
		//your question arrays list here
		
		String questionTitle = "qrs";
		String questionBody = "tuv";
		User user = new User("Bob");

		//public Question(String questionTitle, String questionBody, User user) {

		ElasticSearchAddQuestion xyz = new ElasticSearchAddQuestion();
		Question qstn = new Question(questionTitle, questionBody, user);
		Thread thread = xyz.new StartThread(qstn);
		thread.start();
	}
	
	class StartThread extends Thread{
	
		private Question aquestion;
		
		public StartThread(Question qstn){
			this.aquestion = qstn;
		}
		
		@Override
		public void run(){
			
			//ElasticSearchHelper es = new ElasticSearchHelper();
			//es.SaveToDatabase(aquestion);
			
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("STARTTHREAD");
			}
			runOnUiThread(doFinishAdd);
		}
	
	}
}
