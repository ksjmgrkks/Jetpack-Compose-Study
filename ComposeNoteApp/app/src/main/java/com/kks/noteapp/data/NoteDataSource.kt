package com.kks.noteapp.data

import com.kks.noteapp.model.Note

class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "제목1", description = "내용1"),
            Note(title = "제목2", description = "내용2"),
            Note(title = "제목3", description = "내용3"),
            Note(title = "제목4", description = "내용4"),
            Note(title = "제목5", description = "내용5"),
        )
    }
}