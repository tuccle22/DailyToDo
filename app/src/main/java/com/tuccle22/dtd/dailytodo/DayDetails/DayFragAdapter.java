package com.tuccle22.dtd.dailytodo.DayDetails;

/**
 * Created by Tucker
 * For Daily To Do
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


public class DayFragAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 7;
    public View currentView;

    public DayFragAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        return DayFragment.newInstance(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getArguments().getString("someTitle");
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment f = (Fragment) object;
        currentView = f.getView();
    }

    public View getCurrentView() {
        return currentView;
    }



}
