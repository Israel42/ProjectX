package com.example.projectx;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    private static final String FIRST_LAUNCH = "firstLaunch";
    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("FIRST_TIME",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(FIRST_LAUNCH, isFirstTime);
        editor.apply();
    }
    public boolean FirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }
}
