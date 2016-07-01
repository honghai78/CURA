package com.example.haitran.cura.activities;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.example.haitran.cura.R;
import com.example.haitran.cura.adapters.HomePagerAdapter;
import com.example.haitran.cura.data.MyData;
import com.example.haitran.cura.fragments.InQueueFragment;
import com.example.haitran.cura.fragments.RegisteredPatientFragment;
import com.example.haitran.cura.models.Patient;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private HomePagerAdapter homePagerAdapter;
    private ViewPager viewPagerHome;
    private PagerSlidingTabStrip pagerTabStripHome;

    private Patient patientSelected;
    private List<Patient> registeredPatientList;
    private List<Patient> patientsInQueueList;
    private MyData data;
    private String[] mTitlesOfTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWidgets();
        data = new MyData();

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Home");
//        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_home_3);
        ab.setDefaultDisplayHomeAsUpEnabled(true);

        //Get data from MyData
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTitlesOfTab = new String[]{"Registered (" + registeredPatientList.size() + ")", "In Queue (" + patientsInQueueList.size() + ")"};
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), mTitlesOfTab);
        viewPagerHome.setAdapter(homePagerAdapter);
        viewPagerHome.setPageTransformer(true, new RotateUpTransformer());

        pagerTabStripHome.setAllCaps(false);
        pagerTabStripHome.setViewPager(viewPagerHome);


    }

    public void reload() {
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTitlesOfTab = new String[]{"Registered\n (" + registeredPatientList.size() + ")", "In Queue\n (" + patientsInQueueList.size() + ")"};
//        homePagerAdapter.setTitles(mTitlesOfTab);
//        homePagerAdapter.notifyDataSetChanged();
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), mTitlesOfTab);
        viewPagerHome.setAdapter(homePagerAdapter);
        viewPagerHome.setPageTransformer(true, new RotateUpTransformer());

        pagerTabStripHome.setAllCaps(false);
        pagerTabStripHome.setViewPager(viewPagerHome);
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
                Toast.makeText(getBaseContext(), "About clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getWidgets() {
        viewPagerHome = (ViewPager) findViewById(R.id.view_pager_home);
        pagerTabStripHome = (PagerSlidingTabStrip) findViewById(R.id.pager_tab_strip_home);
    }

    public void setPatientSelected(Patient patientSelected) {
        this.patientSelected = patientSelected;
    }

    public Patient getPatientSelected() {
        return patientSelected;
    }

    public void updateRegisteredPatient(int num) {
        addPatientInQueue();
        removeRegisteredPatient(num);

        reload();
    }

    public void addPatientInQueue() {
        data.addPatientInQueue(getPatientSelected());
    }

    public void removeRegisteredPatient(int position) {
        data.removeRegisteredPatient(position);
    }

    public List<Patient> getPatientsInQueueList() {
        return data.sortPatientByTimeInQueue(data.getPatientListInQueue());
    }

    public List<Patient> getRegisteredPatientList() {
        return registeredPatientList;
    }
}
