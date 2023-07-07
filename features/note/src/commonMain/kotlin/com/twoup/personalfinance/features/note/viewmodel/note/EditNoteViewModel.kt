package com.twoup.personalfinance.features.note.viewmodel.note

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseUpdateNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditNoteViewModel() : ScreenModel, KoinComponent {
    private val useCaseUpdateNote : UseCaseUpdateNote by inject()
    private val useCaseGetAllNote : UseCaseGetAllNote by inject()
    private val useCaseDeleteNoteById: UseCaseDeleteNoteById by inject()

    fun updateComic(note : NoteEntity) {
        useCaseUpdateNote.updateNote(note, useCaseGetAllNote.getAllNote())
    }

    fun deleteNoteById(id: Long) {
        useCaseDeleteNoteById.deleteNoteById(id, useCaseGetAllNote.getAllNote())
        useCaseGetAllNote.getAllNote()
    }
}