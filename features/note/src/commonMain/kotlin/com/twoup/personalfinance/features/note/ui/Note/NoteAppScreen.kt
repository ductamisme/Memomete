package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.twoup.personalfinance.features.note.viewmodel.note.ThemeViewModel
import com.twoup.personalfinance.resources.DarkColorPalette
import com.twoup.personalfinance.resources.LightColorPalette


//class NoteAppScreen() : Screen {
//    @Composable
//    override fun Content() {
//        val viewModel = rememberScreenModel { ThemeViewModel() }
//        val currentTheme by viewModel.currentTheme.collectAsState()
//
//        when (currentTheme) {
//            ThemeMode.Light -> LightThemeContent {
//                Navigator(NoteScreen())
//            }
//
//            ThemeMode.Dark -> DarkThemeContent {
//                Navigator(NoteScreen())
//            }
//        }
//        Button(onClick = { viewModel.toggleTheme() }) {
//            Text("switch")
//        }
//    }
//}
//
//@Composable
//fun LightThemeContent(content: @Composable () -> Unit) {
//    MaterialTheme(colors = LightColorPalette) {
//        content()
//    }
//}

@Composable
fun DarkThemeContent(content: @Composable () -> Unit) {
    MaterialTheme(colors = DarkColorPalette) {
        content()
    }
}
