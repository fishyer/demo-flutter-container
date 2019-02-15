package com.ezbuy.flutter.container.flutter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.ezbuy.flutter.container.R;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

public class ChannelFlutterActivity extends AppCompatActivity {

    private static final String CHANNEL = "com.ezbuy.flutter";

    FlutterView flutterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        FrameLayout frFlutter = findViewById(R.id.fr_flutter);
        flutterView = getFlutterView("channelPage");
        frFlutter.addView(flutterView);
        flutterView.post(new Runnable() {
            @Override
            public void run() {
                initMethodChannel(flutterView);
            }
        });
    }

    public FlutterView initMethodChannel(FlutterView flutterView) {
        MethodChannel channel = new MethodChannel(flutterView, CHANNEL);
        //1.原生调用Flutter方法
        channel.invokeMethod("invokeFlutterMethod", "hello,flutter", new MethodChannel.Result() {
            @Override
            public void success(@Nullable Object o) {
                Log.i("flutter","1.原生调用invokeFlutterMethod-success:"+o.toString());
            }

            @Override
            public void error(String s, @Nullable String s1, @Nullable Object o) {
                Log.i("flutter","1.原生调用invokeFlutterMethod-error");
            }

            @Override
            public void notImplemented() {
                Log.i("flutter","1.原生调用invokeFlutterMethod-notImplemented");
            }
        });
        Log.i("flutter","1.原生调用invokeFlutterMethod");
        //4.Flutter调用原生方法的监听
        channel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                switch (call.method) {
                    case "invokeNativeMethod":
                        String args = (String) call.arguments;
                        Log.i("flutter","4.原生执行invokeNativeMethod："+args);
                        result.success(200);
                        break;
                    default:
                }
            }
        });
        return flutterView;
    }

    public FlutterView getFlutterView(String initialRoute) {
        return Flutter.createView(
                this,
                getLifecycle(),
                initialRoute
        );
    }
}
