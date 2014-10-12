package cs.ualberta.octoaskt12;

<<<<<<< HEAD

public class Reply {
	private String replyText = "";
	private int upvote = 0;
	
	
	// get the text of the reply
	public String getReplyText() {
		return replyText;
	}
	// set the text of the reply
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	// get the count of the upVote for the answer
	public int getUpVote() {
		return upvote;
	}
	// increment the count of upVote of the answer
	public void incrementUpVote() {
		this.upvote++;
	}
		
=======
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
>>>>>>> beae88ca4458365291a2f48cac9adabcffe17f16
	
}
