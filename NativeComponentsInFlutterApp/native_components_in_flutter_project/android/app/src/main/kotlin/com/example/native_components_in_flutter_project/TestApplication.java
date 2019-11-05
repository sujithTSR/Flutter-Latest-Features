package com.example.native_components_in_flutter_project;

import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

import io.flutter.app.FlutterApplication;

public class TestApplication extends FlutterApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        Working Fine
        Log.v("TestApplication", "Let's get Started");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}