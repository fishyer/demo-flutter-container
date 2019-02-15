package com.ezbuy.flutter.container.flutter;

import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

/**
 * debug模式原生跳转到flutter界面会出现白屏，release包就不会出现白屏了
 */
public class MainFlutterActivity extends FlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
  }
}
