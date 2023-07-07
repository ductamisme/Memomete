package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.viewmodel.note.NoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.resources.DarkColorPalette
import com.twoup.personalfinance.resources.LightColorPalette
import io.github.aakira.napier.Napier

class NoteScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NoteViewModel() }
        val navigator = LocalNavigator.currentOrThrow
        val searchScreen = rememberScreen(SharedScreen.SearchNote)
        val addNoteScreen = rememberScreen(SharedScreen.AddNoteScreen)
        val avatarScreen = rememberScreen(SharedScreen.AvatarScreen)
        val settingScreen = rememberScreen(SharedScreen.SettingScreen)
        val notes by viewModel.notes.collectAsState(emptyList())
        val showUp by viewModel.showUp.collectAsState()

        LaunchedEffect(navigator) {
            viewModel.loadNotes()
        }

        Scaffold(
            topBar = {
                TopBarHomePage(
                    onSearchClicked = { navigator.push(searchScreen) },
                    onAvatarClick = { navigator.push(avatarScreen) },
                    onDeleteClicked = { viewModel.changeShowUp() }
                )
            },
            backgroundColor = Color.White,
            floatingActionButton =
            {
                FloatingActionButton(
                    onClick = { navigator.push(addNoteScreen) },
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier.offset(y = (-50).dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Black)
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            noteView(
                notes,
                viewModel,
                navigator,
                showUp
            ) { navigator.push(settingScreen) }
            Napier.d(
                tag = "Test on show up",
                message = showUp.toString()
            )
        }
    }
}

@Composable
fun noteView(
    notes: List<NoteEntity>,
    viewModel: NoteViewModel,
    navigator: Navigator,
    onShowUp: Boolean,
    onNavigateToSetting: () -> Unit
) {
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

            if (!onShowUp) {
                AnimatedVisibility(
                    visible = !onShowUp,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    IconButton(
                        onClick = onNavigateToSetting,
                        enabled = !onShowUp
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Setting Button",
                            tint = Color.Gray,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            } else {
                AnimatedVisibility(
                    visible = onShowUp,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Button(
                        onClick = { viewModel.deleteNotes() },
                        enabled = onShowUp
                    ) {
                        Text("Clear all")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                items(notes) { note ->
                    val editNoteScreen = rememberScreen(
                        SharedScreen.EditNoteScreen(
                            note
                        )
                    )
                    ItemNotes(
                        noteEntity = note,
                        onNoteClick = { navigator.push(editNoteScreen) },
                        onNoteDelete = { viewModel.deleteNoteById(note.id!!) },
                        onShowUp = onShowUp,
                    )
                }
            }
        }
    }
}

@Composable
fun ItemNotes(
    noteEntity: NoteEntity,
    onNoteClick: () -> Unit,
    onNoteDelete: () -> Unit,
    onShowUp: Boolean,
) {
    val formattedDate = remember(noteEntity.created) {
        DateTimeUtil.formatNoteDate(noteEntity.created)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            backgroundColor = Color.White,
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onNoteClick)
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = noteEntity.description,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal
                )
            }
            AnimatedVisibility(
                visible = onShowUp,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Box(modifier = Modifier.padding(8.dp)) {
                    IconButton(
                        onClick = onNoteDelete,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear Icon",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = noteEntity.title,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = formattedDate,
            color = Color.DarkGray,
        )
    }
}

@Composable
fun TopBarHomePage(
    onSearchClicked: () -> Unit,
    onAvatarClick: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = { Text("Memomates", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = onAvatarClick) {
                Icon(Icons.Filled.Person, contentDescription = "Avatar")
            }
            IconButton(onClick = onDeleteClicked) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    )
}
