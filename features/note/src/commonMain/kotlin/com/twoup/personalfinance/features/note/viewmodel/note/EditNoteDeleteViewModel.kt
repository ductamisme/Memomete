package com.twoup.personalfinance.features.note.viewmodel.note

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseDeleteNoteDeleteById
import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseGetAllNoteDelete
import com.twoup.personalfinance.local.noteDelete.usecase.UseCaseUpdateNoteDelete
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EditNoteDeleteViewModel() : ScreenModel, KoinComponent {
    private val useCaseUpdateNoteDelete: UseCaseUpdateNoteDelete by inject()
    private val useCaseGetAllNoteDelete: UseCaseGetAllNoteDelete by inject()
    private val useCaseDeleteNoteDeleteById: UseCaseDeleteNoteDeleteById by inject()

    fun updateNote(note: NoteDeleteEntity) {
        useCaseUpdateNoteDelete.updateNoteDelete(note, useCaseGetAllNoteDelete.getAllNoteDelete())
    }

    fun deleteNoteById(id: Long) {
        useCaseDeleteNoteDeleteById.deleteNoteDeleteById(id, useCaseGetAllNoteDelete.getAllNoteDelete())
        useCaseGetAllNoteDelete.getAllNoteDelete()
    }
}
