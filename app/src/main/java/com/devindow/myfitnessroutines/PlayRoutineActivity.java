package com.devindow.myfitnessroutines;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayRoutineActivity extends AppCompatActivity {

	// Private Fields
	private Routine routine;
	private int stepNum = 1;

	private CountDownTimer countDownTimer;
	private TextView txtTimer;
	long poseSecondsRemaining;
	long restSecondsRemaining;


	// Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_play_routine);

		Intent intent = getIntent();
		routine = (Routine)intent.getSerializableExtra("routine");

		// txtRoutineName
		final TextView txtRoutineName = findViewById(R.id.txtRoutineName);
		txtRoutineName.setText(routine.Name);

		// txtTimer
		txtTimer = findViewById(R.id.txtTimer);

		ShowStep();
	}

	private void ShowStep() {
		Pose pose;
		if (stepNum > routine.Steps.size()) {
			pose = new FrontalPose("Done", Category.MEDITATION);
			countDownTimer = null;
		} else {
			Step step = routine.Steps.get(stepNum - 1);
			pose = step.Pose;
			poseSecondsRemaining = step.Duration;
			restSecondsRemaining = step.RestDuration;
			UpdateTimerView(poseSecondsRemaining);
		}

		// txtPoseName
		final TextView txtPoseName = findViewById(R.id.txtPoseName);
		txtPoseName.setText(pose.Name);

		// imgPose
		final ImageView imgPose = findViewById(R.id.imgPose);
		int w = imgPose.getWidth();
		int h = imgPose.getHeight();
		Bitmap bitmap = pose.getBitmap();
		imgPose.setImageBitmap(bitmap);

		// If timer was running then run.
		if (countDownTimer != null) {
			runPoseTimer();
		}
	}

	private void UpdateTimerView(long secondsRemaining) {
		txtTimer.setText(String.format("%d:%02d", secondsRemaining / 60, secondsRemaining % 60));
	}

	public void onGoClick(View v) {
		// Pause
		if (countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer = null;
			// Set btnGo image to Play
			return;
		}

		// Play
		// Set btnGo image to Pause

		if (poseSecondsRemaining > 0) {
			runPoseTimer();
		} else {
			runRestTimer();
		}
	}

	private void runPoseTimer() {
		countDownTimer = new CountDownTimer(poseSecondsRemaining * 1000, 1000) {
			@Override
			public void onTick(long millisRemaining) {
				poseSecondsRemaining = millisRemaining / 1000;
				UpdateTimerView(poseSecondsRemaining);
			}

			@Override
			public void onFinish() {
				runRestTimer();
			}
		}.start();
	}

	private void runRestTimer() {
		countDownTimer = new CountDownTimer(restSecondsRemaining * 1000, 1000) {
			@Override
			public void onTick(long millisRemaining) {
				restSecondsRemaining = millisRemaining / 1000;
				UpdateTimerView(restSecondsRemaining);
			}

			@Override
			public void onFinish() {
				stepNum++;
				ShowStep();
			}
		}.start();
	}

	public void onNextClick(View v) {
		if (stepNum < routine.Steps.size()) {
			stepNum++;
			ShowStep();
		}
	}

	public void onPrevClick(View v) {
		if (stepNum > 1) {
			stepNum--;
			ShowStep();
		}
	}
}
