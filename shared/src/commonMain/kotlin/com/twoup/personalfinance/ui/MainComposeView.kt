package com.twoup.personalfinance.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.twoup.personalfinance.features.note.ui.Note.noteApp.yourNote.NoteScreen
import com.twoup.personalfinance.features.note.viewmodel.note.ThemeViewModel
import com.twoup.personalfinance.resources.DarkColorPalette
import com.twoup.personalfinance.resources.LightColorPalette
import com.twoup.personalfinance.viewmodel.ApplicationViewModel

@Composable
internal fun MainComposeView(viewModel: ApplicationViewModel, modifier: Modifier = Modifier) {
    PersonalFinanceTheme(themeViewModel = ThemeViewModel()){
        Navigator(NoteScreen())
    }
}

@Composable
internal fun PersonalFinanceTheme(
    themeViewModel: ThemeViewModel,
    content: @Composable () -> Unit,
) {
    val colors = if (themeViewModel.darkTheme.value) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content,
    )
}
