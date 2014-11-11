package cs.ualberta.octoaskt12.ES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;

/*
 * inspiration taken from https://github.com/rayzhangcl/ESDemo/
 */
public class ESClient {
	private HttpClient httpClient = new DefaultHttpClient();

	private Gson gson = new Gson();

	public QuestionArrayList getQuestions() throws ClientProtocolException,
			IOException {
		HttpPost searchRequest = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f14t12/_search?size=150");

		String SEARCH_USER_FAV = "{\n" + "\"query\":{\n" + "\"match_all\":{}\n"
				+ "}\n" + "}";

		StringEntity stringEntity = new StringEntity(SEARCH_USER_FAV);

		searchRequest.setHeader("Accept", "application/json");
		searchRequest.setEntity(stringEntity);

		HttpResponse response = httpClient.execute(searchRequest);

		String status = response.getStatusLine().toString();
		System.out.println("Status: " + status);

		String json = getEntityContent(response);

		Type esResponseType = new TypeToken<ESSearchSearchResponse<Question>>() {
		}.getType();
		ESSearchSearchResponse<Question> esResponse = gson.fromJson(json,
				esResponseType);

		// System.out.println("Response: " + esResponse);
		QuestionArrayList qal = new QuestionArrayList();
		for (ESResponse<Question> r : esResponse.getHits()) {
			Question question = r.getSource();
			// System.out.println(question.getTitle());
			if (question.getImageBase64() != null) {
				question.setImage(question.decodeBase64(question
						.getImageBase64()));
			}
			if (question.getId() == null) {
				question.setId(r.getId());
				System.out.println("ID: " + question.getId());
			}
			qal.addQuestion(question);
		}
		return qal;
	}

	public void addQuestion(Question question) {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f14t12/question");
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(gson.toJson(question));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringEntity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();
	}

	public void updateQuestion(Question question) {
		HttpPost updateRequest = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f14t12/question/"
						+ question.getId());
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(gson.toJson(question));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		updateRequest.setHeader("Accept", "application/json");

		updateRequest.setEntity(stringEntity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(updateRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();
		// HttpPost updateRequest = new HttpPost(
		// "http://cmput301.softwareprocess.es:8080/cmput301f14t12/question/"
		// + question.getId() + "/_update");
		//
		// String UPDATE_UPVOTE = "{\n" + "\"doc\":{\n" + "\"numVotes\":"
		// + question.getVotes() + "}\n" + "}";
		//
		// String UPDATE_ANSWER = "{\n" + "\"doc\":{\n" + "\answer"
		//
		// StringEntity stringEntity = new StringEntity(UPDATE_UPVOTE);
		//
		// updateRequest.setHeader("Accept", "application/json");
		// updateRequest.setEntity(stringEntity);
		//
		// HttpResponse response = httpClient.execute(updateRequest);
		//
		// String status = response.getStatusLine().toString();
		// System.out.println("Status: " + status);
		//
		// String json = getEntityContent(response);
	}

	public String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		// System.out.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			// System.err.println(output);
			json += output;
		}
		// System.out.println("JSON:"+json);
		return json;
	}
}
