package com.twoup.personalfinance.features.note.ui.Note.noteApp.favoriteNote

import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.noteApp.DrawerContent
import com.twoup.personalfinance.features.note.ui.Note.noteApp.TopBarHomePage
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import io.github.aakira.napier.Napier

class NoteScreenFavorite : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val searchScreen = rememberScreen(SharedScreen.SearchNote)
//        val addNoteScreen = rememberScreen(SharedScreen.AddNoteScreen)
        val avatarScreen = rememberScreen(SharedScreen.AvatarScreen)
        var oldOrNew by remember { mutableStateOf(false) }
        val notes by viewModel.notes.collectAsState(emptyList())
        val notesFromOldTONew = notes.sortedByDescending { it.created }
        val showUp by viewModel.showUp.collectAsState()
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        LaunchedEffect(navigator) {
            viewModel.loadNotes()
            drawerState.currentValue

        }

        ModalDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(viewModel)
            },
            content = {
                Scaffold(
                    topBar = {
                        TopBarHomePage(
                            onSearchClicked = { navigator.push(searchScreen) },
                            onAvatarClick = { navigator.push(avatarScreen) },
                            onDeleteClicked = { viewModel.changeShowUp() },
                            scope,
                            drawerState,
                            viewModel
                        )
                    },
                    backgroundColor = Color.White,
//                    floatingActionButton = {
//                        FloatingActionButton(
//                            onClick = { navigator.push(addNoteScreen) },
//                            backgroundColor = MaterialTheme.colors.primary,
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Add,
//                                contentDescription = "Add",
//                                tint = MaterialTheme.colors.onPrimary
//                            )
//                        }
//                    },
//                    floatingActionButtonPosition = FabPosition.End
                ) {
                    NoteViewFavorite(
                        notes = if (oldOrNew) notes else notesFromOldTONew, viewModel = viewModel,
                        navigator = navigator,
                        showUp = showUp,
                        fromOldest = { oldOrNew = true },
                        fromNewest = { oldOrNew = false }
                    )
                    Napier.d(tag = "Test on show up", message = showUp.toString())
                }
            }
        )
    }
}
