package com.example.myapp007toastsnackbar

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.view.View
import kotlinx.coroutines.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import android.widget.Toast
import com.example.myapp007toastsnackbar.databinding.ActivityMainBinding
import android.view.Gravity
import android.graphics.Color
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var job: Job? = null
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val btnstartstop = findViewById<Button>(R.id.btnStartStop)
        //val btnreset = findViewById<Button>(R.id.btn_reset)
        //val btnsplit = findViewById<Button>(R.id.btn_mezicas)

        binding.btnStartStop.setOnClickListener {
            if (job == null || job?.isCancelled == true) {
                startCounter()
            } else {
                stopCounter()
            }
        }

        binding.btnReset.setOnClickListener {
            resetCounter()
            stopCounter()
           // val toastReset = Toast.makeText(this@MainActivity, "TIMER has reset", Toast.LENGTH_LONG)

            val customToastLayout = layoutInflater.inflate(R.layout.custom_layout,null)
            val customToast = Toast(this)
            customToast.view = customToastLayout
            customToast.setGravity(Gravity.BOTTOM,0,0)
            customToast.duration = Toast.LENGTH_SHORT
            customToast.show()



        }

        binding.btnMezicas.setOnClickListener {
            SplitTime()
        }

        binding.btnSnackbar.setOnClickListener {

                Snackbar.make(binding.root, "Já jsem SNACKBAR",Snackbar.LENGTH_SHORT)

                .setDuration(7000)
                .setBackgroundTint(Color.parseColor("#4D4D4D"))
                .setTextColor(Color.WHITE)
                .setActionTextColor(Color.WHITE)
                .setAction("Zavřít") {
                    Toast.makeText(this, "Zavírám SNACKBAR", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }
    private fun startCounter() {
        job = CoroutineScope(Dispatchers.Main).launch {

            while (isActive) {
                val tv_cas = findViewById<TextView>(R.id.tv_cas)
                val duration = counter.seconds
                val formattedTime = duration.toComponents { hours, minutes, seconds, _ ->
                    "%02d:%02d:%02d".format(hours, minutes, seconds)
                }
                tv_cas.text = formattedTime.toString()
                counter++
                delay(1000L) // Delay for 1 second
            }

        }
    }

    private fun stopCounter() {
        job?.cancel()
    }

    private fun resetCounter(){
        counter = 0
        val tv_cas = findViewById<TextView>(R.id.tv_cas)
        val duration = counter.seconds
        val tv_mezicas = findViewById<TextView>(R.id.tv_mezicas)
        val formattedTime = duration.toComponents { hours, minutes, seconds, _ ->
            "%02d:%02d:%02d".format(hours, minutes, seconds)
        }
        tv_cas.text = formattedTime.toString()
        tv_mezicas.text = ""
    }

    private fun SplitTime(){

        val tv_mezicas = findViewById<TextView>(R.id.tv_mezicas)
        val duration = counter.seconds
        val hours = duration.inWholeHours
        val minutes = (duration - hours.hours).inWholeMinutes
        val seconds = (duration - hours.hours - minutes.minutes).inWholeSeconds

        when{
            counter > 60 -> tv_mezicas.append("Time elapsed: $minutes min $seconds sec\n")
            counter > 3600 -> tv_mezicas.append("Time elapsed: $hours h $minutes min a $seconds sec\n")
            else -> tv_mezicas.append("Time elapsed: $seconds sec\n")
        }

    }

}