//11) Databázová vrstva aplikace - Vytvoření Singleton instance pro NoteHubDatabase (NoteHubDatabaseInstance.kt)
//Nastavíme instanci databáze jako Singleton. To zajistí, že databáze bude inicializována jen jednou a pak opakovaně používána, což je efektivní a bezpečný přístup.
//
//Tento soubor (NoteHubDatabaseInstance.kt) bude obsahovat logiku pro vytvoření jediného instance databáze.
//@Volatile: Zajistí, že se hodnota INSTANCE okamžitě projeví všem vláknům, což je důležité pro bezpečný přístup k jediné instanci.
// synchronized(this): Zajistí, že při inicializaci databáze ji vytvoří pouze jedno vlákno, což je klíčové pro správnou inicializaci.
// Room.databaseBuilder: Vytváří instanci databáze pomocí názvu "notehub_database". Tento název je identifikátorem databáze v zařízení.

package com.example.myapp014amynotehub

import android.content.Context
import androidx.room.Room

object NoteHubDatabaseInstance {

    // Lazy inicializace instance databáze
    @Volatile
    private var INSTANCE: NoteHubDatabase? = null

    // Funkce pro získání instance databáze
    fun getDatabase(context: Context): NoteHubDatabase {
        // Pokud je instance null, inicializuje ji
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteHubDatabase::class.java,
                "notehub_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}

