package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteAllNoteDeleted
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteNoteDeletedById
import com.twoup.personalfinance.local.note.usecase.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseSearchNoteFromOldTONew
import com.twoup.personalfinance.local.note.usecase.UseCaseUpdateNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.brightify.hyperdrive.multiplatformx.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteViewModel : BaseViewModel(), ScreenModel, KoinComponent {

    private val useCaseDeleteNoteById: UseCaseDeleteNoteById by inject()
    private val useCaseGetAllNote: UseCaseGetAllNote by inject()
    private val useCaseDeleteAllNote: UseCaseDeleteAllNote by inject()
    private val useCaseUpdateNote: UseCaseUpdateNote by inject()
    private val useCaseSearchNoteFromOldTONew: UseCaseSearchNoteFromOldTONew by inject()
    private val useCaseDeleteAllNoteDeleted : UseCaseDeleteAllNoteDeleted by inject()
    private val useCaseDeleteNoteDeletedById : UseCaseDeleteNoteDeletedById by inject()

    val notes: StateFlow<List<NoteEntity>> get() = useCaseGetAllNote.noteState.asStateFlow()
    var showUp: MutableStateFlow<Boolean> = MutableStateFlow(false)


    private val _searchResult = useCaseSearchNoteFromOldTONew.searchResults
    private val _searchResultsFlow = MutableStateFlow(_searchResult)
    private val _selectedNote = MutableStateFlow<NoteEntity?>(null)
    val selectedNote: StateFlow<NoteEntity?> = _selectedNote.asStateFlow()
    val searchResults: MutableStateFlow<StateFlow<List<NoteEntity>>> = _searchResultsFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun fromNewest() {
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                useCaseSearchNoteFromOldTONew.noteNewest()
                useCaseSearchNoteFromOldTONew.searchResults
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fromOldest() {
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                useCaseSearchNoteFromOldTONew.noteOldest()
                useCaseSearchNoteFromOldTONew.searchResults
            }
        }
    }

    fun changeShowUp() {
        showUp.value = !showUp.value
    }
    init {
        loadNotes()
        loadNoteNoteContainTrash()
    }

    fun deleteNoteById(id: Long) {
        useCaseDeleteNoteById.deleteNoteById(id, loadNotes())
        loadNotes()
    }

    fun deleteNotes() {
        useCaseDeleteAllNote.deleteALlNote(loadNotes())
        loadNotes()

    }

    fun deleteNoteDeletedById(id: Long) {
        useCaseDeleteNoteDeletedById.deleteNoteDeletedById(id, loadNotes())
        loadNotes()
    }

    fun deleteAllNotesDeleted() {
        useCaseDeleteAllNoteDeleted.deleteALlNoteDeleted(loadNotes())
        loadNotes()

    }

    fun loadNotes() {
        useCaseGetAllNote.getAllNote()
    }

    fun loadNoteNoteContainTrash() {
        useCaseGetAllNote.getAllNoteNotContainTrash()
    }

    fun updateNote(note: NoteEntity) {
        useCaseUpdateNote.updateNote(note, useCaseGetAllNote.getAllNoteNotContainTrash())
    }

    class Factory(private val dataSource: NoteLocalDataSource) {
        fun create() = NoteViewModel()
    }
}