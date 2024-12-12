package com.example.myapp016avanocniappka

//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapp016avanocniappka.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//}

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp016avanocniappka.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval: Long = 1000 // 1 sekunda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nastavení View Bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the custom action bar
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)

        // Spuštění odpočítávání
        startCountdown()

        // Nastavení tlačítka pro obnovení
        binding.btnRefresh.setOnClickListener {
            startCountdown()
        }
    }

    private fun startCountdown() {
        handler.post(object : Runnable {
            override fun run() {
                updateCountdowns()
                handler.postDelayed(this, updateInterval)
            }
        })
    }

    private fun updateCountdowns() {
        // Aktuální datum a čas
        val currentTime = Calendar.getInstance()

        // Cílové datum pro Vánoce (25. prosince)
        val christmasTime = Calendar.getInstance().apply {
            set(currentTime.get(Calendar.YEAR), Calendar.DECEMBER, 24, 0, 0, 0)
        }
        if (currentTime.after(christmasTime)) {
            christmasTime.add(Calendar.YEAR, 1) // Pokud jsou Vánoce už pryč, nastav na příští rok
        }

        // Cílové datum pro Silvestra (31. prosince)
        val newYearTime = Calendar.getInstance().apply {
            set(currentTime.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59, 59)
        }
        if (currentTime.after(newYearTime)) {
            newYearTime.add(Calendar.YEAR, 1) // Pokud je Silvestr už pryč, nastav na příští rok
        }

        // Odpočty
        val christmasCountdown = calculateTimeDifference(currentTime, christmasTime)
        val newYearCountdown = calculateTimeDifference(currentTime, newYearTime)

        // Aktualizace UI
        binding.tvChristmasCountdown.text = christmasCountdown
        binding.tvNewYearCountdown.text = newYearCountdown
    }

    private fun calculateTimeDifference(from: Calendar, to: Calendar): String {
        val diffMillis = to.timeInMillis - from.timeInMillis

        val days = diffMillis / (24 * 60 * 60 * 1000)
        val hours = (diffMillis / (60 * 60 * 1000)) % 24
        val minutes = (diffMillis / (60 * 1000)) % 60
        val seconds = (diffMillis / 1000) % 60

        return String.format(Locale.getDefault(), "%02d dní, %02d hodin, %02d minut, %02d sekund", days, hours, minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Zastavení handleru při zničení aktivity
    }
}
