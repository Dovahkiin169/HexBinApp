package com.verkhovskygroup.HexBinApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity
 {
    private final static int THEME_White = 1;
    private final static int THEME_Dark = 2;
    boolean CheckStatus = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
        updateTheme();
     }
    public void updateTheme()
     {
        if (Utility.getTheme(getApplicationContext()) <= THEME_White)
         {
            CheckStatus=true;
            setTheme(R.style.AppTheme);
         }
        else if (Utility.getTheme(getApplicationContext()) == THEME_Dark)
         {
            setTheme(R.style.AppThemeDark);
            CheckStatus=false;
         }
     }
    public boolean GetStatus()
     {
        return CheckStatus;
     }
 }