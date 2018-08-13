package com.simi.codestrokealert.activity.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.simi.codestrokealert.R;
import com.simi.codestrokealert.SharedPref;
import com.simi.codestrokealert.activity.clinicians.HomeScreenActivity;
import com.simi.codestrokealert.activity.paramedics.PatientDetailsActivity;
import com.simi.codestrokealert.chat.FirebaseManager;
import com.simi.codestrokealert.chat.LoginUserResponse;
import com.simi.codestrokealert.chat.Utility;
import com.simi.codestrokealert.model.rest.RequestInterface;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginUserActivity extends AppCompatActivity {

    private EditText et_first_name, et_last_name, et_password;
    private Button btn_login;
    private String firstName, lastName, password, role, category;
    private Spinner spinner_role, spinner_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get translucent status bar and push activity layout to status bar for the gradient to work for status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_login);
        initViews();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = et_first_name.getText().toString();
                lastName = et_last_name.getText().toString();
                password = et_password.getText().toString();
                role = "paramedic";
                category = "Paramedic";

                //Check whether username or password is empty or not
                if (!firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty() && !category.isEmpty() && !role.isEmpty()) {
                    category = spinner_category.getSelectedItem().toString();
                    role = spinner_role.getSelectedItem().toString();
                    loginProcess(firstName, lastName, password, role, category);
                    Log.i("detail1: ", firstName + " " + lastName + " " + password + " " + role + " " + category);

                } else {
                    Toast.makeText(getBaseContext(), "Field is empty !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner_role);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
    }

    protected void initViews() {
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_last_name = (EditText) findViewById(R.id.et_last_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        spinner_role = (Spinner) findViewById(R.id.spinner_role);
        spinner_category = (Spinner) findViewById(R.id.spinner_category);

    }

    protected void loginProcess(String firstname, String lastname, String pass, String strRole, String strCategory) {

        Log.i("detail2: ", firstname + " " + lastname + " " + password + " " + strRole + " " + strCategory);
        //User validation
        if (pass.equals("changethislater")) {
            SharedPref.write(SharedPref.SIGNOFF_FIRST_NAME, firstname);
            SharedPref.write(SharedPref.SIGNOFF_LAST_NAME, lastname);
            SharedPref.write(SharedPref.SIGNOFF_ROLE, strRole);
            login(firstname, lastname, role, strRole, strCategory);

        } else {
            Toast.makeText(getBaseContext(), "password incorrect.", Toast.LENGTH_SHORT).show();
        }
    }


    private void login(final String firstname, String lastname, String role, String strRole,
                       final String strCategory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.15.42.249/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface getCasesApi = retrofit.create(RequestInterface.class);

        //  String base = "changethislater";
        //  String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        Call<LoginUserResponse> response = getCasesApi.login("application/json",
                firstname, lastname, role, strCategory, "token"
        );
        response.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                if (response != null && response.isSuccessful() && response.body().data != null) {
                    Utility.loginUserResponse = response.body();
                    Utility.CURRENT_USER_ID = response.body().loginUserId;
                    Utility.CURRENT_USER = firstname;
                    if (strCategory.equals("Clinician")) {
                        FirebaseManager.loginAnonymously(LoginUserActivity.this);
                        startActivity(new Intent(getBaseContext(), HomeScreenActivity.class));
                    }
                    if (strCategory.equals("Paramedic")) {
                        startActivity(new Intent(getBaseContext(), PatientDetailsActivity.class));
                    }
                } else {
                    Log.i("ELse", response.message());
                }

            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
