package com.simi.codestrokealert.activity.paramedics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.model.CaseAssessments;


public class ClinicalAssessmentActivity extends AppCompatActivity {

    private ImageButton back_btn;
    private Button next_page, unknown;
    private CaseAssessments caseAssessments;
    private RadioButton rd_yes_cannula, rd_no_cannula;
    private String strCannula;
    private enum Cannula{yes, no}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_clinical_assessment);
        initViews();

        caseAssessments = (CaseAssessments) getIntent().getSerializableExtra("raceScore");

        rd_no_cannula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if(isCheck){
                    strCannula = Cannula.no.name();
                }
            }
        });

        rd_yes_cannula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if(isCheck){
                    strCannula = Cannula.yes.name();
                }
            }
        });

        unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rd_no_cannula.setChecked(false);
                rd_yes_cannula.setChecked(false);
                strCannula = "Unknown";
            }
        });

        //Go to previous activity
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ProfileSummaryActivity.class);
                caseAssessments.setCannula(strCannula);
                SharedPref.write(SharedPref.CANNULA, strCannula);
                intent.putExtra("caseAsses", caseAssessments);
                startActivity(intent);
            }
        });

    }

    protected void initViews(){
        back_btn = (ImageButton)findViewById(R.id.arrow_back);
        next_page = (Button)findViewById(R.id.btn_next);
        unknown   = (Button)findViewById(R.id.unknown);
        rd_no_cannula = (RadioButton)findViewById(R.id.rd_no_cannula);
        rd_yes_cannula = (RadioButton)findViewById(R.id.rd_yes_cannula);
    }
}
