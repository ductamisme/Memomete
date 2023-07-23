package com.twoup.personalfinance.features.note.ui.Note.noteApp.yourNote

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.NoteEntity
import io.github.aakira.napier.Napier

@Composable
fun NoteViews(
    notes: List<NoteEntity>,
    viewModel: NoteViewModel,
    navigator: Navigator,
    showUp: Boolean,
    fromNewest: () -> Unit,
    fromOldest: () -> Unit
) {
    val trashNotes = notes.filter { it.trash == 0L }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var selectedNote: NoteEntity? by remember { mutableStateOf(null) }

    Box() {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your notes",
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
                                onClick = fromNewest,
                                enabled = !showUp
                            ) {
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Drop down",
                                    tint = colors.primaryVariant,
                                    modifier = Modifier.size(30.dp)
                                )
                                Napier.d(
                                    tag = "Test on searchResult.value",
                                    message = "fromNewest"
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
                                Napier.d(
                                    tag = "Test on searchResult.value",
                                    message = "fromOldest"
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
                                showDeleteConfirmation = true
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
                visible = trashNotes.isNotEmpty(),
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
                        val editNoteScreen = rememberScreen(
                            SharedScreen.EditNoteScreen(
                                note
                            )
                        )
                        ItemNotes(
                            noteEntity = note,
                            onNoteClick = { navigator.push(editNoteScreen) },
                            viewModel = viewModel,
                            onShowUp = showUp,
                            onDeleteClick = {
                                showDeleteConfirmation = true
                                selectedNote = note
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = trashNotes.isEmpty(),
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.TopCenter)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Create,
                            contentDescription = "Attention Icon",
                            tint = colors.primary, // Use a color that matches your design
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Your notes await",
                            fontSize = 24.sp,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.LightGray, // Use a pastel color of your choice
                            modifier = Modifier
                                .padding(vertical = 100.dp)
                                .animateContentSize() // Apply smooth animation
                        )
                    }
                }
            }
        }
        if (trashNotes.isNotEmpty()) {
            dialog(
                showDeleteConfirmation = showDeleteConfirmation,
                onYesClick = {
                    trashNotes.forEach { note ->
                        note.trash = if (note.trash == 0L) 1L else 0L
                        note.deleteCreated = DateTimeUtil.now()
                        viewModel.updateNote(note)
                    }
                    viewModel.loadNoteNoteContainTrash()
                    showDeleteConfirmation = false
                },
                onCancelClick = {
                    showDeleteConfirmation = false
                },
                titleDialog = "Are you sure delete all the notes?"
            )
        }

        if (showDeleteConfirmation && selectedNote != null) {
            dialog(
                showDeleteConfirmation = showDeleteConfirmation,
                onYesClick = {
                    selectedNote?.trash = if (selectedNote?.trash == 0L) 1L else 0L
                    selectedNote?.deleteCreated = DateTimeUtil.now()
                    viewModel.updateNote(selectedNote!!)
                    viewModel.loadNoteNoteContainTrash()
                    showDeleteConfirmation = false
                    selectedNote = null // Reset the selectedNote after deletion
                }
                ,
                onCancelClick = {
                    showDeleteConfirmation = false
                },
                titleDialog = "Are you sure delete this note?"
            )
        }
    }
}



