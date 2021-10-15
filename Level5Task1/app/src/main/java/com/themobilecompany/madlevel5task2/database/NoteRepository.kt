package com.themobilecompany.madlevel5task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.themobilecompany.madlevel5task2.model.Note

class NoteRepository(context: Context) {
    private val noteDao: NoteDao

    init {
        val database = NotepadRoomDatabase.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotepad(): LiveData<Note?> {
        return noteDao.getNotepad()
    }

    suspend fun updateNotepad(note: Note) {
        noteDao.updateNote(note)
    }
}