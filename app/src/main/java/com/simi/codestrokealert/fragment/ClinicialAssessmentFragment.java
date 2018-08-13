package com.simi.codestrokealert.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.simi.codestrokealert.Constants;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.model.CaseAssessments;
import com.simi.codestrokealert.model.CaseAssessmentsResponse;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.simi.codestrokealert.Constants.AUTH_HEADER;

public class ClinicialAssessmentFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private int id;
    private CaseAssessments caseAssessments;
    private RadioButton yes_facial_drop, no_facial_drop, unknown_facial_drop,
            yes_arm_drift, no_arm_drift, unknown_arm_drift, yes_weak_grip, no_weak_grip, unknown_weak_grip,
            yes_speech_difficulty, no_speech_difficulty, unknown_speech_difficulty, regular_heart_rate, irregular_heart_rate,
            yes_cannula_in_cub_fossa, no_cannula_in_cub_fossa, unknown_cannula_in_cub_fossa;
    private EditText weight, heart_rate, respiratory_rate, temperature, gcs, blood_glucose;
    private Button submit;
    private Spinner spinner_facial_palsy, spinner_arm_motor_impairment, spinner_leg_motor_impairment, spinner_head_and_gaze_deviation,
            spinner_level_of_consciousness, spinner_month_and_age, spinner_cub_in_fussa;
    private enum facial_droop {yes, no, unknown}
    private enum arm_drift {yes, no, unknown}
    private enum weak_grip {yes, no, unknown}
    private enum speach_difficulty {yes, no, unknown}
    private enum heart_rhythm {regular, irregular, unknown}
    private enum cannula {yes, no, unknown}
    private String strFacialDroop, strArmDrift, strWeakGrip, strSpeachDifficulty, strHeartRythm, strCannula, strWeight,
            strHeartRate, strRespiratoryRate, strTemprature, StrGCS, strBloodGlucose,
            signoff_first_name, signoff_last_name, signoff_role;
    private byte facialPalsyRace, armMotorImpair, legMotorImpair, headDeviation, levelCons, monthAge ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_clinical_assessment, container, false);
        initViews();
        getCaseAssessmentsInfo();

        return rootView;
    }

    protected void initViews(){
        caseAssessments = new CaseAssessments();
        submit = (Button)rootView.findViewById(R.id.submit);
        yes_facial_drop = (RadioButton) rootView.findViewById(R.id.yes_facial_drop);
        no_facial_drop = (RadioButton) rootView.findViewById(R.id.no_facial_drop);
        unknown_facial_drop = (RadioButton) rootView.findViewById(R.id.unknown_facial_drop);
        yes_arm_drift = (RadioButton) rootView.findViewById(R.id.yes_arm_drift);
        no_arm_drift = (RadioButton) rootView.findViewById(R.id.no_arm_drift);
        unknown_arm_drift = (RadioButton) rootView.findViewById(R.id.unknown_arm_drift);
        yes_weak_grip = (RadioButton) rootView.findViewById(R.id.yes_weak_grip);
        no_weak_grip = (RadioButton) rootView.findViewById(R.id.no_weak_grip);
        unknown_weak_grip = (RadioButton) rootView.findViewById(R.id.unknown_weak_grip);
        yes_speech_difficulty = (RadioButton) rootView.findViewById(R.id.yes_speech_difficulty);
        no_speech_difficulty = (RadioButton) rootView.findViewById(R.id.no_speech_difficulty);
        unknown_speech_difficulty = (RadioButton) rootView.findViewById(R.id.unknown_speech_difficulty);
        regular_heart_rate = (RadioButton) rootView.findViewById(R.id.regular_heart_rate);
        irregular_heart_rate = (RadioButton) rootView.findViewById(R.id.irregular_heart_rate);
        yes_cannula_in_cub_fossa = (RadioButton) rootView.findViewById(R.id.yes_cannula_in_cub_fossa);
        no_cannula_in_cub_fossa = (RadioButton) rootView.findViewById(R.id.no_cannula_in_cub_fossa);
        unknown_cannula_in_cub_fossa = (RadioButton) rootView.findViewById(R.id.unknown_cannula_in_cub_fossa);
        weight = (EditText) rootView.findViewById(R.id.weight);
        heart_rate = (EditText) rootView.findViewById(R.id.heart_rate);
        respiratory_rate = (EditText) rootView.findViewById(R.id.respiratory_rate);
        temperature = (EditText) rootView.findViewById(R.id.temperature);
        gcs = (EditText) rootView.findViewById(R.id.gcs);
        blood_glucose = (EditText) rootView.findViewById(R.id.blood_glucose);
        spinner_facial_palsy = (Spinner)rootView.findViewById(R.id.spinner_facial_palsy);
        spinner_arm_motor_impairment = (Spinner)rootView.findViewById(R.id.spinner_arm_motor_impairment);
        spinner_leg_motor_impairment = (Spinner)rootView.findViewById(R.id.spinner_leg_motor_impairment);
        spinner_head_and_gaze_deviation = (Spinner)rootView.findViewById(R.id.spinner_head_and_gaze_deviation);
        spinner_level_of_consciousness = (Spinner)rootView.findViewById(R.id.spinner_level_of_consciousness);
        spinner_month_and_age = (Spinner)rootView.findViewById(R.id.spinner_month_and_age);
        spinner_cub_in_fussa = (Spinner)rootView.findViewById(R.id.spinner_cub_in_fussa);
        submit.setOnClickListener(this);
        yes_facial_drop.setOnClickListener(this);
        no_facial_drop.setOnClickListener(this);
        unknown_facial_drop.setOnClickListener(this);
        yes_weak_grip.setOnClickListener(this);
        no_weak_grip.setOnClickListener(this);
        unknown_weak_grip.setOnClickListener(this);
        yes_arm_drift.setOnClickListener(this);
        no_arm_drift.setOnClickListener(this);
        unknown_arm_drift.setOnClickListener(this);
        yes_speech_difficulty.setOnClickListener(this);
        no_speech_difficulty.setOnClickListener(this);
        unknown_speech_difficulty.setOnClickListener(this);
        irregular_heart_rate.setOnClickListener(this);
        respiratory_rate.setOnClickListener(this);
        yes_cannula_in_cub_fossa.setOnClickListener(this);
        no_cannula_in_cub_fossa.setOnClickListener(this);
        unknown_cannula_in_cub_fossa.setOnClickListener(this);

    }

    protected void getCaseAssessmentsInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface getCaseAssessmentsApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        Call<CaseAssessmentsResponse> responseCaseAssessments =  getCaseAssessmentsApi.getCaseAssessments(AUTH_HEADER, id);
        responseCaseAssessments.enqueue(new Callback<CaseAssessmentsResponse>() {
            @Override
            public void onResponse(Call<CaseAssessmentsResponse> call, Response<CaseAssessmentsResponse> response) {
                if(response.body().getResult() != null) {
                    List<CaseAssessments> resp = response.body().getResult();
                    caseAssessments = resp.get(0);
                    updateViews(caseAssessments);
                }

            }

            @Override
            public void onFailure(Call<CaseAssessmentsResponse> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void updateViews(CaseAssessments caseAss){
        String strFD = caseAss.getFacial_droop();
        if(strFD != null) {
            switch (strFD) {
                case "yes":
                    yes_facial_drop.setChecked(true);
                    break;

                case "no":
                    no_facial_drop.setChecked(true);
                    break;

                case "unknown":
                    unknown_facial_drop.setChecked(true);
                    break;
            }
        }

        String strAD = caseAss.getArm_drift();
        if(strAD != null) {
            switch (strAD) {
                case "yes":
                    yes_arm_drift.setChecked(true);
                    break;

                case "no":
                    no_arm_drift.setChecked(true);
                    break;

                case "unknown":
                    unknown_arm_drift.setChecked(true);
                    break;
            }
        }

        String strWG = caseAss.getWeak_grip();
        if(strWG != null) {
            switch (strWG) {
                case "yes":
                    yes_weak_grip.setChecked(true);
                    break;

                case "no":
                    no_weak_grip.setChecked(true);
                    break;

                case "unknown":
                    unknown_weak_grip.setChecked(true);
                    break;
            }
        }

        String strSD = caseAss.getSpeech_difficulty();
        if(strSD != null) {
            switch (strSD) {
                case "yes":
                    yes_speech_difficulty.setChecked(true);
                    break;

                case "no":
                    no_speech_difficulty.setChecked(true);
                    break;

                case "unknown":
                    unknown_speech_difficulty.setChecked(true);
                    break;
            }
        }

        String strCannula = caseAss.getCannula();
        if(strCannula != null) {
            switch (strCannula) {
                case "yes":
                    yes_cannula_in_cub_fossa.setChecked(true);
                    break;

                case "no":
                    no_cannula_in_cub_fossa.setChecked(true);
                    break;

                case "unknown":
                    unknown_cannula_in_cub_fossa.setChecked(true);
                    break;
            }
        }

        String strHR = caseAss.getHeart_rhythm();
        if(strHR != null) {
            switch (strHR) {
                case "regular":
                    regular_heart_rate.setChecked(true);
                    break;

                case "irregular":
                    irregular_heart_rate.setChecked(true);
                    break;
            }
        }

        Integer heartRate = caseAss.getHeart_rate();
        if(heartRate != null) {
            heart_rate.setText(String.valueOf(heartRate));
        }
        Integer rr = caseAss.getRr();
        if(rr != null) {
            respiratory_rate.setText(String.valueOf(rr));
        }
        Integer temp = caseAss.getTemp();
        if(temp != null) {
            temperature.setText(String.valueOf(temp));
        }
        Integer gcsVal = caseAss.getGcs();
        if(gcsVal != null) {
            gcs.setText(String.valueOf(gcsVal));
        }
        Integer BG = caseAss.getBlood_glucose();
        if(BG != null) {
            blood_glucose.setText(String.valueOf(BG));
        }
        Byte facial_race = caseAss.getFacial_palsy_race();
        if(facial_race != null) {
            spinner_facial_palsy.setSelection(facial_race);
        }
        Byte arm_motor_imp = caseAss.getArm_motor_impair();
        if(arm_motor_imp != null) {
            spinner_arm_motor_impairment.setSelection(arm_motor_imp);
        }
        Byte leg_motor_imp = caseAss.getLeg_motor_impair();
        if(leg_motor_imp != null) {
            spinner_leg_motor_impairment.setSelection(leg_motor_imp);
        }
        Byte head = caseAss.getHead_gaze_deviate();
        if(head != null) {
            spinner_head_and_gaze_deviation.setSelection(head);
        }
        Byte con_level = caseAss.getConscious_level();
        if(con_level != null) {
            spinner_level_of_consciousness.setSelection(con_level);
        }
        Byte month_age = caseAss.getMonth_age();
        if(month_age != null) {
            spinner_month_and_age.setSelection(month_age);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yes_facial_drop:
                 strFacialDroop = facial_droop.yes.name();
                 break;

            case R.id.no_facial_drop:
                strFacialDroop = facial_droop.no.name();
                break;

            case R.id.unknown_facial_drop:
                strFacialDroop = facial_droop.unknown.name();
                break;

            case R.id.yes_arm_drift:
                strArmDrift = arm_drift.yes.name();
                break;

            case R.id.no_arm_drift:
                strArmDrift = arm_drift.no.name();
                break;

            case R.id.unknown_arm_drift:
                strArmDrift = arm_drift.unknown.name();
                break;

            case R.id.yes_weak_grip:
                strWeakGrip = weak_grip.yes.name();
                break;

            case R.id.no_weak_grip:
                strWeakGrip = weak_grip.no.name();
                break;

            case R.id.unknown_weak_grip:
                strWeakGrip = weak_grip.unknown.name();
                break;

            case R.id.yes_speech_difficulty:
                strSpeachDifficulty = speach_difficulty.yes.name();
                break;

            case R.id.no_speech_difficulty:
                strSpeachDifficulty = speach_difficulty.no.name();
                break;

            case R.id.unknown_speech_difficulty:
                strSpeachDifficulty = speach_difficulty.unknown.name();
                break;

            case R.id.regular_heart_rate:
                strHeartRythm = heart_rhythm.regular.name();
                break;

            case R.id.irregular_heart_rate:
                strHeartRythm = heart_rhythm.irregular.name();
                break;

            case R.id.yes_cannula_in_cub_fossa:
                strCannula = cannula.yes.name();
                break;

            case R.id.no_cannula_in_cub_fossa:
                strCannula = cannula.no.name();
                break;

            case R.id.unknown_cannula_in_cub_fossa:
                strCannula = cannula.unknown.name();
                break;

            case R.id.submit:
                submitInfo();
                break;
        }
    }

    protected void submitInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updateCaseAssessmentsApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        caseAssessments.setCase_id(id);
        caseAssessments.setFacial_droop(strFacialDroop);
        caseAssessments.setArm_drift(strArmDrift);
        caseAssessments.setWeak_grip(strWeakGrip);
        caseAssessments.setSpeech_difficulty(strSpeachDifficulty);
        if(!heart_rate.getText().toString().isEmpty()) {
            caseAssessments.setHeart_rate(Integer.parseInt(heart_rate.getText().toString()));
        }
        if(!respiratory_rate.getText().toString().isEmpty()) {
            caseAssessments.setRr(Integer.parseInt((respiratory_rate.getText().toString())));
        }
        if(!temperature.getText().toString().isEmpty()) {
            caseAssessments.setTemp(Integer.parseInt(temperature.getText().toString()));
        }
        if(!gcs.getText().toString().isEmpty()) {
            caseAssessments.setGcs(Integer.parseInt(gcs.getText().toString()));
        }
        if(!blood_glucose.getText().toString().isEmpty()) {
            caseAssessments.setBlood_glucose(Integer.parseInt(blood_glucose.getText().toString()));
        }
        caseAssessments.setHeart_rhythm(strHeartRythm);
        switch (spinner_facial_palsy.getSelectedItemPosition()){
            case 0:
                facialPalsyRace = 0;
                break;

            case 1:
                facialPalsyRace = 1;
                break;

            case 2:
                facialPalsyRace = 2;
                break;
        }
        caseAssessments.setFacial_palsy_race(facialPalsyRace);
        switch (spinner_arm_motor_impairment.getSelectedItemPosition()){
            case 0:
                armMotorImpair = 0;
                break;

            case 1:
                armMotorImpair = 1;
                break;

            case 2:
                armMotorImpair = 2;
                break;
        }
        caseAssessments.setArm_motor_impair(armMotorImpair);
        switch (spinner_leg_motor_impairment.getSelectedItemPosition()){
            case 0:
                legMotorImpair = 0;
                break;

            case 1:
                legMotorImpair = 1;
                break;

            case 2:
                legMotorImpair = 2;
                break;
        }
        caseAssessments.setLeg_motor_impair(legMotorImpair);
        switch (spinner_head_and_gaze_deviation.getSelectedItemPosition()){
            case 0:
                headDeviation = 0;
                break;

            case 1:
                headDeviation = 1;
                break;
        }
        caseAssessments.setHead_gaze_deviate(headDeviation);
        caseAssessments.setCannula(strCannula);
        switch (spinner_level_of_consciousness.getSelectedItemPosition()){
            case 0:
                levelCons = 0;
                break;
        }
        caseAssessments.setConscious_level(levelCons);
        switch (spinner_month_and_age.getSelectedItemPosition()){
            case 0:
                monthAge = 0;
                break;
        }
        caseAssessments.setMonth_age(monthAge);
        signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
        signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
        signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
        caseAssessments.setSignoff_first_name(signoff_first_name);
        caseAssessments.setSignoff_last_name(signoff_last_name);
        caseAssessments.setSignoff_role(signoff_role);
        Call<CaseAssessments> response = updateCaseAssessmentsApi.updateCaseAssessment(AUTH_HEADER, id, caseAssessments);
        response.enqueue(new Callback<CaseAssessments>() {
            @Override
            public void onResponse(Call<CaseAssessments> call, Response<CaseAssessments> response) {
                CaseAssessments resp = response.body();
                if(resp != null){
                    Toast.makeText(getActivity(), "Clinical Assessment Updated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CaseAssessments> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
