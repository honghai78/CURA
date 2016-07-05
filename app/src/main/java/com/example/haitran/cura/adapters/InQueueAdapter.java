package com.example.haitran.cura.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.cura.R;
import com.example.haitran.cura.fragments.PatientSummaryFragment;
import com.example.haitran.cura.models.Patient;

import java.util.List;

/**
 * Created by hanh.tran on 6/30/2016.
 */
public class InQueueAdapter extends RecyclerView.Adapter<InQueueAdapter.MyViewHolder> {

    private Activity mActivity;
    private AppCompatActivity mAppCompatActivity;
    private List<Patient> patientList;

    public InQueueAdapter(Activity mActivity, List<Patient> patientList, AppCompatActivity mAppCompatActivity) {
        this.mActivity = mActivity;
        this.patientList = patientList;
        this.mAppCompatActivity = mAppCompatActivity;
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

        int[] times = separateTime(patient.getTimeArrival());
        if (times[0] >= 12) {
            holder.txt_arrival_time.setText(patient.getTimeArrival() + " pm");
        } else {
            holder.txt_arrival_time.setText(patient.getTimeArrival() + " am");
        }

        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        holder.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_id_patient, txt_name_patient, txt_age_patient, txt_gender_patient,
                txt_code_patient, txt_count_visit, txt_arrival_time;

        public ImageView img_patient;
        public View mView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txt_id_patient = (TextView) itemView.findViewById(R.id.txt_id_patient_cv_iq);
            txt_name_patient = (TextView) itemView.findViewById(R.id.txt_name_patient_cv_iq);
            txt_age_patient = (TextView) itemView.findViewById(R.id.txt_age_patient_cv_iq);
            txt_gender_patient = (TextView) itemView.findViewById(R.id.txt_gender_patient_cv_iq);
            txt_code_patient = (TextView) itemView.findViewById(R.id.txt_code_patient_cv_iq);
            txt_count_visit = (TextView) itemView.findViewById(R.id.txt_count_visit_cv_iq);
            img_patient = (ImageView) itemView.findViewById(R.id.img_patient_cv_iq);
            txt_arrival_time = (TextView) itemView.findViewById(R.id.txt_arrival_time_cv_iq);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PatientSummaryFragment frag = new PatientSummaryFragment();
                    frag.setSubTitle(txt_name_patient.getText().toString());
                     FragmentTransaction fragmentTransaction =mAppCompatActivity.getSupportFragmentManager().beginTransaction();
                      fragmentTransaction.add(R.id.layout_home, frag,"PAGE_PATIENT_DETAIL").commit();
                }
            });
        }

        public void clearAnimation()
        {
            mView.clearAnimation();
        }
    }

    public int[] separateTime(String time) {
        String[] values = time.split(":");
        return new int[]{Integer.parseInt(values[0]), Integer.parseInt(values[1])};
    }
}
