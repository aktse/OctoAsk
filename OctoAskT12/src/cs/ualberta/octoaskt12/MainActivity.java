package cs.ualberta.octoaskt12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cs.ualberta.octoaskt12.adapters.CustomArrayAdapter;
import cs.ualberta.octoaskt12.adapters.DetailViewAdapter;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	// Don't delete userText, we are assuming these are the questions added to
	// the userText

	public static QuestionArrayList questionArrayList = new QuestionArrayList();

	/*
	 * public static ArrayList<Question> questions = new ArrayList<Question>();
	 * public static ArrayList<Question> myQuestions = new
	 * ArrayList<Question>();
	 */

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	private static String MyQuestionFilename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		// Kevin and Chris commented these two userText.add when making the
		// junit testcase
		/*
		 * questions.add(new Question("sup bruh")); questions.add(new
		 * Question("nm homes"));
		 */

		questionArrayList.addQuestion(new Question("sup bruh", "neel",
				new User("Ivan Burrito")));
		// MyQuestionFilename = "ChrisFile";
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		switch (position + 1) {
		case 1:
			fragmentManager.beginTransaction()
					.replace(R.id.container, QuestionFragment.newInstance())
					.commit();
			break;
		case 2:
			fragmentManager.beginTransaction()
					.replace(R.id.container, MyQuestionsFragment.newInstance())
					.commit();
			break;
		case 3:
			fragmentManager.beginTransaction()
					.replace(R.id.container, FavoriteFragment.newInstance())
					.commit();
			break;
		case 4:
			fragmentManager.beginTransaction()
					.replace(R.id.container, LaterFragment.newInstance())
					.commit();
			break;
		case 5:
			fragmentManager.beginTransaction()
					.replace(R.id.container, ProfileFragment.newInstance())
					.commit();
			break;
		case 6:
			fragmentManager.beginTransaction()
					.replace(R.id.container, HistoryFragment.newInstance())
					.commit();
			break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			break;

		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}

	public static class QuestionFragment extends Fragment {

		public CustomArrayAdapter questionsViewAdapter = null;

		public static QuestionFragment newInstance() {
			QuestionFragment fragment = new QuestionFragment();
			return fragment;
		}

		public QuestionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.questionsViewAdapter = new CustomArrayAdapter(getActivity(),
					questionArrayList);
			View rootView = inflater.inflate(R.layout.fragment_question,
					container, false);
			ListView lv = (ListView) rootView.findViewById(R.id.question_list);
			lv.setAdapter(questionsViewAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(
									R.id.container,
									QuestionDetailFragment
											.newInstance(questionArrayList
													.getQuestions().get(
															position)))
							.commit();
					// QuestionDetailFragment fragment =
					// QuestionDetailFragment.newInstance(questionArrayList.getQuestions().get(position));
				}

			});

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(1);
		}
	}

	/*
	 * @Override protected void onPause() { super.onPause(); questions.clear();
	 * }
	 */
	//

	//

	// public void SaveMyQuestions() {
	// try {
	// FileOutputStream fos = openFileOutput(MyQuestionFilename, MODE_PRIVATE);
	// ObjectOutputStream oos = new ObjectOutputStream(fos);
	// oos.writeObject(questionArrayList);
	// fos.close();
	// } catch(IOException e) {
	// Log.i("heysave", "heysave");
	// }
	// }
	/*
	 * public void LoadMyQuestions() throws ClassNotFoundException{
	 * 
	 * ArrayList<Question> MyQuestions = new ArrayList<Question>();
	 * 
	 * try { FileInputStream fos = openFileInput(MyQuestionFilename);
	 * ObjectInputStream ois = new ObjectInputStream(fos); MyQuestions =
	 * (ArrayList<Question>) ois.readObject(); fos.close(); } catch (IOException
	 * e) { Log.i("Well", "GoBackToLoadAgain"); }
	 * 
	 * int instanceinarraysize = MyQuestions.size(); questions.clear(); int
	 * dummy;
	 * 
	 * for (dummy = 0; dummy < instanceinarraysize; dummy++) {
	 * questions.add(MyQuestions.get(dummy)); } }
	 */

	/*
	 * @Override protected void onDestroy() { super.onStop(); SaveMyQuestions();
	 * }
	 */

	public static class MyQuestionsFragment extends Fragment {

		public static MyQuestionsFragment newInstance() {
			MyQuestionsFragment fragment = new MyQuestionsFragment();
			return fragment;
		}

		public MyQuestionsFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_myquestions,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(2);
		}
	}

	public static class FavoriteFragment extends Fragment {

		public static FavoriteFragment newInstance() {
			FavoriteFragment fragment = new FavoriteFragment();
			return fragment;
		}

		public FavoriteFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_favorite,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(3);
		}
	}

	public static class LaterFragment extends Fragment {

		public static LaterFragment newInstance() {
			LaterFragment fragment = new LaterFragment();
			return fragment;
		}

		public LaterFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_later,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(4);
		}
	}

	public static class ProfileFragment extends Fragment {

		private static ProfileFragment newInstance() {
			ProfileFragment fragment = new ProfileFragment();
			return fragment;
		}

		public ProfileFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_profile,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(5);
		}
	}

	//

	public static class HistoryFragment extends Fragment {

		public static HistoryFragment newInstance() {
			HistoryFragment fragment = new HistoryFragment();
			return fragment;
		}

		public HistoryFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_history,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(6);
		}
	}

	public static class QuestionDetailFragment extends Fragment {
		public static QuestionDetailFragment newInstance(Question question) {
			QuestionDetailFragment fragment = new QuestionDetailFragment();
			Bundle args = new Bundle();
			args.putSerializable("question", question);
			fragment.setArguments(args);
			return fragment;
		}

		public QuestionDetailFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.detail_view, container,
					false);
			Question question = (Question) getArguments().getSerializable(
					"question");

			ExpandableListView expListView = (ExpandableListView) rootView
					.findViewById(R.id.view_question_detail);

			DetailViewAdapter detailViewAdapter = new DetailViewAdapter(
					getActivity(), question);

			expListView.setAdapter(detailViewAdapter);

			return rootView;
		}

		// @Override
		// public void onAttach(Activity activity) {
		// super.onAttach(activity);
		// ((MainActivity) activity).onSec
		// }
	}

	public void createQuestion(MenuItem menu) {
		Intent intent = new Intent(MainActivity.this,
				CreateQuestionActivity.class);
		startActivity(intent);
	}
}
