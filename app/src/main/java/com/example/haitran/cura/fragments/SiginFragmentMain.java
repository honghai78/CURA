package com.example.haitran.cura.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.haitran.cura.R;
import com.example.haitran.cura.views.adapters.FragmentSignInPagerAdapter;

/**
 * Created by hai.tran on 6/29/2016.
 */
public class SiginFragmentMain extends Fragment {

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabsStrip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sigin_main, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        FragmentSignInPagerAdapter adapter = new FragmentSignInPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        mTabsStrip.setViewPager(mViewPager);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
