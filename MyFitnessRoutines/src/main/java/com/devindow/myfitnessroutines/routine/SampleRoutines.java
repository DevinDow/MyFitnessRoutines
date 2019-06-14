package com.devindow.myfitnessroutines.routine;

import com.devindow.myfitnessroutines.BuildConfig;
import com.devindow.myfitnessroutines.util.MethodLogger;
import com.devindow.myfitnessroutines.util.Side;

/**
 * Created by Devin on 1/27/2018.
 */

public class SampleRoutines {

	// Public Static Methods
	public static void generate() {
		MethodLogger methodLogger = new MethodLogger();

		if (BuildConfig.DEBUG) {
			generateTestRoutine();
		}

		switch (BuildConfig.FLAVOR) {
			case "free":
			case "full":
			default:
				generate7MinuteWorkout();

				generateMorningYoga();
				generateSunSalutation();
				generateYogaStretch();

				generateWarmup();

				generatePreActivation();

				generateLowerAbs();
				generateObliqueAbs();
				generateUpperAbs();
				generateMixedAbs();
				generatePlanks();

				generate5MinMeditation();
				generate10MinMeditation();
				generate15MinMeditation();

				generateLadderDrills();
				generateSoccerTouches();
				break;

			case "yoga":
				generateMorningYoga();
				generateSunSalutation();
				generateYogaStretch();

				generate5MinMeditation();
				generate10MinMeditation();
				generate15MinMeditation();
				break;

			case "abs":
				generateLowerAbs();
				generateObliqueAbs();
				generateUpperAbs();
				generateMixedAbs();
				generatePlanks();
				break;

			case "soccer":
				generateMorningYoga();
				generateWarmup();
				generatePreActivation();
				generateLadderDrills();
				generateSoccerTouches();
				generate7MinuteWorkout();
				break;
		}

		methodLogger.end();
	}


