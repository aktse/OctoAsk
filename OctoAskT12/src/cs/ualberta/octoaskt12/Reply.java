package cs.ualberta.octoaskt12;

import java.util.GregorianCalendar;

public class Reply {

	private String reply;
	private GregorianCalendar dateCreated;

	public Reply (String string) {
		this.reply = string;
		this.dateCreated = new GregorianCalendar();
	}
	
	public String getString() {
		return reply;
	}
	
	public GregorianCalendar getTime() {
		return dateCreated;
	}	
}
