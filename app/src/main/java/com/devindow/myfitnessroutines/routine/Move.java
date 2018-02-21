package com.devindow.myfitnessroutines.routine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.devindow.myfitnessroutines.pose.Pose;

import java.io.Serializable;

/**
 * Created by Devin on 2/17/2018.
 */

public class Move implements Serializable {

	// Constants
	public static final int bitmapSize = 900;
	public static final int bitmapScale = 10;
	public static final int bitmapInches = bitmapSize / bitmapScale;


	// Public Fields
	public String name;
	public String description;
	public Category category;
	public boolean twoSides;
	public Pose pose1;
	public Pose pose2;
	public boolean hasFloor = true;


	// Protected Fields
	protected Bitmap bitmap;
	protected Canvas canvas;


	// Constructors
	public Move(String name) {
		this(name, false);
	}

	public Move(String name, boolean twoSides) {
		this(name, Category.NONE, twoSides);
	}

	public Move(String name, Category category) {
		this(name, category, false);
	}

	public Move(String name, String description) {
		this(name, description, false);
	}

	public Move(String name, String description, Category category) {
		this(name, description, category, false);
	}

	public Move(String name, Category category, boolean twoSides) {
		this(name, "", category, twoSides);
	}

	public Move(String name, String description, boolean twoSides) {
		this(name, description, Category.NONE, twoSides);
	}

	public Move(String name, String description, Category category, boolean twoSides) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.twoSides = twoSides;
	}


	// Public Methods
	public Bitmap getBitmap(boolean secondSide) {
		if (pose1 == null) {
			return null;
		}

		bitmap = Bitmap.createBitmap(bitmapSize, bitmapSize, Bitmap.Config.ARGB_8888);

		canvas = new Canvas(bitmap);
		drawFrame(canvas);
		canvas.translate(bitmapSize/2, bitmapSize-1); // Origin at floor center
		canvas.scale(bitmapScale, bitmapScale); // 10x bitmapScale
		canvas.scale(1, -1); // up is positive Y
		if (secondSide) {
			canvas.scale(-1, 1); // mirror X
		}

		pose1.draw(canvas);

		if (pose2 != null) {
			pose2.draw(canvas);
		}

		return bitmap;
	}


	// Private Methods
	private void drawFrame(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20);
		canvas.drawRect(0, 0, bitmapSize, bitmapSize, paint);
	}


	// Overrides
	@Override
	public String toString() {
		return this.name;
	}
}
