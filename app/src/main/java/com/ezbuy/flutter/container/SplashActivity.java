package com.ezbuy.flutter.container;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ezbuy.flutter.container.flutter.HomeFlutterActivity;
import com.ezbuy.flutter.container.flutter.MainFlutterActivity;
import com.ezbuy.flutter.container.flutter.ChannelFlutterActivity;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME = "FlutterSharedPreferences";
    private SlimAdapter slimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initSP();
        initRv();
        initData();
    }

    private void initData() {
        List<TestItem> data = new ArrayList<>();
        data.add(new TestItem("Flutter默认页面", MainFlutterActivity.class));
        data.add(new TestItem("首页", HomeFlutterActivity.class));
        data.add(new TestItem("View嵌入+传参", ChannelFlutterActivity.class));
        slimAdapter.updateData(data);
    }

    private void initRv() {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        slimAdapter = SlimAdapter.create()
                .register(R.layout.item_test, new SlimInjector<TestItem>() {
                    @Override
                    public void onInject(final TestItem data, IViewInjector injector) {
                        injector.text(R.id.tv, data.name)
                                .clicked(R.id.root, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(SplashActivity.this, data.target);
                                        startActivity(intent);
                                    }
                                });
                    }
                })
                .attachTo(rv);
    }

    private void initSP() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("sp_cache", "sp_native_value").commit();
    }

    public static class TestItem {
        String name;
        Class<?> target;

        public TestItem(String name, Class<? extends Activity> target) {
            this.name = name;
            this.target = target;
        }
    }

}


