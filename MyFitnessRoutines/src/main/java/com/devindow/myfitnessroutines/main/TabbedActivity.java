package com.devindow.myfitnessroutines.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.devindow.myfitnessroutines.BuildConfig;
import com.devindow.myfitnessroutines.R;
import com.devindow.myfitnessroutines.routine.RoutineLibrary;

/**
 * Activity with Tabs of GenericFragments
 */
public class TabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabbed);

        // When debugging, regenerate Moves & Routines every time TabbedActivity is created.
        if (BuildConfig.DEBUG) {
            RoutineLibrary.generate();
        }

        // Tab Pager
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
