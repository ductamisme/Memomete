package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
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
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.EditNoteUiState
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.EditNoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity

class EditNoteScreen(private val note: NoteEntity) : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { EditNoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val noteScreen = rememberScreen(SharedScreen.NoteScreen)
        val uiState = remember { EditNoteUiState(note) }
        val scrollState = rememberScrollState()
        var isHintTitleVisible by remember { mutableStateOf(uiState.title.isEmpty()) }
        var isHintDescriptionVisible by remember { mutableStateOf(uiState.description.isEmpty()) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Edit Note",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onPrimary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.push(noteScreen)
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = colors.onPrimary
                            )
                        }
                    },
                    backgroundColor = colors.primary,
                    elevation = AppBarDefaults.TopAppBarElevation,
                    actions = {
                        IconButton(onClick = {
                            uiState.favourite = if (uiState.favourite == 0L) 1L else 0L
                            saveNote(uiState, viewModel)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (uiState.favourite == 1L) colors.error else colors.primaryVariant.copy(alpha = 0.6f)
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
                            color = colors.onPrimary
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
                            color = colors.onPrimary
                        ),
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

private fun saveNote(uiState: EditNoteUiState, viewModel: EditNoteViewModel) {
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
