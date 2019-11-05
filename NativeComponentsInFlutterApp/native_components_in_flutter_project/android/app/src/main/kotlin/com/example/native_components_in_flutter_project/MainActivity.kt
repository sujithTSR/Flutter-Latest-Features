package com.example.native_components_in_flutter_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
    MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
      if (call.method == "helloFromNativeCode") {
        val greetings = helloFromNativeCode()
        result.success(greetings)
        intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)

      }
    }

  }

  private fun helloFromNativeCode(): String {
    return "Hello from the Native Code!"
  }

  companion object {
    private const val CHANNEL = "flutter.native.within/channel"
  }
}

