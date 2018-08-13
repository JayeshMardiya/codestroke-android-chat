package com.simi.codestrokealert.activity.paramedics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.adapter.CustomAdapter;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.model.CaseAssessments;


public class RaceScoreActivity extends AppCompatActivity {

    private CaseAssessments caseAssessments;
    private ImageButton back_btn;
    private Button next_btn;
    private String[] facial_palsy = {"Absent", "Mild", "Mod-Severe"};
    private String[] arm_motor_impairment = {"Normal-Mild", "Mod", "Severe"};
    private String[] leg_motor_impairment = {"Normal-Mild", "Mod", "Severe"};
    private String[] head_and_gaze_deviation = {"Absent", "Present"};
    private ListView facial_palsy_list, arm_motor_impairment_list, leg_motor_impairment_list, deviation_list;
    private String strFacialPalsy, strArmMotorImp, strLegMotorImp, strDeviation;
    private byte indexFacialPalsy = (byte)255, indexArmMotorImp = (byte) 255,
            indexLegMotorImp = (byte) 255, indexDeviation = (byte) 255;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_race_score);
        caseAssessments = (CaseAssessments) getIntent().getSerializableExtra("vitalSings");
        initViews();


        //Go to previous activity
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ClinicalAssessmentActivity.class );
                savedRaceScoreData(strFacialPalsy,  strArmMotorImp, strLegMotorImp, strDeviation);
                caseAssessments.setFacial_palsy_race(indexFacialPalsy);
                caseAssessments.setArm_motor_impair(indexArmMotorImp);
                caseAssessments.setLeg_motor_impair(indexLegMotorImp);
                caseAssessments.setHead_gaze_deviate(indexDeviation);
                intent.putExtra("raceScore", caseAssessments);
                startActivity(intent);
            }
        });

        // set the adapter to fill the data in ListView
        final CustomAdapter facialPalsyAdapter = new CustomAdapter(getApplicationContext(), facial_palsy);
        facial_palsy_list.setAdapter(facialPalsyAdapter);
        facial_palsy_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        facial_palsy_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                facialPalsyAdapter.setSelectedIndex(position);
                facialPalsyAdapter.notifyDataSetChanged();
                strFacialPalsy = facial_palsy[position];
                indexFacialPalsy = (byte)position;

            }
        });

        final CustomAdapter armMotorImpairmentAdapter = new CustomAdapter(getApplicationContext(), arm_motor_impairment);
        arm_motor_impairment_list.setAdapter(armMotorImpairmentAdapter);

        arm_motor_impairment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                armMotorImpairmentAdapter.setSelectedIndex(position);
                armMotorImpairmentAdapter.notifyDataSetChanged();
                strArmMotorImp = arm_motor_impairment[position];
                indexArmMotorImp = (byte)position;

            }
        });

        final CustomAdapter legMotorImpairmentAdapter = new CustomAdapter(getApplicationContext(), leg_motor_impairment);
        leg_motor_impairment_list.setAdapter(legMotorImpairmentAdapter);

        leg_motor_impairment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                legMotorImpairmentAdapter.setSelectedIndex(position);
                legMotorImpairmentAdapter.notifyDataSetChanged();
                strLegMotorImp = leg_motor_impairment[position];
                indexLegMotorImp = (byte)position;

            }
        });

        final CustomAdapter deviationAdapter = new CustomAdapter(getApplicationContext(), head_and_gaze_deviation);
        deviation_list.setAdapter(deviationAdapter);

        deviation_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                deviationAdapter.setSelectedIndex(position);
                deviationAdapter.notifyDataSetChanged();
                strDeviation = head_and_gaze_deviation[position];
                indexDeviation = (byte)position;

            }
        });
    }

    protected void initViews(){
        facial_palsy_list = (ListView) findViewById(R.id.facial_palsy_list);
        arm_motor_impairment_list = (ListView)findViewById(R.id.arm_motor_impairment_list);
        leg_motor_impairment_list = (ListView)findViewById(R.id.leg_motor_impairment_list);
        deviation_list = (ListView)findViewById(R.id.deviation_list);
        back_btn = (ImageButton)findViewById(R.id.arrow_back);
        next_btn = (Button)findViewById(R.id.btn_next);
    }

    protected void savedRaceScoreData(String strFacialPalsy, String strArmMotorImp,
                                      String strLegMotorImp, String strDeviation){
        SharedPref.write(SharedPref.FACIAL_PALSY, strFacialPalsy);
        SharedPref.write(SharedPref.ARM_MOTOR_IMPAIRMENT, strArmMotorImp);
        SharedPref.write(SharedPref.LEG_MOTOR_IMPAIRMENT, strLegMotorImp);
        SharedPref.write(SharedPref.HEART_RATE, strDeviation);
    }
}
