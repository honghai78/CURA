package com.example.haitran.cura.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.haitran.cura.fragments.InQueueFragment;
import com.example.haitran.cura.fragments.RegisteredPatientFragment;

/**
 * Created by hanh.tran on 7/1/2016.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"Registered (0)", "In Queue (0)"};
    private final int NUM_PAGE = 2;

    public HomePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return RegisteredPatientFragment.newInstance(0, titles[0]);

        return InQueueFragment.newInstance(1, titles[1]);
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }
}
