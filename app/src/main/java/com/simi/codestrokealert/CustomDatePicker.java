package com.simi.codestrokealert;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomDatePicker {

    private Context context;
    private EditText editText;
    private ImageButton imageButton;
    private Calendar calendar = Calendar.getInstance();
    private Calendar dateSelected = Calendar.getInstance();
    private DateFormat dateFormat;
    private int mDay;
    private int mMonth;
    private int mYear;
    private int mHour;
    private int mMinute;

    public CustomDatePicker(final Context context, int editTextId, int imageBtnId){
        this.context = context;
        Activity activity = (Activity) context;
        this.editText = (EditText)activity.findViewById(editTextId);
        this.imageButton = (ImageButton)activity.findViewById(imageBtnId);
        final Calendar now = Calendar.getInstance();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch Date Picker Dialog
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        if(dateSelected.after(now)){
                            Toast.makeText(context, "Invalid Date!", Toast.LENGTH_SHORT).show();

                        }
                        mYear = year;
                        mMonth = month;
                        mDay = day;
                        dateSelected.set(mYear, mMonth, mDay);
                        setTime();

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH )).show();
            }
        });
    }

    protected void setTime(){

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;
                        dateSelected.set(mYear, mMonth, mDay, mHour, mMinute);
                        updateView();

                    }
                },calendar.get(Calendar.HOUR_OF_DAY) , calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }



    public void updateView(){
        try {
           dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        editText.setText(sdFormat.format(dateSelected.getTime()));

    }
}
