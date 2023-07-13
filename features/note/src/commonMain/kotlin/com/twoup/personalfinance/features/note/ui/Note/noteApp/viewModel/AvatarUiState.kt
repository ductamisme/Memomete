package com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.twoup.personalfinance.local.date.DateTimeUtil
import kotlinx.datetime.LocalDateTime

class AvatarUiState(
    name: String = "",
    email: String = "",
) {
    var name by mutableStateOf(name)
    var email by mutableStateOf(email)

    fun updateName(newName: String) {
        name = newName
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

}
