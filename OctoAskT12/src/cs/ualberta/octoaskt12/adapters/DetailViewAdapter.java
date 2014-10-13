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

public class DetailViewAdapter extends BaseExpandableListAdapter {

	private Context context;
	private Question question;

	public DetailViewAdapter(Context context, Question question) {
		super();
		this.context = context;
		this.question = question;
	}

	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.question.getReplies().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return question;
	}

	@Override
	public Reply getChild(int groupPosition, int childPosition) {
		return this.question.getReplies().get(childPosition);
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
		String questionTitle = question.getTitle();
		String questionBody = question.getBody();
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.detail_question_header,
					null);
		}
		TextView questionBodyTextView = (TextView) convertView
				.findViewById(R.id.detail_question_body);
		TextView questionTitleTextView = (TextView) convertView
				.findViewById(R.id.detail_question_header);
		questionBodyTextView.setText(questionBody);
		questionTitleTextView.setText(questionTitle);
		return convertView;

	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String replyBody = getChild(groupPosition, childPosition)
				.getBody();

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.detail_question_replies,
					null);
		}

		TextView questionReply = (TextView) convertView
				.findViewById(R.id.detail_question_replies);
		questionReply.setText(replyBody);

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
