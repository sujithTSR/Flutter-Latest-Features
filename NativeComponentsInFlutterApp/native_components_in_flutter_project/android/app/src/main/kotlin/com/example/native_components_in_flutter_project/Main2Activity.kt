package com.example.native_components_in_flutter_project

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel


class Main2Activity : FlutterActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        findViewById<Button>(R.id.back).setOnClickListener {
            sentDataToFlutter()
        }
    }

    fun sentDataToFlutter(){
        Log.v("MainActivity","Test")
//          result.success(greetings)
        MethodChannel(flutterView, CHANNEL).invokeMethod("fromAndroid", "test")
        finish()
    }

    companion object{
        private const val CHANNEL = "flutter.native.within/channel"

    }
}

