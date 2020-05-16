package com.alinus.od

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

class SampleFragment : Fragment(R.layout.fragment_sample) {
    private lateinit var orientationTextView: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orientationTextView = view.findViewById(R.id.message)
        MainActivity.pitchLiveData.observe(viewLifecycleOwner, Observer {
            orientationTextView.text = it
        })
    }
}