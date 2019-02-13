package com.ezbuy.flutter.container;

import android.app.Application;

import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;

/**
 * author : yutianran
 * time   : 2019/01/30
 * desc   :
 * version: 1.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlutterMain.startInitialization(this);
//        GeneratedPluginRegistrant.registerWith(this);
    }
}
