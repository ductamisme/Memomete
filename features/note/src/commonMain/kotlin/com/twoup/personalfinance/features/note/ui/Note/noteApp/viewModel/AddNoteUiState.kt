package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.twoup.personalfinance.local.date.DateTimeUtil
import kotlinx.datetime.LocalDateTime

class AddNoteUiState(
    id: Long = 0L,
    title: String = "",
    description: String = "",
    created: LocalDateTime = DateTimeUtil.now(),
    favourite: Long = 0L,
    trash : Long = 0L
) {
    var id by mutableStateOf(id)
        private set
    var title by mutableStateOf(title)
    var description by mutableStateOf(description)
    var created by mutableStateOf(created)
        private set
    var favourite by mutableStateOf(favourite)
    var trash by mutableStateOf(trash)

}
