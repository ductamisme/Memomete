package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.note.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.note.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseInsertNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseUpdateNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditNoteViewModel() : ScreenModel, KoinComponent {
    private val useCaseUpdateNote : UseCaseUpdateNote by inject()
    private val useCaseGetAllNote : UseCaseGetAllNote by inject()
    private val useCaseDeleteNoteById: UseCaseDeleteNoteById by inject()

    fun updateNote(note : NoteEntity) {
        useCaseUpdateNote.updateNote(note, useCaseGetAllNote.getAllNote())
    }

    fun deleteNoteById(id: Long) {
        useCaseDeleteNoteById.deleteNoteById(id, useCaseGetAllNote.getAllNote())
        useCaseGetAllNote.getAllNote()
    }



    private val useCaseInsertNote : UseCaseInsertNote by inject()
//    private val useCaseInsertNewTag : UseCaseInsertNewTag by inject()
//    private val useCaseTagAssociateWithNote : UseCaseTagAssociateWithNote by inject()
//    private val useCaseAssociateTagWithNote : UseCaseAssociateTagWithNote by inject()
//    private val useCaseGetAllTags : UseCaseGetAllTags by inject()

//    val tags: StateFlow<List<TagEntity>> get() = useCaseGetAllTags.tagState.asStateFlow()
//    init {
//        loadTags()
//    }
//    fun loadTags() {
//        useCaseGetAllTags.getAllTag()
//    }
    fun insertNote(note : NoteEntity) {
        useCaseInsertNote.insertNote(note)
    }
//    fun insertTag(tag: TagEntity){
//        useCaseInsertNewTag.insertTag(tag)
//    }
//    fun tagAssociateWithNote(note: NoteEntity){
//        useCaseTagAssociateWithNote.getTagAssociateWithNote(note)
//    }
//    fun insertNoteTag(note: NoteEntity, tag: TagEntity){
//        useCaseAssociateTagWithNote.insertAssociateTagWithNote(note, tag)
//    }
}