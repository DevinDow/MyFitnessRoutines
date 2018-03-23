package com.devindow.myfitnessroutines;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.devindow.myfitnessroutines.db.AppDatabase;
import com.devindow.myfitnessroutines.routine.*;
import com.devindow.myfitnessroutines.util.MessageDialog;
import com.devindow.myfitnessroutines.util.MethodLogger;

import java.util.List;

public class MainActivity extends OptionsMenuActivity {

	// Fields
	private ListView lstRoutines;


	// Overrides
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MethodLogger methodLogger = new MethodLogger();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);


		// When debugging, regenerate Moves & Routines every time MainActivity is created.
		if (BuildConfig.DEBUG) {
			RoutineLibrary.generate();
		}


		// lstRoutines
		lstRoutines = findViewById(R.id.lstRoutines);
		lstRoutines.setAdapter(new RoutineAdapter(this, R.layout.routine_row, RoutineLibrary.routines));
		final Context context = this;
		lstRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				Routine routine = (Routine) lstRoutines.getItemAtPosition(position);
				if (BuildConfig.FLAVOR.equals("free") && !routine.isFree) {
					MessageDialog.show(context, "Please purchase the paid version in the app store to access these Routines.");
				} else {
					Intent intent = new Intent(view.getContext(), PlayRoutineActivity.class);
					intent.putExtra("routine", routine);
					startActivity(intent);
				}
			}
		});


		// btnNewRoutine
		FloatingActionButton btnNewRoutine = (FloatingActionButton) findViewById(R.id.btnNewRoutine);
		switch (BuildConfig.FLAVOR) {
			case "soccer":
			default:
				btnNewRoutine.setVisibility(View.GONE);
				break;
			case "full":
			case "yoga":
				btnNewRoutine.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Snackbar.make(view, "Creating new Routines is coming soon.", Snackbar.LENGTH_LONG)
								.setAction("Action", null).show();
					}
				});
				break;
			case "free":
				btnNewRoutine.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Snackbar.make(view, "Creating new Routines is only available in the PAID app.", Snackbar.LENGTH_LONG)
								.setAction("Action", null).show();
					}
				});
				break;
		}

		methodLogger.end();
	}

	@Override
	protected void onStart() { // becoming visible
		super.onStart();

	}

	@Override
	protected void onResume() { // becoming interactive or returning from another Activity
		MethodLogger methodLogger = new MethodLogger();

		super.onResume();

		// query DB for Sessions run recently then update Routine.ranRecently
		new GetRecentSessionsAsyncTask().execute(this);

		methodLogger.end();
	}

	@Override
	protected void onPause() { // still visible
		super.onPause();

	}

	@Override
	protected void onStop() { // no longer visible
		super.onStop();

	}

	@Override
	protected void onRestart() { // restore from stopped
		super.onRestart();

	}

	@Override
	protected void onDestroy() { // ensure resources are freed before being destroyed
		super.onDestroy();

	}


	// GetRecentSessionsAsyncTask class
	private class GetRecentSessionsAsyncTask extends AsyncTask<Context, Void, Void> {

		private Context context;
		private List<Session> sessions;

		@Override
		protected Void doInBackground(Context... context) {
			MethodLogger methodLogger = new MethodLogger();
			this.context = context[0];

			// Query for list of recent Sessions
			sessions = AppDatabase.getRecentSessionsAsync();

			methodLogger.end();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			MethodLogger methodLogger = new MethodLogger();
			for (Routine routine : RoutineLibrary.routines) {
				routine.ranRecently = false;
				for (Session session : sessions) {
					if (routine.name.equals(session.getRoutineName())) {
						routine.ranRecently = true;
					}
				}
			}

			lstRoutines.setAdapter(new RoutineAdapter(context, R.layout.routine_row, RoutineLibrary.routines));
			methodLogger.end();
		}

	}

}
