package cs.ualberta.octoaskt12.test;

import java.util.ArrayList;

import cs.ualberta.octoaskt12.Answer;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.Reply;
import cs.ualberta.octoaskt12.User;
import junit.framework.TestCase;

public class BrowsingTests extends TestCase {
	// use case 1?
	public void testBrowse()
	{
		MainActivity.updateQuestions();
		int temp = MainActivity.questionArrayList.getSize();
		assertTrue(temp > 0);
	}
	
	// use cases 2 and 5?
	/*
	public void testBrowseQuestionAndAnswers()
	{
		User mock_user = new User("Chris");
		
		String title = "What's for lunch?";
		String body = "Hungry. What is lunch?";
		Question q1 = new Question(title, body, mock_user);

		ArrayList<Answer> answer_list = new ArrayList<Answer>();
		
		for (int i = 0; i < 10; i++)
		{
			Answer answer = new Answer("Spam and eggs " + i + ".", mock_user);
			answer_list.add(answer);
		}
		
		for (Answer answer : answer_list)
		{
			q1.addAnswer(answer);
		}
		
		assertEquals("Total answers not tracked.", q1.getNumAnswers(), answer_list.size());
		
		for (int i = 0; i < answer_list.size(); i++)
		{
			assertEquals(q1.getAnswers().get(i), answer_list.get(i));
		}
	}
	
	// use cases 3 and 6?
	// test on replies to questions
	public void testRepliesToQuestions()
	{
		User mock_user = new User("Chris");
		
		String title = "What's for lunch?";
		String body = "Hungry. What is lunch?";
		Question q1 = new Question(title, body, mock_user);

		ArrayList<Reply> reply_list = new ArrayList<Reply>();
		
		for (int i = 0; i < 10; i++)
		{
			Reply reply = new Reply("Spam and eggs " + i + ".", mock_user);
			reply_list.add(reply);
		}
		
		for (Reply reply : reply_list)
		{
			q1.addReply(reply);
		}
		
		assertEquals("Total replies not tracked.", q1.getNumReplies(), reply_list.size());
		
		for (int i = 0; i < reply_list.size(); i++)
		{
			assertEquals(q1.getReplies().get(i), reply_list.get(i));
		}
	}
	
	// test on replies to answers
	// just found duplicates...
	public void testRepliesToAnswers()
	{
		;
	}
	*/
	
	// use case 14
	public void testTrackAnswerCount()
	{
		User mock_user = new User("Chris");
		
		String title = "What's for lunch?";
		String body = "Hungry. What is lunch?";
		Question q1 = new Question(title, body, mock_user);

		ArrayList<Answer> answer_list = new ArrayList<Answer>();
		
		for (int i = 0; i < 10; i++)
		{
			Answer answer = new Answer("Spam and eggs " + i + ".", mock_user);
			answer_list.add(answer);
		}
		
		for (Answer answer : answer_list)
		{
			q1.addAnswer(answer);
		}
		
		assertEquals("Total answers not tracked.", q1.getNumAnswers(), answer_list.size());
	}
}