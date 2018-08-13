package com.simi.codestrokealert.activity.clinicians;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.chat.ChatActivity;
import com.simi.codestrokealert.fragment.ClinicalHistoryFragment;
import com.simi.codestrokealert.fragment.ClinicialAssessmentFragment;
import com.simi.codestrokealert.fragment.EDFragment;
import com.simi.codestrokealert.fragment.ManagementFragment;
import com.simi.codestrokealert.fragment.PatientDetailsFragment;
import com.simi.codestrokealert.fragment.RadiologyFragment;
import com.simi.codestrokealert.model.Patient;


public class CliniciansPatientDetails extends AppCompatActivity {

    private Button ed_btn, patient_details_btn,
            clinical_history_btn, clinical_assessment_btn,
            radiology_btn, management_btn;
    private TextView name, age, gender, last_seen_well, incoming_eta;
    private Fragment fragment;
    private ImageButton arrow_back;
    private Patient patient;

    private ImageView chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_clinicians_patient_details);
        initViews();

        patient = (Patient) getIntent().getSerializableExtra("patient");
        name.setText(patient.getName());
        age.setText("Age: " + patient.getAge());
        gender.setText(patient.getGender());
        incoming_eta.setText(patient.getEta());
        last_seen_well.setText("Last seen" + "  " + "ago");


        ed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgroundButton(view);
                fragment = new EDFragment();
                initFragment();
            }
        });

        patient_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgroundButton(view);
                fragment = new PatientDetailsFragment();
                initFragment();
            }
        });

        clinical_history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgroundButton(view);
                fragment = new ClinicalHistoryFragment();
                initFragment();
            }
        });

        clinical_assessment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgroundButton(view);
                fragment = new ClinicialAssessmentFragment();
                initFragment();
            }
        });

        radiology_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgroundButton(view);
                fragment = new RadiologyFragment();
                initFragment();
            }
        });

        management_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgroundButton(view);
                fragment = new ManagementFragment();
                initFragment();
            }
        });

        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.pushActivity(CliniciansPatientDetails.this,
                        String.valueOf(patient.getId()),
                        String.valueOf(patient.getId()));

            }
        });
    }

    protected void initViews() {
        ed_btn = (Button) findViewById(R.id.ed_btn);
        patient_details_btn = (Button) findViewById(R.id.patient_details_btn);
        clinical_history_btn = (Button) findViewById(R.id.clinical_history_btn);
        clinical_assessment_btn = (Button) findViewById(R.id.clinical_assessment_btn);
        radiology_btn = (Button) findViewById(R.id.radiology_btn);
        management_btn = (Button) findViewById(R.id.management_btn);
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        gender = (TextView) findViewById(R.id.gender);
        last_seen_well = (TextView) findViewById(R.id.last_dose);
        incoming_eta = (TextView) findViewById(R.id.incoming_eta);
        arrow_back = (ImageButton) findViewById(R.id.arrow_back_toolbar);
        chat = findViewById(R.id.img_chat);
    }

    protected void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, fragment).commit();
    }

    protected void changeBackgroundButton(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ed_btn:
                ed_btn.setBackgroundResource(R.drawable.selected_button);
                patient_details_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_history_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_assessment_btn.setBackgroundResource(R.drawable.rounded_btn);
                radiology_btn.setBackgroundResource(R.drawable.rounded_btn);
                management_btn.setBackgroundResource(R.drawable.rounded_btn);
                break;
            case R.id.patient_details_btn:
                patient_details_btn.setBackgroundResource(R.drawable.selected_button);
                ed_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_history_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_assessment_btn.setBackgroundResource(R.drawable.rounded_btn);
                radiology_btn.setBackgroundResource(R.drawable.rounded_btn);
                management_btn.setBackgroundResource(R.drawable.rounded_btn);
                break;
            case R.id.clinical_history_btn:
                clinical_history_btn.setBackgroundResource(R.drawable.selected_button);
                ed_btn.setBackgroundResource(R.drawable.rounded_btn);
                patient_details_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_assessment_btn.setBackgroundResource(R.drawable.rounded_btn);
                radiology_btn.setBackgroundResource(R.drawable.rounded_btn);
                management_btn.setBackgroundResource(R.drawable.rounded_btn);
                break;
            case R.id.clinical_assessment_btn:
                clinical_assessment_btn.setBackgroundResource(R.drawable.selected_button);
                ed_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_history_btn.setBackgroundResource(R.drawable.rounded_btn);
                patient_details_btn.setBackgroundResource(R.drawable.rounded_btn);
                radiology_btn.setBackgroundResource(R.drawable.rounded_btn);
                management_btn.setBackgroundResource(R.drawable.rounded_btn);
                break;
            case R.id.radiology_btn:
                radiology_btn.setBackgroundResource(R.drawable.selected_button);
                ed_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_history_btn.setBackgroundResource(R.drawable.rounded_btn);
                patient_details_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_assessment_btn.setBackgroundResource(R.drawable.rounded_btn);
                management_btn.setBackgroundResource(R.drawable.rounded_btn);
                break;
            case R.id.management_btn:
                management_btn.setBackgroundResource(R.drawable.selected_button);
                ed_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_history_btn.setBackgroundResource(R.drawable.rounded_btn);
                patient_details_btn.setBackgroundResource(R.drawable.rounded_btn);
                clinical_assessment_btn.setBackgroundResource(R.drawable.rounded_btn);
                radiology_btn.setBackgroundResource(R.drawable.rounded_btn);
                break;


        }
    }


}
