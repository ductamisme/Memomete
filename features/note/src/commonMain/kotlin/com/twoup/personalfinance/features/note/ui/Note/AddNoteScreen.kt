package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.AddNoteUiState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.AddNoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import io.github.aakira.napier.Napier

class AddNoteScreen : Screen {
    @Composable
    override fun Content() {
        AddScreen()
    }

    @Composable
    fun AddScreen() {
        val viewModel = rememberScreenModel { AddNoteViewModel() }
        val tags by viewModel.tags.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val noteScreen = rememberScreen(SharedScreen.NoteScreen)
        val uiState = remember { AddNoteUiState() }
        val scrollState = rememberScrollState()
        var isHintTitleVisible by remember { mutableStateOf(uiState.title.isEmpty()) }
        var isHintDescriptionVisible by remember { mutableStateOf(uiState.description.isEmpty()) }
        var showTagDiaLog by remember { mutableStateOf(false) }

        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
        )

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
                                uiState.deleteCreated,
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
                    actions = {
                        IconButton(onClick = {
                            showTagDiaLog = true
                        }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
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
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        singleLine = true,
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
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        singleLine = false
                    )

                }
            }
            AnimatedVisibility(
                visible = showTagDiaLog,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                LaunchedEffect(navigator) {
                    viewModel.loadTags()
                }
                Box(
                    modifier = Modifier
                        .offset(y = (450).dp)
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.Bottom)
                        .padding(16.dp)
                        .background(MaterialTheme.colors.primaryVariant)
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Add Tag",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 14.dp)
                                )

                                TextButton(
                                    onClick = {
                                        showTagDiaLog = !showTagDiaLog
                                        val note = NoteEntity(
                                            uiState.id,
                                            uiState.title,
                                            uiState.description,
                                            uiState.created,
                                            uiState.deleteCreated,
                                            uiState.favourite,
                                            uiState.trash
                                        )
                                        val tag = TagEntity(
                                            uiState.idTag,
                                            uiState.nameTag
                                        )
                                        viewModel.insertNoteTag(note = note, tag = tag)
                                        viewModel.tagAssociateWithNote(note = note)
                                    },
                                ) {
                                    Text(
                                        "Save", fontSize = 16.sp,
                                    )
                                }
                            }

                            AnimatedVisibility(
                                visible = tags.isNotEmpty(),
                                enter = fadeIn() + slideInVertically(),
                                exit = fadeOut() + slideOutVertically()
                            ) {
                                Column(modifier = Modifier.height(100.dp)) {
                                    LazyVerticalGrid(
                                        columns = GridCells.Adaptive(100.dp),
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(20.dp),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        items(tags) { tag ->
                                            Text(text = tag.name)
//                                            Text(text = note)

                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.padding(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                OutlinedTextField(
                                    value = uiState.nameTag,
                                    onValueChange = { newText ->
                                        uiState.nameTag = newText
                                    },
                                    label = {
                                        Text(
                                            "Name",
                                            style = TextStyle(MaterialTheme.colors.onPrimary)
                                        )
                                    },
                                    colors = textFieldColors,
                                )

                                Spacer(modifier = Modifier.padding(16.dp))

                                Button(onClick = {
                                    val tag = TagEntity(
                                        uiState.idTag,
                                        uiState.nameTag
                                    )
                                    viewModel.insertTag(tag = tag)
                                    viewModel.loadTags()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
