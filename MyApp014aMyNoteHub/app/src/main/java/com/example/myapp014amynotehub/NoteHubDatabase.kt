//10) Databázová vrstva aplikace - Vytvoření třídy databáze (NoteHubDatabase)
//Nyní vytvoříme hlavní Room Database třídu, která propojí všechny naše DAO a bude sloužit jako centrální bod pro práci s databází.
//@Database: Tato anotace označuje třídu jako Room Database. Uvádíme zde seznam entit (tabulek), které budou součástí databáze, a verzi databáze (version = 1).
//abstract class NoteHubDatabase: Třída NoteHubDatabase dědí od RoomDatabase, což umožňuje Room vytvořit instanci databáze.
//abstract fun noteDao(), categoryDao(), tagDao(), noteTagDao(): Metody, které poskytují přístup k jednotlivým DAO.

package com.example.myapp014amynotehub

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class, Category::class, Tag::class, NoteTagCrossRef::class],
    version = 1,
    exportSchema = false
)

abstract class NoteHubDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
    abstract fun tagDao(): TagDao
    abstract fun noteTagDao(): NoteTagDao
}
