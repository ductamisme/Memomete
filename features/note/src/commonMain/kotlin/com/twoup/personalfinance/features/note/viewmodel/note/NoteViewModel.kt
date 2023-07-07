package com.twoup.personalfinance.features.note.viewmodel.note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseGetNoteById
import com.twoup.personalfinance.local.note.usecase.UseCaseUpdateNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.brightify.hyperdrive.multiplatformx.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteViewModel : BaseViewModel(), ScreenModel, KoinComponent {

    private val useCaseDeleteNoteById: UseCaseDeleteNoteById by inject()
    private val useCaseGetAllNote: UseCaseGetAllNote by inject()
    private val useCaseDeleteAllNote: UseCaseDeleteAllNote by inject()

    private val _note = useCaseGetAllNote.noteState
    val notes: StateFlow<List<NoteEntity>> = _note.asStateFlow()

    private val _showUp = MutableStateFlow(false)
    val showUp: StateFlow<Boolean> = _showUp

//    private val _selectedNote = MutableStateFlow(false)
//    val selectedNote: StateFlow<Boolean> = _selectedNote

    fun changeShowUp() {
        _showUp.value = !_showUp.value
    }

//    fun changeSelectedNote() {
//        _selectedNote.value = !_selectedNote.value
//    }

    init {
        loadNotes()
    }

    fun deleteNoteById(id: Long) {
        useCaseDeleteNoteById.deleteNoteById(id, loadNotes())
        loadNotes()
    }

    fun deleteNotes() {
        useCaseDeleteAllNote.deleteALlNote(loadNotes())
        loadNotes()
    }

    fun loadNotes() {
            useCaseGetAllNote.getAllNote()
    }

    class Factory(private val dataSource: NoteLocalDataSource) {
        fun create() = NoteViewModel()
    }
}