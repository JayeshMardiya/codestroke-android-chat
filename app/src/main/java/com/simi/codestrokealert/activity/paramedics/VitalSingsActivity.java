package com.simi.codestrokealert.activity.paramedics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.support.design.widget.TabLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.fragment.EyeFragment;
import com.simi.codestrokealert.fragment.MotorFragment;
import com.simi.codestrokealert.fragment.VerbalFragment;
import com.simi.codestrokealert.model.CaseAssessments;

import java.util.ArrayList;
import java.util.List;


public class VitalSingsActivity  extends AppCompatActivity implements View.OnClickListener,
        EyeFragment.SendData, MotorFragment.SendIndexMotor, VerbalFragment.SendIndexVerbal{

    private ImageButton back_btn;
    private Button next_btn;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText txtBloodPressure, txtHeartRate, txtRespiratoryRate,
            txtOxygenSaturation, txtTemperature, txtBloodGlucose;
    private RadioButton btnRegular, btnIrregular;
    private CaseAssessments caseAssessments;
    private String strBloodPressure, strHeartRate, strRespiratoryRate, stroxygenSaturation,
            strTemperature, strBloodGlucose, strGCS, strHeartRythm;

    private int bloodPressure = -1, heartRate = -1,
            respiratoryRate = -1, oxygenSaturation = -1, temperature = -1,
            bloodGlucose = -1, GCS = -1 ;

    @Override
    public void sendIndex(int index) {
        caseAssessments.setGcs(index);
         switch (index){
             case 1:
                 strGCS = getString(R.string.no_eye_opening_1);
                 break;
             case 2:
                 strGCS = getString(R.string.eye_open_to_pain_2);
                 break;
             case 3:
                 strGCS = getString(R.string.eye_open_to_speech_3);
                 break;
             case 4:
                 strGCS = getString(R.string.eye_open_spontaneously_4);
                 break;
         }
    }

    @Override
    public void sendIdxMotor(int index) {
        caseAssessments.setGcs(index);
        switch (index){
            case 1:
                strGCS = getString(R.string.no_motor_response);
                break;
            case 2:
                strGCS = getString(R.string.extension_to_pain);
                break;
            case 3:
                strGCS = getString(R.string.abnormal_ﬂexion_to_pain);
                break;
            case 4:
                strGCS = getString(R.string.flexion_to_pain);
                break;
            case 5:
                strGCS = getString(R.string.localises_to_pain);
                break;
            case 6:
                strGCS = getString(R.string.obeys_commands);
                break;
        }
    }

    @Override
    public void sendIdxVerbal(int index) {
        caseAssessments.setGcs(index);
        switch (index){
            case 1:
                strGCS = getString(R.string.no_verbal_response);
                break;
            case 2:
                strGCS = getString(R.string.incomprehensible_sounds);
                break;
            case 3:
                strGCS = getString(R.string.inappropriate_words);
                break;
            case 4:
                strGCS = getString(R.string.confused);
                break;
            case 5:
                strGCS = getString(R.string.oriented);
                break;
        }
    }

    private enum heartRhythm{
       regular, irregular
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
        setContentView(R.layout.activity_vital_sings);
        initViews();

        caseAssessments = (CaseAssessments) getIntent().getSerializableExtra("mass");

        //Defines the number of tabs by setting appropriate fragment and tab name
        setupViewPager(viewPager);

        //Assigns the ViewPager to TabLayout
        tabLayout.setupWithViewPager(viewPager);


    }

    protected void initViews(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        back_btn = (ImageButton)findViewById(R.id.arrow_back);
        next_btn = (Button)findViewById(R.id.btn_next);
        txtBloodPressure = (EditText)findViewById(R.id.blood_pressure);
        txtHeartRate = (EditText)findViewById(R.id.heart_rate);
        txtRespiratoryRate = (EditText)findViewById(R.id.respiratory_rate);
        txtOxygenSaturation = (EditText) findViewById(R.id.oxygen_saturation);
        txtTemperature = (EditText)findViewById(R.id.temperature);
        txtTemperature.setInputType(InputType.TYPE_CLASS_NUMBER);
        txtBloodGlucose = (EditText)findViewById(R.id.blood_glucose);
        btnIrregular = (RadioButton)findViewById(R.id.btn_irregular);
        btnRegular = (RadioButton)findViewById(R.id.btn_regular);
        back_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        btnIrregular.setOnClickListener(this);
        btnRegular.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EyeFragment(), "Eye");
        adapter.addFragment(new VerbalFragment(), "Verbal");
        adapter.addFragment(new MotorFragment(), "Motor");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.arrow_back:
               onBackPressed();
               break;

           case R.id.btn_irregular:
               strHeartRythm = heartRhythm.irregular.name();
               caseAssessments.setHeart_rhythm(strHeartRythm);
               btnIrregular.setChecked(true);
               break;

           case R.id.btn_regular:
               strHeartRythm = heartRhythm.regular.name();
               caseAssessments.setHeart_rhythm(strHeartRythm);
               btnRegular.setChecked(true);
               break;

           case R.id.btn_next:
               strBloodPressure = txtBloodPressure.getText().toString();
               strTemperature = txtTemperature.getText().toString();
               stroxygenSaturation = txtOxygenSaturation.getText().toString();
               strRespiratoryRate = txtRespiratoryRate.getText().toString();
               strHeartRate = txtHeartRate.getText().toString();
               strBloodGlucose  = txtBloodGlucose.getText().toString();
               bloodPressure = (strBloodPressure.isEmpty() ? -1 : Integer.parseInt(txtBloodPressure.getText().toString()));
               heartRate = (strHeartRate.isEmpty() ? -1 : Integer.parseInt(txtHeartRate.getText().toString()));
               respiratoryRate = (strRespiratoryRate.isEmpty() ? -1 : Integer.parseInt(txtRespiratoryRate.getText().toString()));
               oxygenSaturation = (stroxygenSaturation.isEmpty() ? -1 : Integer.parseInt(txtOxygenSaturation.getText().toString()));
               temperature = (strTemperature.isEmpty() ? -1 : Integer.parseInt(txtTemperature.getText().toString()));
               bloodGlucose = (strBloodGlucose.isEmpty() ? -1 : Integer.parseInt(txtBloodGlucose.getText().toString()));
               caseAssessments.setBp_diastolic(bloodPressure);
               caseAssessments.setHeart_rate(heartRate);
               caseAssessments.setRr(respiratoryRate);
               caseAssessments.setO2sats(oxygenSaturation);
               caseAssessments.setTemp(temperature);
               caseAssessments.setBlood_glucose(bloodGlucose);
               savedVitalSingsData(strBloodPressure, strHeartRate, strHeartRythm,
                       strRespiratoryRate,stroxygenSaturation, strTemperature,
                       strBloodGlucose, strGCS );

               Intent intent = new Intent(getBaseContext(), RaceScoreActivity.class );
               intent.putExtra("vitalSings", caseAssessments);
               startActivity(intent);
               break;
       }
    }

    protected void savedVitalSingsData(String strBloodPressure, String strHeartRate, String strHeartRythm, String strRespiratoryRate,
                                       String strOxygenSaturation, String strTemperature, String strBloodGlucose, String strGCS){
        SharedPref.write(SharedPref.BLOOD_PRESSURE, strBloodPressure);
        SharedPref.write(SharedPref.HEART_RATE, strHeartRate);
        SharedPref.write(SharedPref.RESPIRATORY_RATE, strRespiratoryRate);
        SharedPref.write(SharedPref.OXYGEN_SATURATION, strOxygenSaturation);
        SharedPref.write(SharedPref.TEMPERATURE, strTemperature);
        SharedPref.write(SharedPref.BLOOD_GLUCOSE, strBloodGlucose);
        SharedPref.write(SharedPref.HEART_RYTHM, strHeartRythm);
        SharedPref.write(SharedPref.GCS, strGCS);


    }

    //Custom adapter class provides fragments required for the view pager
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}