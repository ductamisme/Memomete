package com.twoup.personalfinance.features.note.ui.Note.noteApp.trashNote

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.noteApp.dialog
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import io.github.aakira.napier.Napier

@Composable
fun NoteViewTrash(
    notes: List<NoteEntity>,
    viewModel: NoteViewModel,
    navigator: Navigator,
    showUp: Boolean,
    onSettingClicked: () -> Unit
) {
    val trashNotes = notes.filter { it.trash != null && it.trash == 1L }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var selectedNote: NoteEntity? by remember { mutableStateOf(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Your Trash",
                fontSize = 24.sp,
            )

            if (!showUp) {
                AnimatedVisibility(
                    visible = !showUp,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Row() {
                        IconButton(
                            onClick = onSettingClicked,
                            enabled = !showUp
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "Drop down",
                                tint = colors.primaryVariant,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(
                            onClick = onSettingClicked,
                            enabled = !showUp
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = "Forward",
                                tint = colors.primaryVariant,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            } else {
                AnimatedVisibility(
                    visible = showUp,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Button(
                        onClick = {
                            viewModel.deleteAllNotesDeleted()
                        },
                        enabled = showUp
                    ) {
                        Text("Clear all")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = notes.isNotEmpty(),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(trashNotes) { note ->
                    val editNoteTrashScreen = rememberScreen(
                        SharedScreen.EditNoteTrashScreen(
                            note
                        )
                    )
                    ItemNoteTrash(
                        noteEntity = note,
                        onNoteClick = { navigator.push(editNoteTrashScreen) },
                        onNoteDelete = {
                            viewModel.deleteNoteDeletedById(note.id!!)
                        },
                        onShowUp = showUp,
                        viewModel = viewModel,
                        showDialog = {
                            selectedNote = note
                            showDeleteConfirmation = true
                        }
                    )
                }
            }
        }
    }

    if (showDeleteConfirmation && selectedNote != null) {
        dialog(
            showDeleteConfirmation = showDeleteConfirmation,
            onYesClick = {
                selectedNote?.trash = if (selectedNote?.trash == 0L) 1L else 0L
                viewModel.updateNote(selectedNote!!)
                Napier.d(
                    tag = "Test on restore",
                    message = selectedNote?.trash.toString()
                )
                showDeleteConfirmation = false
            },
            onCancelClick = {
                showDeleteConfirmation = false
            },
            titleDialog = "Are you sure restore this note?"
        )
    }
}




