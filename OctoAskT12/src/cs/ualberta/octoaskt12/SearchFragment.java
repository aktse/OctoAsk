package cs.ualberta.octoaskt12;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

// Dialog fragment displayed to the user to prompt for an input string.
// Calls SearchController after user confirms search string

public class SearchFragment extends DialogFragment {

	String searchString = null;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final View inflater = getActivity().getLayoutInflater().inflate(
				R.layout.search_dialog, null);

		setCancelable(false);

		builder.setTitle("Search for: ")
				.setView(inflater)
				.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								EditText mEdit = (EditText) inflater.findViewById(R.id.search_dialog);
								searchString = mEdit.getText().toString();
								System.out.println(searchString);
								((MainActivity) getActivity()).searchQuestions(
										searchString);
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								((MainActivity) getActivity())
										.doNegativeClick();
							}

						});

		return builder.create();
	}

	public static SearchFragment newInstance() {
		SearchFragment searchFragment = new SearchFragment();

		return searchFragment;
	}

}
