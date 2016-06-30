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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.haitran.cura.R;
import com.example.haitran.cura.activities.HomeActivity;
import com.example.haitran.cura.fragments.RegisteredPatientFragment;
import com.example.haitran.cura.models.Patient;

import java.text.SimpleDateFormat;
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
    private Patient patientSelected;
    private int currentPosition;


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
                patientSelected = patient;
                currentPosition = position;

                Calendar cal = Calendar.getInstance();
                TimePickerDialog tpd = new TimePickerDialog(new ContextThemeWrapper(mContext, R.style.TimePicker), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time = hourOfDay + ":" + minute;
                        patientSelected.setTimeArrival(time);
                        setArrivalTime(patientSelected, currentPosition);
                    }
                }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);

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
    }

    public void setArrivalTime(Patient patient, int position) {
        if (mContext instanceof HomeActivity) {
            ((HomeActivity) mContext).setPatientSelected(patient);     //Sent patient to In Queue
            ((HomeActivity) mContext).updateRegisteredPatient(position);
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

        public ImageView img_patient, img_set_arrival_time;

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
            img_set_arrival_time = (ImageView) itemView.findViewById(R.id.img_set_arrival_time_cv);

            txt_arrival_time = (TextView) itemView.findViewById(R.id.txt_arrival_time_cv);
        }
    }
}
