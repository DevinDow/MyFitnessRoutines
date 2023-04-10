package com.devindow.myfitnessroutines.routine;

import com.devindow.myfitnessroutines.generic.Generic;
import com.devindow.myfitnessroutines.util.MethodLogger;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Library of Routines
 */
public class RoutineLibrary {

	// Public Fields
	public static Dictionary<String, Routine> routines;


	// Public Methods
	public static void generate() {
		new MethodLogger();

		MoveLibrary.generate();

		routines = new Hashtable<>(); // new hashtable since this could be a re-run
		SampleRoutines.generate();
	}


	// Private Static Methods
	public static void addRoutine(Routine routine) {
		// avoid multiple Routines with same Name/Key
		Routine existingRoutine = routines.get(routine.name);
		if (existingRoutine != null)
			throw new AssertionError("multiple Routines with same Name/Key: " + routine.name);

		routines.put(routine.name, routine);
	}


	// Constants
	// Routine.name is used as Key in Hashtable, so all names must be unique.

	public static final String TEST = "Test Routine";
	public static final String SEVEN_MINUTE_WORKOUT = "7 Minute Workout";
	public static final String MORNING_YOGA = "Morning Yoga";
	public static final String SUN_SALUTATION = "Sun Salutation";
	public static final String YOGA_STRETCH = "Yoga Stretch";
	public static final String TWENTY_FOUR_FORMS = "24 Forms";
	public static final String SHIBASHI_1 = "Shibashi 1";
	public static final String WARMUP_THERMOREGULATION = "Warmup/Thermoregulation";
	public static final String PRE_ACTIVATION = "Pre-Activation";
	public static final String LOWER_ABS = "Lower Abs";
	public static final String OBLIQUE_ABS = "Oblique Abs";
	public static final String UPPER_ABS = "Upper Abs";
	public static final String MIXED_ABS = "Mixed Abs";
	public static final String PLANKS = "Planks";
	public static final String FIVE_MIN_MEDITATION = "5 min Meditation";
	public static final String TEN_MIN_MEDITATION = "10 min Meditation";
	public static final String FIFTEEN_MIN_MEDITATION = "15 min Meditation";
	public static final String LADDER_DRILLS = "Ladder Drills";
	public static final String SOCCER_TOUCHES = "Soccer Touches";

}
