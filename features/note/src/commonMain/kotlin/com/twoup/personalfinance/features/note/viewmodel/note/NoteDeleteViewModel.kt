//package com.twoup.personalfinance.features.note.viewmodel.note
//
//import cafe.adriel.voyager.core.model.ScreenModel
//import com.twoup.personalfinance.local.note.NoteLocalDataSource
//import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteAllNote
//import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteNoteById
//import com.twoup.personalfinance.local.note.usecase.UseCaseGetAllNote
//import com.twoup.personalfinance.local.note.usecase.UseCaseUpdateNote
//import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
//import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseDeleteAllNoteDelete
//import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseDeleteNoteDeleteById
//import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseGetAllNoteDelete
//import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseUpdateNoteDelete
//import com.twoup.personalfinance.model.note.local.NoteEntity
//import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import org.brightify.hyperdrive.multiplatformx.BaseViewModel
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//
//class NoteDeleteViewModel : BaseViewModel(), ScreenModel, KoinComponent {
//
//    private val useCaseDeleteNoteDelete: UseCaseDeleteNoteDeleteById by inject()
//    private val useCaseGetAllNoteDelete: UseCaseGetAllNoteDelete by inject()
//    private val useCaseDeleteAllNoteDelete: UseCaseDeleteAllNoteDelete by inject()
//    private val useCaseUpdateNoteDelete: UseCaseUpdateNoteDelete by inject()
//
//    private val useCaseGetAllNote: UseCaseGetAllNote by inject()
//
//    private val _noteTrash = useCaseGetAllNoteDelete.noteState
//    val noteTrash: StateFlow<List<NoteDeleteEntity>> = _noteTrash.asStateFlow()
//
//    private val _note = useCaseGetAllNote.noteState
//    val notes: StateFlow<List<NoteEntity>> = _note.asStateFlow()
//
//    private val _showUp = MutableStateFlow(false)
//    val showUp: StateFlow<Boolean> = _showUp
//
////    private val _selectedNote = MutableStateFlow(false)
////    val selectedNote: StateFlow<Boolean> = _selectedNote
//
//    fun changeShowUp() {
//        _showUp.value = !_showUp.value
//    }
//
////    fun changeSelectedNote() {
////        _selectedNote.value = !_selectedNote.value
////    }
//
//    init {
//        loadNotes()
//    }
//
//    fun deleteNoteById(id: Long) {
//        useCaseDeleteNoteDelete.deleteNoteDeleteById(id, loadNotes())
//        loadNotes()
//    }
//
//    fun deleteNotes() {
//        useCaseDeleteAllNoteDelete.deleteAllNoteDelete(loadNotes())
//        loadNotes()
//    }
//
//    fun loadNotes() {
//        useCaseGetAllNoteDelete.getAllNoteDelete()
//    }
//
//    fun updateNote(note: NoteDeleteEntity) {
//        useCaseUpdateNoteDelete.updateNoteDelete(note, useCaseGetAllNoteDelete.getAllNoteDelete())
//    }
//
//    class Factory(private val dataSource: NoteDeleteDataSource) {
//        fun create() = NoteDeleteViewModel()
//    }
//}
