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
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.adapters.RegisteredPatientAdapter;
import com.example.haitran.cura.models.Patient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisteredPatientFragment extends Fragment {

    private String mTitleFragment;
    private int page;

    private TextView mTxtDateCurrent, mTxtCountPatient;
    private RecyclerView recycler_patient;
    private RegisteredPatientAdapter adapter;
    private List<Patient> registeredPatientList;

    private int countRegisteredPatient;

    public static RegisteredPatientFragment newInstance(int page, String mTitleFragment) {
        RegisteredPatientFragment frg = new RegisteredPatientFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("someInt", page);
        bundle.putString("someTitle", mTitleFragment);
        frg.setArguments(bundle);
        return frg;
    }

    public RegisteredPatientFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        mTitleFragment = getArguments().getString("someTitle");
    }

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

        setUpRecyclerView();
        countRegisteredPatient = registeredPatientList.size();
        mTxtCountPatient.setText(countRegisteredPatient + "");
    }

    public void getWidgets(View view) {
        mTxtDateCurrent = (TextView) view.findViewById(R.id.txt_today_frg_registered);
        mTxtCountPatient = (TextView) view.findViewById(R.id.txt_count_patient_frg_registered);
        recycler_patient = (RecyclerView) view.findViewById(R.id.recycler_view_frg_registered);
    }

    public void setUpRecyclerView() {
        //Get all registered patient
        registeredPatientList = ((HomeActivity) getActivity()).getRegisteredPatientList();

        adapter = new RegisteredPatientAdapter(getActivity(), registeredPatientList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_patient.setLayoutManager(mLayoutManager);
        recycler_patient.setAdapter(adapter);
    }

    public void reload_list() {
        registeredPatientList = ((HomeActivity)getActivity()).getRegisteredPatientList();
        adapter.notifyDataSetChanged();
        mTxtCountPatient.setText(registeredPatientList.size() + "");
    }
}
