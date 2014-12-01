package cs.ualberta.octoaskt12;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends Activity {
	private static final String FILENAME = "UserName.sav";
	ArrayList<User> userList;
	User loggedInUser;
	String username;
	EditText usernameText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		// hide the actionbar
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		// get the username text entered by the user in login screen
		usernameText = (EditText) findViewById(R.id.loginText);
		Button setUsernameButton = (Button) findViewById(R.id.setUsernameButton);
		setUsernameButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = usernameText.getText().toString();
				userList = UserController.getUserList();
				// check if the username is already in our list of users
				if (!userAlreadyKnown(username)) {
					// user doesnt exist, so make this user
					User newUser = new User(username);
					// add this user into our user list
					UserController.addUser(newUser);
					// set this user as the one that is currently logged in
					loggedInUser = newUser;
				} else {
					// do nothing
					// the userAlreadyKnown method will set the loggedInUser.
				}
				// set the loggedInUser
				UserController.setCurrentUser(loggedInUser);
				Toast.makeText(getApplicationContext(), "Logged in as "+loggedInUser.getName(), Toast.LENGTH_SHORT).show();
				finish();
			}
		});

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

	/*
	 * this method will iterate through the list of all user objects if a known
	 * user already has the name of the username current login in then set the
	 * variable loggedInUser as this known user.
	 */
	public boolean userAlreadyKnown(String username) {
		boolean userKnown = false;
		Iterator<User> user_itr = userList.iterator();
		while (user_itr.hasNext()) {
			User user = user_itr.next();
			// if the entered username is already a name of a known user
			if (user.getName() == username) {
				// user is found in our user list
				userKnown = true;
				loggedInUser = user;
				/*
				 * no need to iterate through the rest of the user objects so,
				 * break out of the while loop.
				 */
				break;
			}
		}
		// if user is known, return true
		// if user is not known, return false
		return userKnown;
	}
}
