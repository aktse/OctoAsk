package cs.ualberta.octoaskt12.test;

import java.util.Random;

import android.R;

import cs.ualberta.octoaskt12.CustomImage;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.User;
import junit.framework.TestCase;

public class SortTest extends TestCase {
	// user story 10?
	// test sort by date from newest to oldest
	public void testSortByDateFromNew()
	{
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user = new User("Chris");

		for (int i = 0; i < 20; i++)
		{
			question_list.addQuestion(new Question("Q " + i, "Body " + i, mock_user));	
		}
		
		// virtualSortByDateFromNew()
		
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
		
		// virtualSortByDateFromOld()
		
		for (int i = 0; i < 19; i++)
		{
			assertTrue(question_list.get(i).getTime().getTimeInMillis() < 
						question_list.get(i+1).getTime().getTimeInMillis());
		}
	}
	
	// user story 10
	// test sort questions by number of upvotes
	public void testSortQuestionsByUpvotes()
	{
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user = new User("Chris");

		Random random = new Random();
		
		for (int i = 0; i < 20; i++)
		{
			int rn = random.nextInt(100);
			Question temp_question = new Question("Q " + i, "Body " + i, mock_user);
			// increment vote of temp_question
			for (int j = 0; j < rn; j ++)
			{
				temp_question.incrementVotes();
			}
			question_list.addQuestion(temp_question);	
		}
		
		// virtualSortQuestionsByUpvotes()
		
		for (int i = 0; i < 19; i++)
		{
			assertTrue(question_list.get(i).getVotes() > 
						question_list.get(i+1).getVotes());
		}
	}
	
	// use story 9
	// sort questions by image
	public void testSortQuestiondByImage()
	{
		QuestionArrayList question_list = new QuestionArrayList();
		User mock_user = new User("Chris");
		
		Random random = new Random();
		
		for (int i = 0; i < 20; i++)
		{
			// generate 0/1 True/False
			int rn = random.nextInt(1);
			Question temp_question = new Question("Q " + i, "Body " + i, mock_user);
			// attach image if True
			if (rn == 1)
			{
				temp_question.attachImage(new CustomImage());
			}
			question_list.addQuestion(temp_question);	
		}
		
		question_list.sortByImage();
		
		// true when checking for image existing
		// switches to false when checking for empty images
		Boolean bool = new Boolean(true);
		
		for (int i = 0; i < 20; i++)
		{
			// changes state of checks
			if (question_list.get(i).imageExists() == false)
			{
				bool = false;
			}
			// if checking that image exists
			if (bool == true)
			{
				// assert image is attached
				assertTrue(question_list.get(i).imageExists());
			}
			else
			{
				assertFalse(question_list.get(i).imageExists());
			}
		}
		
		// test subsort
		// defaulting to most recent first
		for (int i = 0; i < 19; i++)
		{
			assertTrue(question_list.get(i).getTime().getTimeInMillis() < 
				question_list.get(i+1).getTime().getTimeInMillis());
		}
	}
}