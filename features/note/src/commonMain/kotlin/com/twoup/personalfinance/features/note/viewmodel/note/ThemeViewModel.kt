package com.twoup.personalfinance.features.note.viewmodel.note

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel: ScreenModel {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme: StateFlow<Boolean> = _darkTheme

    fun changeDarkTheme() {
        _darkTheme.value = !_darkTheme.value
    }
}

//enum class ThemeMode {
//    Light, Dark
//}