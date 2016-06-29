package com.example.haitran.cura.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.haitran.cura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InQueueFragment extends Fragment {

    private TextView mTxtSelectedDoctor;
    private Spinner  mSpinSelectDoctor;

    public InQueueFragment() {}


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

        String[] doctors = new String[]{"Tom", "Jerry", "Jenifer", "Peter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                 android.R.layout.simple_spinner_item, doctors);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinSelectDoctor.setAdapter(adapter);

        mSpinSelectDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTxtSelectedDoctor.setText(mSpinSelectDoctor.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getWidget(View view){
        mTxtSelectedDoctor = (TextView) view.findViewById(R.id.txt_selected_doctor_frg_queue);
        mSpinSelectDoctor = (Spinner)view.findViewById(R.id.spin_select_doctor_frg_queue);
    }

}
