package com.devindow.myfitnessroutines.util;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

/**
 * Created by Devin on 2/12/2018.
 */

public final class Debug {

	// Public Static Constants
	static final int logLevel = Log.INFO; // ERROR = 6, WARN = 5, INFO = 4, DEBUG = 3, VERBOSE = 2

	public static final boolean on = true; // Set to false to allow compiler to identify and eliminate unreachable code.

	public static final boolean colors = false; // Set to true to help debug Pose bitmap issues using unique colors for body parts.

	public static final String TAG_ENTER = " ** ENTER ** ";
	public static final String TAG_EXIT = " -- EXIT  -- ";
	public static final String TAG_TIME = " ## TIME ## ";


	// Public Static Methods
	public static void setPenColor(Paint paint) {
		if (colors) {
			paint.setColor(Colors.random());
		} else {
			paint.setColor(Colors.body);
		}
	}


	// Logging
	public static void e(String tag, String string) {
		if (logLevel <= Log.ERROR){
			Log.e(tag, string);
		}
	}

	public static void w(String tag, String string) {
		if (logLevel <= Log.WARN) {
			Log.w(tag, string);
		}
	}

	public static void i(String tag, String string) {
		if (logLevel <= Log.INFO) {
			Log.i(tag, string);
		}
	}

	public static void d(String tag, String string) {
		if (logLevel <= Log.DEBUG) {
			Log.d(tag, string);
		}
	}

	public static void v(String tag, String string) {
		if (logLevel <= Log.VERBOSE) {
			Log.v(tag, string);
		}
	}

}