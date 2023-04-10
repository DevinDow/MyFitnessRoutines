package com.devindow.myfitnessroutines.pic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.devindow.myfitnessroutines.App;
import com.devindow.myfitnessroutines.R;
import com.devindow.myfitnessroutines.ladder.Ladder;
import com.devindow.myfitnessroutines.routine.Category;
import com.devindow.myfitnessroutines.routine.Move;
import com.devindow.myfitnessroutines.routine.MoveLibrary;
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
        switch (name)
        {
            case MoveLibrary.TAICHI_OPENING:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.open);
            case MoveLibrary.TAICHI_COMMENCING:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.commence);
            case MoveLibrary.TAICHI_BROADEN_CHEST:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.broaden_chest);
            case MoveLibrary.TAICHI_DANCING_WITH_RAINBOWS:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.rainbow);
            case MoveLibrary.TAICHI_CIRCLING_ARMS:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.circle_arms);
            case MoveLibrary.TAICHI_SWING_ARMS:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.swing_arms);
            case MoveLibrary.TAICHI_ROWING_BOAT:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.row_boat);
            case MoveLibrary.TAICHI_HOLDING_BALL:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.hold_ball);
            case MoveLibrary.TAICHI_CARRYING_MOON:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.carry_moon);
            case MoveLibrary.TAICHI_PUSH_PALM:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.push_hands);
            case MoveLibrary.TAICHI_CLOUD_HANDS:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.cloud_hands);
            case MoveLibrary.TAICHI_SCOOPING_SEA:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.scoop_sea);
            case MoveLibrary.TAICHI_PLAYING_WITH_WAVES:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.play_waves);
            case MoveLibrary.TAICHI_SPREAD_WINGS:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.wings);
            case MoveLibrary.TAICHI_PUNCHING:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.punch);
            case MoveLibrary.TAICHI_FLYING_LIKE_GOOSE:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.fly);
            case MoveLibrary.TAICHI_SPINNING_WHEEL:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.wheel);
            case MoveLibrary.TAICHI_BOUNCING_A_BALL:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.bounce_ball);
            case MoveLibrary.TAICHI_PRESSING_PALMS:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.press_palms);
            case MoveLibrary.TAICHI_CLOSING:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.open);
            case MoveLibrary.TAICHI_RUB_BELLY:
                return BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.open);

            default:
                return Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        }
    }
}
