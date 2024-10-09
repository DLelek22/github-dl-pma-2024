package com.example.myapp04linearlayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.myapp04linearlayout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSummary.setOnClickListener {
            val pizza_group = binding.radiogPizza.checkedRadioButtonId
            val pizza = findViewById<RadioButton>(pizza_group)

            val size_group = binding.radiogSize.checkedRadioButtonId
            val size = findViewById<RadioButton>(size_group)

            val onion =  binding.chbOnion.isChecked
            val ham =  binding.chbHam.isChecked
            val tomatoes =  binding.chbTomatoes.isChecked
            val extra = (if (onion) "+ extra onion" else "") + (if (ham) "+ extra ham" else "") + (if (tomatoes) "+ extra tomatoes" else "")


            val sum_text = "Souhrn objednávky: " + "${pizza.text} " + "${size.text}" + "${extra.toString()}"




            setContentView(R.layout.summary_layout)
            val tv_summary = findViewById<TextView>(R.id.tv_summary)
            val btn_close: Button = findViewById(R.id.btn_cancel)
            val btn_addtocart: Button = findViewById(R.id.btn_addtocart)


            tv_summary.text = sum_text

            btn_addtocart.setOnClickListener {
                Toast.makeText(this@MainActivity, "Pizza added to cart", Toast.LENGTH_SHORT).show()
                setContentView(R.layout.activity_main)
                recreate()
            }




            btn_close.setOnClickListener {
                setContentView(R.layout.activity_main)
                recreate()
            }


        }
        binding.rbMargherita.setOnClickListener{
            binding.ivPizza.setImageResource(R.drawable.margherita)
        }
        binding.rbMexicana.setOnClickListener{
            binding.ivPizza.setImageResource(R.drawable.mexicana)
        }
        binding.rbQuatro.setOnClickListener{
            binding.ivPizza.setImageResource(R.drawable.quatroformagi)
        }
//
//        //check box
//        val chb_onion = findViewById<CheckBox>(R.id.chb_onion)
//        val chb_tomatoes = findViewById<CheckBox>(R.id.chb_tomatoes)
//        val chb_ham = findViewById<CheckBox>(R.id.chb_ham)
//
//        //radio group button
//        val radiog_pizza = findViewById<RadioGroup>(R.id.radiog_pizza)
//        val radiog_size = findViewById<RadioGroup>(R.id.radiog_size)
//        val chb_margherita = findViewById<RadioButton>(R.id.rb_margherita)
//        val chb_quatro = findViewById<RadioButton>(R.id.rb_quatro)
//        val chb_mexicana = findViewById<RadioButton>(R.id.rb_mexicana)


//        val btn_summary: Button = findViewById(R.id.btn_summary)
//        btn_summary.setOnClickListener {
//            setContentView(R.layout.summary_layout)
//            val btn_close: Button = findViewById(R.id.btn_cancel)
//
//
//            btn_close.setOnClickListener {
//                setContentView(R.layout.activity_main)
//            }

        }



    }
