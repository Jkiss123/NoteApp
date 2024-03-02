package com.example.noteapp.DI

import com.example.noteapp.database.NoteDao
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NoteModule {
    @Provides
    fun provideNoteRepository(noteDatabase: NoteDatabase) : NoteRepository{
        return NoteRepository(noteDatabase)
    }
}