package com.devindow.myfitnessroutines.generic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.devindow.myfitnessroutines.BuildConfig;
import com.devindow.myfitnessroutines.R;
import com.devindow.myfitnessroutines.routine.PlayRoutineActivity;
import com.devindow.myfitnessroutines.routine.Routine;
import com.devindow.myfitnessroutines.routine.RoutineLibrary;
import com.devindow.myfitnessroutines.video.Video;
import com.devindow.myfitnessroutines.video.VideoStreamActivity;

import java.util.ArrayList;

/**
 * GenericFragment holds ListView of Generics
 */
public class GenericFragment extends Fragment {

    // Constants
    private boolean BLOCK_NONFREE_ROUTINES = false;
    private static final String ARG_SECTION_NUMBER = "section_number";


    // Private Fields
    private int sectionNum = 0;


    // newInstance() pattern
    public static GenericFragment newInstance(int index) {
        GenericFragment fragment = new GenericFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }


    // Overrides
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            sectionNum = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        // When debugging, regenerate Moves & Routines every time MainActivity is created.
        if (BuildConfig.DEBUG) {
            RoutineLibrary.generate();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // lstGeneric
        final ListView lstGeneric = view.findViewById(R.id.lstGeneric);
        final Context context = getActivity();
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                switch (sectionNum) {
                    case 0:
                    default:
                        ArrayList<Generic> videos = new ArrayList<Generic>();
                        videos.add(new Video("Tiny Video", "a quick, small video", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/small.mp4"));
                        videos.add(new Video("Family Workout", "a small, choppy video", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/Workout 1.mp4"));
                        videos.add(new Video("High Res", "a high-res video that takes longer to buffer", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/flute.mp4"));
                        lstGeneric.setAdapter(new GenericAdapter(context, R.layout.generic_row, videos));
                        break;
                    case 1:
                        lstGeneric.setAdapter(new GenericAdapter(context, R.layout.generic_row, RoutineLibrary.routines));
                }
                break;
            default:
                lstGeneric.setAdapter(new GenericAdapter(context, R.layout.generic_row, RoutineLibrary.routines));
                break;
        }

        // lstGeneric OnItemClick
        lstGeneric.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Generic generic = (Generic) lstGeneric.getItemAtPosition(position);

                // Link to PAID apps
                if (BLOCK_NONFREE_ROUTINES && BuildConfig.FLAVOR.equals("free") && !generic.isFree) {
                    final String link = "https://play.google.com/store/apps/developer?id=Devin+Dow";
                    final SpannableString message = new SpannableString(
                            "This app is a free sample with only a few routines enabled.\n\n" +
                                    "To access the other routines please purchase a paid version in the app store :\n" +
                                    link);
                    Linkify.addLinks(message, Linkify.ALL);

                    final AlertDialog dialog = new AlertDialog.Builder(context)
                            .setTitle("Paid App Required")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage(message)
                            .setPositiveButton(android.R.string.ok, null)
                            .create();
                    dialog.show();

                    // Make the TextView clickable. (must be called after show())
                    ((TextView) dialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                }

                // Start Activity
                else {
                    if (generic instanceof Routine) {
                        Intent intent = new Intent(view.getContext(), PlayRoutineActivity.class);
                        intent.putExtra("routine", generic);
                        startActivity(intent);
                    } else if (generic instanceof Video) {
                        Video video = (Video) lstGeneric.getItemAtPosition(position);
                        Intent intent = new Intent(view.getContext(), VideoStreamActivity.class);
                        intent.putExtra("video", generic);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
