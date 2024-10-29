package com.example.myapp008bfragmentsexample1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp008bfragmentsexample1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Přidáme ListFragment, pokud ještě neexistuje
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_listPizza, listFragment)
                .commit()
        }
    }

    // Voláno při výběru knihy
    fun onBookSelected(title: String, author: String) {
        // Vytvoření nového DetailFragment a nastavení argumentů
        val detailFragment = DetailFragment()

        val bundle = Bundle().apply {
            putString("title", title)
            putString("author", author)
        }

        if (title == "Margherita"){
            binding.ivPizza.setImageResource(R.drawable.margherita)
        }
        if (title == "Mexicana"){
            binding.ivPizza.setImageResource(R.drawable.mexicana)
        }
        if (title == "Quatro Formagi"){
            binding.ivPizza.setImageResource(R.drawable.quatroformagi)
        }
        detailFragment.arguments = bundle

        // Nahradíme starý fragment novým a commitneme transakci
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_ingredients, detailFragment)
            .commit()
    }
}