package com.example.myapp02linearlayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.et_test)
        val textView = findViewById<TextView>(R.id.tv_list)
        val button = findViewById<Button>(R.id.btn_add)
        val deleteButton = findViewById<Button>(R.id.btn_delete)

        button.setOnClickListener {
            val newText = editText.text.toString()
            textView.append("a fa fa \n$newText")
            editText.text.clear()

        }

    }

}