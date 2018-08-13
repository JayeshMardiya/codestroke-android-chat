package com.simi.codestrokealert.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.simi.codestrokealert.R;


public class VerbalFragment extends Fragment {

    private View rootView;
    private Spinner verbalSpinner;
    private SendIndexVerbal sendIndexVerbal;
    public VerbalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_verbal, container, false);
        initViewS();

        verbalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                sendIndexVerbal.sendIdxVerbal(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return rootView;
    }

    protected void initViewS(){
        verbalSpinner = (Spinner)rootView.findViewById(R.id.spinner_verbal);
    }

    public interface SendIndexVerbal {
        void sendIdxVerbal(int index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sendIndexVerbal = (SendIndexVerbal) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
}
