package cs.ualberta.octoaskt12;

import java.io.Serializable;
import java.util.GregorianCalendar;

// Object used to represent user's replies to questions or answers.
// Much simpler than the question/answer classes and cannot contain images.

public class Reply implements Serializable {

	private static final long serialVersionUID = 3694885075802427310L;
	private String reply;
	// the user of this reply
	private User user;
	// the date this reply was created
	private GregorianCalendar dateCreated;

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

	private Double longitude;
	private Double latitude;
	private String location;

	// constructor:
	public Reply(String reply, User user) {
		// set the user of this reply
		this.user = user;
		// set the reply text
		this.reply = reply;
		// set the date the reply is created
		this.dateCreated = new GregorianCalendar();
	}

	// constructor 2
	public Reply(String reply, User user, Double latitude, Double longitude,
			String location) {
		// set the user of this reply
		this.user = user;
		// set the reply text
		this.reply = reply;
		// set the date the reply is created
		this.dateCreated = new GregorianCalendar();

		this.longitude = longitude;
		this.latitude = latitude;
		this.location = location;
	}

	/***************************************************************************
	 * These method concerns the text of the reply.
	 ***************************************************************************/

	public String getBody() {
		if (location == null) {
			return reply;
		} else {
			return reply.concat(" (from:" + location + " )");
		}
	}

	public void setBody(String reply) {
		this.reply = reply;
	}

	/***************************************************************************
	 * Methods which get the user's name that created the reply and the date the
	 * reply was created.
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