	// Private Methods
	private static void generateTestRoutine() {
		Routine routine = new Routine("Test Routine", Category.NONE, "", true);

		routine.tasks.add(new Task(MoveLibrary.HAMSTRING_STRETCH, 15));
		routine.tasks.add(new Task(MoveLibrary.HIP_HAMSTRING, 15));
		routine.tasks.add(new Task(MoveLibrary.PLOW,10, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.CHAIR_POSE,5));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_1,5, Side.RIGHT));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_2,5, Side.RIGHT));
		routine.tasks.add(new Task(MoveLibrary.TRIANGLE,5, Side.RIGHT));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_3,9, Side.RIGHT));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_1,5, Side.LEFT));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_2,5, Side.LEFT));
		routine.tasks.add(new Task(MoveLibrary.TRIANGLE,5, Side.LEFT));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_3,9, Side.LEFT));

		RoutineLibrary.routines.add(routine);
	}


	private static void generate7MinuteWorkout() {
		Routine routine = new Routine("7 Minute Workout", Category.CARDIO, "High-intensity circuit training that alternates muscle groups", true);

		routine.tasks.add(new Task(MoveLibrary.JUMPING_JACKS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.WALL_SIT, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.PUSH_UPS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.KNEE_UP_CRUNCHES, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.STEP_UPS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SQUATS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.CHAIR_DIPS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.ELBOWS_PLANK, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.HIGH_KNEES, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.LUNGES, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.PUSH_UP_ROTATE, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SIDE_PLANK, 30));

		RoutineLibrary.routines.add(routine);
	}


	private static void generateMorningYoga() {
		Routine routine = new Routine("Morning Yoga", Category.YOGA, "Yoga for getting going when stiff from inactivity.  Breathe with each movement.");

		routine.tasks.add(new Task(MoveLibrary.CORPSE_POSE, 60, "Lie on your back. Relax. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.KNEE_CROSS_OVER, 30, "Knee across body. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.HIP_OPEN, 30, "Hip opened up. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.RECLINED_COBBLER_POSE, 15, "Legs open, feet together. Press legs to extend spine. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.HEAD_TO_KNEES_TOPVIEW, 15, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.RECLINED_TWIST, 30, "Knees across body a few inches off the ground. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.RECLINED_HAMSTRING_W_STRAP, 60, "Bend knee then straighten. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.COBBLER_POSE, 20, "Sit. Butterfly. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.BOAT_POSE, 15, "Body & legs in a V. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.SHOULDER_PRESS, 15, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.PLOW, 10, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.BRIDGE_POSE, 20, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.LOCUST_POSE, 15, "On Belly. Lift legs & chest. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.CHILD_POSE, 20, "Walk your fingers out. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.ROTATE_ON_ALL_FOURS, 20, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.CAT_POSE, 20, "Arch back, then bow back. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.DOWN_DOG_ALTERNATING_CALVES, 40, "Alternate calves. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.WIDE_LEG_BEND, 30, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.MOUNTAIN_POSE, 15, "Roll up slowly. Stand tall. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.CHAIR_POSE, 15, "Palms together, navel towards spine. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.STANDING_SIDE_BEND, 20, "Feet shoulder-width apart. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_2, 30, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.HIP_STRETCH, 40, "Breathe."));
		routine.tasks.add(new Task(MoveLibrary.SAGE_POSE, 10, "Sit Tall. Legs together. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.TWISTED_SAGE_POSE, 30, "Sit Tall. Pretzel. Breathe."));
		routine.tasks.add(new Task(MoveLibrary.LOTUS, 60, "Meditate & Breathe."));

		RoutineLibrary.routines.add(routine);
	}

	private static void generateSunSalutation() {
		Routine routine = new Routine("Sun Salutation", Category.YOGA, "Yoga warmup of folding & unfolding along with your breath");

		int breathSeconds = 5;
		for (int i=1; i<=2; i++) { // 2 times
			routine.tasks.add(new Task(MoveLibrary.PRAYER, breathSeconds * 2, "Breathe"));
			routine.tasks.add(new Task(MoveLibrary.BACK_BEND, breathSeconds, "Inhale, reach up and back"));
			routine.tasks.add(new Task(MoveLibrary.TOUCH_TOES, breathSeconds, "Exhale, fold forward"));
			routine.tasks.add(new Task(MoveLibrary.PRONE_LUNGE, breathSeconds, "Inhale, step back to lunge"));
			routine.tasks.add(new Task(MoveLibrary.HANDS_PLANK, breathSeconds, "Retain, step back to plank"));
			routine.tasks.add(new Task(MoveLibrary.CHATURANGA, breathSeconds, "Exhale, Chaturanga parallel to ground"));
			routine.tasks.add(new Task(MoveLibrary.COBRA, breathSeconds, "Inhale, up to Cobra"));
			routine.tasks.add(new Task(MoveLibrary.DOWN_DOG, breathSeconds * 5, "Exhale up to Downward Dog, stay for 5 breaths"));
			routine.tasks.add(new Task(MoveLibrary.PRONE_LUNGE, breathSeconds, "Inhale, step forward to lunge"));
			routine.tasks.add(new Task(MoveLibrary.TOUCH_TOES, breathSeconds, "Exhale, fold forward"));
			routine.tasks.add(new Task(MoveLibrary.BACK_BEND, breathSeconds, "Inhale, roll spine up, reach up and back"));
		}

		routine.tasks.add(new Task(MoveLibrary.PRAYER, breathSeconds * 3, "Breathe."));

		RoutineLibrary.routines.add(routine);
	}

	private static void generateYogaStretch() {
		Routine routine = new Routine("Yoga Stretch", Category.YOGA, "Yoga with 2 minute holds for stretching connective tissue, releasing stagnation, and meditation.");

		routine.tasks.add(new Task(MoveLibrary.HIP_STRETCH, 120, Side.RIGHT, "Sink forward to relax hip flexor."));
		routine.tasks.add(new Task(MoveLibrary.HAMSTRING_STRETCH, 30, Side.RIGHT, "Push back & straighten front leg to rest hip and stretch hamstring."));
		routine.tasks.add(new Task(MoveLibrary.HIP_STRETCH, 120, Side.LEFT, "Sink forward to relax hip flexor."));
		routine.tasks.add(new Task(MoveLibrary.HAMSTRING_STRETCH, 30, Side.LEFT, "Push back & straighten front leg to rest hip and stretch hamstring."));
		routine.tasks.add(new Task(MoveLibrary.HERO, 120, "Sit on ankles, lean back."));
		routine.tasks.add(new Task(MoveLibrary.DOWN_DOG, 60, "Stretch hamstrings and calves."));
		routine.tasks.add(new Task(MoveLibrary.COBBLER_POSE, 60, "Butterfly, lean forward"));
		routine.tasks.add(new Task(MoveLibrary.FROG_SPLITS, 120, "Knees wide & bent 90, push back."));
		routine.tasks.add(new Task(MoveLibrary.CHILD_POSE, 30, "An easy break for a moment."));
		routine.tasks.add(new Task(MoveLibrary.PIGEON, 240, "One leg bent on the ground, chest to thigh, stretching glute piraformis."));
		routine.tasks.add(new Task(MoveLibrary.KNEE_CROSS_OVER, 60, "Hug knee then cross over."));

		RoutineLibrary.routines.add(routine);
	}


	private static void generateWarmup() {
		Routine routine = new Routine("Warmup/Thermoregulation", Category.WARMUP, "A warmup to do when starting out cold");

		routine.tasks.add(new Task(MoveLibrary.JOG_LATERALLY, 30));
		routine.tasks.add(new Task(MoveLibrary.PUSH_UPS, 30));
		routine.tasks.add(new Task(MoveLibrary.DOWN_DOG_ALTERNATING_CALVES, 30));
		routine.tasks.add(new Task(MoveLibrary.TWIST_PIVOT, 30));
		routine.tasks.add(new Task(MoveLibrary.ROMAN_LUNGES, 30));
		routine.tasks.add(new Task(MoveLibrary.SAFETY_JACKS, 30));
		routine.tasks.add(new Task(MoveLibrary.HIP_HAMSTRING, 60));
		routine.tasks.add(new Task(MoveLibrary.LEG_SWINGS, 30));
		routine.tasks.add(new Task(MoveLibrary.HIGH_KNEES, 30));
		routine.tasks.add(new Task(MoveLibrary.FAST_FEET, 30));
		routine.tasks.add(new Task(MoveLibrary.SKIP, 30));

		RoutineLibrary.routines.add(routine);
	}

	private static void generatePreActivation() {
		Routine routine = new Routine("Pre-Activation", Category.WARMUP, "From the England National Soccer Team.  Do a warmup first.");

		routine.tasks.add(new Task(MoveLibrary.FOAM_ROLLER, 60));
		routine.tasks.add(new Task(MoveLibrary.THORACIC_ROLL_OUTS, 60));
		routine.tasks.add(new Task(MoveLibrary.REACH_BACK, 20));
		routine.tasks.add(new Task(MoveLibrary.WARRIOR_3, 40));
		routine.tasks.add(new Task(MoveLibrary.INCH_WORMS, 30));
		routine.tasks.add(new Task(MoveLibrary.WALKING_BACKWARD_LUNGES, 30));
		routine.tasks.add(new Task(MoveLibrary.SINGLE_LEG_BRIDGES, 30));
		routine.tasks.add(new Task(MoveLibrary.SIDE_LYING_ABDUCTION_W_BAND, 30));
		routine.tasks.add(new Task(MoveLibrary.SQUATS_W_BAND, 30));
		routine.tasks.add(new Task(MoveLibrary.LATERAL_WALK_W_BAND, 30));
		routine.tasks.add(new Task(MoveLibrary.STANDING_HURDLES_W_BAND, 30));
		routine.tasks.add(new Task(MoveLibrary.JUMPS_180, 30));
		routine.tasks.add(new Task(MoveLibrary.JUMPS_90_TO_1_FOOT_LANDING, 30));

		RoutineLibrary.routines.add(routine);
	}


	private static void generateLowerAbs() {
		Routine routine = new Routine("Lower Abs", Category.STRENGTH, "Focus on lower part of the abdominal muscles");

		routine.tasks.add(new Task(MoveLibrary.HIP_RAISES, 30));
		routine.tasks.add(new Task(MoveLibrary.REVERSE_CRUNCHES, 30, 10));

		routine.tasks.add(new Task(MoveLibrary.HIP_RAISES, 30));
		routine.tasks.add(new Task(MoveLibrary.REVERSE_CRUNCHES, 30, 10));

		routine.tasks.add(new Task(MoveLibrary.HIP_RAISES, 30));
		routine.tasks.add(new Task(MoveLibrary.REVERSE_CRUNCHES, 30));

		RoutineLibrary.routines.add(routine);
	}

	private static void generateObliqueAbs() {
		Routine routine = new Routine("Oblique Abs", Category.STRENGTH, "Focus on the oblique abdominal muscles");

		routine.tasks.add(new Task(MoveLibrary.CROSSOVER_CRUNCHES, 60, 10));

		routine.tasks.add(new Task(MoveLibrary.CATCH_CRUNCHES, 60, 10));

		routine.tasks.add(new Task(MoveLibrary.SIDE_CRUNCHES, 60));

		RoutineLibrary.routines.add(routine);
	}

	private static void generateUpperAbs() {
		Routine routine = new Routine("Upper Abs", Category.STRENGTH, "Focus on upper part of the abdominal muscles");

		routine.tasks.add(new Task(MoveLibrary.LEG_UP_CRUNCHES, 30));
		routine.tasks.add(new Task(MoveLibrary.KNEE_UP_CRUNCHES, 30, 10));

		routine.tasks.add(new Task(MoveLibrary.KNEE_BENT_CRUNCHES, 30));
		routine.tasks.add(new Task(MoveLibrary.FROG_LEG_CRUNCHES, 30, 10));

		routine.tasks.add(new Task(MoveLibrary.HORSE_RIDING_CRUNCHES, 30));
		routine.tasks.add(new Task(MoveLibrary.LEG_UP_CRUNCHES, 30));

		RoutineLibrary.routines.add(routine);
	}

	private static void generateMixedAbs() {
		Routine routine = new Routine("Mixed Abs", Category.STRENGTH, "A mixture of different abdominal areas", true);

		routine.tasks.add(new Task(MoveLibrary.KNEE_UP_CRUNCHES, 30));
		routine.tasks.add(new Task(MoveLibrary.HIP_RAISES, 30, 10));

		routine.tasks.add(new Task(MoveLibrary.CROSSOVER_CRUNCHES, 60, 10));

		routine.tasks.add(new Task(MoveLibrary.REVERSE_CRUNCHES, 15));
		routine.tasks.add(new Task(MoveLibrary.CATCH_CRUNCHES, 30));
		routine.tasks.add(new Task(MoveLibrary.BOAT_POSE, 15));

		RoutineLibrary.routines.add(routine);
	}

	private static void generatePlanks() {
		Routine routine = new Routine("Planks", Category.STRENGTH, "Develop core strength with a variety of planks");

		routine.tasks.add(new Task(MoveLibrary.HANDS_PLANK, 30));
		routine.tasks.add(new Task(MoveLibrary.ELBOWS_PLANK, 30));

		routine.tasks.add(new Task(MoveLibrary.SIDE_PLANK, 60));

		routine.tasks.add(new Task(MoveLibrary.HANDS_PLANK, 10));
		routine.tasks.add(new Task(MoveLibrary.CHATURANGA, 10));
		routine.tasks.add(new Task(MoveLibrary.HANDS_PLANK, 10));
		routine.tasks.add(new Task(MoveLibrary.ELBOWS_PLANK, 10));
		routine.tasks.add(new Task(MoveLibrary.HANDS_PLANK, 10));
		routine.tasks.add(new Task(MoveLibrary.ELBOWS_PLANK, 10));

		RoutineLibrary.routines.add(routine);
	}


	private static void generate5MinMeditation() {
		Routine routine = new Routine("5 min Meditation", Category.MEDITATION, "Meditation timer");

		routine.tasks.add(new Task(MoveLibrary.LOTUS, 5*60));

		RoutineLibrary.routines.add(routine);
	}

	private static void generate10MinMeditation() {
		Routine routine = new Routine("10 min Meditation", Category.MEDITATION, "Meditation timer", true);

		routine.tasks.add(new Task(MoveLibrary.LOTUS, 10*60));

		RoutineLibrary.routines.add(routine);
	}

	private static void generate15MinMeditation() {
		Routine routine = new Routine("15 min Meditation", Category.MEDITATION, "Meditation timer");

		routine.tasks.add(new Task(MoveLibrary.LOTUS, 15*60));

		RoutineLibrary.routines.add(routine);
	}


	private static void generateLadderDrills() {
		Routine routine = new Routine("Ladder Drills", Category.AGILITY, "Improve your Agility, Speed, Coordination, & Fitness");

		routine.tasks.add(new Task(MoveLibrary.LADDER_SPRINT, 15, 5));
		routine.tasks.add(new Task(MoveLibrary.LADDER_LATERAL, 15, 5));
		routine.tasks.add(new Task(MoveLibrary.LADDER_LATERAL_IN_OUT, 15, 5));
		routine.tasks.add(new Task(MoveLibrary.LADDER_SHUFFLE, 15, 5));
		routine.tasks.add(new Task(MoveLibrary.LADDER_CROSS_BEHIND, 15));
		routine.tasks.add(new Task(MoveLibrary.LADDER_JUMPING_JACK, 15, 5));
		routine.tasks.add(new Task(MoveLibrary.LADDER_HOPSCOTCH, 15, 5));
		routine.tasks.add(new Task(MoveLibrary.LADDER_SLALOM, 15, 5));

		RoutineLibrary.routines.add(routine);
	}


	private static void generateSoccerTouches() {
		Routine routine = new Routine("Soccer Touches", Category.SOCCER, "Improve your Touches & Fitness");

		routine.tasks.add(new Task(MoveLibrary.SOCCER_INSIDE_ROLLS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_BELLS, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_PULL_OPEN_OUTWARD, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_OUTSIDE_TURN, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_TRIANGLE, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_ADVANCED_TURN, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_TRIANGLE_OUTSIDE_ADVANCED, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_ZIKO_TURN, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_CRUYFF_TURN, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_STEP_OVER_ESCAPE_OUT, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_2_STEP_OVERS_ESCAPE_OUT, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_HAT_DANCE, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_HAT_DANCE_CIRCLE, 30, 5));
		routine.tasks.add(new Task(MoveLibrary.SOCCER_2_TOUCHES_THEN_ACROSS, 30, 5));

		RoutineLibrary.routines.add(routine);
	}

}
