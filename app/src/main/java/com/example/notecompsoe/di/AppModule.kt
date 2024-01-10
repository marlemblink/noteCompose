package com.example.notecompsoe.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.notecompsoe.db.NoteDataBase
import com.example.notecompsoe.db.NoteDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNotesDao(noteDataBase: NoteDataBase): NoteDataBaseDao
    = noteDataBase.noteDao()

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): NoteDataBase
    = Room.databaseBuilder(
        context = context,
        NoteDataBase::class.java,
        name = "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}