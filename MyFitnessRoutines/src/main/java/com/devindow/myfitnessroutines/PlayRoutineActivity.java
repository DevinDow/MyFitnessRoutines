package com.devindow.myfitnessroutines;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.devindow.myfitnessroutines.pose.MoveWithPose;
import com.devindow.myfitnessroutines.routine.*;
import com.devindow.myfitnessroutines.util.MethodLogger;

import java.util.Locale;

public class PlayRoutineActivity extends OptionsMenuActivity implements PlayRoutineTaskFragment.PlayRoutineCallbacks {

	// Constants
	public static final String PLAY_ROUTINE_TASK_FRAGMENT = "PlayRoutineTaskFragment";


	// Private Fields
	private PlayRoutineTaskFragment taskFragment;
	private TextToSpeech speech;
	// If we need to speak before TTS is initialized (isSpeechInitialized), we'll have to wait, so store it where speech initialization can play it (textToSpeakAfterInitialized).
	private boolean isSpeechInitialized = false;
	private String textToSpeakAfterInitialized;


	// Lifecycle Overrides
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MethodLogger methodLogger = new MethodLogger();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_routine);

		speech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				MethodLogger methodLogger = new MethodLogger();
				if (status != TextToSpeech.SUCCESS) {
					methodLogger.e("TextToSpeech initialization failed");
					return;
				}
				int result = speech.setLanguage(Locale.US);
				if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
					methodLogger.e("TextToSpeech language not supported");
				}
				isSpeechInitialized = true;

				// Play stored textToSpeakAfterInitialized now that speech is running.
				if (textToSpeakAfterInitialized != null) {
					speech.speak(textToSpeakAfterInitialized, TextToSpeech.QUEUE_FLUSH, null);
					textToSpeakAfterInitialized = null;
				}

				methodLogger.end();
			}
		});

		// keep Screen ON
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// If the Fragment is non-null, then it is currently being retained across a configuration change.
		FragmentManager fragmentManager = getFragmentManager();
		taskFragment = (PlayRoutineTaskFragment) fragmentManager.findFragmentByTag(PLAY_ROUTINE_TASK_FRAGMENT);
		if (taskFragment == null) {
			taskFragment = new PlayRoutineTaskFragment();
			fragmentManager.beginTransaction().add(taskFragment, PLAY_ROUTINE_TASK_FRAGMENT).commit();
		}

		// get Routine passed in by Intent
		Intent intent = getIntent();
		taskFragment.routine = (Routine)intent.getSerializableExtra("routine");

		// Routine Name in Title
		setTitle(taskFragment.routine.name);

		// show the current Task
		displayTask();

		// update btnPlay in case it is running
		updatePlayButton();

		methodLogger.end();
	}

	@Override protected void onDestroy() {
		MethodLogger methodLogger = new MethodLogger();
		super.onDestroy();

		speech.shutdown();
	}


	// PlayRoutineCallbacks implementation
	@Override
	public void displayTask() {
		MethodLogger methodLogger = new MethodLogger();

		clearInstructions();
		clearNextMoveName();

		displayInstructions();

		displayMove();

		updatePlayButton(); // taskFragment.next() might have reached DONE and paused it

		updateTimer(taskFragment.getSecondsRemaining());

		displayNextMoveName();

		displayTasksRemaining();

		methodLogger.end();
	}

	@Override
	public void displayMove() {
		MethodLogger methodLogger = new MethodLogger();

		final TextView txtMoveName = findViewById(R.id.txtMoveName);
		final ImageView imgMove = findViewById(R.id.imgMove);

		String moveName = taskFragment.getMoveName();

		if (taskFragment.move == null) {
			txtMoveName.setText(moveName);
			imgMove.setImageBitmap(Bitmap.createBitmap(MoveWithPose.BITMAP_PIXELS, MoveWithPose.BITMAP_PIXELS, Bitmap.Config.ARGB_8888));
		} else {
			Task currentTask = taskFragment.getCurrentTask();
			boolean mirrored = false;
			if (taskFragment.move.twoSides) {
				if (currentTask.side.hasBoth()) {
					if (taskFragment.isSecondSide()) {
						moveName += " <-";
						mirrored = true;
					} else {
						moveName += " ->";
					}
				} else if (currentTask.side.hasRight()) {
					moveName += " ->";
				} else if (currentTask.side.hasLeft()) {
					moveName += " <-";
					mirrored = true;
				}
			}
			txtMoveName.setText(moveName);
			imgMove.setImageBitmap(taskFragment.move.getBitmap(mirrored));
		}
		methodLogger.end();
	}

	@Override
	public void updateTimer(int secondsRemaining) { // pass in secondsRemaining because otherwise it would show Rest Time instead of 0 at the end of Move
		MethodLogger methodLogger = new MethodLogger();

		TextView txtTimer = findViewById(R.id.txtTimer);
		if (txtTimer != null) {
			String timeRemaining = String.format("%d:%02d", secondsRemaining / 60, secondsRemaining % 60);
			methodLogger.d(timeRemaining);
			txtTimer.setText(timeRemaining);
		}
	}

	@Override
	public void clearInstructions() {
		MethodLogger methodLogger = new MethodLogger();
		final TextView txtInstructions = findViewById(R.id.txtInstructions);
		if (txtInstructions != null) {
			txtInstructions.setText("");
		}
	}

	@Override
	public void speak(String moveName, String moveInstructions) {
		MethodLogger methodLogger = new MethodLogger();

		if (Preferences.getSpeakMoveNames()) { // speak
			String text = moveName;

			// concatenate Instructions
			if (moveInstructions != null && Preferences.getSpeakMoveInstructions()) {
				text += ". " + moveInstructions;
			}

			if (isSpeechInitialized) {
				speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
				textToSpeakAfterInitialized = null;
			} else {
				// Cache textToSpeakAfterInitialized so it will be played after TTS is initialized (isSpeechInitialized).
				textToSpeakAfterInitialized = text;
			}

		} else { // play chime
			ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_NOTIFICATION,100);
			toneGenerator.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT,500);
		}

	}


	// Private Methods
	private void displayInstructions() {
		MethodLogger methodLogger = new MethodLogger();

		final TextView txtInstructions = findViewById(R.id.txtInstructions);
		if (txtInstructions != null) {
			txtInstructions.setText(taskFragment.getInstructions());
		}
	}

	private void clearNextMoveName() {
		MethodLogger methodLogger = new MethodLogger();
		final TextView txtNextTask = findViewById(R.id.txtNextTask);
		if (txtNextTask != null) {
			txtNextTask.setText("");
		}
	}

	private void displayNextMoveName() {
		MethodLogger methodLogger = new MethodLogger();

		final TextView txtNextTask = findViewById(R.id.txtNextTask);
		if (txtNextTask != null) {
			Task nextTask = taskFragment.getNextTask();
			if (nextTask == null) {
				txtNextTask.setText("");
			} else {
				txtNextTask.setText("Next: " + nextTask.moveName);
			}
		}
	}

	private void displayTasksRemaining() {
		MethodLogger methodLogger = new MethodLogger();

		final TextView txtRemaining = findViewById(R.id.txtRemaining);
		if (txtRemaining != null) {
			txtRemaining.setText(taskFragment.getTasksRemaining());
		}
	}

	private void updatePlayButton() {
		MethodLogger methodLogger = new MethodLogger();

		ImageButton btnPlay = findViewById(R.id.btnPlay);

		if (taskFragment.isPaused()) {
			btnPlay.setImageResource(android.R.drawable.ic_media_play);
		} else {
			btnPlay.setImageResource(android.R.drawable.ic_media_pause);
		}
	}


	// Event Handlers
	public void onScreenClick(View v) {
		MethodLogger methodLogger = new MethodLogger();

		if (!taskFragment.isPaused()) {
			taskFragment.pause();
		} else {
			taskFragment.next();
		}

		updatePlayButton();
	}

	public void onPlayClick(View v) {
		MethodLogger methodLogger = new MethodLogger();

		if (!taskFragment.isPaused()) {
			taskFragment.pause();
		} else {
			taskFragment.play();
		}

		updatePlayButton();
	}

	public void onNextClick(View v) {
		MethodLogger methodLogger = new MethodLogger();

		taskFragment.next();
	}

	public void onPrevClick(View v) {
		MethodLogger methodLogger = new MethodLogger();

		taskFragment.prev();
	}

}
