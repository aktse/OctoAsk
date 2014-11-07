package cs.ualberta.octoaskt12.ESbackup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import android.app.Activity;
import android.util.Log;

public class ElasticSearchAddQuestion extends Activity{
	
	private static final String SEARCH_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t12/abc/_search";

	private static Gson gson;

	
	// Thread that close the activity after finishing add
	//passed from intent
	
	
	private Runnable doFinishAdd = new Runnable(){
		
		public void run(){
			finish();
		}
	};

	public static void AddToDatabase(){
	}
	public static void AddToDatabase(Question question){
		
		/*
		String questionTitle = "qrs";
		String questionBody = "tuv";
		User user = new User("Bob");
		*/
		
		//public Question(String questionTitle, String questionBody, User user) {

		ElasticSearchAddQuestion xyz = new ElasticSearchAddQuestion();
		//Question qstn = new Question(questionTitle, questionBody, user);
		Thread thread = xyz.new StartThread(question);
		thread.start();
		
	}
	
	class StartThread extends Thread{
	
		private Question aquestion;
		
		public StartThread(Question qstn){
			this.aquestion = qstn;
		}
		
		@Override
		public void run(){
			
			ElasticSearchAddQuestionHelper es = new ElasticSearchAddQuestionHelper();
			es.SaveToDatabase(aquestion);
			
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("STARTTHREAD");
			}
			runOnUiThread(doFinishAdd);
		}
	
	}
	
	

	//grabs something from the database
	public static QuestionArrayList LoadFromDatabase(String searchString, String field){
		QuestionArrayList result = new QuestionArrayList();


		// TODO: Implement search movies using ElasticSearch
		if (searchString == null || "".equals(searchString)) {
			searchString = "*";
		}
		
		HttpClient httpClient = new DefaultHttpClient();


		try {
			HttpPost searchRequest = createSearchRequest(searchString, field);
			
			//searchRequest would be a fixed httppost
			HttpResponse response = httpClient.execute(searchRequest);
			
			String status = response.getStatusLine().toString();
			Log.i("load from database", status);
			
			//esresponse would be a json
			ServerResponse<Question> esResponse = parseSearchResponse(response);
			
			ElasticSearchArrayQuestion<Question> arrayofquestions = esResponse.getHits();
			
			if (arrayofquestions != null) {
				if (arrayofquestions.getHits() != null) {
					
					// object in array :original array
					for (SearchHit<Question> sesr : arrayofquestions.getHits()) {
						result.addQuestion(sesr.getSource());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	//========
	
	public static String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}
	//========
	
	private static ServerResponse<Question> parseSearchResponse(HttpResponse response) throws IOException {
		String json;
		json = getEntityContent(response);

		
		Type searchResponseType = new TypeToken<ServerResponse<Question>>() {
		}.getType();
		
		ServerResponse<Question> esResponse = gson.fromJson(json, searchResponseType);

		return esResponse;
	}
	//========
	
	private static HttpPost createSearchRequest(String searchString, String field)	throws UnsupportedEncodingException {
		
		HttpPost searchRequest = new HttpPost(SEARCH_URL);

		String[] fields = null;
		if (field != null) {
			fields = new String[1];
			fields[0] = field;
		}	
		
		HttpHeaderNEntity command = new HttpHeaderNEntity(searchString,	fields);
		
		String query = command.getJsonCommand();
		Log.i("createSearchRequest", "Json command: " + query);

		StringEntity stringEntity;
		stringEntity = new StringEntity(query);

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringEntity);

		return searchRequest;
	}
	
	//========
}
