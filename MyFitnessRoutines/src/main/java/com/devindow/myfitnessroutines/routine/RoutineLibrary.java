package com.devindow.myfitnessroutines.routine;

import com.devindow.myfitnessroutines.util.MethodLogger;

import java.util.ArrayList;

/**
 * Created by Devin on 3/20/2018.
 */

public class RoutineLibrary {

	// Public Fields
	public static ArrayList<Routine> routines;


	// Public Methods
	public static void generate() {
		new MethodLogger();

		MoveLibrary.generateMoves();

		routines = new ArrayList<>();
		SampleRoutines.generateSampleRoutines();
	}

}
