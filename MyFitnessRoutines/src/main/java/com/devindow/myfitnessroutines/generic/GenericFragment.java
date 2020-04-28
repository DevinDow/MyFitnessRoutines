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
 * GenericFragment fills ListView of Generics based on FLAVOR & Tab Section Number
 * - handles lstGeneric OnItemClick
 */
public class GenericFragment extends Fragment {

    // Constants
    private boolean BLOCK_NONFREE_ROUTINES = false;
    private static final String ARG_SECTION_NUMBER = "section_number";


    // Private Fields
    private int sectionNum = 0;


    // newInstance() pattern for passing Tabbed Section Index
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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generic, container, false);
    }

    // build list of Generics for the Flavor & Tab Section Number
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // lstGeneric
        final ListView lstGeneric = view.findViewById(R.id.lstGeneric);
        final Context context = getActivity();
        ArrayList<Generic> generics = new ArrayList<Generic>();
        if (BuildConfig.DEBUG) {
            generics.add(RoutineLibrary.routines.get(RoutineLibrary.TEST));
        }
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                switch (sectionNum) {
                    case 0: // Learn
                        generics.add(new Video("Tiny Video", "a quick, small video", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/small.mp4"));
                        generics.add(new Video("Family Workout", "a small, choppy video", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/Workout 1.mp4"));
                        generics.add(new Video("High Res", "a high-res video that takes longer to buffer", "https://mytaichiroutines.s3-us-west-2.amazonaws.com/flute.mp4"));
                        break;
                    case 1: // Practice
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.TWENTY_FOUR_FORMS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SHIBASHI_1));
                        break;
                }
                break;
            case "full":
            case "free":
                switch (sectionNum) {
                    case 0: // Warm-up
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.MORNING_YOGA));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.WARMUP_THERMOREGULATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.PRE_ACTIVATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SUN_SALUTATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.YOGA_STRETCH));
                        break;
                    case 1: // Fitness
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SEVEN_MINUTE_WORKOUT));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.MIXED_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.LOWER_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.OBLIQUE_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.UPPER_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.PLANKS));
                        break;
                    case 2: // Agility
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.LADDER_DRILLS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SOCCER_TOUCHES));
                        break;
                    case 3: // Meditation
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.FIVE_MIN_MEDITATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.TEN_MIN_MEDITATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.FIFTEEN_MIN_MEDITATION));
                        break;
                }
                break;
            case "soccer":
                switch (sectionNum) {
                    case 0: // Warm-up
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.MORNING_YOGA));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.WARMUP_THERMOREGULATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.PRE_ACTIVATION));
                        break;
                    case 1: // Agility
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.LADDER_DRILLS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SOCCER_TOUCHES));
                        break;
                    case 2: // Fitness
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SEVEN_MINUTE_WORKOUT));
                        break;
                }
                break;
            case "yoga":
                switch (sectionNum) {
                    case 0: // Yoga
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.MORNING_YOGA));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SUN_SALUTATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.YOGA_STRETCH));
                        break;
                    case 1: // Meditation
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.FIVE_MIN_MEDITATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.TEN_MIN_MEDITATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.FIFTEEN_MIN_MEDITATION));
                        break;
                }
                break;
            case "abs":
                switch (sectionNum) {
                    case 0: // Warm-up
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.MORNING_YOGA));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.WARMUP_THERMOREGULATION));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.SEVEN_MINUTE_WORKOUT));
                        break;
                    case 1: // Core
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.MIXED_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.LOWER_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.OBLIQUE_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.UPPER_ABS));
                        generics.add(RoutineLibrary.routines.get(RoutineLibrary.PLANKS));
                        break;
                }
                break;
        }
        lstGeneric.setAdapter(new GenericAdapter(context, R.layout.generic_row, generics));

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
