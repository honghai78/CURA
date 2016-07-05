package com.example.haitran.cura.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.cura.R;
import com.example.haitran.cura.views.adapters.RecyclerPatientDetailAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kha.phan on 6/30/2016.
 */
public class PatientSummaryFragment extends Fragment{
    private View mLayout;
    private RecyclerView mRecyclerView;
    private List<String> mList;
    private String subTitle ="";

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient_summary, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle("Patient Detail :");
        appCompatActivity.getSupportActionBar().setSubtitle(subTitle);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mLayout = (View) view.findViewById(R.id.layout_full_screen);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String [] m = {"Profile","Chief Complaint", "Labs", "Files", "Current Medication", "Diabetes & Monitoring", "History",
                "Physical Exam", "Review Systems"};
        mList =new ArrayList<String>();
        for(String detail: m){
            mList.add(detail);
        }

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_patient_detail);
        RecyclerPatientDetailAdapter recyclerPatientDetailAdapter = new RecyclerPatientDetailAdapter(getContext(),(AppCompatActivity) getActivity(),mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(recyclerPatientDetailAdapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("PAGE_PATIENT_DETAIL");
                    if (fragment != null) {
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.transition.sli_re_in, R.transition.sli_re_out);
                        fragmentTransaction.remove(fragment).commit();
                        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
                        appCompatActivity.getSupportActionBar().setTitle("Home");
                        appCompatActivity.getSupportActionBar().setSubtitle(null);
                        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
