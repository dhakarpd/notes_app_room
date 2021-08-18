package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:LiveData<List<NoteEnt>> = noteDao.getAllNotes()

    suspend fun insert(noteEnt: NoteEnt){
        noteDao.insert(noteEnt)
    }

    suspend fun delete(noteEnt: NoteEnt){
        noteDao.delete(noteEnt)
    }
}