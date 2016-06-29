package com.example.haitran.cura.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.haitran.cura.fragments.SignInFragment;
/**
 * Created by hai.tran on 6/28/2016.
 */
public class FragmentSignInPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "All", "Educators", "Doctors" };

    public FragmentSignInPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        SignInFragment fragment = new SignInFragment();
        fragment.setmPage(position+1);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
