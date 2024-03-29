package com.example.noteapp.repository

import androidx.room.Query
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.model.Note
import javax.inject.Inject

class NoteRepository(private val db : NoteDatabase) {
    suspend fun insertNote(note: Note) = db.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    fun getAllNote() = db.getNoteDao().getallNote()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
}