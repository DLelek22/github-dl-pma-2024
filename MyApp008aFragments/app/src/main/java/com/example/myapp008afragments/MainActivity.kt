package com.example.myapp008afragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.myapp008afragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            replaceFragment(Fragment1())
        }

        binding.btn2.setOnClickListener {
            replaceFragment(Fragment2())
        }

    }

    private fun replaceFragment(fragment : Fragment){
        //získá instanci správce fragmentu
        val fragmentManager = supportFragmentManager

        //vytvoří novou transakci s fragmenty
        val fragmentTransaction = fragmentManager.beginTransaction()

        //nahradí fragment novým fragmentem, který byl přidán jako argument
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)

        //potvrdí transakci a provede výměnu fragmentu
        fragmentTransaction.commit()
    }

}