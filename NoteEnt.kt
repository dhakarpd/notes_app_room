package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Primary constructor
@Entity(tableName = "notes_table")
class NoteEnt(@ColumnInfo(name = "text") val text:String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}