package com.ezbuy.flutter.container;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "FlutterSharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = "ezbuy-cache";

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("sp_cache", "sp_native_value").commit();
//        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
