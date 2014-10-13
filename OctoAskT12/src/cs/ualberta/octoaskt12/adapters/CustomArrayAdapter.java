package cs.ualberta.octoaskt12.adapters;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import cs.ualberta.octoaskt12.Question;
import cs.ualberta.octoaskt12.QuestionArrayList;
import cs.ualberta.octoaskt12.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Question>{

	private ArrayList<Question> questions = null;
	
	public CustomArrayAdapter(Context context, QuestionArrayList questions){
		super(context, R.layout.fragment_question_list_item, questions.getQuestions());
		
		this.questions = questions.getQuestions();
	}
	
	//Used to get the item located at the position of the onClick event
	@Override
	public Question getItem(int position) {
		return questions.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.fragment_question_list_item,null);
		}
		
		TextView question = (TextView) view.findViewById(R.id.list_question_title);
		question.setText(questions.get(position).getTitle());
		TextView answers = (TextView) view.findViewById(R.id.list_question_answer);
		answers.setText(questions.get(position).getAnswers().size() + " answers");
		
		TextView time = (TextView) view.findViewById(R.id.list_question_time);
		GregorianCalendar gc = questions.get(position).getTime();
		GregorianCalendar now = new GregorianCalendar();
		long difference = now.getTime().getTime() - gc.getTime().getTime();
		long timeInSeconds = difference/1000;
		long timeInMinutes = timeInSeconds/60;
		long timeInHours = timeInMinutes/60;
		
		if (timeInSeconds < 60) {
			time.setText(timeInSeconds + " seconds ago");
		} else if (timeInMinutes < 60) {
			time.setText(timeInMinutes + " minutes ago");
		} else if (timeInHours < 24) {
			time.setText(timeInHours + " hours ago");
		} else {
			time.setText(DateFormat.getDateInstance().format(gc.getTime()));
		}
		System.out.println(difference);
		System.out.println(timeInSeconds);
		System.out.println(timeInHours);

		TextView upvotes = (TextView) view.findViewById(R.id.list_question_upvote_caption);
		upvotes.setText(questions.get(position).getVotes() + " upvotes");
		
		return view;
	}

}
