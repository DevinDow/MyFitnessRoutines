package com.devindow.myfitnessroutines.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.devindow.myfitnessroutines.BuildConfig;
import com.devindow.myfitnessroutines.routine.PracticeFragment;
import com.devindow.myfitnessroutines.video.LearnFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                switch (position) {
                    case 0:
                    default:
                        return new LearnFragment();
                    case 1:
                        return new PracticeFragment();
                    case 2:
                        return new GenericFragment();
                }
            default:
                return new GenericFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                switch (position) {
                    case 0:
                    default:
                        return "Learn";
                    case 1:
                        return "Practice";
                    case 2:
                        return "Test";
                }
            default:
                return "Routines";
        }
    }

    // returns number of Tabs
    @Override
    public int getCount() {
        switch (BuildConfig.FLAVOR) {
            case "taichi":
                //return 2;
                return 3;
            default:
                return 1;
        }
    }
}