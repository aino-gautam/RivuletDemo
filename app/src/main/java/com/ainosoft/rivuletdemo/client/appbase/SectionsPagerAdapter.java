package com.ainosoft.rivuletdemo.client.appbase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ainosoft.rivuletdemo.client.home.HomeFragment;
import com.ainosoft.rivuletdemo.client.logscreen.HomeLogScreenFragment;
import com.ainosoft.rivuletdemo.client.projectscreen.ProjectScreen;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private HomeLogScreenFragment homeLogScreenFragment;
    private HomeFragment homeActivity;
    private ProjectScreen projectScreen;
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a LineChartFragment (defined as a static inner class below).
        homeLogScreenFragment = new HomeLogScreenFragment();
        homeActivity = new HomeFragment();
        projectScreen = new ProjectScreen();

        if(position == 0){
            return homeActivity.newInstance(position+1);
        }if(position == 2){
            return projectScreen.newInstance(position+1);
        }else if(position == 3){
            return homeLogScreenFragment.newInstance(position+1);
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Insight";
            case 2:
                return "Project";
            case 3:
                return "Logs";
        }
        return null;
    }
}

