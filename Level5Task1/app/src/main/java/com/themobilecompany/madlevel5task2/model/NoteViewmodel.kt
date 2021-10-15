package com.themobilecompany.madlevel5task2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.themobilecompany.madlevel5task2.database.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NoteViewmodel(application: Application): AndroidViewModel(application) {
    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()

    private val mainscope = CoroutineScope(Dispatchers.IO)

    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateNote(title: String, text: String) {
        val newNote = Note(
            id = note.value?.id,
            title = title,
            lastUpdated = Date(),
            text = text
        )

        if (isNoteValid(newNote)) {
            mainscope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNotepad(newNote)
                }
                success.value = true
            }
        }
    }

    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}