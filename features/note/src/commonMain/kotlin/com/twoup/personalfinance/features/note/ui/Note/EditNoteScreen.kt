package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
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
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.EditNoteUiState
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.EditNoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity

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
//        var showTagDiaLog by remember { mutableStateOf(false) }
//        val tags by viewModel.tags.collectAsState()
//
//        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
//            cursorColor = Color.Black,
//            focusedBorderColor = Color.Black,
//            focusedLabelColor = Color.Black,
//        )

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
                            navigator.pop()
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
//                        IconButton(onClick = {
//                            showTagDiaLog = true
//                        }) {
//                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
//                        }
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
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
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
                        singleLine = false,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),

                        )
//                    Text(text = uiState.id.toString())

                    Spacer(modifier = Modifier.height(16.dp))
                }
//                AnimatedVisibility(
//                    visible = showTagDiaLog,
//                    enter = fadeIn() + slideInVertically(),
//                    exit = fadeOut() + slideOutVertically()
//                ) {
//                    LaunchedEffect(navigator) {
//                        viewModel.loadTags()
//                    }
//                    Box(
//                        modifier = Modifier
//                            .offset(y = (450).dp)
//                            .fillMaxWidth()
//                            .wrapContentHeight(Alignment.Bottom)
//                            .padding(16.dp)
//                            .background(colors.primaryVariant)
//                    ) {
//                        Card(
//                            modifier = Modifier.fillMaxWidth(),
//                            elevation = 8.dp
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .padding(16.dp)
//                                    .fillMaxWidth()
//                            ) {
//                                Row(
//                                    modifier = Modifier.fillMaxWidth(),
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    Text(
//                                        text = "Add Tag",
//                                        fontSize = 16.sp,
//                                        fontWeight = FontWeight.Bold,
//                                        modifier = Modifier.padding(top = 14.dp)
//                                    )
//
//                                    TextButton(
//                                        onClick = {
//                                            showTagDiaLog = !showTagDiaLog
//                                            val note = NoteEntity(
//                                                uiState.id,
//                                                uiState.title,
//                                                uiState.description,
//                                                uiState.created,
//                                                uiState.deleteCreated,
//                                                uiState.favourite,
//                                                uiState.trash
//                                            )
//                                            val tag = TagEntity(
//                                                uiState.idTag,
//                                                uiState.nameTag
//                                            )
//                                            viewModel.insertNoteTag(note = note, tag = tag)
//                                            viewModel.tagAssociateWithNote(note = note)
//                                        },
//                                    ) {
//                                        Text(
//                                            "Save", fontSize = 16.sp,
//                                        )
//                                    }
//                                }
//
//                                AnimatedVisibility(
//                                    visible = tags.isNotEmpty(),
//                                    enter = fadeIn() + slideInVertically(),
//                                    exit = fadeOut() + slideOutVertically()
//                                ) {
//                                    Column(modifier = Modifier.height(100.dp)) {
//                                        LazyVerticalGrid(
//                                            columns = GridCells.Adaptive(100.dp),
//                                            modifier = Modifier.fillMaxWidth(),
//                                            verticalArrangement = Arrangement.spacedBy(20.dp),
//                                            horizontalArrangement = Arrangement.spacedBy(12.dp)
//                                        ) {
//                                            items(tags) { tag ->
//                                                Text(text = tag.name)
//                                                uiState.id
//                                                uiState.idTag
//                                            }
//                                        }
//                                    }
//                                }
//
//                                Spacer(modifier = Modifier.padding(16.dp))
//
//                                Row(
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    OutlinedTextField(
//                                        value = uiState.nameTag,
//                                        onValueChange = { newText ->
//                                            uiState.nameTag = newText
//                                        },
//                                        label = {
//                                            Text(
//                                                "Name",
//                                                style = TextStyle(colors.onPrimary)
//                                            )
//                                        },
//                                        colors = textFieldColors,
//                                    )
//
//                                    Spacer(modifier = Modifier.padding(16.dp))
//
//                                    Button(onClick = {
//                                        val tag = TagEntity(
//                                            uiState.idTag,
//                                            uiState.nameTag
//                                        )
//                                        viewModel.insertTag(tag = tag)
//                                        viewModel.loadTags()
//                                    }) {
//                                        Icon(
//                                            imageVector = Icons.Default.Add,
//                                            contentDescription = null,
//                                            modifier = Modifier.size(20.dp)
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
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
        uiState.deleteCreated,
        uiState.favourite,
        uiState.trash
    )
    viewModel.updateNote(note)
}
