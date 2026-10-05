package com.kotlin.basic.trpl3c

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("siklus","On Create")
    }

    override fun onStart() {
        super.onStart()
        Log.d("siklus","On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("siklus","On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("siklus","On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("siklus","On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("siklus","On Destroy")
    }
}