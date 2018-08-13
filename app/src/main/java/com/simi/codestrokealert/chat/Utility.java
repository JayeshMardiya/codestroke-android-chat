package com.simi.codestrokealert.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.simi.codestrokealert.SharedPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by nikhil on 21/9/17.
 */

public class Utility {

    public static final String FIREBASE_DB_URL = "https://fir-e64a1.firebaseio.com/";
    public static final String DB_USER = "User";
    public static final String DB_CHAT = "Chat";
    public static final String DB_RECENT = "RecentChat";
    public static String CURRENT_USER = "3";
    public static int CURRENT_USER_ID = 3;
    public static int KEY;

    public static LoginUserResponse loginUserResponse;

    public static boolean isDateEqual(long timestamp1, long timestamp2) {
        Calendar date1 = Calendar.getInstance();
        date1.setTimeInMillis(timestamp1);
        Calendar date2 = Calendar.getInstance();
        date2.setTimeInMillis(timestamp2);
        return date1.get(Calendar.DATE) == date2.get(Calendar.DATE);
    }

    public static String getDate(long timestamp) {
        Calendar messageStamp = Calendar.getInstance();
        messageStamp.setTimeInMillis(timestamp);
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DATE) == messageStamp.get(Calendar.DATE)) {
            return "today";
        } else if (now.get(Calendar.DATE) - messageStamp.get(Calendar.DATE) == 1) {
            return "yesterday";
        } else if (now.get(Calendar.YEAR) == messageStamp.get(Calendar.YEAR)) {
            return new SimpleDateFormat("MMMM dd", Locale.ENGLISH).format(timestamp);
        } else {
            return new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH).format(timestamp);
        }
    }

    public static String getOtherUser(String convName) {
        String[] users = convName.split("_");
        for (String user : users) {
            if (!user.equals(CURRENT_USER)) {
                return user;
            }
        }
        return "";
    }

    public static String getOtherUserId(String convId) {
        String[] users = convId.split("_");
        for (String user : users) {
            if (!user.equals(String.valueOf(CURRENT_USER_ID))) {
                return user;
            }
        }
        return "";
    }

    public static String getConversationId(String otherUserId) {
        try {
            Integer otherUserIdInt = Integer.valueOf(otherUserId);
            return otherUserIdInt > CURRENT_USER_ID ? String.valueOf(CURRENT_USER_ID).concat("_" + otherUserId)
                    : String.valueOf(otherUserIdInt).concat("_" + CURRENT_USER_ID);
        } catch (Exception e) {
            Log.e("Exception in Parsing", e.getMessage());
            return null;
        }
    }

    public static String getTime(long timestamp) {
        return new SimpleDateFormat("h:mm a", Locale.ENGLISH).format(timestamp);
    }

    public static float convertDpToPixel(int dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static void addMembersToPref(Context context,String groupId,String members){
        SharedPreferences preference=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=preference.edit();
        editor.putString(groupId,members);
        editor.apply();
    }

    public static String getMemberId(Context context,String groupId){
        SharedPreferences preference=PreferenceManager.getDefaultSharedPreferences(context);
        return preference.getString(groupId,"");
    }

 /*   public static String getMemberId(List<String> ) {
        String s = "";
        if ( loginUserResponse.data.size() > 0) {
            for (LoginUserResponse.Datum s1 : loginUserResponse.data) {
                s = TextUtils.isEmpty(s) ? "" + s1.getUserId()
                        : s.concat("," + s1.getUserId());
            }
        }
        return s;
    }*/
}
