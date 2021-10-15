package com.themobilecompany.madlevel5task2.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.themobilecompany.madlevel5task2.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM noteTable LIMIT 1")
    fun getNotepad(): LiveData<Note?>

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}