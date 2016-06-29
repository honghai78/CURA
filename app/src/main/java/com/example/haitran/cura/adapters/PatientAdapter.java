package com.example.haitran.cura.adapters;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.DatePickerFragment;
import com.example.haitran.cura.fragments.TimePickerFragment;
import com.example.haitran.cura.models.Patient;

import java.util.List;

/**
 * Created by hanh.tran on 6/28/2016.
 */
public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {
    private Activity mContext;
    private List<Patient> patientList;


    public PatientAdapter(Activity mContext, List<Patient> patientList) {
        this.mContext = mContext;
        this.patientList = patientList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_patient, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Patient patient = patientList.get(position);

        if (patient.getGender() == 0) {
            holder.txt_name_patient.setText("Mr. " + patient.getName());
            holder.txt_gender_patient.setText(" yrs, Male");
        } else {
            holder.txt_name_patient.setText("Ms. " + patient.getName());
            holder.txt_gender_patient.setText(" yrs, Female");
        }
        holder.txt_id_patient.setText(patient.getId());
        holder.txt_code_patient.setText(patient.getCode());
        holder.txt_name_doctor.setText(patient.getNameDoctor());
        holder.txt_count_visit.setText(patient.getNumOfExam() + "");
        holder.txt_age_patient.setText(patient.getAge() + "");

        holder.img_arrival_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "Set time!", Toast.LENGTH_SHORT).show();
                DialogFragment timeFragment = new TimePickerFragment();
                timeFragment.show(mContext.getFragmentManager(), "TimePicker");

//                dayFragment.onDateSet());
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_id_patient, txt_name_patient, txt_age_patient, txt_gender_patient,
                txt_code_patient, txt_name_doctor, txt_count_visit;

        public ImageView img_patient, img_arrival_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_id_patient = (TextView) itemView.findViewById(R.id.txt_id_patient_cv);
            txt_name_patient = (TextView) itemView.findViewById(R.id.txt_name_patient_cv);
            txt_age_patient = (TextView) itemView.findViewById(R.id.txt_age_patient_cv);
            txt_gender_patient = (TextView) itemView.findViewById(R.id.txt_gender_patient_cv);
            txt_code_patient = (TextView) itemView.findViewById(R.id.txt_code_patient_cv);
            txt_name_doctor = (TextView) itemView.findViewById(R.id.txt_name_doctor_cv);
            txt_count_visit = (TextView) itemView.findViewById(R.id.txt_count_visit_cv);

            img_patient = (ImageView) itemView.findViewById(R.id.img_patient_cv);
            img_arrival_time = (ImageView) itemView.findViewById(R.id.img_set_arrival_time_cv);
        }
    }
}
