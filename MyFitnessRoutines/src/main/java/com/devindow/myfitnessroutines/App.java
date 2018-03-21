package com.devindow.myfitnessroutines;

import android.app.Application;
import android.content.Context;

import com.devindow.myfitnessroutines.db.AppDatabase;
import com.devindow.myfitnessroutines.routine.RoutineLibrary;
import com.devindow.myfitnessroutines.util.MethodLogger;

/**
 * Created by Devin on 3/17/2018.
 */

public class App extends Application {

	// Static Fields
	private static Application instance;


	// Public Properties
	public static Application getApplication() {
		return instance;
	}

	public static Context getContext() {
		return getApplication().getApplicationContext();
	}


	// Overrides
	@Override
	public void onCreate() {
		new MethodLogger();
		super.onCreate();

		instance = this;

		AppDatabase.init();

		// When not debugging, generate Moves & Routines once.
		if (!BuildConfig.DEBUG) {
			RoutineLibrary.generate();
		}
	}

}
