package com.twoup.personalfinance.features.note.ui.Note.information

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.viewmodel.note.AvatarUiState
import com.twoup.personalfinance.features.note.viewmodel.note.AvatarViewModel
import com.twoup.personalfinance.features.people.ui.icons.Description
import com.twoup.personalfinance.features.people.ui.icons.Info
import com.twoup.personalfinance.features.people.ui.icons.Language
import com.twoup.personalfinance.features.people.ui.icons.Schedule
import com.twoup.personalfinance.model.information.local.InformationEntity
import com.twoup.personalfinance.model.note.local.NoteEntity

enum class AvatarOption(val icon: ImageVector) {
    PERSON(Icons.Default.Person),
    STAR(Icons.Default.Star),
    SCHEDULE(Icons.Default.Schedule),
    INFO(Icons.Default.Info),
    LANGUAGE(Icons.Default.Language),
    FACE(Icons.Default.Face),
    FAVORITE(Icons.Default.Favorite),
    EMAIL(Icons.Default.Email),
    PHONE(Icons.Default.Phone),
    HOME(Icons.Default.Home),
    SEND(Icons.Default.Send),
    WARNING(Icons.Default.Warning),
    DESCRIPTION(Icons.Default.Description),
    DELETE(Icons.Default.Delete),
    LOCK(Icons.Default.Lock)
}

class AvatarScreen : Screen {
    @Composable
    override fun Content() {
//        val viewModel = rememberScreenModel { AvatarViewModel() }
        val uiState = remember { AvatarUiState() }
        val navigator = LocalNavigator.currentOrThrow
        var enteredName by rememberSaveable { mutableStateOf("") }
        var enteredEmail by rememberSaveable { mutableStateOf("") }
        var selectedAvatar by rememberSaveable { mutableStateOf(AvatarOption.PERSON) }

        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Avatar", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
//                            .alpha(animatedAlpha)
                        .animateContentSize(
                            animationSpec = spring(dampingRatio = 0.6f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = selectedAvatar.icon,
                        contentDescription = "User Avatar",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .animateContentSize(
                                animationSpec = spring(dampingRatio = 0.6f)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                AnimatedVisibility(visible = true) {
                    Column {
                        OutlinedTextField(
                            value = uiState.name,
                            onValueChange = { uiState.updateName(it) },
                            label = { Text("Name", style = TextStyle(color = Color.Black)) },
                            colors = textFieldColors,
                            modifier = Modifier.width(330.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.email,
                            onValueChange = {uiState.updateEmail(it) },
                            label = { Text("Email", style = TextStyle(color = Color.Black)) },
                            colors = textFieldColors,
                            modifier = Modifier.width(330.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        val information = InformationEntity(
                            uiState.name,
                            uiState.email
                        )
//                        viewModel.insertInformation(information)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Add Information")
                }
                Spacer(modifier = Modifier.height(48.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Select an Avatar",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    itemsIndexed(AvatarOption.values().toList().chunked(5)) { rowIndex, row ->
                        LazyRow(content = {
                            items(row) { avatar ->
                                AvatarOption(
                                    avatar = avatar.icon,
                                    selected = avatar == selectedAvatar,
                                    onClick = { selectedAvatar = avatar }
                                )
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun AvatarOption(
    avatar: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clickable(onClick = onClick)
            .background(
                color = if (selected) Color.Gray else Color.White,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = avatar,
            contentDescription = "Avatar Option",
            tint = Color.LightGray,
            modifier = Modifier.size(48.dp)
        )
    }
}