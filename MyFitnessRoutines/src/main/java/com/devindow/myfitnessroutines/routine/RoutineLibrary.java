package com.devindow.myfitnessroutines.routine;

import com.devindow.myfitnessroutines.generic.Generic;
import com.devindow.myfitnessroutines.util.MethodLogger;

import java.util.ArrayList;

/**
 * Created by Devin on 3/20/2018.
 */

public class RoutineLibrary {

	// Public Fields
	public static ArrayList<Generic> routines;


	// Public Methods
	public static void generate() {
		new MethodLogger();

		MoveLibrary.generate();

		routines = new ArrayList<>(); // new array since this could be a re-run
		SampleRoutines.generate();
	}

}
