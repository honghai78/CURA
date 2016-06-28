package com.example.haitran.cura.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.example.haitran.cura.R;
import com.example.haitran.cura.views.adapters.FragmentSignInPagerAdapter;

/**
 * Created by hai.tran on 6/28/2016.
 */
public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentSignInPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(viewPager);
        getSupportActionBar().hide();
    }
}
