package cs.ualberta.octoaskt12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.http.client.ClientProtocolException;

import cs.ualberta.octoaskt12.ES.ES;
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
	// esc.getQuestions();
	// public static QuestionArrayList questionArrayList =
	// QuestionsController.getAllQuestions();
	//
	// public static QuestionArrayList sortedQuestionArrayList =
	// questionArrayList;
	public static QuestionArrayList questionArrayList = new QuestionArrayList();
	public static QuestionArrayList sortedQuestionArrayList;

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

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

		// ThreadPoolExecutor tpe = new ThreadPoolExecutor();

		// ESRequests req = new ESRequests();
		// try {
		// req.getQuestions();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// questionArrayList = QuestionsController.getAllQuestions();
		// try {
		// questionArrayList = esc.getQuestions();
		// } catch (ClientProtocolException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
//		try {
//			questionArrayList = QuestionsController.getAllQuestions();
//		} catch (ClientProtocolException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}


//		ESClient esclient = new ESClient();
//		try {
//			esclient.getQuestions();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		updateQuestions();

//		sortedQuestionArrayList = questionArrayList;

		
		// ES.sendRequest();

		String userName = "Ivan";
		User currentUser = new User(userName);
		UserArrayList.addUser(currentUser);
		UserArrayList.setCurrentUser(currentUser);

		context = this;

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		MyQuestionFilename = "ChrisFile";

		// ElasticSearchAddQuestion.AddToDatabase();

	}
	
	public static void updateQuestions() {
		try {
			questionArrayList = QuestionsController.getAllQuestions();
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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

	public void createDialog(MenuItem menu) {
		SortFragment sortFragment = SortFragment.newInstance(sortIndex);
		sortFragment.show(getSupportFragmentManager(), "Sort");
	}

	public void doPositiveClick(String string, int sortIndex) {

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
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, QuestionFragment.newInstance())
				.commit();
	}

	public void doNegativeClick() {

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
			questionsViewAdapter.notifyDataSetChanged();
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
											.newInstance(questionArrayList
													.getQuestions().get(
															position)))
							.commit();
				}

			});

			final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
			swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					swipeLayout.setRefreshing(true);
					updateQuestions();
					swipeLayout.setRefreshing(false);
					onResume();
					questionsViewAdapter.notifyDataSetChanged();
				}
			});
			swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
					android.R.color.holo_green_light,
					android.R.color.holo_orange_light,
					android.R.color.holo_red_light);
			

		    lv.setOnScrollListener(new AbsListView.OnScrollListener() {
		        @Override
		        public void onScrollStateChanged(AbsListView absListView, int i) {
		 
		        }
		 
		        @Override
		        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		                if (firstVisibleItem == 0)
		                    swipeLayout.setEnabled(true);
		                else
		                    swipeLayout.setEnabled(false);
		        }	
		    });
			
			return rootView;
		}

//		@Override
//		public void onRefresh() {
//			updateQuestions();
//		}
		
		
		@Override
		public void onResume() {
			super.onResume();
			System.out.println("needy");
			SortManager sortManager = new SortManager();
			if (sortIndex == 0) {
				questionArrayList = sortManager.SortByDate(questionArrayList);
			} else if (sortIndex == 1) {
				questionArrayList = sortManager.SortByVotes(questionArrayList);
			} else {
				questionArrayList = sortManager.SortByImages(questionArrayList);
			}
			questionsViewAdapter.notifyDataSetChanged();
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(1);
		}
	}

	public void createQuestion(MenuItem menu) {
		Intent intent = new Intent(MainActivity.this,
				CreateQuestionActivity.class);
		startActivity(intent);
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

	public static void LoadMyQuestions(Context context,
			QuestionArrayList questions) throws ClassNotFoundException {
		OfflineDataManager.LoadMyQuestions(context, questions);
	}

	public static class MyQuestionsFragment extends Fragment {

		public CustomArrayAdapter MyQuestionAdapter = null;

		public static MyQuestionsFragment newInstance() {
			MyQuestionsFragment fragment = new MyQuestionsFragment();
			return fragment;
		}

		public MyQuestionsFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			this.MyQuestionAdapter = new CustomArrayAdapter(getActivity(),
					questionArrayList);

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
											.newInstance(questionArrayList
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

	// ===
	public static class FavoriteFragment extends Fragment {

		public CustomArrayAdapter FavouriteAdapter = null;

		public static FavoriteFragment newInstance() {
			FavoriteFragment fragment = new FavoriteFragment();
			return fragment;
		}

		public FavoriteFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			this.FavouriteAdapter = new CustomArrayAdapter(getActivity(),
					questionArrayList);

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
											.newInstance(questionArrayList
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

	// ====
	public static class LaterFragment extends Fragment {

		public CustomArrayAdapter LaterAdapter = null;

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
											.newInstance(questionArrayList
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

	// ======

	public static class HistoryFragment extends Fragment {

		public CustomArrayAdapter HistoryViewAdapter = null;

		public static HistoryFragment newInstance() {
			HistoryFragment fragment = new HistoryFragment();
			return fragment;
		}

		public HistoryFragment() {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			this.HistoryViewAdapter = new CustomArrayAdapter(getActivity(),
					questionArrayList);
			View rootView = inflater.inflate(R.layout.fragment_history,
					container, false);
			ListView lv = (ListView) rootView.findViewById(R.id.history_list);
			lv.setAdapter(HistoryViewAdapter);
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
											.newInstance(questionArrayList
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
			((MainActivity) activity).onSectionAttached(6);
		}
	}

	// =========

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
					Intent intent = new Intent(getActivity(),
							CreateAnswerActivity.class);
					startActivityForResult(intent, CREATE_ANSWER_ACTIVITY_CODE);
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
							UserArrayList.getCurrentUser());
					question.addAnswer(answer);
				}
			}

		}

		public void onResume() {
			super.onResume();
			detailViewAdapter.notifyDataSetChanged();
		}
	}
}
