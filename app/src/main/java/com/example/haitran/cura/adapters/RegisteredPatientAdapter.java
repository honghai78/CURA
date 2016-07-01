package com.example.haitran.cura.adapters;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.fragments.RegisteredPatientFragment;
import com.example.haitran.cura.models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hanh.tran on 6/28/2016.
 */
public class RegisteredPatientAdapter extends RecyclerView.Adapter<RegisteredPatientAdapter.MyViewHolder> {
    private Activity mContext;
    private List<Patient> patientList;
    private RegisteredPatientFragment registeredFragment;
    private static String time = "";

    public RegisteredPatientAdapter(Activity mContext, List<Patient> patientList, RegisteredPatientFragment registeredFragment) {
        this.mContext = mContext;
        this.patientList = patientList;
        this.registeredFragment = registeredFragment;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_registered_patient, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Patient patient = patientList.get(position);

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

        holder.img_set_arrival_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                final TimePickerDialog tpd = new TimePickerDialog(new ContextThemeWrapper(mContext, R.style.TimePicker), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= cal.get(Calendar.HOUR_OF_DAY) && minute >= cal.get(Calendar.MINUTE)) {
                            if (hourOfDay < 10 && minute < 10) {
                                time = "0" + hourOfDay + ":0" + minute;
                            } else if (hourOfDay < 10) {
                                time = "0" + hourOfDay + ":" + minute;
                            } else if (minute < 10) {
                                time = hourOfDay + ":0" + minute;
                            } else {
                                time = hourOfDay + ":" + minute;
                            }

                            List<Patient> patients = new ArrayList<>();
                            if (mContext instanceof HomeActivity) {
                                patients = ((HomeActivity) mContext).getPatientsInQueueList();
                            }
                            boolean check = false;
                            for (int i = 0; i < patients.size(); i++) {
                                Patient pat = patients.get(i);
                                if (pat.getName().equals(pat.getName()) && pat.getTimeArrival().equals(time)) {
                                    check = true;
                                    break;
                                }
                            }
                            if (check) {
                                Toast.makeText(mContext, "Chose time was duplicate", Toast.LENGTH_SHORT).show();
                            } else {
                                patient.setTimeArrival(time);
                                setArrivalTime(patient, position);
                            }

                        } else {
                            Toast.makeText(mContext, "You must chose time larger or equal current time", Toast.LENGTH_LONG).show();
                        }
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

                TextView txt_title = new TextView(mContext);
                txt_title.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                txt_title.setTextSize(22);
                txt_title.setText(formatDate(cal.getTime()));
                txt_title.setBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
                txt_title.setPadding(5, 3, 5, 3);
                txt_title.setGravity(Gravity.CENTER_HORIZONTAL);
                tpd.setCustomTitle(txt_title);
                tpd.show();
            }
        });

        holder.txt_arrival_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.img_set_arrival_time.performClick();
            }
        });

        setFadeAnimation(holder.itemView);
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
//        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    //Set animation for view
    private void setFadeAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    public void setArrivalTime(Patient patient, int position) {
        if (mContext instanceof HomeActivity) {
            ((HomeActivity) mContext).setPatientSelected(patient);     //Sent patient to In Queue
            ((HomeActivity) mContext).updateRegisteredPatient(position);
            registeredFragment.reload_list();
        }
    }

    public String formatDate(Date date) {
        String myDate = new SimpleDateFormat("EEEE").format(date) + ", " + new SimpleDateFormat("MMMM dd yyyy").format(date);
        return myDate;
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_id_patient, txt_name_patient, txt_age_patient, txt_gender_patient,
                txt_code_patient, txt_name_doctor, txt_count_visit, txt_arrival_time;
        public View mView;
        public ImageView img_patient, img_set_arrival_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txt_id_patient = (TextView) itemView.findViewById(R.id.txt_id_patient_cv);
            txt_name_patient = (TextView) itemView.findViewById(R.id.txt_name_patient_cv);
            txt_age_patient = (TextView) itemView.findViewById(R.id.txt_age_patient_cv);
            txt_gender_patient = (TextView) itemView.findViewById(R.id.txt_gender_patient_cv);
            txt_code_patient = (TextView) itemView.findViewById(R.id.txt_code_patient_cv);
            txt_name_doctor = (TextView) itemView.findViewById(R.id.txt_name_doctor_cv);
            txt_count_visit = (TextView) itemView.findViewById(R.id.txt_count_visit_cv);

            img_patient = (ImageView) itemView.findViewById(R.id.img_patient_cv);
            img_set_arrival_time = (ImageView) itemView.findViewById(R.id.img_set_arrival_time_cv);

            txt_arrival_time = (TextView) itemView.findViewById(R.id.txt_arrival_time_cv);
        }

        public void clearAnimation()
        {
            mView.clearAnimation();
        }
    }
}
