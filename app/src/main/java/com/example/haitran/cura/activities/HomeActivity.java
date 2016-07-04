package com.example.haitran.cura.activities;

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

        Fragment frag = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_home, frag, "FR_HOME").commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                Toast.makeText(getBaseContext(), "Search clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                byte[] bytes = new byte[0];
                CustomDialogChoiceFolderToSave dialog = new CustomDialogChoiceFolderToSave(this, bytes);
                dialog.onCreateDialog();
                return true;
        }
        return true;
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
