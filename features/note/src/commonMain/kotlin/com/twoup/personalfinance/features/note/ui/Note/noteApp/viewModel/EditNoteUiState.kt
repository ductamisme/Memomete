package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.datetime.LocalDateTime

class EditNoteUiState(
    note : NoteEntity,
    idTag: Long = 0L,
    nameTag: String = "",
) {

    var id by mutableStateOf(note.id)
    var title by mutableStateOf(note.title)
    var description by mutableStateOf(note.description)
    var created by mutableStateOf(note.created)
    var deleteCreated by mutableStateOf(note.deleteCreated)
    var favourite by mutableStateOf(note.favourite ) // Use default value if favourite is null
    var trash by mutableStateOf(note.trash )
    var tag by mutableStateOf(note.tag)
    var folder by mutableStateOf(note.folder)
    var idTag by mutableStateOf(idTag)
    var nameTag by mutableStateOf(nameTag)
}