package com.example.haitran.cura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.FileHomeFragment;
import com.example.haitran.cura.fragments.PatientSummaryFragment;
import com.example.haitran.cura.fragments.SiginFragmentMain;

/**
 * Created by kha.phan on 6/29/2016.
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();

            int i = (int)intent.getIntExtra("CAMERA",0);
            if (i==1){
                Fragment frag = new PatientSummaryFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.layout_home, frag).commit();
                Fragment frag1 = new FileHomeFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.add(R.id.layout_home, frag1,"PAGE_FILE_HOME").commit();
            }

        else {
        Fragment frag = new PatientSummaryFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_home, frag).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Fragment frag = new SiginFragmentMain();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if(getSupportActionBar().getTitle().equals("Files")){
                    getSupportActionBar().setTitle("Patient Detail:");
                    getSupportActionBar().setSubtitle("Mr Kha");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_FILE_HOME");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
