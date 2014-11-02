package cs.ualberta.octoaskt12;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import android.content.Context;

public class ElasticSearchHelper {
		
	// import gson jar file
	//remove everything from the database first
	
	
	
	private static Gson gson = new Gson();
	private Context cntxt;
	private static HttpClient client = new DefaultHttpClient();
	
	public static void SaveToDatabase(final QuestionArrayList qal){
		Thread thread = new Thread(){
		
		@Override
		public void run(){
			
			try{
			
			HttpClient client = new DefaultHttpClient();
			HttpPost connect = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301f14t12/");
			String json = gson.toJson(qal);
			
			connect.setEntity(new StringEntity(gson.toJson(qal)));
			connect.setEntity(new StringEntity(""));

			HttpResponse response = client.execute(connect);

			response.getStatusLine().toString();
			HttpEntity entity = response.getEntity();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String output = reader.readLine();
			while (output != null) {
				output = reader.readLine();}
			
			}catch(Exception e){
				System.exit(1);
				
				}//catch
			}//run
		};//thread
		thread.start();

	}//SaveToDatabase
	
	
	
}
