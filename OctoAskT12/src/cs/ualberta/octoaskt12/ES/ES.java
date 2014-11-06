package cs.ualberta.octoaskt12.ES;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.indices.CreateIndex;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cs.ualberta.octoaskt12.Question;

// some code taken from https://github.com/rayzhangcl/ESDemo

public class ES {
	private static final String ES_URL = "http://cmput301.softwareprocess.es:8080/cmput301f14t12/_search";
	static Gson gson = new Gson();
//	private static JestClient jc;
//	
//	public static JestClient getClient() {
//		if (jc == null) {
//			ClientConfig cc = new ClientConfig.Builder(ES_URL).multiThreaded(true).build();
//			JestClientFactory jcf = new JestClientFactory();
//			jcf.setClientConfig(cc);
//			jc = jcf.getObject();
//		}
//		return jc;
//	}
	
//	ClientConfig clientConfig = new ClientConfig.Builder(ES_URL).multiThreaded(true).build();
//	JestClientFactory jcf = new JestClientFactory();
//	jcf.setClientConfig(clientConfig);
//	jestClient = jcf.getObject();
	
	public static void sendRequest() {
		String userid = "37";
		class FetchTask extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String... user) {
				System.out.println("User: " + user);
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(ES_URL);

					String SEARCH_USER_FAV = 
							"{\n" +
								   "\"query\":{\n" +
								        "\"term\":{\"favorites\":\"65\"}\n" +
								   "}\n" +
							"}";
					
					StringEntity stringEntity = new StringEntity(SEARCH_USER_FAV);
					
					httppost.setHeader("Accept", "application/json");
					httppost.setEntity(stringEntity);
					
					HttpResponse response = httpclient.execute(httppost);
					
					String json = getEntityContent(response);
					
//					System.out.println("Response len: " + json);
					
					Type esResponseType = new TypeToken<ESSearchSearchResponse<Question>>(){}.getType();
					ESSearchSearchResponse<Question> esResponse = gson.fromJson(json, esResponseType);
					
					System.out.println("Response: " + esResponse);

					return "";
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}

			@Override
			protected void onPostExecute(String result) {
				if (result != null) {
					// do something
				} else {
					// error occured
				}
			}
		}

		new FetchTask().execute(userid);
	}
	
	public static String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
		String output;
		System.out.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			json += output;
		}
		System.out.println("JSON:"+json);
		return json;
	}
}

