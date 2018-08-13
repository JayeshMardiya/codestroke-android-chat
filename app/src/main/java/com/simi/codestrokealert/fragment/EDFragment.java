package com.simi.codestrokealert.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.simi.codestrokealert.Constants;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.model.CaseEdResponse;
import com.simi.codestrokealert.model.CaseEds;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.simi.codestrokealert.Constants.AUTH_HEADER;

public class EDFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private EditText et_current_location;
    private CheckBox registered, triaged, primary_survey_completed;
    private Button submit_btn;
    private CaseEds caseEds;
    private String strLocation, signoff_first_name, signoff_last_name, signoff_role;
    private byte  strRegistered , strTriaged , strPrimary ;
    private int id;
    private  Retrofit retrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ed, container, false);

        initViews();
        getCaseEdInfo();

        return rootView;
    }

    protected void initViews(){
        caseEds = new CaseEds();
        et_current_location = (EditText)rootView.findViewById(R.id.et_current_location);
        registered = (CheckBox)rootView.findViewById(R.id.registered_in_ed);
        triaged = (CheckBox)rootView.findViewById(R.id.triaged_in_ed);
        primary_survey_completed = (CheckBox)rootView.findViewById(R.id.primary_survey_completed);
        submit_btn = (Button)rootView.findViewById(R.id.submit_btn);
        registered.setOnClickListener(this);
        triaged.setOnClickListener(this);
        primary_survey_completed.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.triaged_in_ed:
                if(triaged.isChecked())
                    strTriaged = 1;
                else
                    strTriaged = 0;
                break;

            case R.id.registered_in_ed:
                if(registered.isChecked())
                    strRegistered = 1;
                else
                    strRegistered = 0;
                break;

            case R.id.primary_survey_completed:
                if(primary_survey_completed.isChecked())
                    strPrimary = 1;
                else
                    strPrimary = 0;
                break;

            case R.id.submit_btn:
                tappedSubmitButton();
        }

    }

    protected void getCaseEdInfo(){


        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        RequestInterface getCaseEdApi = retrofit.create(RequestInterface.class);
        SharedPref.read(SharedPref.PATIANT_ID, -1);
        Call<CaseEdResponse> responseCaseEd = getCaseEdApi.getCaseEd(AUTH_HEADER, id);

        responseCaseEd.enqueue(new Callback<CaseEdResponse>() {
            @Override
            public void onResponse(Call<CaseEdResponse> call, Response<CaseEdResponse> response) {
                List<CaseEds>  resp = response.body().getResults();

                if(resp != null) {
                    caseEds.setCase_id(resp.get(0).getCase_id());
                    caseEds.setLocation(resp.get(0).getLocation());
                    caseEds.setRegistered(resp.get(0).getRegistered());
                    caseEds.setTriaged(resp.get(0).getTriaged());
                    caseEds.setPrimary_survey(resp.get(0).getPrimary_survey());
                    strLocation = caseEds.getLocation();
                    byte registeredChek = caseEds.getRegistered();
                    byte triagedCheck = caseEds.getRegistered();
                    byte primaryCheck = caseEds.getPrimary_survey();
                    initializeViewS(strLocation, registeredChek,
                            triagedCheck, primaryCheck);

                }
            }

            @Override
            public void onFailure(Call<CaseEdResponse> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("message", t.getLocalizedMessage());
                }
            }
        });

    }

    protected void initializeViewS(String strLocation, byte strRegistered,
                                   byte strTriaged, byte strPrimary){

        et_current_location.setText(strLocation);
        switch (strRegistered){
            case 0:
                registered.setChecked(false);
                break;
            case 1:
                registered.setChecked(true);
                break;
            case -1:
                registered.setChecked(false);
                break;
        }
        switch (strTriaged){
            case 0:
                triaged.setChecked(false);
                break;
            case 1:
                triaged.setChecked(true);
                break;
            case -1:
                triaged.setChecked(false);
                break;
        }
        switch (strPrimary){
            case 0:
                primary_survey_completed.setChecked(false);
                break;
            case 1:
                primary_survey_completed.setChecked(true);
                break;
            case -1:
                primary_survey_completed.setChecked(false);
                break;
        }
    }

    protected void tappedSubmitButton(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updateCaseEdApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        caseEds.setCase_id(id);
        caseEds.setRegistered(Byte.valueOf(strRegistered));
        caseEds.setTriaged(Byte.valueOf(strTriaged));
        caseEds.setPrimary_survey(Byte.valueOf(strPrimary));
        strLocation = et_current_location.getText().toString();
        caseEds.setLocation(strLocation);
        signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
        signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
        signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
        caseEds.setSignoff_first_name(signoff_first_name);
        caseEds.setSignoff_last_name(signoff_last_name);
        caseEds.setSignoff_role(signoff_role);
        Call<CaseEds> response = updateCaseEdApi.updateCaseEd(AUTH_HEADER, id, caseEds);
        response.enqueue(new Callback<CaseEds>() {
            @Override
            public void onResponse(Call<CaseEds> call, Response<CaseEds> response) {
                CaseEds resp = response.body();
                if(resp != null){
                    Toast.makeText(getActivity(), "ED updated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CaseEds> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
