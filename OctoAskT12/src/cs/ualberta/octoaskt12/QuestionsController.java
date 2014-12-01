package cs.ualberta.octoaskt12;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;
import cs.ualberta.octoaskt12.ES.ESClient;

public class QuestionsController {
	private static QuestionArrayList allQuestions = null;

	/*
	 * If allQuestions hasn't been instantiated, then this method will make that
	 * instance. If allQuestions is already instantiated, then this method will
	 * simply return it.
	 */
	public static QuestionArrayList getAllQuestions()
			throws ClientProtocolException, IOException {

		ESClient esc = new ESClient();
		allQuestions = esc.getQuestions();
		return allQuestions;

	}

	/*
	 * add a question to the user's questions list, and also add it into list of
	 * all questions.
	 */

	public static void addQuestion(Question question) {
		ESClient esc = new ESClient();
		esc.addQuestion(question);
		allQuestions.addQuestion(question);
	}

	public static void updateQuestion(Question question) {
		ESClient esc = new ESClient();
		esc.updateQuestion(question);
	}

}
