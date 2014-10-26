package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import junit.framework.TestCase;

public class UpvoteQAndAOfOthers extends TestCase {
	
	// test upvote questions of other user
	public void testUpvoteQuestion()
	{
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user_chris = new User("Chris");
		// Kevin upvotes Chris' questions
		User mock_user_kevin = new User("Kevin");
		
		for (int i = 0; i< 10; i++)
		{
			Question question = new Question("Q " + i, "Body " + i, mock_user_chris);
			question_list.add(question);
			mock_user_chris.addQuestion(question);
		}
		
		// stuck
		//question_list.get(i).
	}
}
