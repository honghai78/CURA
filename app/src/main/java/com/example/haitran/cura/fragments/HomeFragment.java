package com.example.haitran.cura.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.adapters.HomePagerAdapter;
import com.example.haitran.cura.data.MyData;
import com.example.haitran.cura.models.Patient;

import java.util.List;

/**
 * Created by hai.tran on 7/4/2016.
 */
public class HomeFragment extends Fragment {

    private HomePagerAdapter homePagerAdapter;
    private ViewPager viewPagerHome;
    private PagerSlidingTabStrip pagerTabStripHome;

    private Patient patientSelected;
    private List<Patient> registeredPatientList;
    private List<Patient> patientsInQueueList;
    private MyData data;
    private String[] mTitlesOfTab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        data = new MyData();

        ActionBar ab = ((HomeActivity)getActivity()).getSupportActionBar();
        ab.setTitle("Home");
//        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_home_3);
        ab.setDefaultDisplayHomeAsUpEnabled(true);

        viewPagerHome = (ViewPager) view.findViewById(R.id.view_pager_home);
        pagerTabStripHome = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tab_strip_home);
        //Get data from MyData
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTitlesOfTab = new String[]{"Registered (" + registeredPatientList.size() + ")", "In Queue (" + patientsInQueueList.size() + ")"};
        homePagerAdapter = new HomePagerAdapter(((HomeActivity)getActivity()).getSupportFragmentManager(), mTitlesOfTab);
        viewPagerHome.setAdapter(homePagerAdapter);
        viewPagerHome.setPageTransformer(true, new RotateUpTransformer());

        pagerTabStripHome.setAllCaps(false);
        pagerTabStripHome.setViewPager(viewPagerHome);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void reload() {
        registeredPatientList = data.getRegisteredPatientList();
        patientsInQueueList = data.sortPatientByTimeInQueue(data.getPatientListInQueue());

        mTitlesOfTab = new String[]{"Registered\n (" + registeredPatientList.size() + ")", "In Queue\n (" + patientsInQueueList.size() + ")"};
        homePagerAdapter = new HomePagerAdapter(((HomeActivity)getActivity()).getSupportFragmentManager(), mTitlesOfTab);
        viewPagerHome.setAdapter(homePagerAdapter);
        viewPagerHome.setPageTransformer(true, new RotateUpTransformer());

        pagerTabStripHome.setAllCaps(false);
        pagerTabStripHome.setViewPager(viewPagerHome);
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
