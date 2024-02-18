package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteLayoutBinding
import com.example.noteapp.fragments.HomeFragmentDirections
import com.example.noteapp.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(val binding :NoteLayoutBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.binding.noteTitle.text = currentNote.noteTitle
        holder.binding.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteTitle == newItem.noteTitle &&
                    oldItem.noteDesc == newItem.noteDesc
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return  oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
}