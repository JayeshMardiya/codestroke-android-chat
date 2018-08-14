package com.codestroke.codestrokealert.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codestroke.codestrokealert.Constants;
import com.codestroke.codestrokealert.R;
import com.codestroke.codestrokealert.SharedPref;
import com.codestroke.codestrokealert.model.Cases;
import com.codestroke.codestrokealert.model.CasesResponse;
import com.codestroke.codestrokealert.model.rest.RequestInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.codestroke.codestrokealert.Constants.AUTH_HEADER;


public class PatientDetailsFragment extends Fragment implements View.OnClickListener{

    View rootView;
    private EditText dob, first_name, last_name, address, last_seen, nok, nok_contact, medicare;
    private ImageButton calenderIcon;
    private DatePickerDialog datePickerDialog;
    private Button submit;
    private RadioButton unspecified, female, male;
    private int id;
    private enum Gender{m, f, u}
    private Cases cases;
    private String strGender, strAddress, strLastSeen, strDob, strNokContact, strNok, strFirstName,
            strLastName, strMedicare, signoff_first_name, signoff_last_name, signoff_role;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPatientDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_patient_details, container, false);
        initViews();

        getPatientDetails();

        calenderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateToDOB();
            }
        });



        return rootView;
    }


    protected void initViews(){
       dob = (EditText)rootView.findViewById(R.id.dob);
       calenderIcon = (ImageButton)rootView.findViewById(R.id.calender);
       male = (RadioButton)rootView.findViewById(R.id.male);
       female = (RadioButton)rootView.findViewById(R.id.female);
       unspecified = (RadioButton)rootView.findViewById(R.id.unspecified);
       first_name = (EditText)rootView.findViewById(R.id.et_first_name);
       last_name = (EditText)rootView.findViewById(R.id.last_name);
       address = (EditText)rootView.findViewById(R.id.et_address);
       nok_contact = (EditText)rootView.findViewById(R.id.nok_contact);
       nok = (EditText)rootView.findViewById(R.id.et_next_of_kin);
       last_seen = (EditText)rootView.findViewById(R.id.last_seen);
       medicare = (EditText)rootView.findViewById(R.id.medicare);
       submit = (Button)rootView.findViewById(R.id.submit_btn);
       submit.setOnClickListener(this);
       unspecified.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.male:
                strGender = Gender.m.name();
                break;

            case R.id.female:
                strGender = Gender.f.name();
                break;

            case R.id.unspecified:
                strGender = Gender.u.name();
                break;

            case R.id.submit_btn:
                tappedSubmitButton();
                break;

        }

    }


    protected void setDateToDOB(){

        final Calendar dateSelected = Calendar.getInstance();
        final Calendar now = Calendar.getInstance();
        final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth);
                if(dateSelected.after(now)){
                    Toast.makeText(getActivity(), "Can't be born in the future", Toast.LENGTH_SHORT).show();

                }else {
                    String birthdayDate = dateFormatter.format(dateSelected.getTime());
                    dob.setText(birthdayDate);

                }
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();

    }

    protected void getPatientDetails(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface getPatientApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        Call<CasesResponse> responsePatientDetails =  getPatientApi.getPatient(AUTH_HEADER, id);
        responsePatientDetails.enqueue(new Callback<CasesResponse>() {
            @Override
            public void onResponse(Call<CasesResponse> call, Response<CasesResponse> response) {
                List<Cases> patients = response.body().getResults();
                if(patients != null){
                    cases = patients.get(0);
                    setupViews(cases.getFirst_name(), cases.getLast_name(), cases.getDob(), cases.getAddress(),
                            cases.getGender(), cases.getLast_well(), cases.getNok(), cases.getNok_phone(), cases.getMedicare_no());
                }

            }

            @Override
            public void onFailure(Call<CasesResponse> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void setupViews( String str_first_name, String str_last_name, String strDob, String str_address,
                               String str_gender, String str_last_well, String str_nok, String str_nok_phone, String str_medicare_no){

        first_name.setText(str_first_name);
        last_name.setText(str_last_name);
        dob.setText(strDob);
        address.setText(str_address);
        last_seen.setText(str_last_well);
        nok.setText(str_nok);
        nok_contact.setText(str_nok_phone);
        medicare.setText(str_medicare_no);
        if(str_gender != null) {
            switch (str_gender) {
                case "m":
                    male.setChecked(true);
                    break;

                case "f":
                    female.setChecked(true);
                    break;

                case "u":
                    unspecified.setChecked(true);
                    break;
            }
        }
    }

    protected void tappedSubmitButton(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface updatePatientDetailsApi = retrofit.create(RequestInterface.class);
        id = SharedPref.read(SharedPref.PATIANT_ID, -1);
        strAddress = address.getText().toString();
        cases.setAddress(strAddress);
        cases.setCase_id(id);
        cases.setGender(strGender);
        strNokContact = nok_contact.getText().toString();
        cases.setNok_phone(strNokContact);
        strNok = nok.getText().toString();
        cases.setNok(strNok);
        strFirstName = first_name.getText().toString();
        cases.setFirst_name(strFirstName);
        strLastName = last_name.getText().toString();
        cases.setLast_name(strNokContact);
        strMedicare = medicare.getText().toString();
        cases.setMedicare_no(strMedicare);
        signoff_first_name = SharedPref.read(SharedPref.SIGNOFF_FIRST_NAME, null);
        signoff_last_name = SharedPref.read(SharedPref.SIGNOFF_LAST_NAME, null);
        signoff_role = SharedPref.read(SharedPref.SIGNOFF_ROLE, null);
        cases.setSignoff_role(signoff_role);
        cases.setSignoff_last_name(signoff_last_name);
        cases.setSignoff_first_name(signoff_first_name);
       // strDob = dob.getText().toString();

        Call<Cases> response = updatePatientDetailsApi.updatePatientDetails(AUTH_HEADER, id, cases);
        response.enqueue(new Callback<Cases>() {
            @Override
            public void onResponse(Call<Cases> call, Response<Cases> response) {
                Cases resp = response.body();
                if(resp != null){
                    Toast.makeText(getActivity(), "Patient Details Updated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cases> call, Throwable t) {
                if(getActivity() != null) {
                    Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
