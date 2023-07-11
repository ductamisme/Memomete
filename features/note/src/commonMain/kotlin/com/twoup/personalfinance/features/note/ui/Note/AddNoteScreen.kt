package com.twoup.personalfinance.features.note.ui.Note

import com.twoup.personalfinance.features.note.viewmodel.note.AddNoteUiState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.twoup.personalfinance.features.note.viewmodel.note.AddNoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import io.github.aakira.napier.Napier

class AddNoteScreen : Screen {
    @Composable
    override fun Content() {
        AddScreen()
    }
    @Composable
    fun AddScreen() {
        val viewModel = rememberScreenModel { AddNoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val noteScreen = rememberScreen(SharedScreen.NoteScreen)
        val uiState = remember { AddNoteUiState() }
        val scrollState = rememberScrollState()
        var isHintTitleVisible by remember { mutableStateOf(uiState.title.isEmpty()) }
        var isHintDescriptionVisible by remember { mutableStateOf(uiState.description.isEmpty()) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Add Note",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            val note = NoteEntity(
                                uiState.id,
                                uiState.title,
                                uiState.description,
                                uiState.created,
                                uiState.favourite,
                                uiState.trash
                            )
                            Napier.d(tag = "Test add note", message = uiState.favourite.toString())
                            viewModel.insertNote(note)
                            navigator.push(noteScreen)
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = AppBarDefaults.TopAppBarElevation,
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    TransparentHintTextField(
                        text = uiState.title,
                        hint = "Enter title",
                        isHintVisible = isHintTitleVisible,
                        onValueChanged = { newText ->
                            uiState.title = newText
                            isHintTitleVisible = newText.isEmpty()
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
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.onPrimary
                        ),
                    )
                }
            }
        }
    }
}
//private fun saveNoteEdit(uiState: com.twoup.personalfinance.features.note.viewmodel.note.AddNoteUiState, viewModel: EditNoteViewModel) {
//    val note = NoteEntity(
//        uiState.id,
//        uiState.title,
//        uiState.description,
//        uiState.created,
//        uiState.favourite
//    )
//    viewModel.updateNote(note)
//}
