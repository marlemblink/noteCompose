package com.example.notecompsoe.vm

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notecompsoe.data.NoteDataSource
import com.example.notecompsoe.model.Note
import com.example.notecompsoe.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        //noteList.addAll(NoteDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
            .collect { listOfNotes->
                if(listOfNotes.isNullOrEmpty()){
                    Log.d("*******","EmptyList")
                } else {
                    _noteList.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: Note) =
        viewModelScope.launch { repository.addNote(note = note) }

    fun updateNote(note: Note) =
        viewModelScope.launch { repository.updateNote(note = note) }

    fun removeNote(note: Note) =
        viewModelScope.launch { repository.deleteNote(note = note) }
}