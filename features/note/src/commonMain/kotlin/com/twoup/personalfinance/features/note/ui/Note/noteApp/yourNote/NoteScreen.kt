package com.twoup.personalfinance.features.note.ui.Note.noteApp.yourNote

import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
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

class NoteScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val searchScreen = rememberScreen(SharedScreen.SearchNote)
        val addNoteScreen = rememberScreen(SharedScreen.AddNoteScreen)
        val avatarScreen = rememberScreen(SharedScreen.AvatarScreen)
        val notes by viewModel.notes.collectAsState(emptyList())
        val showUp by viewModel.showUp.collectAsState()
        val searchResult by viewModel.searchResults.collectAsState()
        val selectedNote by viewModel.selectedNote.collectAsState()
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
                            drawerState
                        )
                    },
                    backgroundColor = MaterialTheme.colorScheme.background,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navigator.push(addNoteScreen) },
                            backgroundColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) {
                    if (searchResult.value.isEmpty()) {
                        NoteViews(
                            notes = notes, viewModel = viewModel,
                            navigator = navigator,
                            showUp = showUp,
                            fromNewest = { viewModel.fromNewest() },
                            fromOldest = { viewModel.fromOldest() },
                        )
                        Napier.d(
                            tag = "Test on searchResult.value",
                            message = searchResult.value.toString()
                        )
                    }
//                    else {
//                        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
//                            items(searchResult.value) { note ->
//                                val editScreen = rememberScreen(
//                                    SharedScreen.EditNoteScreen(
//                                        selectedNote ?: NoteEntity(
//                                            note.id,
//                                            note.title,
//                                            note.description,
//                                            DateTimeUtil.now(),
//                                            note.favourite,
//                                            note.trash
//                                        )
//                                    )
//                                )
//                                ItemNotesSearch(
//                                    note,
//                                ) { navigator.push(editScreen) }
//                            }
//                            Napier.d(
//                                tag = "Test on searchResult.value",
//                                message = searchResult.value.toString()
//                            )
//                        }
//                    }
                    Napier.d(tag = "Test on show up", message = showUp.toString())
                }
            }
        )
    }
}


