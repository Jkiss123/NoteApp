package com.example.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModelHilt @Inject constructor(private val repository: NoteRepository):ViewModel() {
    fun addNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
    fun readAllData() = repository.getAllNote()
    fun searchNote(query:String?) = repository.searchNote(query)
}