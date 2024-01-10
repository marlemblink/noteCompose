package com.example.notecompsoe.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    val title: String,
    @ColumnInfo(name = "note_description")
    val description: String,
    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date = Date.from(Instant.now())
) {
    //constructor(id:UUID, title: String, description: String, entryDate: Date): this(null, "", "", LocalDateTime.now())
}
