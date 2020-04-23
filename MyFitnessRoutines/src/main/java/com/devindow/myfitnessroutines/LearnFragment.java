package com.devindow.myfitnessroutines;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.devindow.myfitnessroutines.routine.Routine;
import com.devindow.myfitnessroutines.routine.RoutineLibrary;
import com.devindow.myfitnessroutines.video.Video;
import com.devindow.myfitnessroutines.video.VideoAdapter;

import java.util.ArrayList;

public class LearnFragment extends Fragment {

    // Constants
    private boolean BLOCK_NONFREE_ROUTINES = false;

    // Fields
    private ListView lstVideos;


    // Overrides
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // lstVideos
        lstVideos = view.findViewById(R.id.lstVideos);
        final Context context = getActivity();
        ArrayList<Video> videos = new  ArrayList<Video>();
        videos.add(new Video("small", "a small video", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/small.mp4"));
        videos.add(new Video("Family Workout", "a small, choppy video", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/Workout 1.mp4"));
        videos.add(new Video("High Res", "a high-res video that takes longer to buffer", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/flute.mp4"));
        lstVideos.setAdapter(new VideoAdapter(context, R.layout.routine_row, videos));
        lstVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Video video = (Video) lstVideos.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), VideoStreamActivity.class);
                intent.putExtra("Video", video);
                startActivity(intent);
            }
        });
    }
}
