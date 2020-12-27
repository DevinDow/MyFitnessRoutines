package com.devindow.myfitnessroutines.pic;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.devindow.myfitnessroutines.ladder.Ladder;
import com.devindow.myfitnessroutines.routine.Category;
import com.devindow.myfitnessroutines.routine.Move;
import com.devindow.myfitnessroutines.util.Step;

import java.io.Serializable;

/**
 * a Picture Move
 */
public class PicMove extends Move implements Serializable {

    // Constructors
    public PicMove(String name) {
        super(name);
    }

    public PicMove(String name, boolean twoSides) {
        super(name, twoSides);
    }

    public PicMove(String name, Category category) {
        super(name, category);
    }

    public PicMove(String name, String description) {
        super(name, description);
    }

    public PicMove(String name, Category category, String description) {
        super(name, description, category);
    }

    public PicMove(String name, Category category, boolean twoSides) {
        super(name, category, twoSides);
    }

    public PicMove(String name, boolean twoSides, String description) {
        super(name, description, twoSides);
    }

    public PicMove(String name, Category category, boolean twoSides, String description) {
        super(name, description, category, twoSides);
    }


    // Overrides
    @Override
    public Bitmap getBitmap(boolean mirrored) {
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        /*Bitmap bitmap = Bitmap.createBitmap(BITMAP_PIXELS, BITMAP_PIXELS, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        drawFrame(canvas, BITMAP_PIXELS);
        canvas.translate(BITMAP_PIXELS/2, BITMAP_PIXELS-1); // Origin at bottom center
        canvas.scale(PIXELS_PER_INCH, PIXELS_PER_INCH); // Scale to Inches
        canvas.scale(1, -1); // up is positive Y
        canvas.translate(0, Ladder.rungGap/2 + Step.radius); // Origin moved up for starting point

        Ladder.draw(canvas);

        drawSteps(canvas);*/

        return bitmap;
    }
}
