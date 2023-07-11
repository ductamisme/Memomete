package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.model.note.local.NoteEntity
import androidx.compose.material.icons.filled.Refresh
import com.twoup.personalfinance.features.note.ui.Note.noteApp.dialog
import com.twoup.personalfinance.features.note.viewmodel.note.EditNoteDeleteUiState
import com.twoup.personalfinance.features.note.viewmodel.note.NoteViewModel
import io.github.aakira.napier.Napier

class EditNoteDeleteScreen(private val note: NoteEntity) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val noteScreen = rememberScreen(SharedScreen.NoteScreen)
        val uiState = remember { EditNoteDeleteUiState(note) }
        val scrollState = rememberScrollState()
        var isHintTitleVisible by remember { mutableStateOf(uiState.title.isEmpty()) }
        var isHintDescriptionVisible by remember { mutableStateOf(uiState.description.isEmpty()) }

        var showDeleteConfirmation by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Edit Note",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.push(noteScreen)
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = AppBarDefaults.TopAppBarElevation,
                    actions = {
                        IconButton(
                            onClick = {
                                showDeleteConfirmation = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    TransparentHintTextField(
                        text = uiState.title,
                        hint = "Enter title",
                        isHintVisible = isHintTitleVisible,
                        onValueChanged = { newText ->
                            uiState.title = newText
                            isHintTitleVisible = newText.isEmpty()
                            saveNote(uiState, viewModel)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textStyle = TextStyle(
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        ),
                        singleLine = true
                    )

                    TransparentHintTextField(
                        text = uiState.description,
                        hint = "Enter description",
                        isHintVisible = isHintDescriptionVisible,
                        onValueChanged = { newText ->
                            uiState.description = newText
                            isHintDescriptionVisible = newText.isEmpty()
                            saveNote(uiState, viewModel)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.onPrimary
                        ),
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            dialog(
                showDeleteConfirmation = showDeleteConfirmation,
                onYesClick = {
                    uiState.trash = if (uiState.trash == 0L) 1L else 0L
                    saveNote(uiState, viewModel)
                    Napier.d(
                        tag = "Test on restore",
                        message = uiState.trash.toString()
                    )
                    showDeleteConfirmation = false
                    navigator.push(noteScreen)

                },
                onCancelClick = {
                    showDeleteConfirmation = false
                }
            )
        }
    }
}

fun saveNote(uiState: EditNoteDeleteUiState, viewModel: NoteViewModel) {
    val note = NoteEntity(
        uiState.id,
        uiState.title,
        uiState.description,
        uiState.created,
        uiState.favourite,
        uiState.trash
    )
    viewModel.updateNote(note)
}
