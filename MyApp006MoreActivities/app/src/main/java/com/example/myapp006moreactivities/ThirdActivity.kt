package com.example.myapp006moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.third_layout)

        val twInfo = findViewById<TextView>(R.id.tvDatathird)

        //Load data from intent
        val secondData = intent.getStringExtra("Second Data")
        twInfo.text = "Data from second activity: $secondData"

        val btnBack = findViewById<Button>(R.id.btnCLose)
        btnBack.setOnClickListener {
            finish()
        }



    }
}