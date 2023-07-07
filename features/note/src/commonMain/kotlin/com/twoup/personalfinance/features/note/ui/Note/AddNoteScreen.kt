package com.twoup.personalfinance.features.note.ui.Note

import AddNoteUiState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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
import com.twoup.personalfinance.features.note.viewmodel.note.AddNoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity

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
        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black
        )
        val scrollState = rememberScrollState()


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Add Note", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
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
                    elevation = AppBarDefaults.TopAppBarElevation
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
                            val note = NoteEntity(
                                uiState.id,
                                uiState.title,
                                uiState.description,
                                uiState.created
                            )
                            viewModel.insertNote(note)
                            navigator.push(noteScreen)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Add Note")
                    }
                }
            }
        }
    }
}
