package com.example.tuanhuynh.timedaypicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YobboPEL on 28/06/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private int year;
    private int month;
    private int day;
    private DatePickerDialog datePickerDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(getActivity(),R.style.TimePicker),this, year,month,day);

        return datePickerDialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getFragmentManager(), "TimePicker");
        super.onDismiss(dialog);

        day = datePickerDialog.getDatePicker().getDayOfMonth();
        month = datePickerDialog.getDatePicker().getMonth()+1;
        year = datePickerDialog.getDatePicker().getYear();

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date(year, month, day);
        String dayOfTheWeek = sdf.format(d);

        Bundle bundle = new Bundle();
        bundle.putString("dayOfWeekSelected",dayOfTheWeek);
        bundle.putString("daySelected",String.valueOf(day));
        bundle.putString("monthSelected", String.valueOf(month));
        bundle.putString("yearSelected", String.valueOf(year));
        timeFragment.setArguments(bundle);
    }

    //onDateSet() callback method
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        tv.setText("Your chosen day is...\n\n");
        tv.setText(tv.getText() + "Day : " + String.valueOf(dayOfMonth)
                + "\nMonth : " + String.valueOf(monthOfYear) + "\nYear : " + String.valueOf(year) + "\n");
    }
}
