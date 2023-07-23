package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.UseCaseAssociateTagWithNote
import com.twoup.personalfinance.local.note.usecase.note.UseCaseInsertNote
import com.twoup.personalfinance.local.note.usecase.tag.UseCaseGetAllTags
import com.twoup.personalfinance.local.note.usecase.tag.UseCaseInsertNewTag
import com.twoup.personalfinance.local.note.usecase.tag.UseCaseTagAssociateWithNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddNoteViewModel() : ScreenModel, KoinComponent {
    private val useCaseInsertNote : UseCaseInsertNote by inject()
    private val useCaseInsertNewTag : UseCaseInsertNewTag by inject()
    private val useCaseTagAssociateWithNote : UseCaseTagAssociateWithNote by inject()
    private val useCaseAssociateTagWithNote : UseCaseAssociateTagWithNote by inject()
    private val useCaseGetAllTags : UseCaseGetAllTags by inject()

    val tags: StateFlow<List<TagEntity>> get() = useCaseGetAllTags.tagState.asStateFlow()
    init {
        loadTags()
    }
    fun loadTags() {
        useCaseGetAllTags.getAllTag()
    }
    fun insertNote(note : NoteEntity) {
        useCaseInsertNote.insertNote(note)
    }
    fun insertTag(tag: TagEntity){
        useCaseInsertNewTag.insertTag(tag)
    }
    fun tagAssociateWithNote(note: NoteEntity){
        useCaseTagAssociateWithNote.getTagAssociateWithNote(note)
    }
    fun insertNoteTag(note: NoteEntity, tag: TagEntity){
        useCaseAssociateTagWithNote.insertAssociateTagWithNote(note, tag)
    }
}
