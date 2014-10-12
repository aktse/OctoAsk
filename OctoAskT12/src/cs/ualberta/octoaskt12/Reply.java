package cs.ualberta.octoaskt12;

import java.util.GregorianCalendar;

public class Reply {

	private String reply;
	private User user;
	private GregorianCalendar dateCreated;

	public Reply (String reply) {
		this.reply = reply;
		this.dateCreated = new GregorianCalendar();
	}
	
	/***************************************************************************
 	* Replies
 	***************************************************************************/
	
	public String getString() {
		return reply;
	}
	
	public void setString(String reply) {
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
