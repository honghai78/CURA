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
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.adapters.InQueueAdapter;
import com.example.haitran.cura.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InQueueFragment extends Fragment {

    private TextView mTxtSelectedDoctor;
    private Spinner mSpinSelectDoctor;

    private RecyclerView recyclerView_In_Queue;
    private InQueueAdapter adapter_In_Queue;

    private List<Patient> patientsInQueueList = new ArrayList<>();

//    private List<Patient> patientList = new ArrayList<Patient>() {{
//        add(new Patient("10000" + 0, "abc 1", 77, "000000000" + 0, 0, "Tom", 1, null));
//        add(new Patient("10000" + 1, "abc 2", 89, "000000000" + 1, 1, "Jerry", 0, null));
//        add(new Patient("10000" + 2, "abc 3", 77, "000000000" + 2, 0, "Jenifer", 2, null));
//        add(new Patient("10000" + 3, "abc 4", 27, "000000000" + 3, 0, "Peter", 1, null));
//        add(new Patient("10000" + 4, "abc 5", 19, "000000000" + 4, 1, "Jerry", 0, null));
//        add(new Patient("10000" + 5, "abc 6", 47, "000000000" + 5, 0, "Tom", 2, null));
//    }};

    private List<Patient> patientsByDoctor = new ArrayList<>();

    public InQueueFragment() {
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

        patientsInQueueList = ((HomeActivity)getActivity()).getPatientListInQueue();

        mSpinSelectDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                patientsByDoctor.clear();
                String nameSelectedDoctor = mSpinSelectDoctor.getSelectedItem().toString();
                if (!nameSelectedDoctor.equals("List Doctors:")) {
                    mTxtSelectedDoctor.setText("Dr. " + nameSelectedDoctor);

                    for (int i = 0; i < patientsInQueueList.size(); i++) {
                        if (patientsInQueueList.get(i).getNameDoctor().equals(nameSelectedDoctor)) {
                            patientsByDoctor.add(patientsInQueueList.get(i));
                        }
                    }
                    adapter_In_Queue.notifyDataSetChanged();
                } else {
                    mTxtSelectedDoctor.setText("Select Doctor");
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
        String[] doctors = new String[]{"List Doctors:", "Tom", "Jerry", "Jenifer", "Peter"};
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
