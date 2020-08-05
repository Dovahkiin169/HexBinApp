package com.verkhovskygroup.HexBinApp;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager ;

public class Utility
 {
    public static void setTheme(Context context, int theme)
     {
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         prefs.edit().putInt(context.getString(R.string.prefs_theme_key), theme).apply();
     }

    public static int getTheme(Context context)
     {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(context.getString(R.string.prefs_theme_key), -1);
     }
     public static void setData(Context context, String data)
     {
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         prefs.edit().putString(context.getString(R.string.prefs_data_key), data).apply();
     }

     public static String getData(Context context)
     {
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         return prefs.getString(context.getString(R.string.prefs_data_key), "");
     }
     public static void setSign(Context context, String data)
     {
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         prefs.edit().putString(context.getString(R.string.prefs_sign_key), data).apply();
     }

     public static String getSign(Context context)
     {
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
         return prefs.getString(context.getString(R.string.prefs_sign_key), "");
     }
 }
