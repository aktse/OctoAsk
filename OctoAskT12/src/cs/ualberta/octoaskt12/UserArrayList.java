package cs.ualberta.octoaskt12;

import java.util.ArrayList;

public class UserArrayList {
	private static User currentUser =  null;
	private static ArrayList<User> userArray = null;
	
	// get the list of all the users of the application
	public static ArrayList<User> getUserList(){
		if (userArray == null){
			userArray = new ArrayList<User>();
		}
		return userArray;
	}
	
	// add a new user into the list of users
	public static void addUser(User user){
		userArray.add(user);
	}
	
	// get the current user logged in.
	public static User getCurrentUser() {
		return currentUser;
	}
	
	/* set the current user, using the application,
	 * this will be the most recent user that logged in.
	 */
	public static void setCurrentUser(User currentuser) {
		currentUser = currentuser;
	}
	
}
