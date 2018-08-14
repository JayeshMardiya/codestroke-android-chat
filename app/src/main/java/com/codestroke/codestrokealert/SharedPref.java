package com.codestroke.codestrokealert;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPreferences mSharedPref;
    public static final String FIRST_NAME           = "first_name";
    public static final String LAST_NAME            = "last_name";
    public static final String GENDER               = "gender";
    public static final String ADDRESS              = "address";
    public static final String NOK                  = "nok";
    public static final String NOK_PHONE            = "nok_phone";
    public static final String LAST_SEEN            = "last_seen";
    public static final String PAST_MEDICAL_HISTORY = "past_medical";
    public static final String MEDICATIONS          = "medications";
    public static final String ANTICOAGULANTS       = "anticoagulants";
    public static final String SITIUATION           = "sitiuation";
    public static final String WEIGHT               = "weight";
    public static final String FACIAL_DROOP         = "facial_droop";
    public static final String ARM_DRIFT            = "arm_drift";
    public static final String WEAK_GRIP            = "weak_grip";
    public static final String SPEACH_DIFFICULTY    = "speach_difficulty";
    public static final String BLOOD_PRESSURE       = "blood_pressure";
    public static final String HEART_RATE           = "heart_rate";
    public static final String HEART_RYTHM          = "heart_rythm";
    public static final String RESPIRATORY_RATE     = "respiratory";
    public static final String OXYGEN_SATURATION    = "oxygen";
    public static final String TEMPERATURE          = "temperature";
    public static final String BLOOD_GLUCOSE        = "blood_glucose";
    public static final String GCS                  = "gcs";
    public static final String FACIAL_PALSY         = "facial_palsy";
    public static final String ARM_MOTOR_IMPAIRMENT = "arm_motor";
    public static final String LEG_MOTOR_IMPAIRMENT = "leg_motor";
    public static final String HEAD_DEVIATION       = "deviation";
    public static final String CANNULA              = "cannula";
    public static final String INCOMING_CASES       = "incoming";
    public static final String PATIANT_ID           = "id";
    public static final String CASE_ID              = "case_id";
    public static final String AGE                  = "age";
    public static final String SIGNOFF_FIRST_NAME   = "first_name";
    public static final String SIGNOFF_LAST_NAME    = "last_name";
    public static final String SIGNOFF_ROLE         = "role";

    public static void init(Context context){
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue){
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value){
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue){
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value){
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue){
        return  mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, int value){
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }
}
