package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import junit.framework.TestCase;

public class SortTest extends TestCase {
	// test sort by date from newest to oldest
	public void testSortByDateFromNew()
	{
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user = new User("Chris");

		for (int i = 0; i < 20; i++)
		{
			question_list.addQuestion(new Question("Q " + i, "Body " + i, mock_user));	
		}
		
		// virtualSortFromNew()
		
		for (int i = 0; i < 19; i++)
		{
			assertTrue(question_list.get(i).getTime().getTimeInMillis() > 
						question_list.get(i+1).getTime().getTimeInMillis());
		}
	}
	
	// test sort by date from oldest to newest
	public void testSortByDateFromOld()
	{
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user = new User("Chris");

		for (int i = 0; i < 20; i++)
		{
			question_list.addQuestion(new Question("Q " + i, "Body " + i, mock_user));	
		}
		
		// virtualSortFromOld()
		
		for (int i = 0; i < 19; i++)
		{
			assertTrue(question_list.get(i).getTime().getTimeInMillis() < 
						question_list.get(i+1).getTime().getTimeInMillis());
		}
	}
}
