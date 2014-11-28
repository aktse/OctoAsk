package cs.ualberta.octoaskt12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.client.ClientProtocolException;

import cs.ualberta.octoaskt12.ES.ESClient;
import cs.ualberta.octoaskt12.adapters.CustomArrayAdapter;
import cs.ualberta.octoaskt12.adapters.DetailViewAdapter;
import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	ESClient esc = new ESClient();

	public static QuestionArrayList questionArrayList = new QuestionArrayList();
	
	public static QuestionArrayList myQuestionsList = new QuestionArrayList();
	
	public static QuestionArrayList sortedQuestionArrayList;

	public static QuestionArrayList historyArrayList = new QuestionArrayList();

	public static QuestionArrayList favoritesArrayList = new QuestionArrayList();

	public static QuestionArrayList laterArrayList = new QuestionArrayList();

	private static NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	private static int REQUEST_CODE_USERNAME = 1521;

	private static int sortIndex = 0;

	private static String MyQuestionFilename;
	private static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		StrictMode.ThreadPolicy p = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(p);



		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();

		// no connection
		if (ni == null)
		{
			AllQuestionsCacheManager aqcm = new AllQuestionsCacheManager(getApplicationContext());
			aqcm.init(); // create sav file
			aqcm.load();
			questionArrayList = aqcm.get();
			aqcm.clear();
		}
		// have connection
		else
		{	
			/*
			updateQuestions();
			
			// may need to modify this part
			// may need to modify this part
			// may need to modify this part
			// may need to modify this part
			// may need to modify this part

			QuestionsCacheManager qcm = new QuestionsCacheManager(getApplicationContext());
			qcm.clear();
			*/
			
			// new
			QuestionsCacheManager qcm = new QuestionsCacheManager(CallContext());
			qcm.loadQuestions();
			ArrayList<Question> cachedQuestions = qcm.get();
			
			for (Question cachedQuestion : cachedQuestions)
			{
				QuestionsController.addQuestion(cachedQuestion);
			}			
			
			ArrayList<Question> emptyQuestionList = new ArrayList<Question>();
			qcm.set(emptyQuestionList);
			qcm.clear();
			qcm.saveQuestions();
			
			updateQuestions();
		}
		
		User currentUser = UserController.getCurrentUser();

		context = this;

		// Set up the drawer.
		// -----------------------------------------------------
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		// -------------------------------------------------------------------------

		MyQuestionFilename = "ChrisFile";
		// ElasticSearchAddQuestion.AddToDatabase();

		// create new .sav data
		
		// may need to modify this part
		// may need to modify this part
		// may need to modify this part
		// may need to modify this part
		// may need to modify this part
		QuestionsCacheManager qcm = new QuestionsCacheManager(getApplicationContext());
		qcm.init();
		
		MyQuestionsCacheManager mqcm = new MyQuestionsCacheManager(getApplicationContext());
		mqcm.init();
		System.out.println(myQuestionsList);
		myQuestionsList = mqcm.get();
		System.out.println("after"+myQuestionsList);
		
		FavouritesCacheManager fcm = new FavouritesCacheManager(getApplicationContext());
		fcm.init();
		System.out.println(favoritesArrayList);
		favoritesArrayList = fcm.get();
		System.out.println("after"+favoritesArrayList);
		
		HistoryCacheManager hcm = new HistoryCacheManager(getApplicationContext());
		hcm.init();
		historyArrayList = hcm.get();
		
		ReadLaterCacheManager rlcm = new ReadLaterCacheManager(getApplicationContext());
		rlcm.init();
		laterArrayList = rlcm.get();
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
					.replace(R.id.container, HistoryFragment.newInstance())
					.commit();
			break;
		case 6:
			fragmentManager.beginTransaction()
					.replace(R.id.container, ProfileFragment.newInstance())
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

	@Override
	public void onPause()
	{
		super.onPause();
		
		// save all viewable questions
		AllQuestionsCacheManager aqcm = new AllQuestionsCacheManager(getApplicationContext());
		aqcm.clear();
		aqcm.set(questionArrayList);
		aqcm.save();

		MyQuestionsCacheManager mqcm = new MyQuestionsCacheManager(getApplicationContext());
		mqcm.clear();
		mqcm.set(myQuestionsList);
		mqcm.save();
		
		// save favourites
		FavouritesCacheManager fcm = new FavouritesCacheManager(getApplicationContext());
		fcm.clear();
		fcm.set(favoritesArrayList);
		fcm.save();
		
		// save history
		HistoryCacheManager hcm = new HistoryCacheManager(getApplicationContext());
		/*
		Log.i("LOOK HERE", "HEEEEEEEEEERE");
		Log.i("LOOK HERE", "HEEEEEEEEEERE");
		Log.i("LOOK HERE", "HEEEEEEEEEERE");
		Log.i("LOOK HERE", "HEEEEEEEEEERE");
		Log.i("LOOK HERE", "HEEEEEEEEEERE");
		*/
		hcm.clear();
		hcm.set(historyArrayList);
		hcm.save();
		
		// save read laters
		ReadLaterCacheManager rlcm = new ReadLaterCacheManager(getApplicationContext());
		rlcm.clear();
		rlcm.set(laterArrayList);
		rlcm.save();
	}
	public void createSortDialog(MenuItem menu) {
		// Creates a dialog fragment to prompt user into making a selection to
		// sort view
		SortFragment sortFragment = SortFragment.newInstance(sortIndex);
		sortFragment.show(getSupportFragmentManager(), "Sort");
	}

	public void createSearchDialog(MenuItem menu) {
		// Creates a dialog fragment to prompt user into entering keywords to
		// search the database
		SearchFragment searchFragment = SearchFragment.newInstance();
		searchFragment.show(getSupportFragmentManager(), "Search");
	}

	public void doPositiveClick(String string, int sortIndex) {
		// Positive click of sorting dialog fragment
		// Calls a SortManager and sorts the current array based on user
		// selection
		SortManager sortManager = new SortManager();
		this.sortIndex = sortIndex;
		if (string == "Date") {
			questionArrayList = sortManager.SortByDate(questionArrayList);
		} else if (string == "Upvotes") {
			questionArrayList = sortManager.SortByVotes(questionArrayList);
		} else if (string == "Contains Image") {
			questionArrayList = sortManager.SortByImages(questionArrayList);
		} else {
			System.out.println("invalid selection");
		}
		// Refresh display
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, QuestionFragment.newInstance())
				.commit();
	}

	public void doNegativeClick() {
		// Cancels dialog fragments
	}

	public static void updateQuestions() {
		// Updates questions by querying server
		try {
			questionArrayList = QuestionsController.getAllQuestions();
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void searchQuestions(String searchString) {
		try {
			questionArrayList = SearchController.searchQuestions(searchString);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, QuestionFragment.newInstance())
				.commit();
	}

	public void createQuestion(MenuItem menu) {
		if (UserController.getCurrentUser() == null) {
			Intent intent = new Intent(MainActivity.this,
					UserLoginActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(MainActivity.this,
					CreateQuestionActivity.class);
			startActivity(intent);
		}
	}
	
	public void addFavorite(View view) {
		//favoritesArrayList.addQuestion(question);
	}

	public void setUsername(View view) {
		Intent intent = new Intent(this, UserLoginActivity.class);
		startActivityForResult(intent, REQUEST_CODE_USERNAME);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_USERNAME) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, ProfileFragment.newInstance())
					.commit();
		}

	}

	public static void EditUsername() {
		// TestCase 23
		// waiting for implementation of other methods
	}

	public static void SeeFreshestComment() {
		// TestCase 22
		// waiting for implementation of other methods

	}

	public static void SeeMostUpvotedQuestion() {
		// TestCase 13
		// waiting for implementation of other methods

	}

	public static void SeeMostUpvotedAnswer() {
		// TestCase 13
		// waiting for implementation of other methods

	}

	public static void PushStored() {
		// TestCase 20/21
		// waiting for implementation of other methods

	}

	public static void AuthorReplyOffline() {
		// TestCase 20/21
		// waiting for implementation of other methods

	}

	public static void AuthorQuestionOffline() {
		// TestCase 20/21
		// waiting for implementation of other methods
	}

	public static void AuthorAnswerOffline() {
		// TestCase 20/21
		// waiting for implementation of other methods
	}

	public static void SaveMyQuestions(Context context,
			QuestionArrayList questions) {
		OfflineDataManager.SaveMyQuestions(context, questions);

	}

	public static Context CallContext() {
		return context;
	}

	@Override
	public void onBackPressed() {
		int count = getFragmentManager().getBackStackEntryCount();
		System.out.println(count);
		if (count > 0) {
			FragmentManager fragmentManager = getFragmentManager();
			System.out
					.println(fragmentManager.getBackStackEntryAt(0).getName());

			System.out.println(getFragmentManager().getBackStackEntryCount());
			if (fragmentManager.getBackStackEntryAt(0).getName() == "Questions") {
				fragmentManager.popBackStackImmediate();
				fragmentManager
						.beginTransaction()
						.replace(R.id.container, QuestionFragment.newInstance())
						.commit();
			} else if (fragmentManager.getBackStackEntryAt(0).getName() == "History") {
				fragmentManager.popBackStackImmediate();
				fragmentManager.beginTransaction()
						.replace(R.id.container, HistoryFragment.newInstance())
						.commit();
			} else {
				super.onBackPressed();
			}
		} else {
			super.onBackPressed();
		}
	}

	public static void LoadMyQuestions(Context context,
			QuestionArrayList questions) throws ClassNotFoundException {
		OfflineDataManager.LoadMyQuestions(context, questions);
	}

	public static class QuestionFragment extends Fragment {

		public CustomArrayAdapter questionsViewAdapter = null;

		public static QuestionFragment newInstance() {
			QuestionFragment fragment = new QuestionFragment();
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.questionsViewAdapter = new CustomArrayAdapter(getActivity(),
					questionArrayList);
			View rootView = inflater.inflate(R.layout.fragment_question,
					container, false);

			final ListView lv = (ListView) rootView
					.findViewById(R.id.question_list);
			lv.setAdapter(questionsViewAdapter);
			questionsViewAdapter.notifyDataSetChanged();
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					Question question = questionArrayList.getQuestions().get(position);
					if (historyArrayList.has(question)) {
						historyArrayList.remove(question);
						historyArrayList.addToFront(question);
					} else {
						historyArrayList.addToFront(question);
					}
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(
									R.id.container,
									QuestionDetailFragment
											.newInstance(questionArrayList
													.getQuestions().get(
															position)))
							.addToBackStack("Questions").commit();
				}

			});

			// Code which calls the update function to get new questions from
			// the server
			final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) rootView
					.findViewById(R.id.swipe_container);
			swipeLayout
					.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
						@Override
						public void onRefresh() {
							swipeLayout.setRefreshing(true);
							updateQuestions();
							swipeLayout.setRefreshing(false);
							questionsViewAdapter = new CustomArrayAdapter(
									getActivity(), questionArrayList);
							lv.setAdapter(questionsViewAdapter);
							onResume();
							questionsViewAdapter.notifyDataSetChanged();
						}
					});

			swipeLayout.setColorSchemeResources(
					android.R.color.holo_blue_bright,
					android.R.color.holo_green_light,
					android.R.color.holo_orange_light,
					android.R.color.holo_red_light);

			// Listener for scrolling refresh
			lv.setOnScrollListener(new AbsListView.OnScrollListener() {
				@Override
				public void onScrollStateChanged(AbsListView absListView, int i) {

				}

				// Disables scrolling refresh if view is not at the top of the
				// screen
				@Override
				public void onScroll(AbsListView absListView,
						int firstVisibleItem, int visibleItemCount,
						int totalItemCount) {
					if (firstVisibleItem == 0)
						swipeLayout.setEnabled(true);
					else
						swipeLayout.setEnabled(false);
				}
			});

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();

			ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(CallContext().CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();

			// have connection
			if (ni != null)
			{
				
				QuestionsCacheManager qcm = new QuestionsCacheManager(CallContext());
				qcm.loadQuestions();
				ArrayList<Question> cachedQuestions = qcm.get();
				
				for (Question cachedQuestion : cachedQuestions)
				{
					QuestionsController.addQuestion(cachedQuestion);
				}			
				
				ArrayList<Question> emptyQuestionList = new ArrayList<Question>();
				qcm.set(emptyQuestionList);
				qcm.clear();
				qcm.saveQuestions();
			}		
			
			// Re-sorts the array when app is closed and reopened
			// Guarentees consistency in sorting (doesn't randomly unsort)
			SortManager sortManager = new SortManager();
			if (sortIndex == 0) {
				questionArrayList = sortManager.SortByDate(questionArrayList);
			} else if (sortIndex == 1) {
				questionArrayList = sortManager.SortByVotes(questionArrayList);
			} else {
				questionArrayList = sortManager.SortByImages(questionArrayList);
			}
			questionsViewAdapter.notifyDataSetChanged();
			// -----------------------------------------------------------------

		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(1);
		}

	}

	// ========================================================================

	public static class MyQuestionsFragment extends Fragment {

		public CustomArrayAdapter MyQuestionAdapter = null;

		public static MyQuestionsFragment newInstance() {
			MyQuestionsFragment fragment = new MyQuestionsFragment();
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			this.MyQuestionAdapter = new CustomArrayAdapter(getActivity(),
					myQuestionsList);

			View rootView = inflater.inflate(R.layout.fragment_myquestions,
					container, false);

			ListView lv = (ListView) rootView
					.findViewById(R.id.myquestion_list);
			lv.setAdapter(MyQuestionAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(
									R.id.container,
									QuestionDetailFragment
											.newInstance(myQuestionsList
													.getQuestions().get(
															position)))
							.commit();
				}

			});

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(2);
		}
	}

	// ========================================================================

	public static class FavoriteFragment extends Fragment {

		public CustomArrayAdapter FavouriteAdapter = null;

		public static FavoriteFragment newInstance() {
			FavoriteFragment fragment = new FavoriteFragment();
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			this.FavouriteAdapter = new CustomArrayAdapter(getActivity(),
					favoritesArrayList);

			View rootView = inflater.inflate(R.layout.fragment_favorite,
					container, false);

			// ==
			ListView lv = (ListView) rootView.findViewById(R.id.favourite_list);
			lv.setAdapter(FavouriteAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(
									R.id.container,
									QuestionDetailFragment
											.newInstance(favoritesArrayList
													.getQuestions().get(
															position)))
							.commit();
				}

			});
			// ==
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(3);
		}
	}

	// ========================================================================

	public static class LaterFragment extends Fragment {

		public CustomArrayAdapter LaterAdapter = null;

		public static LaterFragment newInstance() {
			LaterFragment fragment = new LaterFragment();
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_later,
					container, false);

			this.LaterAdapter = new CustomArrayAdapter(getActivity(),
					laterArrayList);

			ListView lv = (ListView) rootView.findViewById(R.id.later_list);
			lv.setAdapter(LaterAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(
									R.id.container,
									QuestionDetailFragment
											.newInstance(laterArrayList
													.getQuestions().get(
															position)))
							.commit();
				}

			});
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(4);
		}
	}

	// ========================================================================

	public static class HistoryFragment extends Fragment {

		public CustomArrayAdapter historyViewAdapter = null;

		public static HistoryFragment newInstance() {
			HistoryFragment fragment = new HistoryFragment();
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			this.historyViewAdapter = new CustomArrayAdapter(getActivity(),
					historyArrayList);

			View rootView = inflater.inflate(R.layout.fragment_history,
					container, false);

			ListView lv = (ListView) rootView.findViewById(R.id.history_list);
			lv.setAdapter(historyViewAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(
									R.id.container,
									QuestionDetailFragment
											.newInstance(historyArrayList
													.getQuestions().get(
															position)))
							.addToBackStack("History").commit();
				}

			});
			return rootView;
		}
		
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(5);
		}
	}

	// ========================================================================

	public static class ProfileFragment extends Fragment {

		private static ProfileFragment newInstance() {
			ProfileFragment fragment = new ProfileFragment();
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_profile,
					container, false);

			if (UserController.getCurrentUser() == null) {
				Intent intent = new Intent(getActivity(),
						UserLoginActivity.class);
				startActivityForResult(intent, REQUEST_CODE_USERNAME);
			} else {
				TextView textView = (TextView) rootView
						.findViewById(R.id.username);
				textView.setText(UserController.getCurrentUser().getName());
			}

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(6);
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (requestCode == REQUEST_CODE_USERNAME) {
				if (UserController.getCurrentUser() == null) {
					mNavigationDrawerFragment.selectItem(0);
				} else {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager
							.beginTransaction()
							.replace(R.id.container,
									ProfileFragment.newInstance()).commit();
				}
			}

		}
	}

	// ========================================================================

	public static class QuestionDetailFragment extends Fragment {
		protected static final int CREATE_ANSWER_ACTIVITY_CODE = 1234;
		private static final int CREATE_REPLY_ACTIVITY_CODE = 1235;
		DetailViewAdapter detailViewAdapter = null;
		Question question = null;

		public static QuestionDetailFragment newInstance(Question question) {
			QuestionDetailFragment fragment = new QuestionDetailFragment();
			Bundle args = new Bundle();
			args.putSerializable("question", question);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.detail_view, container,
					false);
			View detailView = inflater.inflate(
					R.layout.detail_answer_replies_button, container, false);
			question = (Question) getArguments().getSerializable("question");
			ExpandableListView questionExpandable = (ExpandableListView) rootView
					.findViewById(R.id.view_question_detail);

			this.detailViewAdapter = new DetailViewAdapter(getActivity(),
					question);
			detailViewAdapter.notifyDataSetChanged();
			Button addAnswerButtton = (Button) rootView
					.findViewById(R.id.add_answer_button);

			addAnswerButtton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (UserController.getCurrentUser() == null) {
						Intent intent = new Intent(getActivity(),
								UserLoginActivity.class);
						startActivity(intent);
					} else {
						Intent intent = new Intent(getActivity(),
								CreateAnswerActivity.class);
						startActivityForResult(intent,
								CREATE_ANSWER_ACTIVITY_CODE);
					}
				}
			});

			questionExpandable.setAdapter(detailViewAdapter);

			return rootView;
		}

		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (requestCode == CREATE_ANSWER_ACTIVITY_CODE) {
				if (resultCode == RESULT_OK) {
					String answerBodyText = data.getStringExtra("answerBody");
					Answer answer = new Answer(answerBodyText,
							UserController.getCurrentUser());
					question.addAnswer(answer);
					QuestionsController.updateQuestion(question);

				}
			}
		}

		// Updates the displays with any new replies/answers
		public void onResume() {
			super.onResume();
			detailViewAdapter.notifyDataSetChanged();
			ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();

			// no connection
			if (ni == null)
			{
				;
			} else {
				QuestionsController.updateQuestion(question);
			}
		}
	}
}
