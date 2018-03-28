package com.devindow.myfitnessroutines;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.devindow.myfitnessroutines.db.AppDatabase;
import com.devindow.myfitnessroutines.routine.Session;
import com.devindow.myfitnessroutines.util.MessageDialog;

import java.util.List;

/**
 * Created by Devin on 3/17/2018.
 */

public class OptionsMenuActivity extends AppCompatActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		int id = item.getItemId();

		switch (id) {


			case R.id.action_speakMoveNames: {
				if (Preferences.getSpeakMoveNames()) {
					item.setChecked(false);
					Preferences.setSpeakMoveNames(false);
				} else {
					item.setChecked(true);
					Preferences.setSpeakMoveNames(true);
				}

				return true;
			}


			case R.id.action_speakMoveInstructions: {
				if (Preferences.getSpeakMoveInstructions()) {
					item.setChecked(false);
					Preferences.setSpeakMoveInstructions(false);
				} else {
					item.setChecked(true);
					Preferences.setSpeakMoveInstructions(true);
				}

				return true;
			}


			case R.id.action_tips: {
				String tips =
						"TIPS:\n" +
						"\n" +
						"- The app marks a routine that you've completed today in GREEN.\n" +
						"\n" +
						"- Some Poses have a Left and a Right component.  The app will signal you to SWITCH half way through.\n" +
						"\n" +
						"- Sometimes I hit PLAY and follow the timer, sometimes I manually advance through the moves at my own pace.\n" +
						"(I recommend first getting familiar with a routine's moves before working with the timer.)\n" +
						"\n" +
						"- Tapping the screen while playing will PAUSE.\n" +
						"- Tapping the screen while paused will manually advance to the NEXT MOVE.\n";

				switch (BuildConfig.FLAVOR) {
					case "abs":
					case "full":
					case "free":
						tips +=
								"\n" +
								"\n" +
								"I LIKE TO:\n" +
								"  - do a different abs routine every day.\n";
						break;
				}
				switch (BuildConfig.FLAVOR) {
					case "full":
					case "free":
					case "soccer":
						tips +=
								"  - go for a walk where I stop at the park and do \"7 Minute Workout\".\n" +
								"  - do \"Morning Yoga\" then \"Warmup\" then \"Pre-Activation\" before playing soccer.\n";
						break;
				}

				MessageDialog.show(this, tips);
				return true;
			}


			case R.id.action_feedback: {
				Intent Email = new Intent(Intent.ACTION_SEND);
				Email.setType("text/email");
				Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "DevinDowApps@gmail.com" });
				Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback on " + App.getContext().getResources().getString(R.string.app_name));
				Email.putExtra(Intent.EXTRA_TEXT, "Version = " + BuildConfig.VERSION_NAME + "\n\nDear Devin, \n");
				startActivity(Intent.createChooser(Email, "Send Feedback:"));
				return true;
			}

		}

		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem mnuSpeakNames = menu.findItem(R.id.action_speakMoveNames);
		MenuItem mnuSpeakInstructions = menu.findItem(R.id.action_speakMoveInstructions);

		if (Preferences.getSpeakMoveNames()) {
			mnuSpeakNames.setChecked(true);
			mnuSpeakInstructions.setEnabled(true);
		} else {
			mnuSpeakNames.setChecked(false);
			mnuSpeakInstructions.setEnabled(false);
		}

		mnuSpeakInstructions.setChecked(Preferences.getSpeakMoveInstructions());

		return true;
	}

}
