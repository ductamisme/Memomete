package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.local.note.usecase.note.UseCaseDeleteAllNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseDeleteAllNoteDeleted
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteBy30Days
import com.twoup.personalfinance.local.note.usecase.UseCaseSearchNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.note.UseCaseDeleteNoteDeletedById
import com.twoup.personalfinance.local.note.usecase.note.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseSearchNoteFromOldTONew
import com.twoup.personalfinance.local.note.usecase.note.UseCaseInsertNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseUpdateNote
//import com.twoup.personalfinance.local.note.usecase.tag.UseCaseGetAllTags
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDateTime
import org.brightify.hyperdrive.multiplatformx.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteViewModel : BaseViewModel(), ScreenModel, KoinComponent {

    private val useCaseInsertNote : UseCaseInsertNote by inject()
    private val useCaseGetAllNote: UseCaseGetAllNote by inject()
    private val useCaseUpdateNote: UseCaseUpdateNote by inject()
    private val useCaseDeleteAllNoteDeleted : UseCaseDeleteAllNoteDeleted by inject()
    private val useCaseDeleteNoteDeletedById : UseCaseDeleteNoteDeletedById by inject()
    private val useCaseDeleteBy30Days : UseCaseDeleteBy30Days by inject()
//    private val useCaseGetAllTags : UseCaseGetAllTags by inject()
    private val useCaseSearchNote : UseCaseSearchNote by inject()

    val notes: StateFlow<List<NoteEntity>> get() = useCaseGetAllNote.noteState.asStateFlow()
//    val tags: StateFlow<List<TagEntity>> get() = useCaseGetAllTags.tagState.asStateFlow()
    var showUp: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var changeColorButton: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val _selectedItemIndex = MutableStateFlow(DrawerItem.YOUR_NOTES)
    val selectedItemIndex: MutableStateFlow<DrawerItem> get() = _selectedItemIndex

    // search note
//    private val _searchResults = useCaseSearchNote.filterNoteTags
//    private val _searchResultsFlow = MutableStateFlow(_searchResults)
//
//    val searchResults: MutableStateFlow<StateFlow<List<NoteEntity>>> = _searchResultsFlow
//    fun filterTag(tag : String) {
//        useCaseSearchNote.filterNote(tag)
//    }
    fun setSelectedItemIndex(item: DrawerItem) {
        _selectedItemIndex.value = item
    }
    fun changeColorButton() {
        changeColorButton.value = !changeColorButton.value
    }
    fun changeShowUp() {
        showUp.value = !showUp.value
    }
    init {
        loadNotes()
        loadNoteNoteContainTrash()
    }
    fun deleteNoteBy30Days(id: Long, createdDateTime: LocalDateTime) {
        useCaseDeleteBy30Days.deleteNoteBy30Days(id, loadNotes(),createdDateTime)
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

enum class DrawerItem {
    YOUR_NOTES,
    FAVORITES,
    TAGS,
    TRASH,
    FOLDERS,
    SETTINGS
}
