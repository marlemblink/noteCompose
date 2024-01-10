package com.example.notecompsoe.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notecompsoe.model.Note

class NoteDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Blackpink", description = "kpop group"),
            Note(title = "BTS", description = "kpop group"),
            Note(title = "Twice", description = "kpop group"),
            Note(title = "New Jeans", description = "kpop group")
        )
    }
}