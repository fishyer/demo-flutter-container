package com.ezbuy.flutter.container;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

/**
 * Flutter容器
 * <p>
 * author : yutianran
 * time   : 2019/01/30
 * desc   : debug模式原生跳转到flutter界面会出现白屏，release包就不会出现白屏了！！！
 * version: 1.0
 */
public class FlutterContainerActivity extends AppCompatActivity {

    private static final String CHANNEL = "com.ezbuy.flutter";

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
                        //测试Flutter写入的SP能否在原生读取到
                        SharedPreferences sharedPreferences=getSharedPreferences(SplashActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                        String spCache = sharedPreferences.getString("sp_cache", "");
                        Log.i("flutter","读取Flutter存储的spCache:"+spCache);
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
