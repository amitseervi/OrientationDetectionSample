package com.alinus.od

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class SampleFragment : Fragment(R.layout.fragment_sample) {
    private lateinit var orientationTextView: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orientationTextView = view.findViewById(R.id.message)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("SampleFragment", "On Config changed")
        orientationTextView.text = newConfig.orientation.toString()
    }
}