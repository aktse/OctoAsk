package cs.ualberta.octoaskt12;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

// Dialog fragment used to prompt user into making a selection for how to sort the questions list.
// Defaults to sorting by date.
// Calls SortManager when user confirms selection.

public class SortFragment extends DialogFragment {

	public String[] sortBy = { "Date", "Upvotes", "Contains Image" };
	public String selected;
	int sortIndex;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		View inflater = getActivity().getLayoutInflater().inflate(
				R.layout.sort_dialog, null);

		sortIndex = getArguments().getInt("index");
		setCancelable(false);

		builder.setTitle("Sort Items by: ").setSingleChoiceItems(sortBy,
				sortIndex, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						selected = sortBy[which];
						sortIndex = which;
					}
				});
		builder.setView(inflater)
				.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								((MainActivity) getActivity()).doPositiveClick(
										selected, sortIndex);
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

	public static SortFragment newInstance(int sortIndex) {
		SortFragment sortFragment = new SortFragment();

		Bundle args = new Bundle();
		args.putInt("index", sortIndex);
		sortFragment.setArguments(args);

		return sortFragment;
	}
}
