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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
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
import com.twoup.personalfinance.features.note.ui.Note.noteApp.CustomItem
import com.twoup.personalfinance.features.note.ui.Note.noteApp.CustomTextButton
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.AddNoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import io.github.aakira.napier.Napier

class AddNoteScreen() : Screen {
    @Composable
    override fun Content() {
        AddScreen()
    }

    @Composable
    fun AddScreen() {
        val viewModel = rememberScreenModel { AddNoteViewModel() }
        val folders by viewModel.folders.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val noteScreen = rememberScreen(SharedScreen.NoteScreen)
        val uiState = remember { AddNoteUiState() }
        val scrollState = rememberScrollState()
        var isHintTitleVisible by remember { mutableStateOf(uiState.title.isEmpty()) }
        var isHintDescriptionVisible by remember { mutableStateOf(uiState.description.isEmpty()) }
        var showTagDiaLog by remember { mutableStateOf(false) }
        val isHintTagVisible by remember { mutableStateOf(uiState.description.isEmpty()) }
        val uniqueFolders = folders.filter { it.trash == 0L }.map { it.folder }.distinct()
        val currentlySelectedTag = remember { mutableStateOf("") }
        var shouldLoadFolderData by remember { mutableStateOf(true) }

        val note = NoteEntity(
            uiState.id,
            uiState.title,
            uiState.description,
            uiState.created,
            uiState.deleteCreated,
            uiState.favourite,
            uiState.trash,
            uiState.tag,
            uiState.folder
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Add Note",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onPrimary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            Napier.d(tag = "Test add note", message = uiState.favourite.toString())
                            viewModel.insertNote(note)
                            navigator.push(noteScreen)
                            Napier.d(tag = "Test edit note ", message = uiState.id.toString())
                            Napier.d(tag = "Test edit note ", message = uiState.title)
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    backgroundColor = colors.primary,
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
                            Napier.d(tag = "Test edit note ", message = uiState.id.toString())
                            Napier.d(tag = "Test edit note ", message = uiState.title)
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
                            Napier.d(tag = "Test edit note ", message = uiState.id.toString())
                            Napier.d(tag = "Test edit note ", message = uiState.description)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = colors.onPrimary
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                        singleLine = false
                    )

                }
            }

            LaunchedEffect(key1 = shouldLoadFolderData) {
                if (shouldLoadFolderData) {
                    viewModel.loadFolder()
                    // Set it to false after loading the data to prevent reloading when the composable recomposes
                    shouldLoadFolderData = false
                }
            }

            AnimatedVisibility(
                visible = showTagDiaLog,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                LaunchedEffect(key1 = Unit) {
                    viewModel.loadFolder()
                }
                Box(
                    modifier = Modifier
                        .offset(y = (400).dp)
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.Bottom)
                        .padding(16.dp)
                        .background(colors.primaryVariant)
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
                                    text = "Add Folder",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 14.dp)
                                )

                                TextButton(
                                    onClick = {
                                        showTagDiaLog = !showTagDiaLog
//                                            viewModel.loadFolder()
                                        viewModel.updateNote(note.copy(tag = uiState.tag))
                                        viewModel.loadFolder()

                                    },
                                ) {
                                    Text(
                                        "Save", fontSize = 16.sp,
                                    )
                                }
                            }

                            AnimatedVisibility(
                                visible = folders.isNotEmpty(),
                                enter = fadeIn() + slideInVertically(),
                                exit = fadeOut() + slideOutVertically()
                            ) {
                                LazyVerticalGrid(
                                    columns = GridCells.Adaptive(minSize = 100.dp),
                                    modifier = Modifier.fillMaxWidth().height(100.dp),
                                    verticalArrangement = Arrangement.spacedBy(20.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    itemsIndexed(uniqueFolders) { _, buttonTitle ->
                                        val isPressed = buttonTitle == currentlySelectedTag.value
                                        CustomTextButton(
                                            text = buttonTitle,
                                            onClick = { clickedTitle ->
                                                if (currentlySelectedTag.value == clickedTitle) {
                                                    // Deselect the button if it was already selected
                                                    currentlySelectedTag.value = ""
                                                } else {
                                                    // Select the button if it was not already selected
                                                    currentlySelectedTag.value = clickedTitle
                                                }
                                                // Perform the desired action when the button is clicked
                                                // (e.g., navigate to a new screen or perform some action)
                                            },
                                            isPressed = isPressed // Pass the pressed state to the button
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.padding(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.padding(4.dp)
                            ) {
                                TransparentHintTextFieldDialog(
                                    text = currentlySelectedTag.value.ifEmpty { uiState.folder },
//                                    hint = "",
                                    isHintVisible = isHintTagVisible,
                                    onValueChanged = { newText ->
                                        uiState.folder = currentlySelectedTag.value.ifEmpty {
                                            newText
                                        }
//                                            isHintTagVisible = newText.isEmpty()
                                    },
                                    modifier = Modifier.width(300.dp),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        color = colors.onPrimary
                                    ),
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                                    singleLine = true
                                )

                                CustomItem(
                                    onClick = {
                                        viewModel.updateNote(note.copy(folder = uiState.folder))
//                                        viewModel.loadFolder()
                                        shouldLoadFolderData = true // Set it to true to trigger the reload when the composable recomposes
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}