package com.codestroke.codestrokealert.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codestroke.codestrokealert.Constants;
import com.codestroke.codestrokealert.R;
import com.codestroke.codestrokealert.SharedPref;
import com.codestroke.codestrokealert.model.CaseRadiologies;
import com.codestroke.codestrokealert.model.CaseRadiologiesResponse;
import com.codestroke.codestrokealert.model.rest.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.codestroke.codestrokealert.Constants.AUTH_HEADER;


public class RadiologyFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private int id;
    private CaseRadiologies caseRadiologies;
    private RadioButton yes_ct1, no_ct1, yes_ct2, no_ct2, yes_ct3, no_ct3,
            yes_pt_arrived, no_pt_arrived, yes_ct_complete, no_ct_complete,
            yes_ich, no_ich, yes_proceed, no_proceed, yes_cta_com, no_cta_com,
            yes_large_vessel, no_large_vessel;
    private Button submit;
    private String signoff_first_name, signoff_last_name, signoff_role;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_radiology, container, false);
        initViews();
        getCaseRadioLogeisInfo();

        return rootView;
    }

    protected void initViews(){
        yes_ct1 = (RadioButton)rootView.findViewById(R.id.yes_ct1);
        no_ct1 = (RadioButton)rootView.findViewById(R.id.no_ct1);
        yes_ct2 = (RadioButton)rootView.findViewById(R.id.yes_ct2);
        no_ct2 = (RadioButton)rootView.findViewById(R.id.no_ct2);
        yes_ct3 = (RadioButton)rootView.findViewById(R.id.yes_ct3);
        no_ct3 = (RadioButton)rootView.findViewById(R.id.no_ct3);
        yes_pt_arrived = (RadioButton)rootView.findViewById(R.id.yes_pt_arrived);
        no_pt_arrived = (RadioButton)rootView.findViewById(R.id.no_pt_arrived);
        yes_ct_complete = (RadioButton)rootView.findViewById(R.id.yes_ct_complete);
        no_ct_complete = (RadioButton)rootView.findViewById(R.id.no_ct_complete);
        yes_ich = (RadioButton)rootView.findViewById(R.id.yes_ich);
        no_ich = (RadioButton)rootView.findViewById(R.id.no_ich);
        yes_proceed = (RadioButton)rootView.findViewById(R.id.yes_proceed);
        no_proceed = (RadioButton)rootView.findViewById(R.id.no_proceed);
        yes_cta_com = (RadioButton)rootView.findViewById(R.id.yes_cta_com);
        no_cta_com = (RadioButton)rootView.findViewById(R.id.no_cta_com);
        yes_large_vessel = (RadioButton)rootView.findViewById(R.id.yes_large_vessel);
        no_large_vessel = (RadioButton)rootView.findViewById(R.id.no_large_vessel);
        submit = (Button)rootView.findViewById(R.id.submitBtn);
        submit.setOnClickListener(this);
        yes_ct1.setOnClickListener(this);
        yes_ct2.setOnClickListener(this);
        yes_ct3.setOnClickListener(this);
        no_ct1.setOnClickListener(this);
        no_ct2.setOnClickListener(this);
        no_ct3.setOnClickListener(this);
        yes_cta_com.setOnClickListener(this);
        no_cta_com.setOnClickListener(this);
        yes_proceed.setOnClickListener(this);
        no_proceed.setOnClickListener(this);
        yes_ich.setOnClickListener(this);
        no_ich.setOnClickListener(this);
        yes_pt_arrived.setOnClickListener(this);
        no_pt_arrived.setOnClickListener(this);
        yes_ct_complete.setOnClickListener(this);
        no_ct_complete.setOnClickListener(this);
        yes_large_vessel.setOnClickListener(this);
        no_large_vessel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yes_ct1:
                yes_ct1.setChecked(true);
                break;

            case R.id.no_ct1:
                no_ct1.setChecked(true);
                break;

            case R.id.no_ct2:
                no_ct2.setChecked(true);
                break;

            case R.id.yes_ct2:
                yes_ct2.setChecked(true);
                break;

            case R.id.yes_ct3:
                yes_ct3.setChecked(true);
                break;

            case R.id.no_ct3:
                no_ct3.setChecked(true);
                break;

            case R.id.yes_pt_arrived:
                yes_pt_arrived.setChecked(true);
                break;

            case R.id.no_pt_arrived:
                no_pt_arrived.setChecked(true);
                break;

            case R.id.yes_ct_complete:
                yes_ct_complete.setChecked(true);
                break;

            case R.id.no_ct_complete:
                no_ct_complete.setChecked(true);
                break;

            case R.id.yes_ich:
                yes_ich.setChecked(true);
                break;

            case R.id.no_ich:
                no_ich.setChecked(true);
                break;

            case R.id.no_proceed:
                no_proceed.setChecked(true);
                break;

            case R.id.yes_proceed:
                yes_proceed.setChecked(true);
                break;

            case R.id.yes_cta_com:
                yes_cta_com.setChecked(true);
                break;

            case R.id.no_cta_com:
                no_ct_complete.setChecked(true);
                break;

            case R.id.yes_large_vessel:
                yes_large_vessel.setChecked(true);
                break;

            case R.id.no_large_vessel:
                no_large_vessel.setChecked(true);
                break;

            case R.id.submitBtn:
                submitRadiologyInfo();
                break;

        }
    }

    protected void getCaseRadioLogeisInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface getCaseRadiologiesApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        Call<CaseRadiologiesResponse> responseCaseRadiologies =  getCaseRadiologiesApi.getCaseRadiologies(AUTH_HEADER, id);
        responseCaseRadiologies.enqueue(new Callback<CaseRadiologiesResponse>() {
            @Override
            public void onResponse(Call<CaseRadiologiesResponse> call, Response<CaseRadiologiesResponse> response) {
                List<CaseRadiologies> resp = response.body().getResult();
                if(resp != null) {
                    caseRadiologies = resp.get(0);
                    updateViews(caseRadiologies);
                }

            }

            @Override
            public void onFailure(Call<CaseRadiologiesResponse> call, Throwable t) {

            }
        });
    }

    protected void updateViews(CaseRadiologies caseRadiology){
        Byte strCT1 = caseRadiology.getCt1();
        if(strCT1 != null) {
            switch (strCT1) {
                case 1:
                    yes_ct1.setChecked(true);
                    break;

                case 0:
                    no_ct1.setChecked(true);
                    break;
            }
        }

        Byte strCT2 = caseRadiology.getCt2();
        if(strCT2 != null) {
            switch (strCT2) {
                case 1:
                    yes_ct2.setChecked(true);
                    break;

                case 0:
                    no_ct2.setChecked(true);
                    break;
            }
        }

        Byte strCT3 = caseRadiology.getCt3();
        if(strCT3 != null) {
            switch (strCT3) {
                case 1:
                    yes_ct3.setChecked(true);
                    break;

                case 0:
                    no_ct3.setChecked(true);
                    break;
            }
        }

        Byte strArrivedToCT = caseRadiology.getArrived_to_ct();
        if(strArrivedToCT != null) {
            switch (strArrivedToCT) {
                case 1:
                    yes_pt_arrived.setChecked(true);
                    break;

                case 0:
                    no_pt_arrived.setChecked(true);
                    break;
            }
        }

        Byte strCtComplete = caseRadiology.getCt_complete();
        if(strCtComplete != null) {
            switch (strCtComplete) {
                case 1:
                    yes_ct_complete.setChecked(true);
                    break;

                case 0:
                    no_ct_complete.setChecked(true);
                    break;
            }
        }

        Byte strIch = caseRadiology.getIch_found();
        if(strIch != null) {
            switch (strIch) {
                case 1:
                    yes_ich.setChecked(true);
                    break;

                case 0:
                    no_ich.setChecked(true);
                    break;
            }
        }

        Byte strProceed = caseRadiology.getDo_cta_ctp();
        if(strProceed != null) {
            switch (strProceed) {
                case 1:
                    yes_proceed.setChecked(true);
                    break;

                case 0:
                    no_proceed.setChecked(true);
                    break;
            }
        }

        Byte strCta_ctp_comp = caseRadiology.getCta_ctp_complete();
        if(strCta_ctp_comp != null) {
            switch (strCta_ctp_comp) {
                case 1:
                    yes_cta_com.setChecked(true);
                    break;

                case 0:
                    no_cta_com.setChecked(true);
                    break;
            }
        }

        Byte strLarge_vessel = caseRadiology.getLarge_vessel_occlusion();
        if(strLarge_vessel != null) {
            switch (strLarge_vessel) {
                case 1:
                    yes_large_vessel.setChecked(true);
                    break;

                case 0:
                    no_large_vessel.setChecked(true);
                    break;
            }
        }
    }

    protected void submitRadiologyInfo() {
        Log.i("RRRRr", "RRRRRRRRR");
        byte ct1, ct2, ct3, arrived, ctComplete, ich, proceed, ctp_complete, large_vessel;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updateCaseRadiologyApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        caseRadiologies.setCase_id(id);
        if (yes_ct1.isChecked()){
            ct1 = 1;
        }else {
            ct1 = 0;
        }
        caseRadiologies.setCt1(ct1);
        if (yes_ct2.isChecked()){
            ct2 = 1;
        }else {
            ct2 = 0;
        }
        caseRadiologies.setCt2(ct2);
        if (yes_ct3.isChecked()){
            ct3 = 1;
        }else {
            ct3 = 0;
        }
        caseRadiologies.setCt3(ct3);

        if (yes_pt_arrived.isChecked()){
            arrived = 1;
        }else {
            arrived = 0;
        }
        caseRadiologies.setArrived_to_ct(arrived);
        if (yes_ct_complete.isChecked()){
            ctComplete = 1;
        }else {
            ctComplete = 0;
        }
        caseRadiologies.setCt_complete(ctComplete);
        if (yes_ich.isChecked()){
            ich = 1;
        }else {
            ich = 0;
        }
        caseRadiologies.setIch_found(ich);

        if (yes_proceed.isChecked()){
            proceed = 1;
        }else {
            proceed = 0;
        }
        caseRadiologies.setDo_cta_ctp(proceed);
        if (yes_cta_com.isChecked()){
            ctp_complete = 1;
        }else {
            ctp_complete = 0;
        }
        caseRadiologies.setCta_ctp_complete(ctp_complete);
        if (yes_large_vessel.isChecked()){
            large_vessel = 1;
        }else {
            large_vessel = 0;
        }
        caseRadiologies.setLarge_vessel_occlusion(large_vessel);
        signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
        signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
        signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
        caseRadiologies.setSignoff_first_name(signoff_first_name);
        caseRadiologies.setSignoff_last_name(signoff_last_name);
        caseRadiologies.setSignoff_role(signoff_role);

        Call<CaseRadiologies> response = updateCaseRadiologyApi.updateCaseRadiologies(AUTH_HEADER, id, caseRadiologies);
        response.enqueue(new Callback<CaseRadiologies>() {
            @Override
            public void onResponse(Call<CaseRadiologies> call, Response<CaseRadiologies> response) {
                CaseRadiologies resp = response.body();
                if(resp != null){
                    Toast.makeText(getActivity(), "Radiology Updated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CaseRadiologies> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
