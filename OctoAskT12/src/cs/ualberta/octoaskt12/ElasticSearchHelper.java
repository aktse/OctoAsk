package cs.ualberta.octoaskt12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.util.Log;

public class ElasticSearchHelper {
		
	// import gson jar file
	//remove everything from the database first
	

	
	//private static Gson gson = new Gson();
	private static Gson gson = null;
	private Context cntxt;
	
	public ElasticSearchHelper(){
		gson = new Gson();
		
	}
	
	private static HttpClient client = new DefaultHttpClient();
	
	public static void SaveToDatabase(final QuestionArrayList qal){
		Thread thread = new Thread(){
		
		@Override
		public void run(){
			
			try{
			
			HttpClient client = new DefaultHttpClient();
			
			//type is arraylist or maybe something like that or arraylistofquestionwithanswers then identifier is the instance	
			//you search in ....../type/_search ......
			//identifier is like getid of instance or something
			
			//The POST method is used to request that the origin server accept the entity enclosed in the request as a new subordinate of the resource identified by the Request-URI in the Request-Line. POST is designed to allow a uniform method to cover the following functions:
			HttpPost connect = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301f14t12/" + "type" + "identifier");
			
			//StringEntity stringEntity = new StringEntity(gson.toJson(qal));
			String json = gson.toJson(qal);
			
			//Basic implementation of an HTTP request that can be modified.
			connect.setEntity(new StringEntity(gson.toJson(qal)));
			//connect.setEntity(new StringEntity(""));

			HttpResponse response = client.execute(connect);

			String getresponse = response.getStatusLine().toString();
			Log.i("addsomething", getresponse);

			
			/*
			HttpEntity entity = response.getEntity();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String output = reader.readLine();
			
			while (output != null) {
				output = reader.readLine();}
			*/
			}catch(Exception e){
				System.exit(1);
				
				}//catch
			}//run
		};//thread
		thread.start();

	}//SaveToDatabase
	
	
	//http://www.programcreek.com/java-api-examples/index.php?api=com.google.gson.GsonBuilder
	//convert byte streams into character streams
	private static void GsonFactory() throws IOException{
		
		
		//input
		/*
		 {
    	"query": {
        	"match_all": {}
    			}
		}
		 returns everything */
		
		Writer writer = new OutputStreamWriter(new FileOutputStream("Output.json"));
		//set gson instance other than default
		GsonBuilder factory = new GsonBuilder();
		//factory.registerTypeAdapter(Id.class, new IdTypeAdapter());
		Gson gson = factory.create();
		//gson.toJson

		
	} 
	
}
