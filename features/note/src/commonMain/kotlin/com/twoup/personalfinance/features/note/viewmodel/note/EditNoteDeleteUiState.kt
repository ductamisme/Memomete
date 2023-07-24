package com.twoup.personalfinance.features.note.viewmodel.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity

class EditNoteDeleteUiState(note: NoteEntity) {

    var id by mutableStateOf(note.id)
    var title by mutableStateOf(note.title)
    var description by mutableStateOf(note.description)
    var created by mutableStateOf(note.created)
    var deleteCreated by mutableStateOf(note.deleteCreated)
    var favourite by mutableStateOf(note.favourite )
    var trash by mutableStateOf(note.trash )
    var tag by mutableStateOf(note.tag)
    var folder by mutableStateOf(note.folder)
}
