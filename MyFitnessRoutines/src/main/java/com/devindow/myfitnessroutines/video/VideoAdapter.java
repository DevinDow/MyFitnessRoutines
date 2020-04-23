package com.devindow.myfitnessroutines.video;

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
import com.devindow.myfitnessroutines.video.Video;

import java.util.ArrayList;

/**
 * Created by Devin on 2/21/2018.
 */

public class VideoAdapter extends ArrayAdapter<Video> {

	// Private Fields
	private Context context;
	private int resource;


	// Constructor
	public VideoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Video> videos) {
		super(context, resource, videos);
		this.context = context;
		this.resource = resource;
	}


	// ArrayAdapter implementation
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

		Video video = getItem(position);

		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(resource, parent, false);

		TextView txtName = convertView.findViewById(R.id.txtName);
		txtName.setText(video.name);

		TextView txtDuration = convertView.findViewById(R.id.txtDuration);
		txtDuration.setVisibility(View.GONE);

		TextView txtDescription = convertView.findViewById(R.id.txtDescription);
		if (video.description.length() == 0) {
			txtDescription.setVisibility(View.GONE);
		} else {
			txtDescription.setText(video.description);
		}

		return convertView;
	}

}
