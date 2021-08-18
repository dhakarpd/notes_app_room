package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun onItemClicked(noteEnt: NoteEnt) {
        viewModel.deleteNote(noteEnt)
        Toast.makeText(this,"${noteEnt.text} deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty())
        {
            viewModel.insertNote(NoteEnt(noteText))
            Toast.makeText(this,"$noteText inserted",Toast.LENGTH_SHORT).show()
        }
    }
}