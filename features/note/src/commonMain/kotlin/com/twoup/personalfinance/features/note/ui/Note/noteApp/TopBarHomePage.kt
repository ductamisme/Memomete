package com.twoup.personalfinance.features.note.ui.Note.noteApp

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBarHomePage(
    onSearchClicked: () -> Unit,
    onAvatarClick: () -> Unit,
    onDeleteClicked: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
) {
    TopAppBar(
        title = {
            Text(
                "Memomates",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
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
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}