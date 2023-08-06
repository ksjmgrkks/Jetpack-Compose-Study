package com.kks.noteapp.data

import androidx.room.*
import com.kks.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from note_table")
    fun getNoteList(): Flow<List<Note>>

    @Query("SELECT * from note_table where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from note_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)


}