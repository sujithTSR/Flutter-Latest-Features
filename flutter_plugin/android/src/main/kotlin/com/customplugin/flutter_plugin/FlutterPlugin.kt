package com.customplugin.flutter_plugin

import android.app.Activity
import android.content.Intent
import com.tsr.test_lib.MainActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterPlugin: MethodCallHandler {
  companion object {
    var activity: Activity? = null
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      activity = registrar.activity()
      val channel = MethodChannel(registrar.messenger(), "flutter_plugin")
      channel.setMethodCallHandler(FlutterPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
      val intent = Intent(activity, MainActivity::class.java)
      activity?.startActivity(intent)
    } else {
      result.notImplemented()
    }
  }
}
