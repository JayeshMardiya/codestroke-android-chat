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
import android.widget.NumberPicker;
import android.widget.Toast;

import com.simi.codestrokealert.Constants;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.model.CaseHospitals;
import com.simi.codestrokealert.model.Cases;
import com.simi.codestrokealert.model.Hospital;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.simi.codestrokealert.Constants.AUTH_HEADER;


public class DestinationActivity extends AppCompatActivity {

    private Button btn_next;
    private NumberPicker hospitalPicker;
    private Cases cases;
    private String hospital_name, signoff_first_name, signoff_last_name, signoff_role;
    private CaseHospitals caseHospitals;
    private String[] hospitalName;
    private int[] hospitalIds;
    private List<Hospital> hospitals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_destination);
        initViews();

        cases = (Cases) getIntent().getSerializableExtra("cases");
        getHospitalList();


        hospitalPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                  hospital_name = hospitalName[newValue];
                  cases.setHopspital_id(hospitalIds[newValue]);
            }
        });

        //Go to Clinical History activity
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ClinicalHistoryActivity.class));
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                RequestInterface casesApi = retrofit.create(RequestInterface.class);
                signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
                signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
                signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
                cases.setSignoff_first_name(signoff_first_name);
                cases.setSignoff_last_name(signoff_last_name);
                cases.setSignoff_role(signoff_role);

                Call<Cases> response = casesApi.addCase(AUTH_HEADER, cases);
                response.enqueue(new Callback<Cases>() {
                    @Override
                    public void onResponse(Call<Cases> call, Response<Cases> response) {
                        Cases casesRes = response.body();
                        if (casesRes != null) {
                            SharedPref.write(SharedPref.CASE_ID, casesRes.getCase_id());
                        }

                    }

                    @Override
                    public void onFailure(Call<Cases> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    protected void initViews(){
        hospitalPicker = (NumberPicker) findViewById(R.id.choose_hospital);
        btn_next = (Button)findViewById(R.id.btn_next);
        caseHospitals = new CaseHospitals();

    }

    protected void getHospitalList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOSPITAL_LIST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RequestInterface hospitalsApi = retrofit.create(RequestInterface.class);
        Call<List<Hospital>> response = hospitalsApi.getHospitals(AUTH_HEADER);
        response.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                hospitals = response.body();
                if(hospitals != null){
                    int length = hospitals.get(0).getHighest_assigned_id();
                    hospitalName = new String[length];
                    hospitalIds = new int[length];
                    for(int i = 1; i < hospitals.size(); i++){
                        hospitalName[i-1] = hospitals.get(i).getHospital_name();
                        hospitalIds[i-1] = hospitals.get(i).getHospital_id();
                    }
                    hospitalPicker.setMinValue(0);
                    hospitalPicker.setMaxValue(length-1);
                    hospitalPicker.setDisplayedValues(hospitalName);
                    int pos = hospitalPicker.getValue();
                    hospital_name = hospitalName[pos];
                    caseHospitals.setHospital_id(hospitalIds[pos]);
                    cases.setHopspital_id(hospitalIds[pos]);
                }

            }

            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

