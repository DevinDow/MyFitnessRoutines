package com.devindow.myfitnessroutines;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
import com.devindow.myfitnessroutines.util.MethodLogger;

public class PracticeFragment extends Fragment {

    // Constants
    private boolean BLOCK_NONFREE_ROUTINES = false;

    // Fields
    private ListView lstRoutines;


    // Overrides
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // When debugging, regenerate Moves & Routines every time MainActivity is created.
        if (BuildConfig.DEBUG) {
            RoutineLibrary.generate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // lstRoutines
        lstRoutines = view.findViewById(R.id.lstRoutines);
        final Context context = getActivity();
        lstRoutines.setAdapter(new RoutineAdapter(context, R.layout.routine_row, RoutineLibrary.routines));
        lstRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Routine routine = (Routine) lstRoutines.getItemAtPosition(position);

                // Link to PAID apps
                if (BLOCK_NONFREE_ROUTINES && BuildConfig.FLAVOR.equals("free") && !routine.isFree) {
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

                // Start PlayRoutineActivity
                else {
                    Intent intent = new Intent(view.getContext(), PlayRoutineActivity.class);
                    intent.putExtra("routine", routine);
                    startActivity(intent);
                }
            }
        });
    }
}
