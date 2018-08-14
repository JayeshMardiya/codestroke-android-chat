package com.codestroke.codestrokealert.activity.paramedics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codestroke.codestrokealert.Constants;
import com.codestroke.codestrokealert.CustomDatePicker;
import com.codestroke.codestrokealert.R;
import com.codestroke.codestrokealert.SharedPref;
import com.codestroke.codestrokealert.model.CaseHistories;
import com.codestroke.codestrokealert.model.rest.RequestInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.codestroke.codestrokealert.Constants.AUTH_HEADER;


public class ClinicalHistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_next_page_clinical_history;
    int id;
    private ImageButton calendarImage;
    private EditText last_dose, past_medical_history, medications, situation, weight;
    private DatePickerDialog datePickerDialog;
    private RadioButton yes_anticoags, no_anticoags;
    private CheckBox checkBoxIHD, checkBoxDM, checkBoxStroke,
            checkBoxEpilepsy, checkBoxAF, checkBoxOtherNeurologicalConditions
            ,checkBoxApixaban, checkBoxRivaroxaban, checkBoxWarfarin
            ,checkBoxDabigatran, checkBoxHeparin;
    private enum Anticoags{
        no, yes, unknown
    }
    private String pastMdHistory = "", strMedication = "", strAnticoags, signoff_first_name, signoff_last_name, signoff_role;
    private CaseHistories caseHistories;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_clinical_history);
        initViews();



        if(!past_medical_history.getText().toString().isEmpty()){
            pastMdHistory += past_medical_history.getText().toString() + ", ";
        }

        if(!medications.getText().toString().isEmpty()){
            strMedication += past_medical_history.getText().toString() + ", ";
        }

    }

    protected void initViews(){
        caseHistories = new CaseHistories();

        btn_next_page_clinical_history = (Button)findViewById(R.id.btn_next);
        calendarImage = (ImageButton) findViewById(R.id.calenderImage);
        past_medical_history = (EditText) findViewById(R.id.past_medical_history);
        last_dose = (EditText) findViewById(R.id.et_last_dose);
        medications = (EditText) findViewById(R.id.medications);
        weight = (EditText) findViewById(R.id.weight);
        situation = (EditText)findViewById(R.id.situation);
        yes_anticoags = (RadioButton) findViewById(R.id.yes_anticoags);
        no_anticoags = (RadioButton) findViewById(R.id.no_anticoags);
        checkBoxIHD = (CheckBox) findViewById(R.id.checkBoxIHD);
        checkBoxDM = (CheckBox) findViewById(R.id.checkBoxDM);
        checkBoxStroke = (CheckBox) findViewById(R.id.checkBoxStroke);
        checkBoxEpilepsy = (CheckBox) findViewById(R.id.checkBoxEpilepsy);
        checkBoxAF = (CheckBox) findViewById(R.id.checkBoxAF);
        checkBoxOtherNeurologicalConditions = (CheckBox) findViewById(R.id.checkBoxOtherNeurologicalConditions);
        checkBoxApixaban = (CheckBox) findViewById(R.id.checkBoxApixaban);
        checkBoxRivaroxaban = (CheckBox) findViewById(R.id.checkBoxRivaroxaban);
        checkBoxWarfarin = (CheckBox) findViewById(R.id.checkBoxWarfarin);
        checkBoxDabigatran = (CheckBox) findViewById(R.id.checkBoxDabigatran);
        checkBoxHeparin = (CheckBox) findViewById(R.id.checkBoxHeparin);

        calendarImage.setOnClickListener(this);
        checkBoxIHD.setOnClickListener(this);
        checkBoxDM.setOnClickListener(this);
        checkBoxStroke.setOnClickListener(this);
        checkBoxEpilepsy.setOnClickListener(this);
        checkBoxAF.setOnClickListener(this);
        checkBoxOtherNeurologicalConditions.setOnClickListener(this);
        checkBoxApixaban.setOnClickListener(this);
        checkBoxRivaroxaban.setOnClickListener(this);
        checkBoxWarfarin.setOnClickListener(this);
        checkBoxDabigatran.setOnClickListener(this);
        checkBoxHeparin.setOnClickListener(this);
        yes_anticoags.setOnClickListener(this);
        no_anticoags.setOnClickListener(this);
        btn_next_page_clinical_history.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.checkBoxIHD:
                pastMdHistory += "HD, ";
                break;

            case R.id.checkBoxDM:
                pastMdHistory += "DM, ";
                break;

            case R.id.checkBoxStroke:
                pastMdHistory += "Stroke, ";
                break;

            case R.id.checkBoxEpilepsy:
                pastMdHistory += "Epilepsy, ";
                break;

            case R.id.checkBoxAF:
                pastMdHistory += "AF, ";
                break;

            case R.id.checkBoxOtherNeurologicalConditions:
                pastMdHistory += "Other Neurological Conditions, ";
                break;

            case R.id.checkBoxApixaban:
                strMedication += "Apixaban, ";
                break;

            case R.id.checkBoxRivaroxaban:
                strMedication += "Rivaroxaban, ";
                break;

            case R.id.checkBoxWarfarin:
                strMedication += "Warfarin, ";
                break;

            case R.id.checkBoxDabigatran:
                strMedication += "Dabigatran, ";
                break;

            case R.id.checkBoxHeparin:
                strMedication += "Heparin, ";


            case R.id.yes_anticoags:
                caseHistories.setAnticoags(Anticoags.yes.name());
                break;

            case R.id.no_anticoags:
                caseHistories.setAnticoags(Anticoags.no.name());
                break;

            case R.id.calenderImage:
                new CustomDatePicker(ClinicalHistoryActivity.this, R.id.et_last_dose, R.id.calenderImage);
                break;

            case R.id.btn_next:
                goToNextActivity();
                break;


        }
    }

    protected void goToNextActivity(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updateCasesApi = retrofit.create(RequestInterface.class);
        int id = SharedPref.read(SharedPref.CASE_ID, -1);
        caseHistories.setCase_id(id);
        caseHistories.setPmhx(pastMdHistory);
        caseHistories.setMeds(strMedication);
        caseHistories.setAnticoags(yes_anticoags.isChecked()? Anticoags.yes.name() : Anticoags.no.name());
        if(!last_dose.getText().toString().isEmpty()) {
            caseHistories.setAnticoags_last_dose(last_dose.getText().toString());
        }
        caseHistories.setHopc(situation.getText().toString());
        if(!weight.getText().toString().isEmpty()) {
            caseHistories.setWeight(Float.parseFloat(weight.getText().toString()));
        }
        if(yes_anticoags.isChecked()){
            strAnticoags = Anticoags.yes.name();
        }else{
            strAnticoags = Anticoags.no.name();
        }
        signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
        signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
        signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
        caseHistories.setSignoff_first_name(signoff_first_name);
        caseHistories.setSignoff_first_name(signoff_last_name);
        caseHistories.setSignoff_first_name(signoff_role);
        saveData(pastMdHistory, strMedication, strAnticoags, situation.getText().toString(), weight.getText().toString() );

        Call<CaseHistories> response = updateCasesApi.updateCase(AUTH_HEADER, id, caseHistories);
        response.enqueue(new Callback<CaseHistories>() {
            @Override
            public void onResponse(Call<CaseHistories> call, Response<CaseHistories> response) {
                CaseHistories resp = response.body();
                if(resp != null){

                    startActivity(new Intent(getBaseContext(), MassActivity.class));

                }else{
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();

                    reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String finallyError = sb.toString();
                    Log.i("Error", finallyError);
                }

            }

            @Override
            public void onFailure(Call<CaseHistories> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void saveData(String past_medical_history, String medications,
                            String strAnticoags, String situation, String weight ){
        SharedPref.write(SharedPref.PAST_MEDICAL_HISTORY, past_medical_history);
        SharedPref.write(SharedPref.MEDICATIONS, medications);
        SharedPref.write(SharedPref.ANTICOAGULANTS, strAnticoags);
        SharedPref.write(SharedPref.SITIUATION, situation);
        SharedPref.write(SharedPref.WEIGHT, weight);
    }
}