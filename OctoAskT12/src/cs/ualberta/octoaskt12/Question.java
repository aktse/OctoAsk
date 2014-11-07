package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648225441126206210L;
	private String questionTitle;
	// the body of the question, the actual question
	private String questionBody;
	// number of upVotes this question has
	private int numVotes;
	// the date the question was created at
//	private GregorianCalendar dateCreated;
	private long dateCreated;
	// the user who asked this question
	private User user;
	private String userName;
	// the answers for this question
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	// the replies for this question
	private ArrayList<Reply> replies = new ArrayList<Reply>();
	
	private ArrayList<User> upvotedUsers = new ArrayList<User>();
	
	private ArrayList<String> favoritedUsers = new ArrayList<String>();
	
//	private CustomImage customImage = null;
	
	private transient Bitmap image = null;
	
	
	// Constructor
	public Question(String questionTitle, String questionBody, User user) {
		// set the title and body of the question
		this.questionTitle = questionTitle;
		this.questionBody = questionBody;
		// associate this question with the date when it is asked
//		this.dateCreated = new GregorianCalendar();
		this.dateCreated = System.currentTimeMillis() / 1000L;
		// associate this question with a user
		this.user = user;
	}
	
	/***************************************************************************
 	* These methods concern the title and body of the question.
 	* title is set using setTitle method, and retrieved by getTitle method
 	* body is set using setBody method, and retrieved by getBody method
 	***************************************************************************/
	
	public void setTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	
	public String getTitle() {
		return questionTitle;
	}
	
	public void setBody(String questionBody) {
		this.questionBody = questionBody;
	}
	
	public String getBody() {
		return questionBody;
	}
	
	/***************************************************************************
	* These methods are responsible for getting the up votes of the question and
	* incrementing up vote whenever a user votes up a question.
	***************************************************************************/
	
	public int getVotes() {
		return numVotes;
	}
	
	public void incrementVotes() {
		numVotes++;
	}
	
	/***************************************************************************
 	* These methods concerns the answers associated with the question.
 	***************************************************************************/
	// get the number of answers for this question
	public int getNumAnswers() {
		return answers.size();
	}
	// get all the answers for this question, in the form of an array
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	// add answer to the answers array list
	public void addAnswer(Answer answer) {
		answers.add(answer);
	}
	
	/***************************************************************************
	 * These methods concerns the replies associated with the question.
	 ***************************************************************************/
	// get the number of replies the question has.
	public int getNumReplies() {
		return replies.size();
	}
	// get all the replies for this question, in the form of an array
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	// add a reply to the reply array list
	public void addReply(Reply reply) {
		replies.add(reply);
	}
	
	/***************************************************************************
 	* Methods which get the user's name that asked the question and the date
 	* the question was created.
 	***************************************************************************/
	// get the user's name
	public String getUser() {
		return user.getName();
	}
	// get the date the question was created
	public long getTime() {
		return dateCreated;
	}

//	public void attachImage(CustomImage customImage) {
//		this.customImage = customImage;
//	}

	public int imageExists() {
		
		if (this.image == null) {
			return 0;
		} else {
			return 1;
		}		
	}

//	public CustomImage getImage() {
//		return this.customImage;
//	}
	
	public void addUpvotedUser(User user) {
		this.upvotedUsers.add(user);
	}
	
	public ArrayList<User> getUpvotedUsers() {
		return this.upvotedUsers;
	}
	
	public void setImage(Bitmap bitmap) {
		this.image = bitmap;
	}
	
	public Bitmap getQuestionImage() {
		return this.image;
	}

}
