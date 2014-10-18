package cs.ualberta.octoaskt12.test;

import java.util.ArrayList;

import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;

import junit.framework.TestCase;

public class SearchQuestionsAndAnswersTest extends TestCase {
	
	public void testSearchQuestion()
	{
		// mock database
		QuestionArrayList question_list = new QuestionArrayList();
		User user_chris = new User("Chris");
	
		for (int i = 0; i < 20; i++)
		{
			question_list.addQuestion(new Question("Q " + i, "Body " + i, user_chris));
		}
		
		String searchTermNotExist = "Body 30";
		ArrayList<Question> result_list = question_list.searchQuestion(searchTermNotExist);
		
		assertTrue(result_list.size() == 0);
		
		String searchTermExist = "Body 12";
		result_list = question_list.searchQuestion(searchTermExist);
		
		assertTrue(result_list.size() > 0);
	}
}