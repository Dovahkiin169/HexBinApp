package com.verkhovskygroup.HexBinApp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class BaseActivity extends AppCompatActivity {
    private final static int THEME_White = 1;
    private final static int THEME_Dark = 2;
    boolean CheckStatus = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();
    }

    public void updateTheme() {
        if (Utility.getTheme(getApplicationContext()) <= THEME_White) {
            CheckStatus = true;
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (Utility.getTheme(getApplicationContext()) == THEME_Dark) {
            CheckStatus = false;
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public boolean GetStatus() {
        return CheckStatus;
    }
}