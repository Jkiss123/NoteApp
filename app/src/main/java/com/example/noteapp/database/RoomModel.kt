package com.example.noteapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) : NoteDatabase{
        return Room.databaseBuilder(context,NoteDatabase::class.java,"Note_Database").build()
    }
    @Provides
    fun provideDao(noteDatabase: NoteDatabase) : NoteDao {
        return noteDatabase.getNoteDao()
    }
}