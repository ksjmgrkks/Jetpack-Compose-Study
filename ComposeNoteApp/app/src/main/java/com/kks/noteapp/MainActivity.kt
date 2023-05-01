package com.kks.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kks.noteapp.data.NoteDataSource
import com.kks.noteapp.model.Note
import com.kks.noteapp.screen.NoteScreen
import com.kks.noteapp.screen.NoteViewModel
import com.kks.noteapp.ui.theme.ComposeNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNoteAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }

    @Composable
    fun NotesApp(noteViewModel: NoteViewModel = viewModel()){
        val noteList = noteViewModel.getAllNotes()

        NoteScreen(
            notes = noteList,
            onAddNote = { noteViewModel.addNote(it) },
            onRemoveNote = { noteViewModel.removeNote(it) })
    }
}