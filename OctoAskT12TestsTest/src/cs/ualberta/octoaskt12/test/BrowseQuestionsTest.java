package cs.ualberta.octoaskt12.test;

import cs.ualberta.octoaskt12.MainActivity;
import junit.framework.TestCase;

public class BrowseQuestionsTest extends TestCase {
	public void testBrowse()
	{
		// how to test 'live' list?
		int temp = MainActivity.questionArrayList.getSize();
		assertTrue(temp > 0);
	}
}