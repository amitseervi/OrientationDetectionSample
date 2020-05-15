package com.alinus.od

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, SampleFragment(), "sampleFragment")
        ft.addToBackStack("sampleFragment")
        ft.commit()
        Log.i("activity_lifecycle", "OnCreate")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("MainActivity", "On Config changed")
    }

    override fun onResume() {
        super.onResume()
        Log.i("activity_lifecycle", "OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("activity_lifecycle", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("activity_lifecycle", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity_lifecycle", "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.i("activity_lifecycle", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i("activity_lifecycle", "onStop")
    }
}
