package com.example.haitran.cura.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient_summary, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
