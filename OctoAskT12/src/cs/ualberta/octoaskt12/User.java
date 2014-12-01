package cs.ualberta.octoaskt12;

import java.io.Serializable;

// Simple object used to represent the user's credentials

public class User implements Serializable {

	private static final long serialVersionUID = -2652571757539537096L;
	private String name;
	
	public User(String name) {
		// set the name of the user
		this.name = name;
	}
	
	// get the name of the user
	public String getName() {
		return name;
	}
}
