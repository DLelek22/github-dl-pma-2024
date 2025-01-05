package com.example.myapp017asemestralniprojekt

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object NoteHubDatabaseInstance {

    // Lazy inicializace instance databáze
    @Volatile
    private var INSTANCE: NoteHubDatabase? = null
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add the new column "recipe" to the table
            database.execSQL("ALTER TABLE note_table ADD COLUMN recipe TEXT NOT NULL DEFAULT ''")
        }
    }

    // Funkce pro získání instance databáze
    fun getDatabase(context: Context): NoteHubDatabase {
        // Pokud je instance null, inicializuje ji
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteHubDatabase::class.java,
                "notehub_database"
            )   .addMigrations(MIGRATION_1_2) // Add the migration here
                .build()
            INSTANCE = instance
            instance
        }
    }

}

