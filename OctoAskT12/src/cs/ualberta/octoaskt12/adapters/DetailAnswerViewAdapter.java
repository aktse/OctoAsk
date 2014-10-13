package cs.ualberta.octoaskt12.adapters;

import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.R;
import cs.ualberta.octoaskt12.Reply;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class DetailAnswerViewAdapter extends BaseExpandableListAdapter {

	private Context context;
	private Question question;

	public DetailAnswerViewAdapter(Context context, Question question) {
		super();
		this.context = context;
		this.question = question;
	}

	@Override
	public int getGroupCount() {
		return question.getAnswers().size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return question.getAnswers().get(groupPosition).getReplies().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return question.getAnswers();
	}

	@Override
	public Reply getChild(int groupPosition, int childPosition) {
		return question.getAnswers().get(groupPosition).getReplies()
				.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String answerBody = question.getAnswers().get(groupPosition).getBody();
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.detail_answer_header, null);
		}
		TextView answerBodyTextView = (TextView) convertView
				.findViewById(R.id.detail_answer_header);
		answerBodyTextView.setText(answerBody);

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String replyBody = getChild(groupPosition, childPosition).getBody();
		if (convertView == null) {
			if (isLastChild) {
				LayoutInflater inflater = (LayoutInflater) this.context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(
						R.layout.detail_answer_replies_button, null);
			} else {
				LayoutInflater inflater = (LayoutInflater) this.context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.detail_answer_replies,
						null);
			}
		}

		TextView answerReply = (TextView) convertView
				.findViewById(R.id.detail_answer_replies);
		answerReply.setText(replyBody);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
