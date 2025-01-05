package com.example.myapp017asemestralniprojekt

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//@Database(
//    entities = [Note::class, Category::class, Tag::class, NoteTagCrossRef::class],
//    version = 1,
//    exportSchema = false
//)
@Database(
    entities = [Note::class, Category::class, Tag::class, NoteTagCrossRef::class],
    version = 2, // Updated version number
    exportSchema = false
    )

abstract class NoteHubDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
    abstract fun tagDao(): TagDao
    abstract fun noteTagDao(): NoteTagDao

//    companion object {
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//            // Migration logic for adding the recipe column
//            database.execSQL("ALTER TABLE notes ADD COLUMN recipe TEXT")
//            }
//
//        }
//
//    }

}
