package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3694885075802427310L;
	private String reply;
	private User user;
	private GregorianCalendar dateCreated;

	public Reply (String reply, User user) {
		this.user = user;
		this.reply = reply;
		this.dateCreated = new GregorianCalendar();
	}
	
	/***************************************************************************
 	* Replies
 	***************************************************************************/
	
	public String getBody() {
		return reply;
	}
	
	public void setBody(String reply) {
		this.reply = reply;
	}
	
	/***************************************************************************
 	* Misc
 	***************************************************************************/
	
	public String getUser() {
		return user.getName();
	}
	
	public GregorianCalendar getTime() {
		return dateCreated;
	}
	
}
