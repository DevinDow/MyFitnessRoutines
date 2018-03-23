package com.devindow.myfitnessroutines.util;

import android.util.Log;

import com.devindow.myfitnessroutines.BuildConfig;

/**
 * Created by Devin on 3/21/2018.
 */

	public class MethodLogger {

        // Private Fields
        private String tag;
        private String methodName;
        private long startTime;


        // Constructor
        public MethodLogger() {
            if (BuildConfig.DEBUG) { // Avoid doing any processing nor debug logging when built for RELEASE.
                StackTraceElement callersStackTraceElement = Thread.currentThread().getStackTrace()[3];
                tag = callersStackTraceElement.getClassName();
                methodName = callersStackTraceElement.getMethodName() + "()";
                startTime = System.currentTimeMillis();
                Log.d(tag, methodName);
            }
        }


        // Public Methods
        public void e(String message) {
            Log.e(tag, methodName + " " + message);
        }

        public void i(String message) {
            Log.i(tag, methodName + " " + message);
        }

        public void d(String message) {
            if (BuildConfig.DEBUG) { // Avoid doing any debug logging when built for RELEASE.
                Log.d(tag, methodName + " " + message);
            }
        }

        public void end() {
            if (BuildConfig.DEBUG) { // Avoid doing any processing nor debug logging when built for RELEASE.
                long elapsedMillis = System.currentTimeMillis() - startTime;
                Log.d(tag, String.format("%s finished in %.3f seconds.", methodName, 0.001f * elapsedMillis));
            }
        }

    }
