package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context:Context, private val listener: INoteRVAdapter) : RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<NoteEnt>()

    inner class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val txtView = itemView.findViewById<TextView>(R.id.text1)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,
                                                                            parent,false))

        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.txtView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<NoteEnt>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INoteRVAdapter{
    fun onItemClicked(noteEnt: NoteEnt)
}