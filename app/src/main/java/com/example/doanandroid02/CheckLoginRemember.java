package com.example.doanandroid02;

import android.content.Context;
import android.content.SharedPreferences;

public class CheckLoginRemember {
    public static int checkLoginRemember(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE.txt", Context.MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("status", false);
        if (check) {
            sharedPreferences.getString("name", "");
            sharedPreferences.getString("email", "");
            sharedPreferences.getString("password", "");
            sharedPreferences.getString("token", "");
            return 1;
        }
        return -1;
    }
}
