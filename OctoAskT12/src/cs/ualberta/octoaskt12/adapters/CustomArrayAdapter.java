package cs.ualberta.octoaskt12.adapters;

import java.util.ArrayList;

import cs.ualberta.octoaskt12.R;
import cs.ualberta.octoaskt12.UserText;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<UserText>{

	private ArrayList<UserText> userText = null;
	
	public CustomArrayAdapter(Context context, ArrayList<UserText> userText){
		super(context, R.layout.fragment_question_list_item, userText);
		
		this.userText = userText;
	}
	
	//Used to get the item located at the position of the onClick event
	@Override
	public UserText getItem(int position) {
		return userText.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.fragment_question_list_item,null);
		}
		
		TextView item = (TextView) view.findViewById(R.id.list_question_title);
		item.setText(userText.get(position).getUserText());
		
		return view;
	}

}
