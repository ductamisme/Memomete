package com.twoup.personalfinance.features.note.ui.Note.noteApp.yourNote

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.noteApp.DrawerContent
import com.twoup.personalfinance.features.note.ui.Note.noteApp.TopBarHomePage
import com.twoup.personalfinance.features.note.ui.Note.search.ItemNotesSearch
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.NoteEntity
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
                DrawerContent(notes)
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
                    backgroundColor = Color.White,
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
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                            items(searchResult.value) { note ->
                                val editScreen = rememberScreen(
                                    SharedScreen.EditNoteScreen(
                                        selectedNote ?: NoteEntity(
                                            note.id,
                                            note.title,
                                            note.description,
                                            DateTimeUtil.now(),
                                            note.favourite,
                                            note.trash
                                        )
                                    )
                                )
                                ItemNotesSearch(
                                    note,
                                ) { navigator.push(editScreen) }
                            }
                            Napier.d(
                                tag = "Test on searchResult.value",
                                message = searchResult.value.toString()
                            )
                        }
                    }
                    Napier.d(tag = "Test on show up", message = showUp.toString())
                }
            }
        )
    }
}


