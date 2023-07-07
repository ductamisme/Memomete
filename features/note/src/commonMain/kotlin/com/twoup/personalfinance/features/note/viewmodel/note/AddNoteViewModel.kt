package com.twoup.personalfinance.features.note.viewmodel.note

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.UseCaseInsertNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddNoteViewModel() : ScreenModel, KoinComponent {
    private val useCaseInsertNote : UseCaseInsertNote by inject()
    fun insertNote(note : NoteEntity) {
        useCaseInsertNote.insertNote(note)
    }
}
