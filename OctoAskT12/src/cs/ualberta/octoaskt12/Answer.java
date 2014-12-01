package cs.ualberta.octoaskt12;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

// Object used to represent answers that the user creates.
// Contains an array list containing all replies to this answer.
// Can contain an image.

public class Answer implements Serializable {

	private static final long serialVersionUID = 5590593466133278091L;
	private String answerBody;
	// user who answered the question
	private User user;
	// number of up votes on this answer
	private int numVotes;
	// the replies for this answer
	private ArrayList<Reply> replies = new ArrayList<Reply>();
	// users that upvoted the answer
	private ArrayList<String> upvotedUsers = new ArrayList<String>();
	// the date the answer was created
	private GregorianCalendar dateCreated;
	private transient Bitmap image = null;
	private String imageBase64;
	private Double longitude;
	private Double latitude;
	private String location;
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// constructor
	public Answer(String answerBody, User user) {
		// set the answer body
		this.answerBody = answerBody;
		// set the date the answer was created
		this.dateCreated = new GregorianCalendar();
		// set the user who answered the question
		this.user = user;
	}

	/***************************************************************************
	 * This method concerns the body of the answer.
	 ***************************************************************************/
	// get the answer body
	public String getBody() {
		return answerBody;
	}

	// set the answer body
	public void setBody(String answerBody) {
		this.answerBody = answerBody;
	}

	/***************************************************************************
	 * These methods are responsible for getting the up votes of the answer and
	 * incrementing up vote whenever a user votes up an answer.
	 ***************************************************************************/

	// get the number of up votes for this answer
	public int getVotes() {
		return numVotes;
	}

	// increment the up votes for an answer
	public void incrementVotes() {
		numVotes++;
	}
	
	public void decrementVotes() {
		numVotes--;
	}

	/***************************************************************************
	 * These methods concerns the replies associated with the answer.
	 ***************************************************************************/
	// get the number of replies the answer has.
	public int getNumReplies() {
		return replies.size();
	}

	// get all the replies for this answer, in the form of an array
	public ArrayList<Reply> getReplies() {
		return replies;
	}

	// add a reply to the reply array list
	public void addReply(Reply reply) {
		replies.add(reply);
	}

	/***************************************************************************
	 * Methods which get the user's name that answered the question and the date
	 * the answer was created.
	 ***************************************************************************/
	// get the user that created the answer
	public String getUser() {
		return user.getName();
	}

	// get the date the question was created
	public GregorianCalendar getTime() {
		return dateCreated;
	}

	public int imageExists() {
		if (this.image == null) {
			return 0;
		} else {
			return 1;
		}
	}

	public ArrayList<String> getUpvotedUsers() {
		return this.upvotedUsers;
	}

	public void addUpvotedUser(String string) {
		this.upvotedUsers.add(string);
	}

	public void removeUpvotedUser(String string){
		this.upvotedUsers.remove(string);
	}
	
	public void setImage(Bitmap bitmap) {
		this.image = bitmap;
		this.imageBase64 = encodeToBase64(bitmap);
	}

	public Bitmap getAnswerImage() {
		return this.image;
	}

	public String getImageBase64() {
		return this.imageBase64;
	}

	/*
	 * Author: Roman Truba
	 * http://stackoverflow.com/questions/9768611/encode-and-
	 * decode-bitmap-object-in-base64-string-in-android
	 */
	public String encodeToBase64(Bitmap image) {
		Bitmap pic = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		pic.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] ba = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(ba, Base64.DEFAULT);
		return imageEncoded;
	}

	public Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}
