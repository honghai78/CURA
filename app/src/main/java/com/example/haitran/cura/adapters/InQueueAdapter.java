package com.example.haitran.cura.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.models.Patient;

import java.util.List;

/**
 * Created by hanh.tran on 6/30/2016.
 */
public class InQueueAdapter extends RecyclerView.Adapter<InQueueAdapter.MyViewHolder> {

    private Activity mActivity;
    private List<Patient> patientList;

    public InQueueAdapter(Activity mActivity, List<Patient> patientList) {
        this.mActivity = mActivity;
        this.patientList = patientList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity)
                .inflate(R.layout.card_view_in_queue, parent, false);
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
        holder.txt_count_visit.setText(patient.getNumOfExam() + "");
        holder.txt_age_patient.setText(patient.getAge() + "");
        holder.txt_arrival_time.setText(patient.getTimeArrival());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_id_patient, txt_name_patient, txt_age_patient, txt_gender_patient,
                txt_code_patient, txt_count_visit, txt_arrival_time;

        public ImageView img_patient;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_id_patient = (TextView) itemView.findViewById(R.id.txt_id_patient_cv_iq);
            txt_name_patient = (TextView) itemView.findViewById(R.id.txt_name_patient_cv_iq);
            txt_age_patient = (TextView) itemView.findViewById(R.id.txt_age_patient_cv_iq);
            txt_gender_patient = (TextView) itemView.findViewById(R.id.txt_gender_patient_cv_iq);
            txt_code_patient = (TextView) itemView.findViewById(R.id.txt_code_patient_cv_iq);
            txt_count_visit = (TextView) itemView.findViewById(R.id.txt_count_visit_cv_iq);
            img_patient =(ImageView) itemView.findViewById(R.id.img_patient_cv_iq);
            txt_arrival_time = (TextView) itemView.findViewById(R.id.txt_arrival_time_cv_iq);
        }
    }
}
