package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.datetime.LocalDateTime

class EditNoteUiState(note : NoteEntity) {

    var id by mutableStateOf(note.id)
    var title by mutableStateOf(note.title)
    var description by mutableStateOf(note.description)
    var created by mutableStateOf(note.created)
    var deleteCreated by mutableStateOf(note.deleteCreated)
    var favourite by mutableStateOf(note.favourite ) // Use default value if favourite is null
    var trash by mutableStateOf(note.trash )
//    var idTag by mutableStateOf(tag.id)
//    var nameTag by mutableStateOf(tag.name)
}

//class EditNoteUiState (
//    id: Long = 0L,
//    title: String = "",
//    description: String = "",
//    idTag: Long = 0L,
//    nameTag: String = "",
//    created: LocalDateTime = DateTimeUtil.now(),
//    deleteCreated: LocalDateTime = DateTimeUtil.now(),
//    favourite: Long = 0L,
//    trash : Long = 0L,
//) {
//    var id by mutableStateOf(id)
//    var title by mutableStateOf(title)
//    var description by mutableStateOf(description)
//    var created by mutableStateOf(created)
//    var deleteCreated by mutableStateOf(deleteCreated)
//    var favourite by mutableStateOf(favourite)
//    var trash by mutableStateOf(trash)
//    var idTag by mutableStateOf(idTag)
//    var nameTag by mutableStateOf(nameTag)
//}
