package com.simi.codestrokealert;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility {

    public int getAge(Calendar dob){
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

    public int convertDobToAge(String dob){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(sdf.parse(dob));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        int age = new Utility().getAge(calendar);
        return age;
    }
}
