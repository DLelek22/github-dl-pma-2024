package com.example.myapp006moreactivities

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp006moreactivities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnSendfirst = findViewById<Button>(R.id.btnSendfirst)
        val etDatafirst = findViewById<EditText>(R.id.etDatafirst)
        setSupportActionBar(findViewById(R.id.my_toolbar))


        btnSendfirst.setOnClickListener {
            val data =etDatafirst.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("Data", data)
            startActivity(intent)
        }

    }
}