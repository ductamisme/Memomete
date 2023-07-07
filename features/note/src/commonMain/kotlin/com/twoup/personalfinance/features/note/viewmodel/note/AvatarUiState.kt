package com.twoup.personalfinance.features.note.viewmodel.note

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
        private set
    var email by mutableStateOf(email)
        private set

    fun updateName(newName: String) {
        name = newName
    }

    fun updateEmail(newEmail: String) {
        email = newEmail
    }

}
