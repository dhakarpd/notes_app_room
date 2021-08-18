package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes:LiveData<List<NoteEnt>>
    private val repo:NoteRepository
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repo = NoteRepository(dao)
        allNotes = repo.allNotes

    }

    fun deleteNote(noteEnt: NoteEnt)= viewModelScope.launch(Dispatchers.IO){

        repo.delete(noteEnt)
    }

    fun insertNote(noteEnt: NoteEnt)= viewModelScope.launch(Dispatchers.IO) {
        repo.insert(noteEnt)
    }

}