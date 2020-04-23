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

import java.util.ArrayList;

public class LearnFragment extends Fragment {

    // Constants
    private boolean BLOCK_NONFREE_ROUTINES = false;

    // Fields
    private ListView lstRoutines;


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

        /*// lstVideos
        lstVideos = view.findViewById(R.id.lstVideos);
        final Context context = getActivity();
        ArrayList<String> videos = new  ArrayList<String>();
        lstVideos.setAdapter(new RoutineAdapter(context, R.layout.routine_row, videos));
        lstVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Routine routine = (Routine) lstRoutines.getItemAtPosition(position);
            }
        });*/
    }
}
