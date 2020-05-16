package com.alinus.od

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Display
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlin.math.abs
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), SensorEventListener2 {
    private var mSensorManager: SensorManager by Delegates.notNull<SensorManager>()
    private var mRotationSensor: Sensor by Delegates.notNull<Sensor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, SampleFragment(), "sampleFragment")
        ft.addToBackStack("sampleFragment")
        ft.commit()
        try {
            mSensorManager = getSystemService(Activity.SENSOR_SERVICE) as SensorManager
            mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
            mSensorManager.registerListener(
                this,
                mRotationSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onFlushCompleted(sensor: Sensor?) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor === mRotationSensor) {
            if (event!!.values.size > 4) {
                val truncatedRotationVector = FloatArray(4)
                System.arraycopy(event.values, 0, truncatedRotationVector, 0, 4)
                update(truncatedRotationVector)
            } else {
                update(event.values)
            }
        }
    }

    private fun update(vectors: FloatArray) {
        val rotationMatrix = FloatArray(9)
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors)
        val worldAxisX = SensorManager.AXIS_X
        val worldAxisZ = SensorManager.AXIS_Z
        val adjustedRotationMatrix = FloatArray(9)
        SensorManager.remapCoordinateSystem(
            rotationMatrix,
            worldAxisX,
            worldAxisZ,
            adjustedRotationMatrix
        )
        val orientation = FloatArray(3)
        SensorManager.getOrientation(adjustedRotationMatrix, orientation)
        val roll = orientation[2] * RAD_TO_DEG_MULTIPLIER
        val rotationDegree = abs(roll)
        if (rotationDegree < 45f) {
            pitchLiveData.postValue("Potrait : 1")
        } else {
            if (roll < 0) {
                pitchLiveData.postValue("Landscape : 3")
            } else {
                pitchLiveData.postValue("Landscape : 2")
            }
        }
    }

    companion object {
        val pitchLiveData: MutableLiveData<String> = MutableLiveData()
        private const val RAD_TO_DEG_MULTIPLIER: Float = -57.2958f
    }
}
