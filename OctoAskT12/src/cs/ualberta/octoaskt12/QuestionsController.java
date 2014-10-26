package cs.ualberta.octoaskt12;

import java.util.ArrayList;

public class QuestionsController {
	private static QuestionArrayList allQuestions = null;
	/*
	 * If allQuestions hasn't been instantiated, then this method will make that instance.
	 * If allQuestions is already instantiated, then this method will simply return it.
	 */
	public static QuestionArrayList getAllQuestions(){
		if(allQuestions == null){
			allQuestions = new QuestionArrayList();
		}
		
		return allQuestions;
	}
	
	// return the user's questions, in the form of an ArrayList.
	public static ArrayList<Question> getUserQuestion(User user){
		return user.getUserQuestions();
	}
	// return the user's favourited questions, in the form of an ArrayList.
	public static ArrayList<Question> getUserFavouriteQuestion(User user){
		return user.getFavouriteQuestions();
	}
	
	/* add a question to the user's questions list, and also add it into 
	 * list of all questions.
	 */
	public static void addQuestion(Question question){
		allQuestions.addQuestion(question);
	}
	/* when a user favourites a question, add this question into user
	 * favourite list.
	 */
	public static void addUserFavouriteQuestion(User user, Question question){
		getUserFavouriteQuestion(user).add(question);
	}
	
}
