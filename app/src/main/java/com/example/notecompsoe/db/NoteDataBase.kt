package com.example.notecompsoe.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notecompsoe.model.Note
import com.example.notecompsoe.util.DateConverter
import com.example.notecompsoe.util.UUIDConverter

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDataBaseDao
}