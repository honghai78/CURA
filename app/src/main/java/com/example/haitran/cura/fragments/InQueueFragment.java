package com.example.haitran.cura.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.adapters.InQueueAdapter;
import com.example.haitran.cura.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InQueueFragment extends Fragment {

    private String mTitleFragment;
    private int page;

    private TextView mTxtSelectedDoctor;
    private Spinner mSpinSelectDoctor;
    private RecyclerView recyclerView_In_Queue;
    private InQueueAdapter adapter_In_Queue;
    private List<Patient> patientsInQueueList = new ArrayList<>();
    private List<Patient> patientsByDoctor = new ArrayList<>();

    public InQueueFragment() {
    }

    public static InQueueFragment newInstance(int page, String mTitleFragment) {
        InQueueFragment frg = new InQueueFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("someInt", page);
        bundle.putString("someTitle", mTitleFragment);
        frg.setArguments(bundle);
        return frg;
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
        return inflater.inflate(R.layout.fragment_in_queue, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget(view);

        HomeFragment fragment = (HomeFragment) (getActivity()).getSupportFragmentManager().findFragmentByTag("FR_HOME");
        if (fragment != null)
            patientsInQueueList = fragment.getPatientsInQueueList();

        mSpinSelectDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                patientsByDoctor.clear();
                String nameSelectedDoctor = mSpinSelectDoctor.getSelectedItem().toString();
                if (!nameSelectedDoctor.equals("All")) {

                    for (int i = 0; i < patientsInQueueList.size(); i++) {
                        if (patientsInQueueList.get(i).getNameDoctor().equals(nameSelectedDoctor)) {
                            patientsByDoctor.add(patientsInQueueList.get(i));
                        }
                    }
                    adapter_In_Queue.notifyDataSetChanged();
                    mTxtSelectedDoctor.setText("Dr. " + nameSelectedDoctor + " (" + patientsByDoctor.size() + ")");
                } else {
                    for (int i = 0; i < patientsInQueueList.size(); i++) {
                        patientsByDoctor.add(patientsInQueueList.get(i));
                    }
                    adapter_In_Queue.notifyDataSetChanged();
                    mTxtSelectedDoctor.setText(nameSelectedDoctor + " (" + patientsByDoctor.size() + ")");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getWidget(View view) {
        mTxtSelectedDoctor = (TextView) view.findViewById(R.id.txt_selected_doctor_frg_queue);
        mSpinSelectDoctor = (Spinner) view.findViewById(R.id.spin_select_doctor_frg_queue);
        String[] doctors = new String[]{"All", "Tom", "Jerry", "Jenifer", "Peter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, doctors);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinSelectDoctor.setAdapter(adapter);

        recyclerView_In_Queue = (RecyclerView) view.findViewById(R.id.recycler_view_frg_in_queue);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_In_Queue.setLayoutManager(mLayoutManager);
        adapter_In_Queue = new InQueueAdapter(getActivity(), patientsByDoctor);
        recyclerView_In_Queue.setAdapter(adapter_In_Queue);
    }

}
