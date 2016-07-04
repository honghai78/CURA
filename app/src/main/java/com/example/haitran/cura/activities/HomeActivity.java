package com.example.haitran.cura.activities;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.FileFolderFragment;
import com.example.haitran.cura.fragments.FileHomeFragment;
import com.example.haitran.cura.fragments.PatientSummaryFragment;
import com.example.haitran.cura.fragments.SiginFragmentMain;

/**
 * Created by kha.phan on 6/29/2016.
 */
public class HomeActivity extends AppCompatActivity {
=======
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

public class HomeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

            int i = intent.getIntExtra("CAMERA",0);
            if (i==1){
                Fragment frag = new PatientSummaryFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.layout_home, frag).commit();
                Fragment frag1 = new FileHomeFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.add(R.id.layout_home, frag1,"PAGE_FILE_HOME").commit();
            }else if (i==2){
                Fragment frag = new PatientSummaryFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.layout_home, frag).commit();
                Fragment frag1 = new FileHomeFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.add(R.id.layout_home, frag1,"PAGE_FILE_HOME").commit();
                Fragment frag2 = new FileFolderFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.add(R.id.layout_home, frag2,"PAGE_FILE_FOLDER").commit();
            }

        else {
			Fragment frag = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_home, frag, "FR_HOME").commit();
        }
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
                }
                break;
			case R.id.menu_search:
                Toast.makeText(getBaseContext(), "Search clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                byte[] bytes = new byte[0];
                CustomDialogChoiceFolderToSave dialog = new CustomDialogChoiceFolderToSave(this, bytes);
                dialog.onCreateDialog();
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
