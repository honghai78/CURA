package com.example.haitran.cura.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.haitran.cura.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by YobboPEL on 28/06/2016.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialog timePickerDialog;
    private String dayOfWeek;

    private int hourSelected;
    private int minuteSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current time as the default values for the time picker
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        timePickerDialog = new TimePickerDialog(new ContextThemeWrapper(getActivity(), R.style.TimePicker), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        //get day month year
        dayOfWeek = convertDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK) - 1);
        String date = new SimpleDateFormat("MMMM dd yyyy").format(calendar.getTime());

        TextView tvTitle = new TextView(getActivity());
        tvTitle.setTextColor(getResources().getColor(R.color.colorWhite));
        tvTitle.setTextSize(22);
        tvTitle.setText(dayOfWeek + ", " + date);
        tvTitle.setBackgroundColor(getResources().getColor(R.color.colorRed));
        tvTitle.setPadding(5, 3, 5, 3);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        timePickerDialog.setCustomTitle(tvTitle);
        return timePickerDialog;
    }

    public String convertDayOfWeek(int day) {
        String dayStr;
        switch (day) {
            case 1:
                dayStr = "Sunday";
                break;
            case 2:
                dayStr = "Monday";
                break;
            case 3:
                dayStr = "Tuesday";
                break;
            case 4:
                dayStr = "Wednesday";
                break;
            case 5:
                dayStr = "Thursday";
                break;
            case 6:
                dayStr = "Friday";
                break;
            default:
                dayStr = "Saturday";
                break;
        }
        return dayStr;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hourSelected = hourOfDay;
        minuteSelected = minute;

        Toast.makeText(getActivity(), "Time selected: " + hourSelected + ":" + minuteSelected, Toast.LENGTH_SHORT).show();
    }
}
