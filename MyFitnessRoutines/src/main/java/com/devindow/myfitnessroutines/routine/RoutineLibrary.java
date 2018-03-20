package com.devindow.myfitnessroutines.routine;

import java.util.ArrayList;

/**
 * Created by Devin on 3/20/2018.
 */

public class RoutineLibrary {

	// Public Fields
	public static ArrayList<Routine> routines;


	// Public Methods
	public static void generate() {

		MoveLibrary.generateMoves();

		ArrayList<Routine> sampleRoutines = SampleRoutines.generateSampleRoutines();

		routines = sampleRoutines;
	}

}
