package com.example.tuanhuynh.timedaypicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by YobboPEL on 28/06/2016.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    TimePickerDialog timePickerDialog;
    private String dayOfWeek;
    private String day;
    private String month;
    private String year;
    private int hourSelected;
    private int minuteSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        timePickerDialog = new TimePickerDialog(new ContextThemeWrapper(getActivity(),R.style.TimePicker),this,hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        //get day month year
        dayOfWeek = getArguments().getString("dayOfWeekSelected");
        day = getArguments().getString("daySelected");
        month = getArguments().getString("monthSelected");
        year = getArguments().getString("yearSelected");

        TextView tvTitle = new TextView(getActivity());
        tvTitle.setTextColor(getResources().getColor(R.color.colorWhite));
        tvTitle.setTextSize(22);
        tvTitle.setText(dayOfWeek + ", " + day + " " + getNameMonth(month) + " " + year);
        tvTitle.setBackgroundColor(getResources().getColor(R.color.colorRed));
        tvTitle.setPadding(5, 3, 5, 3);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        timePickerDialog.setCustomTitle(tvTitle);
        return timePickerDialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
        tv.setText(tv.getText()+ "Hour : " + String.valueOf(hourOfDay)
                + "\nMinute : " + String.valueOf(minute) + "\n");

        hourSelected=hourOfDay;
        minuteSelected=minute;

        Bundle timeData = new Bundle();
        timeData.putInt("hourSelected", hourOfDay);
        timeData.putInt("minuteSelected", minute);
        timeData.putString("dayOfWeekSelected", dayOfWeek);
        timeData.putString("daySelected", String.valueOf(day));
        timeData.putString("monthSelected", String.valueOf(month));
        timeData.putString("yearSelected", String.valueOf(year));

        Intent intent = new Intent(getActivity(),ShowTimeActivity.class);
        intent.putExtras(timeData);
        startActivity(intent);
    }

    String getNameMonth(String s){
        switch (s){
            case "1": return "January";
            case "2": return "February";
            case "3": return "March";
            case "4": return "April";
            case "5": return "May";
            case "6": return "June";
            case "7": return "July";
            case "8": return "August";
            case "9": return "September";
            case "10": return "October";
            case "11": return "November";
            case "12": return "December";
            default: return "Month";
        }
    }
}
