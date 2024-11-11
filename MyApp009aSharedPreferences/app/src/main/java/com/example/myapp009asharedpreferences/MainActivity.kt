package com.example.myapp009asharedpreferences

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import com.example.myapp009asharedpreferences.databinding.ActivityMainBinding
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

//        binding.btnSave.setOnClickListener {
//            // 1. Načítá hodnotu z textového pole `etName` jako řetězec a ukládá ji do proměnné `name`.
//            val name = binding.etName.text.toString()
//
//            // 2. Načítá hodnotu z textového pole `etAge`, odstraňuje bílé znaky a ukládá do `ageString`.
//            val ageString = binding.etAge.text.toString().trim()
//
//            // 3. Kontroluje, zda `ageString` není prázdný. Pokud ano, zobrazí upozornění a kód končí.
//            if (ageString.isBlank()) {
//                Toast.makeText(this, "Hele, vyplň věk...", Toast.LENGTH_SHORT).show()
//            } else {
//                // 4. Pokud věk není prázdný, převede ho na `Int` a uloží do `age`.
//                val age = ageString.toInt()
//
//                // 5. Načte hodnotu z checkboxu `cbAdult` (zda je uživatel označen jako dospělý).
//                val isAdult = binding.cbAdult.isChecked
//
//                // 6. Kontroluje, zda zadané údaje odpovídají (např. věk < 18 a přitom checkbox označen jako dospělý).
//                if ((age < 18 && isAdult) || (age >= 18 && !isAdult)) {
//                    // 7. Pokud údaje neodpovídají, zobrazí chybové hlášení a končí.
//                    Toast.makeText(this, "Kecáš, takže nic ukládat nebudu", Toast.LENGTH_SHORT).show()
//                } else {
//                    // 8. Pokud údaje odpovídají, zobrazí se hláška o ukládání a informace se uloží.
//                    Toast.makeText(this, "Jasně, ukládám...", Toast.LENGTH_SHORT).show()
//
//                    // 9. Používá `editor` pro uložení hodnot do `SharedPreferences` a aplikuje změny.
//                    editor.apply {
//                        putString("name", name)
//                        putInt("age", age)
//                        putBoolean("isAdult", isAdult)
//                        apply() // Uloží změny
//                    }
//                }
//            }
//        }
//
//// Funkcionalita pro načtení dat
//        binding.btnLoad.setOnClickListener {
//            // 10. Načítá `name` z `SharedPreferences` (nebo `null`, pokud neexistuje).
//            val name = sharedPref.getString("name", null)
//
//            // 11. Načítá `age` z `SharedPreferences` (nebo 0, pokud neexistuje).
//            val age = sharedPref.getInt("age", 0)
//
//            // 12. Načítá `isAdult` z `SharedPreferences` (nebo `false`, pokud neexistuje).
//            val isAdult = sharedPref.getBoolean("isAdult", false)
//
//            // 13. Nastavuje hodnoty v textových polích a checkboxu podle načtených dat.
//            binding.etName.setText(name)
//            binding.etAge.setText(age.toString())
//            binding.cbAdult.isChecked = isAdult
//        }

        binding.btnSave.setOnClickListener {
            val wifiName = binding.etWifiName.text.toString()
            val isWIFIon = binding.cbWifi.isChecked
            val isBTon = binding.cbBluetooth.isChecked
            val isAIRPLANEon = binding.cbAirplane.isChecked

            if (wifiName.isEmpty() && isWIFIon==true){
                Toast.makeText(this, "Please set your wifi name", Toast.LENGTH_SHORT).show()
            }else   {
                Toast.makeText(this, "Saving data", Toast.LENGTH_SHORT).show()
                editor.apply {
                        putString("wifiName", wifiName)
                        putBoolean("isWIFIon", isWIFIon)
                        putBoolean("isBTon", isBTon)
                        putBoolean("isAIRPLANEon", isAIRPLANEon)
                        apply() // Uloží změny
                    }

            }

            binding.btnLoad.setOnClickListener {
                val wifiName = sharedPref.getString("wifiName", null)
                val isWIFIon = sharedPref.getBoolean("isWIFIon", false)
                val isBTon = sharedPref.getBoolean("isBTon", false)
                val isAIRPLANEon = sharedPref.getBoolean("isAIRPLANEon", false)

                binding.etWifiName.setText(wifiName)
                binding.cbWifi.isChecked = isWIFIon
                binding.cbBluetooth.isChecked = isBTon
                binding.cbAirplane.isChecked = isAIRPLANEon


            }




        }


    }
}