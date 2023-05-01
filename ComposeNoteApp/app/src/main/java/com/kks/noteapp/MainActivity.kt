package com.kks.noteapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
                Surface(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    color = MaterialTheme.colors.background) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }

    @Composable
    fun NotesApp(noteViewModel: NoteViewModel = viewModel()){
        val noteList = noteViewModel.noteList.collectAsState().value

        NoteScreen(
            notes = noteList,
            onAddNote = { noteViewModel.addNote(it) },
            onUpdateNote = {
                Log.d("kks", "updateNote1: ${it.id}, ${it.title}")
                noteViewModel.updateNote(it) },
            onRemoveNote = { noteViewModel.removeNote(it) },
            onRemoveAllNote = { noteViewModel.removeAllNote() })
    }
}