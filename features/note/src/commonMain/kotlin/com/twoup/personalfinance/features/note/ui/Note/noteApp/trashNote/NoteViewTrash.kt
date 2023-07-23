package com.twoup.personalfinance.features.note.ui.Note.noteApp.trashNote

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.noteApp.dialog
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import com.twoup.personalfinance.features.people.ui.icons.Aod
import com.twoup.personalfinance.features.people.ui.icons.Notifications
import com.twoup.personalfinance.features.people.ui.icons.Warning
import com.twoup.personalfinance.model.note.local.NoteEntity
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun NoteViewTrash(
    notes: List<NoteEntity>,
    viewModel: NoteViewModel,
    navigator: Navigator,
    showUp: Boolean,
    fromNewest: () -> Unit,
    fromOldest: () -> Unit
) {
    val trashNotes = notes.filter { it.trash != null && it.trash == 1L }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var showTakeNote by remember { mutableStateOf(false) }
    var selectedNote: NoteEntity? by remember { mutableStateOf(null) }
    var showDeleteAllConfirmation by remember { mutableStateOf(false) }

    LaunchedEffect(navigator) {
        viewModel.loadNotes()
//        selectedNote?.id?.let { viewModel.deleteNoteBy30Days(it, selectedNote!!.created) }
    }
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
                            onClick = { showTakeNote = !showTakeNote },
                            enabled = !showUp
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Info",
//                                tint = colors.primaryVariant,
                                tint = if (showTakeNote) colors.primary else colors.primaryVariant.copy(alpha = 0.6f),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(
                            onClick = fromNewest,
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
                            onClick = fromOldest,
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
                            showDeleteAllConfirmation = true
//                            viewModel.deleteAllNotesDeleted()
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
            visible = trashNotes.isEmpty(),
            enter = fadeIn() + slideInVertically(),
//                exit = fadeOut() + slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Attention Icon",
                        tint = Color.Black, // Use a color that matches your design
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Your notes will be automatically deleted in 30 days",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.LightGray, // Use a pastel color of your choice
                        modifier = Modifier
                            .padding(vertical = 100.dp)
                            .animateContentSize() // Apply smooth animation
                    )
                }
            }
        }

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
    AnimatedVisibility(
        visible = showTakeNote,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Box(
            modifier = Modifier
                .offset(y = (400).dp)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.Bottom)
                .padding(16.dp)
                .background(colors.primaryVariant)
                .clickable { showTakeNote = !showTakeNote }
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Your notes will be automatically deleted in 30 days",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                }
            }
        }
    }

    if (trashNotes.isNotEmpty()) {
        dialog(
            showDeleteConfirmation = showDeleteAllConfirmation,
            onYesClick = {
                viewModel.deleteAllNotesDeleted()
                showDeleteAllConfirmation = false
            },
            onCancelClick = {
                showDeleteAllConfirmation = false
            },
            titleDialog = "Are you sure delete all the notes?"
        )
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




