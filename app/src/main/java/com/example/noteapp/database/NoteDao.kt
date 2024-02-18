package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Notes ORDER BY id DESC")
    fun getallNote() : LiveData<List<Note>>

    @Query("SELECT * FROM Notes WHERE noteTitle like :query or noteDesc like :query")
    fun searchNote(query: String?) : LiveData<List<Note>>
}