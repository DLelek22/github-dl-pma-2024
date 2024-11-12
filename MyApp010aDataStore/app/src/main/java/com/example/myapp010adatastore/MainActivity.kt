package com.example.myapp010adatastore

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapp010adatastore.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

// Extension property to create DataStore
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Define keys for DataStore
    companion object {
        val NAME_KEY = stringPreferencesKey("name")
        val AGE_KEY = intPreferencesKey("age")
        val IS_ADULT_KEY = booleanPreferencesKey("isAdult")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Save data when `btnSave` is clicked
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val ageString = binding.etAge.text.toString().trim()

            if (ageString.isBlank()) {
                Toast.makeText(this, "Hele, vyplň věk...", Toast.LENGTH_SHORT).show()
            } else {
                val age = ageString.toInt()
                val isAdult = binding.cbAdult.isChecked

                if ((age < 18 && isAdult) || (age >= 18 && !isAdult)) {
                    Toast.makeText(this, "Kecáš, takže nic ukládat nebudu", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Jasně, ukládám...", Toast.LENGTH_SHORT).show()

                    // Use lifecycleScope to save data asynchronously
                    lifecycleScope.launch {
                        saveUserPreferences(name, age, isAdult)
                    }
                }
            }
        }

        // Load data when `btnLoad` is clicked
        binding.btnLoad.setOnClickListener {
            lifecycleScope.launch {
                loadUserPreferences()
            }
        }
    }

    // Save user preferences to DataStore
    private suspend fun saveUserPreferences(name: String, age: Int, isAdult: Boolean) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[AGE_KEY] = age
            preferences[IS_ADULT_KEY] = isAdult
        }
    }

    // Load user preferences from DataStore
    private suspend fun loadUserPreferences() {
        // Define flows for each preference
        val nameFlow: Flow<String> = dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { it[NAME_KEY] ?: "" }

        val ageFlow: Flow<Int> = dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { it[AGE_KEY] ?: 0 }

        val isAdultFlow: Flow<Boolean> = dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { it[IS_ADULT_KEY] ?: false }

        // Combine flows and collect data
        combine(nameFlow, ageFlow, isAdultFlow) { name, age, isAdult ->
            Triple(name, age, isAdult)
        }.collect { (name, age, isAdult) ->
            binding.etName.setText(name)
            binding.etAge.setText(age.toString())
            binding.cbAdult.isChecked = isAdult
        }
    }
}

