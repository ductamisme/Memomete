package com.twoup.personalfinance.features.note.ui.Note.noteApp.folderNote

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
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

class NoteFolderScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val searchScreen = rememberScreen(SharedScreen.SearchNote)
        val addNoteScreen = rememberScreen(SharedScreen.AddNoteScreen)
        val avatarScreen = rememberScreen(SharedScreen.AvatarScreen)
        val notes by viewModel.notes.collectAsState(emptyList())
        val showUp by viewModel.showUp.collectAsState()
//        var showUp = remember { MutableStateFlow(false) }
        val notesFromOldTONew = notes.sortedByDescending { it.created }
        var oldOrNew by remember { mutableStateOf(false) }
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        LaunchedEffect(navigator) {
            viewModel.loadNotes()
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
                    backgroundColor = MaterialTheme.colors.background,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navigator.push(addNoteScreen) },
                            backgroundColor = MaterialTheme.colors.primary,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) {
                    NoteFolderViews(
                        notes = if (oldOrNew) notes else notesFromOldTONew, viewModel = viewModel,
                        navigator = navigator,
                        showUp = showUp,
                        fromNewest = { oldOrNew = false },
                        fromOldest = { oldOrNew = true },
                    )
                    Napier.d(tag = "Test on show up", message = showUp.toString())
                }
            }
        )
    }
}