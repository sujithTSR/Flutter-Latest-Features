package com.example.native_components_in_flutter_project

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class Main2Activity : FlutterActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        findViewById<Button>(R.id.back).setOnClickListener {
            sentDataToFlutter()
        }
        findViewById<Button>(R.id.create_leak).setOnClickListener {
//            DownloadTask().start()
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


    fun start(context: Context) {
        val starter = Intent(context, Main2Activity::class.java)
        context.startActivity(starter)
    }

    private inner class DownloadTask : Thread() {
        override fun start() {
            super.start()
            start()
        }

        override fun run() {
            SystemClock.sleep((2000 * 10).toLong())
        }
    }
}

