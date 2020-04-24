package com.devindow.myfitnessroutines.generic;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.devindow.myfitnessroutines.BuildConfig;
import com.devindow.myfitnessroutines.R;
import com.devindow.myfitnessroutines.generic.Generic;
import com.devindow.myfitnessroutines.routine.Routine;

import java.util.ArrayList;

/**
 * [ArrayAdapter] for Generics for lists in TabbedActivity
 */
public class GenericAdapter extends ArrayAdapter<Generic> {

	// Private Fields
	private Context context;
	private int resource;


	// Constructor
	public GenericAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Generic> generics) {
		super(context, resource, generics);
		this.context = context;
		this.resource = resource;
	}


	// ArrayAdapter implementation
	@NonNull @Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

		Generic generic = getItem(position);

		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(resource, parent, false);

		// GRAY background for paid Routines in free app flavor
		if (BuildConfig.FLAVOR.equals("free") && !generic.isFree) {
			convertView.setBackgroundColor(Color.LTGRAY);
		}

		// GREEN background for Routines completed today
		if (generic.ranRecently) {
			convertView.setBackgroundColor(Color.GREEN);
		}

		TextView txtName = convertView.findViewById(R.id.txtName);
		txtName.setText(generic.name);

		if (generic instanceof Routine) {
			Routine routine = (Routine)generic;
			TextView txtDuration = convertView.findViewById(R.id.txtDuration);
			txtDuration.setText(routine.getTotalMinutesString());
		}

		TextView txtDescription = convertView.findViewById(R.id.txtDescription);
		if (generic.description.length() == 0) {
			txtDescription.setVisibility(View.GONE);
		} else {
			txtDescription.setText(generic.description);
		}

		return convertView;
	}

}
