package com.devindow.myfitnessroutines;

/**
 * Created by Devin on 2/18/2018.
 */

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.devindow.myfitnessroutines.db.AppDatabase;
import com.devindow.myfitnessroutines.routine.Move;
import com.devindow.myfitnessroutines.routine.MoveLibrary;
import com.devindow.myfitnessroutines.routine.Routine;
import com.devindow.myfitnessroutines.routine.Session;
import com.devindow.myfitnessroutines.routine.Task;
import com.devindow.myfitnessroutines.util.Debug;
import com.devindow.myfitnessroutines.util.MethodLogger;

// This Fragment manages a the timers and retains itself across configuration changes.
public class PlayRoutineTaskFragment extends Fragment {

	// PlayRoutineCallbacks Interface (PlayRoutineTaskFragment calls to update PlayRoutineActivity)
	interface PlayRoutineCallbacks {
		void displayTask();
		void displayMove();
		void updateTimer(int secondsRemaining);
		void clearInstructions();
		void speak(String moveName, String moveInstructions);
	}


	// Public Fields
	public Routine routine;
	public Move move;
	public int move1SecondsRemaining;
	public int move2SecondsRemaining;
	public int restSecondsRemaining;


	// Private Fields
	private PlayRoutineCallbacks playRoutineActivity;
	private int taskNum = 1;
	private CountDownTimer countDownTimer;


	// Public Properties
	public boolean isPaused() { return countDownTimer == null; }

	public Task getCurrentTask() {
		return routine.getTask(taskNum);
	}

	public String getInstructions() {
		Task currentTask = getCurrentTask();
		if (currentTask != null && !currentTask.instructions.isEmpty()) {
			return currentTask.instructions;
		}

		if (move != null) {
			return move.description;
		}

		return "";
	}

	public Task getNextTask() {
		return routine.getTask(taskNum+1);
	}

	public String getTasksRemaining() {
		return routine.getTasksRemainingString(taskNum);
	}

	public int getSecondsRemaining() {
		int secondsRemaining = move1SecondsRemaining + move2SecondsRemaining;
		if (secondsRemaining == 0) {
			secondsRemaining = restSecondsRemaining;
		}
		return secondsRemaining;
	}

	public boolean isSecondSide() {
		return move1SecondsRemaining == 0;
	}


	// Fragment Class Overrides
	@Override
	public void onAttach(Activity activity) {
		MethodLogger methodLogger = new MethodLogger();
		super.onAttach(activity);

		// set playRoutineActivity to Activity
		playRoutineActivity = (PlayRoutineCallbacks)activity;

		methodLogger.end();
	}

	// TaskFragment.onCreate() happens after TaskFragment.onAttach()
	@Override
	public void onCreate(Bundle savedInstanceState) {
		MethodLogger methodLogger = new MethodLogger();
		super.onCreate(savedInstanceState);

		// Retain this fragment across configuration changes.
		setRetainInstance(true);

		setMove();

		methodLogger.end();
	}

	// TaskFragment.onDetach() happens on BACK or when ROTATED.
	@Override
	public void onDetach() {
		MethodLogger methodLogger = new MethodLogger();
		super.onDetach();

		// set playRoutineActivity to NULL
		playRoutineActivity = null;

		methodLogger.end();
	}

	// TaskFragment.onDestroy() only happens for Retained Fragment when BACK is hit, not when ROTATED.
	@Override
	public void onDestroy() {
		MethodLogger methodLogger = new MethodLogger();
		super.onDestroy();

		// kill the timer
		pause();

		methodLogger.end();
	}

	// Public Methods
	public void setMove() {
		Task currentTask = getCurrentTask();
		if (currentTask == null) {
			switch (routine.category) {
				case YOGA:
					move = MoveLibrary.moves.get(MoveLibrary.NAMASTE);
					break;
				case SOCCER:
					move = MoveLibrary.moves.get(MoveLibrary.CHAMP);
					break;
				default:
					move = MoveLibrary.moves.get(MoveLibrary.DONE);
					break;
			}
			pause();

			// insertSession Session in DB
			Session session = new Session(routine.name, routine.getTotalSeconds());
            AppDatabase.insertSession(session);
		} else {
			move = MoveLibrary.moves.get(currentTask.moveName);
		}

		resetSecondsRemaining();

		if (playRoutineActivity != null) {
			playRoutineActivity.displayTask();
			playRoutineActivity.speak(move.name, getInstructions());
		}
	}

	public void cancelTimer() {
		if (countDownTimer != null) {
			countDownTimer.cancel();
		}
	}

