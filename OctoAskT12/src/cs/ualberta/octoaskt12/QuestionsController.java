package cs.ualberta.octoaskt12;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

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

	// return the user's questions, in the form of an ArrayList.
	// public static ArrayList<Question> getUserQuestions(User user){
	// return user.getUserQuestions();
	// }
	// return the user's favourited questions, in the form of an ArrayList.
	// public static ArrayList<Question> getUserFavouriteQuestions(User user){
	// return user.getFavouriteQuestions();
	// }

	/*
	 * add a question to the user's questions list, and also add it into list of
	 * all questions.
	 */

	public static void addQuestion(Question question) {
		allQuestions.addQuestion(question);
		ESClient esc = new ESClient();
		esc.addQuestion(question);
	}

	public static void updateQuestion(Question question) {
		ESClient esc = new ESClient();
		esc.updateQuestion(question);
	}
	/*
	 * when a user favourites a question, add this question into user favourite
	 * list.
	 */
	// public static void addUserFavouriteQuestion(User user, Question
	// question){
	// getUserFavouriteQuestions(user).add(question);
	// }

}
