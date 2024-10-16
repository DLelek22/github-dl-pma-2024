package com.example.myapp006moreactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.second_layout)

        val tvDataSecond = findViewById<TextView>(R.id.tvDataSecond)
        val etDatasecond = findViewById<EditText>(R.id.etDatasecond)

        //Load data from intent
        val data = intent.getStringExtra("Data")
        tvDataSecond.text = "Data freom first page: $data"


        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        val btnThird = findViewById<Button>(R.id.btnThird)

        btnThird.setOnClickListener {
            val secondNickname = etDatasecond.text.toString()
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("Second Data", secondNickname)
            startActivity(intent)
            recreate()
        }
    }
}