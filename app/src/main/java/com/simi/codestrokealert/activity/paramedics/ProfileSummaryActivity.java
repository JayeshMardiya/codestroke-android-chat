package com.simi.codestrokealert.activity.paramedics;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.simi.codestrokealert.Constants;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.adapter.ExpandableListAdapter;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.model.CaseAssessments;
import com.simi.codestrokealert.model.Cases;
import com.simi.codestrokealert.model.ChildItem;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.simi.codestrokealert.Constants.AUTH_HEADER;


public class ProfileSummaryActivity extends AppCompatActivity {

    private ImageButton back_btn;
    private Button drop_off;
    private CaseAssessments caseAssessments;
    private Cases cases;
    int id;
    private String signoff_first_name, signoff_last_name, signoff_role;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<ChildItem>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_profile_summary);
        initViews();

        caseAssessments = (CaseAssessments)getIntent().getSerializableExtra("caseAsses");


        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //Go to previous activity
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        drop_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 showAlert();
            }
        });
    }

    protected void showAlert(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ProfileSummaryActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(ProfileSummaryActivity.this);
        }
        builder.setTitle("Drop Off")
                .setMessage(R.string.drop_off_dialog)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendCaseAssessment(caseAssessments);
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    protected void initViews(){
        expListView = (ExpandableListView) findViewById(R.id.list_view_exp);
        back_btn = (ImageButton)findViewById(R.id.arrow_back);
        drop_off = (Button)findViewById(R.id.drop_off);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<ChildItem>>();

        // Adding child data
        listDataHeader.add("Details");
        listDataHeader.add("History");
        listDataHeader.add("Mass");
        listDataHeader.add("Vitals");
        listDataHeader.add("RACE");
        listDataHeader.add("18G IV");

        List<ChildItem> childItemsDetails = new ArrayList<>();
        childItemsDetails.add( new ChildItem("Name: ", SharedPref.read(SharedPref.FIRST_NAME, null) +
        " " + SharedPref.read(SharedPref.LAST_NAME, null)));
        childItemsDetails.add( new ChildItem("Age: ", SharedPref.read(SharedPref.AGE, null)));
        childItemsDetails.add( new ChildItem("Gender: ", SharedPref.read(SharedPref.GENDER, null)));
        childItemsDetails.add( new ChildItem("Address: ", SharedPref.read(SharedPref.ADDRESS, null)));
        childItemsDetails.add( new ChildItem("Last Seen: ", SharedPref.read(SharedPref.LAST_SEEN, null)));
        childItemsDetails.add( new ChildItem("NOK: ", SharedPref.read(SharedPref.NOK, null)));
        childItemsDetails.add( new ChildItem("NOK Contact: ", SharedPref.read(SharedPref.NOK_PHONE, null)));

        List<ChildItem> childItemsHistory = new ArrayList<>();
        childItemsHistory.add( new ChildItem("Past Medical History: ", SharedPref.read(SharedPref.PAST_MEDICAL_HISTORY, null)));
        childItemsHistory.add( new ChildItem("Medication: ", SharedPref.read(SharedPref.MEDICATIONS, null)));
        childItemsHistory.add( new ChildItem("Anticoagulants: ", SharedPref.read(SharedPref.ANTICOAGULANTS, null)));
        childItemsHistory.add( new ChildItem("Situation: ", SharedPref.read(SharedPref.SITIUATION, null)));
        childItemsHistory.add( new ChildItem("Weight: ", SharedPref.read(SharedPref.WEIGHT, null)));

        List<ChildItem> childItemsMass = new ArrayList<>();
        childItemsMass.add( new ChildItem("Facial droop: ", SharedPref.read(SharedPref.FACIAL_DROOP, null)));
        childItemsMass.add( new ChildItem("Arm drift: ", SharedPref.read(SharedPref.ARM_DRIFT, null)));
        childItemsMass.add( new ChildItem("Weak grip: ", SharedPref.read(SharedPref.WEAK_GRIP, null)));
        childItemsMass.add( new ChildItem("Speech Difficulty: ", SharedPref.read(SharedPref.SPEACH_DIFFICULTY, null)));

        List<ChildItem> childItemsVitals = new ArrayList<>();
        childItemsVitals.add( new ChildItem("Blood Pressure: ", SharedPref.read(SharedPref.BLOOD_PRESSURE, null)));
        childItemsVitals.add( new ChildItem("Heart Rate: ", SharedPref.read(SharedPref.HEART_RATE, null)));
        childItemsVitals.add( new ChildItem("Heart Rhythm: ", SharedPref.read(SharedPref.HEART_RYTHM, null)));
        childItemsVitals.add( new ChildItem("Respiratory Rate: ", SharedPref.read(SharedPref.RESPIRATORY_RATE, null)));
        childItemsVitals.add( new ChildItem("Oxygen Saturation: ", SharedPref.read(SharedPref.OXYGEN_SATURATION, null)));
        childItemsVitals.add( new ChildItem("Temperature: ", SharedPref.read(SharedPref.TEMPERATURE, null)));
        childItemsVitals.add( new ChildItem("Blood Glucose: ", SharedPref.read(SharedPref.BLOOD_GLUCOSE, null)));
        childItemsVitals.add( new ChildItem("GCS: ", SharedPref.read(SharedPref.GCS, null)));

        List<ChildItem> childItemsRACE  = new ArrayList<>();
        childItemsRACE.add( new ChildItem("Facial Palsy: ", SharedPref.read(SharedPref.FACIAL_PALSY, null)));
        childItemsRACE.add( new ChildItem("Arm Motor Impairment: ", SharedPref.read(SharedPref.ARM_MOTOR_IMPAIRMENT, null)));
        childItemsRACE.add( new ChildItem("Leg Motor Impairment: ", SharedPref.read(SharedPref.LEG_MOTOR_IMPAIRMENT, null)));
        childItemsRACE.add( new ChildItem("Head and Gaze Deviation: ", SharedPref.read(SharedPref.HEAD_DEVIATION, null)));

        List<ChildItem> childItems18GIV = new ArrayList<>();
        childItems18GIV.add( new ChildItem("18G IV Cannula in Cub Fossa: ", SharedPref.read(SharedPref.CANNULA, null)));


        listDataChild.put(listDataHeader.get(0), childItemsDetails);
        listDataChild.put(listDataHeader.get(1), childItemsHistory);
        listDataChild.put(listDataHeader.get(2), childItemsMass);
        listDataChild.put(listDataHeader.get(3), childItemsVitals);
        listDataChild.put(listDataHeader.get(4), childItemsRACE);
        listDataChild.put(listDataHeader.get(5), childItems18GIV);


    }

    protected void sendCaseAssessment(CaseAssessments caseAssessments){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updateCasesAssessmentApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.CASE_ID, -1);
        signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
        signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
        signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
        caseAssessments.setSignoff_first_name(signoff_first_name);
        caseAssessments.setSignoff_last_name(signoff_last_name);
        caseAssessments.setSignoff_role(signoff_role);
        Call<CaseAssessments> response = updateCasesAssessmentApi.sendCaseAssessments(AUTH_HEADER, id, caseAssessments);

        response.enqueue(new Callback<CaseAssessments>() {
            @Override
            public void onResponse(Call<CaseAssessments> call, Response<CaseAssessments> response) {
                CaseAssessments resp = response.body();
                if (resp != null) {
                    Log.i("Error", "success");
                }

            }


            @Override
            public void onFailure(Call<CaseAssessments> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

    });
    }

}

