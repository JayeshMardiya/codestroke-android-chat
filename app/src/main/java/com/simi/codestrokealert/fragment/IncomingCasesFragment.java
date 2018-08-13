package com.simi.codestrokealert.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.Utility;
import com.simi.codestrokealert.activity.clinicians.CliniciansPatientDetails;
import com.simi.codestrokealert.activity.clinicians.RecyclerViewItemClickListener;
import com.simi.codestrokealert.adapter.PatientRecyclerViewAdapter;
import com.simi.codestrokealert.model.Cases;
import com.simi.codestrokealert.model.Patient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class IncomingCasesFragment extends Fragment implements RecyclerViewItemClickListener {

    private PatientRecyclerViewAdapter mAdapter;
    private List<Patient> patients;
    private int id;

    public IncomingCasesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        patients = new ArrayList<>();

        Bundle bundle = getArguments();
        String incomingList = bundle.getString("incoming");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Cases>>() {
        }.getType();
        List<Cases> casesList = gson.fromJson(incomingList, type);

        for (int i = 0; i < casesList.size(); i++) {
            String first_name = casesList.get(i).getFirst_name();
            String last_name = casesList.get(i).getLast_name();
            String gender = casesList.get(i).getGender();
            String dob = casesList.get(i).getDob();
            int age = new Utility().convertDobToAge(dob);
            id = casesList.get(i).getCase_id();
            if (gender.equals("m")) {
                gender = "male";
            }
            if (gender.equals("f")) {
                gender = "female";
            }
            String name = first_name + " " + last_name;
            patients.add(new Patient(name, gender, age, "", id));

        }


        mAdapter = new PatientRecyclerViewAdapter(patients, getContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setClickListener(this);
        return recyclerView;
    }

    @Override
    public void onClick(View view, int position) {
        final Patient patient = patients.get(position);
        SharedPref.write(SharedPref.PATIANT_ID, patient.getId());
        Intent intent = new Intent(getActivity(), CliniciansPatientDetails.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}
