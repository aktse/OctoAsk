package cs.ualberta.octoaskt12;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class UserLoginActivity extends Activity {
	ArrayList<User> userList;
	User loggedInUser; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		// hide the actionbar
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		// get the username text entered by the user in login screen
		EditText textView = (EditText) findViewById(R.id.loginText);
		String username = textView.getText().toString();
		
		// check if the username is already in our list of users
		userList = UserArrayList.getUserList();
		if (!userAlreadyKnown(username)){
			// user doesnt exist, so make this user
			User newUser = new User(username);
			// add this user into our user list
			UserArrayList.addUser(newUser);
			// set this user as the one that is currently logged in
			loggedInUser = newUser;
		}
		else{ 
			// do nothing
			// the userAlreadyKnown method will set the loggedInUser. 
		}
		// set the loggedInUser
		UserArrayList.setCurrentUser(loggedInUser);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean userAlreadyKnown(String username){
		boolean userKnown = false;
		Iterator<User> user_itr = userList.iterator();
		while(user_itr.hasNext()){
			User user = user_itr.next();
			// if the entered username is already a name of a known user
			if(user.getName() == username){
				userKnown = true;
				loggedInUser = user;
			}
		}
		return userKnown;
	}
}
