package com.example.myapp017asemestralniprojekt

import androidx.room.Entity

@Entity(tableName = "note_tag_cross_ref", primaryKeys = ["noteId", "tagId"])

data class NoteTagCrossRef(
    val noteId: Int,  // ID poznámky
    val tagId: Int    // ID štítku
)