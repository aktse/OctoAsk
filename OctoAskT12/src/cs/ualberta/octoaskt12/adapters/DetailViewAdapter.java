package cs.ualberta.octoaskt12.adapters;

import java.util.ArrayList;
import java.util.List;

import cs.ualberta.octoaskt12.Answer;
import cs.ualberta.octoaskt12.AnswerHolder;
import cs.ualberta.octoaskt12.CreateAnswerActivity;
import cs.ualberta.octoaskt12.CreateReplyActivity;
import cs.ualberta.octoaskt12.MainActivity;
import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionHolder;
import cs.ualberta.octoaskt12.QuestionsController;
import cs.ualberta.octoaskt12.R;
import cs.ualberta.octoaskt12.Reply;
import cs.ualberta.octoaskt12.UserController;
import cs.ualberta.octoaskt12.UserLoginActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailViewAdapter extends BaseExpandableListAdapter {

	private static final int CREATE_REPLY_ACTIVITY_CODE = 1236;
	private Context context;
	private Question question;
	private ArrayList<Answer> answers;
	private ArrayList<Reply> replyList;

	public DetailViewAdapter(Context context, Question question) {
		super();
		this.context = context;
		this.question = question;
		this.answers = question.getAnswers();
	}

	// return a child item
	@Override
	public Reply getChild(int groupPosition, int childPosition) {
		if (groupPosition == 0) {
			return this.question.getReplies().get(childPosition);
		} else {
			return this.answers.get(groupPosition - 1).getReplies()
					.get(childPosition);
		}
	}

	// return item's id
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// return view for each item row
	@Override
	public View getChildView(final int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// get replies for the question
		if (groupPosition == 0) {
			// if the question does not have any replies, notify no replies to
			// user/
			if (this.question.getReplies().size() == 0) {
				convertView = inflater.inflate(
						R.layout.detail_question_replies_button, null);
				TextView questionReply = (TextView) convertView
						.findViewById(R.id.detail_question_replies);
				questionReply
						.setText("This Question has no replies. Be the first to reply.");
				Button replyButton = (Button) convertView
						.findViewById(R.id.detail_question_replies_add);
				replyButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context,
								CreateReplyActivity.class);
						intent.putExtra("replyFor", "1");
						QuestionHolder questionHolder = QuestionHolder
								.getInstance();
						questionHolder.setQuestion(question);
						((Activity) context).startActivity(intent);
					}
				});
			}
			// the question already contains replies, then display them.
			else {
				String replyBody = getChild(groupPosition, childPosition)
						.getBody();
				// if it is the last child, then display the add reply button
				if (isLastChild) {
					convertView = inflater.inflate(
							R.layout.detail_question_replies_button, null);
					Button replyButton = (Button) convertView
							.findViewById(R.id.detail_question_replies_add);
					replyButton.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(context,
									CreateReplyActivity.class);
							intent.putExtra("replyFor", "1");
							QuestionHolder questionHolder = QuestionHolder
									.getInstance();
							questionHolder.setQuestion(question);
							((Activity) context).startActivity(intent);
						}
					});
				}
				// question already contains replies, so display them
				else {
					convertView = inflater.inflate(
							R.layout.detail_question_replies, null);
				}
				TextView questionReply = (TextView) convertView
						.findViewById(R.id.detail_question_replies);
				questionReply.setText(replyBody);
			}
			return convertView;
		}
		// get replies for the answers
		else {
			// if the answer does not have any replies, notify no replies to
			// user.
			if (this.answers.get(groupPosition - 1).getReplies().size() == 0) {

				convertView = inflater.inflate(
						R.layout.detail_answer_replies_button, null);

				TextView answerReply = (TextView) convertView
						.findViewById(R.id.detail_answer_replies);
				answerReply
						.setText("This answer has no replies. Be the first to reply.");
				Button replyButton = (Button) convertView
						.findViewById(R.id.detail_answer_replies_add);

				replyButton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(context,
								CreateReplyActivity.class);
						intent.putExtra("replyFor", "0");
						// intent.putExtra("answerPos", )
						AnswerHolder answerHolder = AnswerHolder.getInstance();
						answerHolder.setAnswer(answers.get(groupPosition - 1));
						((Activity) context).startActivity(intent);
					}
				});

			}
			// if the answer does contain replies, then simply display them
			else {
				String replyBody = getChild(groupPosition, childPosition)
						.getBody();
				// if it is the last child, then display the add reply button
				if (isLastChild) {
					convertView = inflater.inflate(
							R.layout.detail_answer_replies_button, null);
					Button replyButton = (Button) convertView
							.findViewById(R.id.detail_answer_replies_add);

					replyButton.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(context,
									CreateReplyActivity.class);
							intent.putExtra("replyFor", "0");
							AnswerHolder answerHolder = AnswerHolder
									.getInstance();
							answerHolder.setAnswer(answers
									.get(groupPosition - 1));
							((Activity) context).startActivity(intent);
						}
					});
				}
				// if it is not the last child, then just display a single reply
				// row.
				else {
					convertView = inflater.inflate(
							R.layout.detail_answer_replies, null);
				}
				TextView answerReply = (TextView) convertView
						.findViewById(R.id.detail_answer_replies);
				answerReply.setText(replyBody);
			}

			// return the child view
			return convertView;
		}

	}

	// return number of items in each section
	@Override
	public int getChildrenCount(int groupPosition) {
		int replySize;
		if (groupPosition == 0) {
			replySize = question.getReplies().size();
		} else {
			replySize = this.answers.get(groupPosition - 1).getReplies().size();
		}
		if (replySize == 0) {
			return 1;
		}
		;
		return replySize;
	}

	// return sections
	@Override
	public Object getGroup(int groupPosition) {
		if (groupPosition == 0) {
			return this.question;
		} else {
			return this.answers.get(groupPosition - 1);
		}
	}

	// return the number of sections
	@Override
	public int getGroupCount() {
		return this.answers.size() + 1;
	}

	// return a section's id
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// retrun a view for each section header
	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (groupPosition == 0) {
			String questionTitle = question.getTitle();
			System.out.println("Title: " + questionTitle);
			String questionBody = question.getBody();
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.detail_question_header,
					null);
			TextView questionBodyTextView = (TextView) convertView
					.findViewById(R.id.detail_question_body);
			TextView questionTitleTextView = (TextView) convertView
					.findViewById(R.id.detail_question_header);
			questionBodyTextView.setText(questionBody);
			questionTitleTextView.setText(questionTitle);
			final ImageView upvoteButton = (ImageView) convertView
					.findViewById(R.id.upvote_question_button);
			
			if(question.getUpvotedUsers().contains(
					UserController.getCurrentUser())) {
				upvoteButton.setImageResource(R.drawable.upvoted);
			} else {
				upvoteButton.setImageResource(R.drawable.upvote);
			}
			
			upvoteButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (question.getUpvotedUsers().contains(
							UserController.getCurrentUser())) {
						question.decrementVotes();
						question.removeUpvotedUser(UserController
								.getCurrentUser());
						final MediaPlayer mp = MediaPlayer.create(context, R.raw.downvotesound);
				        mp.start();
						notifyDataSetChanged();
						QuestionsController.updateQuestion(question);
						upvoteButton.setImageResource(R.drawable.upvote);
					} else {
						if (UserController.getCurrentUser() == null) {
							Intent intent = new Intent(context,
									UserLoginActivity.class);
							((Activity) context).startActivity(intent);
						} else {
							question.incrementVotes();
							question.addUpvotedUser(UserController
									.getCurrentUser());
							final MediaPlayer mp = MediaPlayer.create(context, R.raw.upvotesound);
					        mp.start();
							notifyDataSetChanged();
							QuestionsController.updateQuestion(question);
							upvoteButton.setImageResource(R.drawable.upvoted);
						}
					}
				}
			});
			
			final ImageView favButton = (ImageView) convertView.findViewById(R.id.addfavoritebutton);
			
			boolean duplicateFav = false;
			for (Question favQuestion : MainActivity.favoritesArrayList.getQuestions()) {
				System.out.println("Look here FAV "+favQuestion.getId());
				System.out.println("Look here current "+question.getId());
				if (favQuestion.getId().equals(question.getId())) {
					duplicateFav = true;
					break;
				}
			}
			
			// new jack/chris
			if(duplicateFav == true) {
				favButton.setImageResource(R.drawable.favorited);
			} else {
				favButton.setImageResource(R.drawable.favorite);
			}
			
			/*
			if(MainActivity.favoritesArrayList.has(question)) {
				favButton.setImageResource(R.drawable.favorited);
			} else {
				favButton.setImageResource(R.drawable.favorite);
			}
			*/
			
			favButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					boolean duplicateFav2 = false;
					
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
					
					for (Question favQuestion : MainActivity.favoritesArrayList.getQuestions()) {
//						Log.i("Looped id", favQuestion.getId());
						if (favQuestion.getId().equals(question.getId())) {
							duplicateFav2 = true;
							break;
						}
					}
					if (duplicateFav2 == true) {
						int questionIndex = MainActivity.favoritesArrayList.searchQuestionIndexById(question.getId());
						MainActivity.favoritesArrayList.removeQuestionByIndex(questionIndex);
						favButton.setImageResource(R.drawable.favorite);
						final MediaPlayer mp = MediaPlayer.create(context, R.raw.unfavorite);
				        mp.start();
					} else {
						MainActivity.favoritesArrayList.addToFront(question);
						favButton.setImageResource(R.drawable.favorited);
						final MediaPlayer mp = MediaPlayer.create(context, R.raw.favorite);
				        mp.start();
					}
					
					/*
					if(MainActivity.favoritesArrayList.has(question)) {
						MainActivity.favoritesArrayList.remove(question);
						favButton.setImageResource(R.drawable.favorite);
					} else {
						MainActivity.favoritesArrayList.addToFront(question);
						favButton.setImageResource(R.drawable.favorited);
					}
					*/
				}
			});
			
			final ImageView rlButton = (ImageView) convertView.findViewById(R.id.addreadlaterbutton);
			
			boolean duplicateRL = false;
			for (Question rlQuestion : MainActivity.laterArrayList.getQuestions()) {
				if (rlQuestion.getId().equals(question.getId())) {
					duplicateRL = true;
					break;
				}
			}
			
			// new jack/chris
			if(duplicateRL == true) {
				rlButton.setImageResource(R.drawable.readlatered);
			} else {
				rlButton.setImageResource(R.drawable.readlater);
			}
			
			/*
			if(MainActivity.laterArrayList.has(question)) {
				rlButton.setImageResource(R.drawable.readlatered);
			} else {
				rlButton.setImageResource(R.drawable.readlater);
			}
			*/
			
			rlButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					boolean duplicateRL2 = false;
					
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
//					Log.i("Current question id", question.getId());
					
					for (Question rlQuestion : MainActivity.laterArrayList.getQuestions()) {
//						Log.i("Looped id", favQuestion.getId());
						if (rlQuestion.getId().equals(question.getId())) {
							duplicateRL2 = true;
							break;
						}
					}
					if (duplicateRL2 == true) {
						int questionIndex = MainActivity.laterArrayList.searchQuestionIndexById(question.getId());
						MainActivity.laterArrayList.removeQuestionByIndex(questionIndex);
						rlButton.setImageResource(R.drawable.readlater);
					} else {
						MainActivity.laterArrayList.addToFront(question);
						rlButton.setImageResource(R.drawable.readlatered);
						final MediaPlayer mp = MediaPlayer.create(context, R.raw.readlater);
				        mp.start();
					}
					
					/*
					if(MainActivity.laterArrayList.has(question)) {
						MainActivity.laterArrayList.remove(question);
						rlButton.setImageResource(R.drawable.readlater);
					} else {
						MainActivity.laterArrayList.addToFront(question);
						rlButton.setImageResource(R.drawable.readlatered);
					}
					*/
				}
			});
			
			TextView upvoteCaption = (TextView) convertView
					.findViewById(R.id.question_upvote_caption);
			upvoteCaption.setText(question.getVotes() + " upvotes");
			ImageView questionPicture = (ImageView) convertView
					.findViewById(R.id.question_ImageViewFinal);
			if (question.getQuestionImage() != null) {
				questionPicture.setImageBitmap(question.getQuestionImage());
			}
			return convertView;
		} else {
			String answerBody = answers.get(groupPosition - 1).getBody();
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.detail_answer_header, null);
			TextView answerBodyTextView = (TextView) convertView
					.findViewById(R.id.detail_answer_header);
			if (answerBodyTextView == null) {
				System.out.println("null");
			}
			answerBodyTextView.setText(answerBody);

			final ImageView image = (ImageView) convertView
					.findViewById(R.id.upvote_answer_button);
			
			if(answers.get(groupPosition - 1).getUpvotedUsers()
					.contains(UserController.getCurrentUser())) {
				image.setImageResource(R.drawable.upvoted);
			} else {
				image.setImageResource(R.drawable.upvote);
			}
			image.setOnClickListener(new View.OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					if (answers.get(groupPosition - 1).getUpvotedUsers()
							.contains(UserController.getCurrentUser())) {
						answers.get(groupPosition - 1).decrementVotes();
						answers.get(groupPosition - 1).removeUpvotedUser(
								UserController.getCurrentUser());
				        final MediaPlayer mp = MediaPlayer.create(context, R.raw.downvotesound);
				        mp.start();

						notifyDataSetChanged();
						QuestionsController.updateQuestion(question);
						image.setImageResource(R.drawable.upvote);
					} else {
						if (UserController.getCurrentUser() == null) {
							Intent intent = new Intent(context,
									UserLoginActivity.class);
							((Activity) context).startActivity(intent);
						} else {
							answers.get(groupPosition - 1).incrementVotes();
							answers.get(groupPosition - 1).addUpvotedUser(
									UserController.getCurrentUser());
							final MediaPlayer mp = MediaPlayer.create(context, R.raw.upvotesound);
					        mp.start();
							notifyDataSetChanged();
							QuestionsController.updateQuestion(question);
							image.setImageResource(R.drawable.upvoted);
						}
					}
				}
			});
			TextView upvoteCaption = (TextView) convertView
					.findViewById(R.id.answer_upvote_caption);
			upvoteCaption.setText(answers.get(groupPosition - 1).getVotes()
					+ " upvotes");

			return convertView;
		}
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
