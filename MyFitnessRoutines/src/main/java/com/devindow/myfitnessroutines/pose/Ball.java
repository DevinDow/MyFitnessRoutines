package com.devindow.myfitnessroutines.pose;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Devin on 2/23/2018.
 */

public class Ball extends Prop {

	// Constants
	public static float diameter = 8.65f;


	// Private Fields
	private float x;
	private float y;


	// Constructor
	public Ball(float x, float y) {
		this.x = x;
		this.y = y;
	}


	// Overrides
	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(diameter);
		paint.setColor(Color.GRAY);

		canvas.drawPoint(x, y, paint);
	}

}
