package com.devindow.myfitnessroutines.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devindow.myfitnessroutines.BuildConfig;
import com.devindow.myfitnessroutines.generic.GenericFragment;

/**
 * A [FragmentPagerAdapter] that determines the Tabs
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    // Constructor
    public TabPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }


    // returns number of Tabs
    @Override
    public int getCount() {
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                return 2;
            case "full":
            case "free":
                return 4;
            case "soccer":
                return 3;
            case "yoga":
                return 2;
            case "abs":
                return 2;
            default:
                return 1;
        }
    }

    // returns Tab Fragment at position
    @Override
    public Fragment getItem(int position) {
        return GenericFragment.newInstance(position);
    }

    // returns Tab Name at position
    @Nullable @Override
    public CharSequence getPageTitle(int position) {
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                switch (position) {
                    case 0:
                        return "Learn";
                    case 1:
                        return "Practice";
                }
            case "full":
            case "free":
                switch (position) {
                    case 0:
                        return "Warm-up";
                    case 1:
                        return "Fitness";
                    case 2:
                        return "Agility";
                    case 3:
                        return "Meditation";
                }
            case "soccer":
                switch (position) {
                    case 0:
                        return "Warm-up";
                    case 1:
                        return "Agility";
                    case 2:
                        return "Fitness";
                }
            case "yoga":
                switch (position) {
                    case 0:
                        return "Yoga";
                    case 1:
                        return "Meditation";
                }
            case "abs":
                switch (position) {
                    case 0:
                        return "Warm-up";
                    case 1:
                        return "Core";
                }
            default:
                return "Routines";
        }
    }

}