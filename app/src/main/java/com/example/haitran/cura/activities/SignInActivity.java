package com.example.haitran.cura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.AuthenticationFragment;
import com.example.haitran.cura.fragments.SiginFragmentMain;
import com.example.haitran.cura.fragments.SignInFragment;
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

        Fragment frag = new SiginFragmentMain();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.signin_line2, frag).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Fragment frag = new SiginFragmentMain();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_2");
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    getSupportActionBar().hide();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
