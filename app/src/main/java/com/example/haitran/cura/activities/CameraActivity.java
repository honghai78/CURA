package com.example.haitran.cura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.haitran.cura.fragments.Camera2BasicFragment;
import com.example.haitran.cura.R;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_main, Camera2BasicFragment.newInstance(), "B")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                 getView().setFocusableInTouchMode(true);
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_PRE");
                android.app.Fragment fragment1 = getFragmentManager().findFragmentByTag("B");
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                    fragmentTransaction.remove(fragment).commit();
                    getSupportActionBar().hide();
                }
                if (fragment1 != null)
                    fragment1.onResume();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_PRE");
        android.app.Fragment fragment1 = getFragmentManager().findFragmentByTag("B");
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
            fragmentTransaction.remove(fragment).commit();
            getSupportActionBar().hide();
            if (fragment1 != null)
                fragment1.onResume();
        }
        else
        finish();

    }
}
