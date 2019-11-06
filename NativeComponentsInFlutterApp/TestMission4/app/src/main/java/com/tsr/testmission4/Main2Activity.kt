package com.tsr.testmission4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)
        findViewById<Button>(R.id.btn2).setOnClickListener{
            intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }
    }
}