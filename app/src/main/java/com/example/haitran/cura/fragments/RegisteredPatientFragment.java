package com.example.haitran.cura.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.adapters.PatientAdapter;
import com.example.haitran.cura.models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisteredPatientFragment extends Fragment {

    private TextView mTxtDateCurrent, mTxtCountPatient;
    private RecyclerView recycler_patient;

    private PatientAdapter adapter;
    private List<Patient> patients = new ArrayList<Patient>(){{
        add(new Patient("10000" + 0, "abc 1", 77, "000000000" + 0, 0, "xyz 1", 1));
        add(new Patient("10000" + 1, "abc 2", 89, "000000000" + 1, 1, "xyz 2", 0));
        add(new Patient("10000" + 2, "abc 3", 77, "000000000" + 2, 0, "xyz 3", 2));
        add(new Patient("10000" + 3, "abc 4", 27, "000000000" + 3, 0, "xyz 4", 1));
        add(new Patient("10000" + 4, "abc 5", 19, "000000000" + 4, 1, "xyz 5", 0));
        add(new Patient("10000" + 5, "abc 6", 47, "000000000" + 5, 0, "xyz 6", 2));
    }};

    private int countRegisteredPatient;

    public RegisteredPatientFragment( ) {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registered_patient, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidgets(view);

        Calendar cal = Calendar.getInstance();
        mTxtDateCurrent.setText(new SimpleDateFormat("dd MMMM yyyy").format(cal.getTime()));

        setUpData();
        countRegisteredPatient = patients.size();
        mTxtCountPatient.setText(countRegisteredPatient + "");
    }

    public void getWidgets(View view) {
        mTxtDateCurrent = (TextView) view.findViewById(R.id.txt_today_frg_registered);
        mTxtCountPatient = (TextView) view.findViewById(R.id.txt_count_patient_frg_registered);
        recycler_patient = (RecyclerView) view.findViewById(R.id.recycler_view_frg_registered);
    }

    public void setUpData() {
//        patients.add(new Patient("10000" + 0, "abc 1", 77, "000000000" + 0, 0, "xyz 1", 1));
//        patients.add(new Patient("10000" + 1, "abc 2", 89, "000000000" + 1, 1, "xyz 2", 0));
//        patients.add(new Patient("10000" + 2, "abc 3", 77, "000000000" + 2, 0, "xyz 3", 2));
//        patients.add(new Patient("10000" + 3, "abc 4", 27, "000000000" + 3, 0, "xyz 4", 1));
//        patients.add(new Patient("10000" + 4, "abc 5", 19, "000000000" + 4, 1, "xyz 5", 0));
//        patients.add(new Patient("10000" + 5, "abc 6", 47, "000000000" + 5, 0, "xyz 6", 2));

        adapter = new PatientAdapter(getActivity(), patients);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_patient.setLayoutManager(mLayoutManager);
        recycler_patient.setAdapter(adapter);
    }

    public int countRegisteredPatient() {
        return patients.size();
    }
}
