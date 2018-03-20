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
import com.devindow.myfitnessroutines.util.Debug;
import com.devindow.myfitnessroutines.util.MessageDialog;

import java.util.List;

public class MainActivity extends OptionsMenuActivity {

	// Fields
	private ListView lstRoutines;


	// Overrides
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Debug.d(Debug.TAG_ENTER, "MainActivity.onCreate()");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);


		// generate Moves & Routines
		RoutineLibrary.generate();


		// lstRoutines
		lstRoutines = findViewById(R.id.lstRoutines);
		lstRoutines.setAdapter(new RoutineAdapter(this, R.layout.routine_row, RoutineLibrary.routines));
		final Context context = this;
		lstRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				Routine routine = (Routine) lstRoutines.getItemAtPosition(position);
				if (routine.isFree || BuildConfig.FLAVOR.equals("paid")) {
					Intent intent = new Intent(view.getContext(), PlayRoutineActivity.class);
					intent.putExtra("routine", routine);
					startActivity(intent);
				} else {
					MessageDialog.show(context, "Please purchase the paid version in the app store to access these Routines.");
				}
			}
		});


		// btnNewRoutine
		FloatingActionButton btnNewRoutine = (FloatingActionButton) findViewById(R.id.btnNewRoutine);
		btnNewRoutine.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Creating new Routines is coming soon.", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

		Debug.d(Debug.TAG_EXIT, "MainActivity.onCreate()");
	}

	@Override
	protected void onStart() { // becoming visible
		super.onStart();

	}

	@Override
	protected void onResume() { // becoming interactive or returning from another Activity
		Debug.d(Debug.TAG_ENTER, "MainActivity.onResume()");

		super.onResume();

		// query DB for Sessions run recently then update Routine.ranRecently
		new GetRecentSessionsAsyncTask().execute(this);

		Debug.d(Debug.TAG_EXIT, "MainActivity.onResume()");
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
			Debug.d(Debug.TAG_ENTER, "GetRecentSessionsAsyncTask.doInBackground()");
			this.context = context[0];

			sessions = AppDatabase.getSessionsInLast24HoursAsync();
			Debug.d(Debug.TAG_EXIT, "GetRecentSessionsAsyncTask.doInBackground()");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Debug.d(Debug.TAG_ENTER, "GetRecentSessionsAsyncTask.onPostExecute()");
			for (Routine routine : RoutineLibrary.routines) {
				routine.ranRecently = false;
				for (Session session : sessions) {
					if (routine.name.equals(session.getRoutineName())) {
						routine.ranRecently = true;
					}
				}
			}

			lstRoutines.setAdapter(new RoutineAdapter(context, R.layout.routine_row, RoutineLibrary.routines));
			Debug.d(Debug.TAG_EXIT, "GetRecentSessionsAsyncTask.onPostExecute()");
		}

	}

}
