package com.kks.noteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kks.noteapp.model.Note
import com.kks.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect { listOfNotes ->
                        _noteList.value = listOfNotes
                }

        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch {
        Log.d("kks", "updateNote2: ${note.id}, ${note.title}")
        repository.updateNote(note)
    }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

    fun removeAllNote() = viewModelScope.launch { repository.deleteAllNotes() }

}