	public void resetSecondsRemaining() {
		MethodLogger methodLogger = new MethodLogger();

		Task currentTask = getCurrentTask();
		if (currentTask == null) {
			move1SecondsRemaining = move2SecondsRemaining = restSecondsRemaining = 0;
		} else {
			if (move != null && move.twoSides && currentTask.side.hasBoth()) {
				move1SecondsRemaining = currentTask.moveSeconds / 2;
				move2SecondsRemaining = currentTask.moveSeconds - move1SecondsRemaining;
			} else {
				move1SecondsRemaining = currentTask.moveSeconds;
				move2SecondsRemaining = 0;
			}
			restSecondsRemaining = currentTask.restSeconds;
		}

		methodLogger.end();
	}

	public void pause() {
		MethodLogger methodLogger = new MethodLogger();

		cancelTimer();
		countDownTimer = null;
	}

	public void play() {
		MethodLogger methodLogger = new MethodLogger();

		if (move1SecondsRemaining > 0) {
			runMove1Timer();
		} else if (move2SecondsRemaining > 0) {
			runMove2Timer();
		} else if (restSecondsRemaining > 0) {
			runRestTimer();
		}
		else {
			restart();
		}
	}

	public void next() {
		MethodLogger methodLogger = new MethodLogger();

		cancelTimer();

		if (taskNum <= routine.tasks.size()) {
			taskNum++;
		} else {
			taskNum = 1; // Restart ended Routine
		}
		setMove();

		// If timer was running then run.
		if (!isPaused()) {
			play();
		}
	}

	public void prev() {
		MethodLogger methodLogger = new MethodLogger();

		cancelTimer();

		if (taskNum > 1) {
			taskNum--;
		}
		setMove();

		// If timer was running then run.
		if (!isPaused()) {
			play();
		}
	}

	public void restart() {
		MethodLogger methodLogger = new MethodLogger();

		taskNum = 1; // Restart ended Routine
		setMove();

		play();
	}

	public void runMove1Timer() {
		MethodLogger methodLogger = new MethodLogger();
		if (playRoutineActivity != null) {
			playRoutineActivity.updateTimer(move1SecondsRemaining + move2SecondsRemaining);
		}

		countDownTimer = new CountDownTimer(move1SecondsRemaining * 1000, 1000) {
			@Override
			public void onTick(long millisRemaining) {
				move1SecondsRemaining = (int)(millisRemaining / 1000);
				if (playRoutineActivity != null) {
					playRoutineActivity.updateTimer(move1SecondsRemaining + move2SecondsRemaining);
				}
			}

			@Override
			public void onFinish() {
				MethodLogger methodLogger = new MethodLogger();

				// second side
				if (move2SecondsRemaining > 0) {
					if (playRoutineActivity != null) {
						playRoutineActivity.speak("switch", null);
						playRoutineActivity.displayMove();
					}
					runMove2Timer();

				// rest
				} else if (restSecondsRemaining > 0) {
					move = MoveLibrary.moves.get(MoveLibrary.REST);
					if (playRoutineActivity != null) {
						playRoutineActivity.clearInstructions();
						playRoutineActivity.displayMove();
						playRoutineActivity.speak("rest", null);
					}
					runRestTimer();

				// next Task
				} else {
					next();
				}
			}
		}.start();
	}

	public void runMove2Timer() {
		MethodLogger methodLogger = new MethodLogger();
		if (playRoutineActivity != null) {
			playRoutineActivity.updateTimer(move2SecondsRemaining);
		}

		countDownTimer = new CountDownTimer(move2SecondsRemaining * 1000, 1000) {
			@Override
			public void onTick(long millisRemaining) {
				move2SecondsRemaining = (int)(millisRemaining / 1000);
				if (playRoutineActivity != null) {
					playRoutineActivity.updateTimer(move2SecondsRemaining);
				}
			}

			@Override
			public void onFinish() {
				MethodLogger methodLogger = new MethodLogger();

				// rest
				if (restSecondsRemaining > 0) {
					move = MoveLibrary.moves.get(MoveLibrary.REST);
					if (playRoutineActivity != null) {
						playRoutineActivity.clearInstructions();
						playRoutineActivity.displayMove();
						playRoutineActivity.speak("rest", null);
					}
					runRestTimer();

				// next Task
				} else {
					next();
				}
			}
		}.start();
	}

	public void runRestTimer() {
		MethodLogger methodLogger = new MethodLogger();
		if (playRoutineActivity != null) {
			playRoutineActivity.updateTimer(restSecondsRemaining);
		}

		countDownTimer = new CountDownTimer(restSecondsRemaining * 1000, 1000) {
			@Override
			public void onTick(long millisRemaining) {
				restSecondsRemaining = (int)(millisRemaining / 1000);
				if (playRoutineActivity != null) {
					playRoutineActivity.updateTimer(restSecondsRemaining);
				}
			}

			@Override
			public void onFinish() {
				MethodLogger methodLogger = new MethodLogger();

				next();
			}
		}.start();
	}

}