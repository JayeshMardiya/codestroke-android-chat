package com.codestroke.codestrokealert.activity.paramedics;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.codestroke.codestrokealert.R;
import com.codestroke.codestrokealert.SharedPref;
import com.codestroke.codestrokealert.Utility;
import com.codestroke.codestrokealert.model.Cases;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PatientDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_next, btn_unknown_first_name, btn_unknown_surname, btn_unknown_dob, btn_unknown_address, btn_unspecified, btn_unknown_last_well, btn_unknown_nok, btn_unknown_nok_telephone;
    private EditText et_first_name, et_surname, et_address, et_next_of_kin, et_nok_telephone;
    private ToggleButton toggle_btn_gender;
    private ImageButton calenderImage;
    private ConstraintLayout date_and_time;
    private DatePickerDialog datePickerDialog;
    private TextView txtWeekDay, txtMonth,
            txtMonthDay, txtHour, txtMinute, txtAmPm, txtDOB;
    private int mYear, mDayWeek, mMonth, mDay, mHour, mMinute;
    private String unknown = "Unknown", strGender = Gender.m.name(), month, day_m, day_w, hour, minute, am_pm;
    private int age;
    private SimpleDateFormat orginalFormat, targetFormat;
    private Cases cases;
    public enum Gender {m, f, u}
    public enum Status {
        INCOMING("incoming", 0),
        ACTIVE("active", 1),
        COMPLETED("completed", 2);
        private String key;
        private int value;
        private Status(String key, int value) {
            key = key;
            value = value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_patient_details);
        initViews();

        setCurrentTimeAndDate();

        toggle_btn_gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if(!isCheck){
                    strGender = Gender.m.name();
                }
                if(isCheck){
                    strGender = Gender.f.name();
                }
            }
        });

    }

    protected void initViews() {
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_unknown_address = (Button) findViewById(R.id.btn_unknown_address);
        btn_unknown_dob = (Button) findViewById(R.id.btn_unknown_dob);
        btn_unknown_first_name = (Button) findViewById(R.id.btn_unknown_first_name);
        btn_unknown_last_well = (Button) findViewById(R.id.btn_unknown_last_well);
        btn_unknown_surname = (Button) findViewById(R.id.btn_unknown_surname);
        btn_unknown_nok = (Button) findViewById(R.id.btn_unknown_nok);
        btn_unknown_nok_telephone = (Button) findViewById(R.id.btn_unknown_nok_telephone);
        btn_unspecified = (Button) findViewById(R.id.btn_unspecified);
        calenderImage = (ImageButton) findViewById(R.id.calenderImage);
        txtDOB = (TextView) findViewById(R.id.DOB);
        date_and_time = (ConstraintLayout) findViewById(R.id.date_and_time);
        txtWeekDay = (TextView) findViewById(R.id.text_week_day);
        txtMonth = (TextView) findViewById(R.id.text_view_month);
        txtMonthDay = (TextView) findViewById(R.id.text_month_day);
        txtHour = (TextView) findViewById(R.id.text_hour);
        txtMinute = (TextView) findViewById(R.id.text_minute);
        txtAmPm = (TextView) findViewById(R.id.text_pm_am);
        et_surname = (EditText) findViewById(R.id.et_surname);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_next_of_kin = (EditText) findViewById(R.id.et_next_of_kin);
        et_nok_telephone = (EditText) findViewById(R.id.et_nok_telephone);
        toggle_btn_gender = (ToggleButton) findViewById(R.id.toggle_btn_gender);

        orginalFormat = new SimpleDateFormat("dd  MMM  yyyy", Locale.US);
        targetFormat = new SimpleDateFormat("yyyy-MM-dd");

        cases = new Cases();
        btn_next.setOnClickListener(this);
        btn_unknown_address.setOnClickListener(this);
        btn_unknown_dob.setOnClickListener(this);
        btn_unknown_first_name.setOnClickListener(this);
        btn_unknown_surname.setOnClickListener(this);
        btn_unknown_last_well.setOnClickListener(this);
        btn_unknown_nok.setOnClickListener(this);
        btn_unknown_nok.setOnClickListener(this);
        btn_unknown_nok_telephone.setOnClickListener(this);
        toggle_btn_gender.setOnClickListener(this);
        calenderImage.setOnClickListener(this);
        date_and_time.setOnClickListener(this);

    }

    protected void setCurrentTimeAndDate(){
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mDayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        txtWeekDay.setText(new DateFormatSymbols().getShortWeekdays()[mDayWeek]);
        txtMonth.setText(new DateFormatSymbols().getShortMonths()[mMonth]);
        txtMonthDay.setText(String.valueOf(mDay));
        if (mHour > 12) {
            txtAmPm.setText("PM");
            txtHour.setText(String.valueOf(mHour - 12));
        } else if (mHour == 12) {
            txtAmPm.setText("PM");
            txtHour.setText("12");
        } else {
            txtAmPm.setText("AM");
            txtHour.setText(String.valueOf(mHour));
        }
        txtMinute.setText(String.valueOf(mMinute));
    }

    protected void setDateOfBirth(){
        final Calendar dateSelected = Calendar.getInstance();
        final Calendar now = Calendar.getInstance();
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        new DatePickerDialog(PatientDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth);
                if(dateSelected.after(now)){
                    Toast.makeText(getBaseContext(), "Can't be born in the future", Toast.LENGTH_SHORT).show();

                }else {
                    String birthdayDate = dateFormatter.format(dateSelected.getTime());
                    txtDOB.setText(birthdayDate);
                    age = new Utility().getAge(dateSelected);

                }
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();

    }


    protected void showDatePicker() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(PatientDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                mYear = year;
                mMonth = month;
                txtMonth.setText(new DateFormatSymbols().getShortMonths()[month]);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                Date date = new Date(year, month, day - 1);
                txtWeekDay.setText(simpledateformat.format(date));
                txtMonthDay.setText(String.valueOf(day));

                timePicker();

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void timePicker() {

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        if (hourOfDay > 12) {
                            txtAmPm.setText("PM");
                            txtHour.setText(String.valueOf(hourOfDay - 12));
                        } else if (hourOfDay == 12) {
                            txtAmPm.setText("PM");
                            txtHour.setText("12");
                        } else {
                            txtAmPm.setText("AM");
                            txtHour.setText(String.valueOf(hourOfDay));
                        }
                        txtMinute.setText(String.valueOf(minute));

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_next:
                goToNextActivity();
                break;

            case R.id.btn_unknown_address:
                et_address.setText(unknown);
                cases.setAddress(unknown);
                break;


            case R.id.btn_unknown_dob:
                txtDOB.setText("1901-01-01");
                cases.setDob("1901-01-01");
                Calendar calendar = Calendar.getInstance();
                calendar.set(1901,01,01);
                age = new Utility().getAge(calendar);
                break;

            case R.id.btn_unknown_first_name:
                et_first_name.setText(unknown);
                cases.setFirst_name(unknown);
                break;

            case R.id.btn_unknown_last_well:
                break;

            case R.id.btn_unknown_surname:
                et_surname.setText(unknown);
                cases.setLast_name(unknown);
                break;

            case R.id.btn_unknown_nok:
                et_next_of_kin.setText(unknown);
                cases.setNok(unknown);
                break;

            case R.id.btn_unknown_nok_telephone:
                et_nok_telephone.setText(unknown);
                cases.setNok_phone(unknown);
                break;

            case R.id.btn_unspecified:
                strGender = Gender.u.name();
                break;

            case R.id.calenderImage:
                //Show popup datePicker when click on calenderImage and store value in DOB
                setDateOfBirth();
                break;

            case R.id.date_and_time:
                //Set date and time picker
                showDatePicker();
                break;
        }

    }


    protected void goToNextActivity(){

        String address = et_address.getText().toString();
        String first_name = et_first_name.getText().toString();
        String last_name = et_surname.getText().toString();
        String nok = et_next_of_kin.getText().toString();
        String nok_phone = et_nok_telephone.getText().toString();
        String dob = txtDOB.getText().toString();
        String last_well = String.valueOf(mYear) + "-" + String.valueOf(mMonth) + "-" +
                txtMonthDay.getText().toString() + " " + String.valueOf(mHour) + ":" +
                txtMinute.getText().toString();
        String status = Status.INCOMING.name();

        if (!first_name.isEmpty() && !last_name.isEmpty() && !dob.isEmpty()
                && !address.isEmpty() && !last_well.isEmpty() && !nok.isEmpty()
                && !nok_phone.isEmpty() && !status.isEmpty() && !strGender.isEmpty()) {

            cases.setFirst_name(first_name);
            cases.setLast_name(last_name);
            cases.setDob(dob);
            cases.setAddress(address);
            cases.setLast_well(last_well);
            cases.setNok(nok);
            cases.setNok_phone(nok_phone);
            cases.setStatus(status);
            cases.setGender(strGender);
            saveCase(first_name, last_name, strGender, address,last_well, nok, nok_phone, age);
            Intent intent = new Intent(getBaseContext(), DestinationActivity.class);
            intent.putExtra("cases", cases);
            startActivity(intent);

        } else {
            Toast.makeText(getBaseContext(), "Fields are empty !", Toast.LENGTH_SHORT).show();
        }

    }

    protected void saveCase(String first_name, String last_name, String gender,
                            String address, String last_well, String nok, String nok_phone, int age){
        SharedPref.write(SharedPref.FIRST_NAME, first_name);
        SharedPref.write(SharedPref.LAST_NAME, last_name);
        SharedPref.write(SharedPref.GENDER, gender);
        SharedPref.write(SharedPref.ADDRESS, address);
        SharedPref.write(SharedPref.LAST_SEEN, last_well);
        SharedPref.write(SharedPref.NOK, nok);
        SharedPref.write(SharedPref.NOK_PHONE, nok_phone);
        SharedPref.write(SharedPref.AGE, String.valueOf(age));
    }
}
