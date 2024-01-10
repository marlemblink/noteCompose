package com.example.notecompsoe

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notecompsoe.data.NoteDataSource
import com.example.notecompsoe.model.Note
import com.example.notecompsoe.screen.NoteScreen
import com.example.notecompsoe.ui.theme.NoteCompsoeTheme
import com.example.notecompsoe.vm.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteCompsoeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val noteViewModel: NoteViewModel by viewModels()
                    val noteViewModel = viewModel<NoteViewModel>()//dif way
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
//fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
fun NotesApp(noteViewModel: NoteViewModel) {
    val noteList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        notes = noteList,
        onAddNote = {
            noteViewModel.addNote(note = it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(note = it)
        }
    )
}