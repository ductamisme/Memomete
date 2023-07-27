package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.note.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.note.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseInsertNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseUpdateNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditNoteViewModel() : ScreenModel, KoinComponent {
    private val useCaseUpdateNote : UseCaseUpdateNote by inject()
    private val useCaseGetAllNote : UseCaseGetAllNote by inject()
    private val useCaseDeleteNoteById: UseCaseDeleteNoteById by inject()
    var showUp: MutableStateFlow<Boolean> = MutableStateFlow(false)
    fun changeShowUp() {
        showUp.value = !showUp.value
    }
    val folders: StateFlow<List<NoteEntity>> get() = useCaseGetAllNote.noteState.asStateFlow()

    fun loadFolder() {
        useCaseGetAllNote.getAllNote()
    }
    fun updateNote(note : NoteEntity) {
        useCaseUpdateNote.updateNote(note, useCaseGetAllNote.getAllNote())
    }

    fun deleteNoteById(id: Long) {
        useCaseDeleteNoteById.deleteNoteById(id, useCaseGetAllNote.getAllNote())
        useCaseGetAllNote.getAllNote()
    }

    private val useCaseInsertNote : UseCaseInsertNote by inject()
    fun insertNote(note : NoteEntity) {
        useCaseInsertNote.insertNote(note)
    }

}