package com.example.myapp01linearlayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        */


        //Jednotlivé reference na EditText
        val etName = findViewById<EditText>(R.id.etName)
        val etSurname = findViewById<EditText>(R.id.etSurname)
        val etPlace = findViewById<EditText>(R.id.etPlace)
        val etAge = findViewById<EditText>(R.id.etAge)
        val text_view = findViewById<TextView>(R.id.text_view)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)

        //Nastavení tlačítka odeslat (=btn1)
        btn1.setOnClickListener {
            val name = etName.text.toString()
            val surname = etSurname.text.toString()
            val place = etPlace.text.toString()
            val age = etAge.text.toString()

            //zapsání do textview
            val formatedText = "Jmenuji se $name $surname, je mi $age let a mým bydlištěm je $place"
            text_view.text = formatedText
        }

        //Nastavení tlačítka Smazat (=btn2)
        btn2.setOnClickListener {
            etName.text.clear()
            etSurname.text.clear()
            etAge.text.clear()
            etPlace.text.clear()
            text_view.text = ""
        }


        //val btn1 = findViewById(R.id.btn1) as Button

        btn1.setOnClickListener {
            // your code to perform when the user clicks on the button
            // text_view.arg.val.
            val text: TextView = findViewById(R.id.text_view) as TextView
            btn1.setOnClickListener {
                //text.setText(getText(etName))

            }
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }



    }
}