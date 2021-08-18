package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    //So when one try to enter same value of primary key they will get ignored and won't get entered
    //Suspend coroutine can only be called by background thread or another suspend function
    //insert & delete are i/o operations which are heavy which makes our app laggy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NoteEnt)

    @Delete
    suspend fun delete(note: NoteEnt)

    /*LiveData is lifecycle aware data
      As room supports LiveData so it will automatically keep us updated about
      our changing list/data*/
    @Query("select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<NoteEnt>>
}