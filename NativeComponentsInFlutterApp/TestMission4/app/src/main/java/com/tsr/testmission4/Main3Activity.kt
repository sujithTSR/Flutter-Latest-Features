package com.tsr.testmission4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity3_main)
        findViewById<Button>(R.id.btn3).setOnClickListener{
//            TODO:: Flutter Operation - Open Flutter Screen.
//            intent = Intent(this, Main3Activity::class.java)
//            startActivity(intent)
        }
    }
}