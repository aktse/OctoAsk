package cs.ualberta.octoaskt12;


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
		
	
}
