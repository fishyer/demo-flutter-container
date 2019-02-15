package com.ezbuy.flutter.container.flutter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

/**
 * author : yutianran
 * time   : 2019/02/14
 * desc   :
 * version: 1.0
 */
public class HomeFlutterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlutterView homePage = Flutter.createView(
                this,
                getLifecycle(),
                "homePage"
        );
        setContentView(homePage);
    }
}
