package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3694885075802427310L;
	private String reply;
	// the user of this reply
	private User user;
	// the date this reply was created
	private GregorianCalendar dateCreated;
	// constructor:
	public Reply (String reply, User user) {
		// set the user of this reply
		this.user = user;
		// set the reply text
		this.reply = reply;
		// set the date the reply is created
		this.dateCreated = new GregorianCalendar();
	}
	
	/***************************************************************************
 	* These method concerns the text of the reply.
 	***************************************************************************/

	public String getBody() {
		return reply;
	}
	
	public void setBody(String reply) {
		this.reply = reply;
	}
	
	/***************************************************************************
 	* Methods which get the user's name that created the reply and the date
 	* the reply was created.
 	***************************************************************************/
	// get the user's name
	public String getUser() {
		return user.getName();
	}
	// get the date that the reply was created
	public GregorianCalendar getTime() {
		return dateCreated;
	}	
}
