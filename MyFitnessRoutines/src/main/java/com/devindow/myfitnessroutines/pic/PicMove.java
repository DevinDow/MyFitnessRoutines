package com.devindow.myfitnessroutines.pic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.devindow.myfitnessroutines.App;
import com.devindow.myfitnessroutines.R;
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

    public PicMove(String name, Category category) {
        super(name, category);
    }

    public PicMove(String name, String description) {
        super(name, description);
    }

    public PicMove(String name, Category category, String description) {
        super(name, description, category);
    }


    // Overrides
    @Override
    public Bitmap getBitmap(boolean mirrored) {
        return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.commence);
    }
}
