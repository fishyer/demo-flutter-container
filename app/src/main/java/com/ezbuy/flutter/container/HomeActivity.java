package com.ezbuy.flutter.container;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

public class HomeActivity extends FlutterContainerActivity {

    FlutterView flutterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FrameLayout frFlutter = findViewById(R.id.fr_flutter);
        flutterView = getFlutterView("/simpleView");
//        flutterView = getFlutterView("/simple/this is native args");
        frFlutter.addView(flutterView);


//        initMethodChannel(flutterView);
//        flutterView.post(new Runnable() {
//            @Override
//            public void run() {
//                initMethodChannel(flutterView);
//            }
//        });
        flutterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                initMethodChannel(flutterView);
            }
        }, 3000);

    }
}
