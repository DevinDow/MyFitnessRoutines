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

}