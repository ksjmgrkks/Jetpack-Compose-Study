package com.kks.noteapp.screen

import androidx.lifecycle.ViewModel
import com.kks.noteapp.data.NoteDataSource
import com.kks.noteapp.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableListOf<Note>()

    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes() = noteList
}