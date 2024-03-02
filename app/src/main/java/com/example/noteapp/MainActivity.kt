package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelFactory
import com.example.noteapp.viewmodel.NoteViewModelHilt
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

// Begin in 13/2/2024
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //lateinit var noteViewModel : NoteViewModel
     val noteViewModel : NoteViewModelHilt by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setupViewModel()
    }

    private fun setupViewModel(){
        //val noteRepository = NoteRepository()
        //val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
        ///noteViewModel = ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]
    }
}