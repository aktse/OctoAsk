package cs.ualberta.octoaskt12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.util.Log;

public class ElasticSearchHelper {
		
	
	private static Gson gson;
	private static final String RESOURCE_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t12/abc/def";
	private static final String TAG = "Add suff";


	//call this first
	public ElasticSearchHelper(){
		gson = new Gson();
		
	}
	
	
	//private static HttpClient client;
	
	//public static void SaveToDatabase(final QuestionArrayList qal){
	public static void SaveToDatabase(Question question){

		HttpClient httpClient = new DefaultHttpClient();

		try {
			System.out.println("1");
			HttpPost addRequest = new HttpPost(RESOURCE_URL);

			
			System.out.println("2");
			StringEntity stringEntity = new StringEntity(gson.toJson(question));
			
			System.out.println("3");
			addRequest.setEntity(stringEntity);
			System.out.println("4");
			addRequest.setHeader("Accept", "application/json");
			
			System.out.println("5");
			
			System.out.println(addRequest);
			HttpResponse response = httpClient.execute(addRequest);
			System.out.println("6");

			String status = response.getStatusLine().toString();
			
			System.out.println("7");

			Log.i(TAG, status);
			System.out.println("finish try");
			System.out.println("finish try");

			System.out.println("finish try");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
			System.out.println("fail");

		}		
	}}
		
		/*
		if (gson == null){
			GsonFactory();
		}*/
		
		/*
		Thread thread = new Thread(){
		
		@Override
		public void run(){
			
			try{
				
			 gson = new Gson();

			
			HttpClient client = new DefaultHttpClient();
			
			//type is arraylist or maybe something like that or arraylistofquestionwithanswers then identifier is the instance	
			//you search in ....../type/_search ......
			//identifier is like getid of instance or something
			
			//The POST method is used to request that the origin server accept the entity enclosed in the request as a new subordinate of the resource identified by the Request-URI in the Request-Line. POST is designed to allow a uniform method to cover the following functions:
			//HttpPost connect = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301f14t12/" + "type" + "identifier");
			HttpPost connect = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301f14t12/");

			//StringEntity stringEntity = new StringEntity(gson.toJson(qal));
			//String json = gson.toJson(qal);
			
			String json = gson.toJson("Aaron Aaron Aaron");

			
			//Basic implementation of an HTTP request that can be modified.
			//connect.setEntity(new StringEntity(gson.toJson(qal)));
			connect.setEntity(new StringEntity(gson.toJson("Aaron Aaron Aaron")));

			//connect.setEntity(new StringEntity(""));

			HttpResponse response = client.execute(connect);

			getresponse = response.getStatusLine().toString();


			
			
			HttpEntity entity = response.getEntity();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String output = reader.readLine();
			
			while (output != null) {
				output = reader.readLine();}
			
			}catch(Exception e){
				System.out.println("not working");
				//		Log.i("addsomething", getresponse);

				//System.exit(1);
				
				}//catch
			}//run
		};//thread
		thread.start();

	}//SaveToDatabase 
	*/
	
	
	
	//http://www.programcreek.com/java-api-examples/index.php?api=com.google.gson.GsonBuilder
	//convert byte streams into character streams
	/*
	private static void GsonFactory(){
		
		//Writer writer = new OutputStreamWriter(new FileOutputStream("Output.json"));
		//set gson instance other than default
		GsonBuilder factory = new GsonBuilder();
		//factory.registerTypeAdapter(Id.class, new IdTypeAdapter());
		Gson gson = factory.create();
		//gson.toJson

		
	} */
	
	/*
	
	public static void picturehelper(){
		
	}
	
	public QuestionArrayList getAll(){
	*/
	
		//input
		/*
		 {
    	"query": {
        	"match_all": {}
    			}
		}
		 returns everything */
		
		//theyre using "query" : *
		
	/*
		HttpClient httpClient = new DefaultHttpClient();
		//use guanos lab function therest too much to type
		//check if it works WITHOUT IMAGES
		//Do the serialization thing for images and see if it work
		// if not work do serialization of just images without the arraylist
		// then combine images with text search

		return null;
	}*/
	
//}
