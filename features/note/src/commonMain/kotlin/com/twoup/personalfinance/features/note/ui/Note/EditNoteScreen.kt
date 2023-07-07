package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.viewmodel.note.EditNoteUiState
import com.twoup.personalfinance.features.note.viewmodel.note.EditNoteViewModel
import com.twoup.personalfinance.features.note.viewmodel.note.NoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity

class EditNoteScreen(private val note: NoteEntity) : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { EditNoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val noteScreen = rememberScreen(SharedScreen.NoteScreen)
        val uiState = remember { EditNoteUiState(note) }

        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black
        )

        val scrollState = rememberScrollState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Edit Note",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator.pop() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = AppBarDefaults.TopAppBarElevation,
                    actions = {
                        IconButton(
                            onClick = {
                                viewModel.deleteNoteById(note.id!!)
                                navigator.pop()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Delete"
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
                    OutlinedTextField(
                        value = uiState.title,
                        onValueChange = { uiState.updateTitle(it) },
                        label = { Text("Title", style = TextStyle(color = Color.Black)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = uiState.description,
                        onValueChange = { uiState.updateDescription(it) },
                        label = { Text("Description", style = TextStyle(color = Color.Black)) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = textFieldColors,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val updatedNote = NoteEntity(
                                uiState.id,
                                uiState.title,
                                uiState.description,
                                uiState.created
                            )
                            viewModel.updateComic(updatedNote)
                            navigator.push(noteScreen)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Update Note")
                    }
                }
            }
        }
    }
}