package com.example.haitran.cura.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.FileFolderFragment;
import com.example.haitran.cura.fragments.FileHomeFragment;
import com.example.haitran.cura.fragments.PatientSummaryFragment;
import com.example.haitran.cura.fragments.SiginFragmentMain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.example.haitran.cura.R;
import com.example.haitran.cura.adapters.HomePagerAdapter;
import com.example.haitran.cura.data.MyData;
import com.example.haitran.cura.fragments.CustomDialogChoiceFolderToSave;
import com.example.haitran.cura.fragments.HomeFragment;
import com.example.haitran.cura.models.Patient;

import java.util.List;

/**
 * Created by kha.phan on 6/29/2016.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

			Fragment frag = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_home, frag, "FR_HOME").commit();

		//Fragment frag = new PatientSummaryFragment();
       // FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      //  fragmentTransaction.add(R.id.layout_home, frag).commit();

        
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Fragment frag = new SiginFragmentMain();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if(getSupportActionBar().getTitle().equals("Files")){
                    getSupportActionBar().setTitle("Patient Detail :");
                    PatientSummaryFragment fragment1 = (PatientSummaryFragment)getSupportFragmentManager().findFragmentByTag("PAGE_PATIENT_DETAIL");
                    getSupportActionBar().setSubtitle(fragment1.getSubTitle());
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_FILE_HOME");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                    }
                }else if(getSupportActionBar().getTitle().equals("Files :")){
                    getSupportActionBar().setTitle("Files");
                    getSupportActionBar().setSubtitle(null);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_FILE_FOLDER");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                    }
                }else if (getSupportActionBar().getTitle().equals("Patient Detail :")){
                    ActionBar ab = getSupportActionBar();
                    ab.setTitle("Home");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);
                    getSupportActionBar().setSubtitle(null);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag("PAGE_PATIENT_DETAIL");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                    }
                }
                break;
			case R.id.menu_search:
                break;
            case R.id.menu_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        for (int i = 0; i < checkBoxList.size(); i++) {
//            if (checkBoxList.get(i).isChecked()) {
//                for (int j = 0; j < checkBoxList.size(); j++) {
//                    if (i != j) {
//                        checkBoxList.get(j).setEnabled(false);
//                    }
//                }
//                break;
//            } else {
//                for (int j = 0; j < checkBoxList.size(); j++) {
//                    if (i != j) {
//                        checkBoxList.get(j).setEnabled(true);
//                    }
//                }
//            }
//        }
//    }
}
