package com.twoup.personalfinance.features.note.ui.Note.noteApp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.people.ui.icons.Description
import com.twoup.personalfinance.features.people.ui.icons.Settings
import com.twoup.personalfinance.model.note.local.NoteEntity

@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    textEnd: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colors
    val typography = MaterialTheme.typography

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(start = 24.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) colors.primary else colors.primaryVariant.copy(alpha = 0.6f),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = typography.subtitle1.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) colors.primary else colors.primaryVariant.copy(alpha = 0.6f)
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = textEnd.takeIf { it.isNotEmpty() } ?: "-",
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun DrawerContent(notes: List<NoteEntity>) {
    val colors = MaterialTheme.colors
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val mainNotesCount = notes.count { it.trash == 0L }
    val favoriteNotesCount = notes.count { it.favourite == 1L }
    val trashNotesCount = notes.count { it.trash == 1L }

    val navigator = LocalNavigator.currentOrThrow
    val noteScreen = rememberScreen(SharedScreen.NoteScreen)
    val noteFavoriteScreen = rememberScreen(SharedScreen.NoteScreenFavorite)
    val noteTrashScreen = rememberScreen(SharedScreen.NoteTrashScreen)
    val noteSettingScreen = rememberScreen(SharedScreen.SettingScreen)

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Menu",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            color = colors.onPrimary,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            icon = Icons.Default.Description,
            text = "Your Notes",
            textEnd = mainNotesCount.toString(),
            isSelected = selectedItemIndex == 0,
            onClick = {
                selectedItemIndex = 0
                navigator.push(noteScreen)
            }
        )

        DrawerItem(
            icon = Icons.Default.Favorite,
            text = "Favorites",
            textEnd = favoriteNotesCount.toString(),
            isSelected = selectedItemIndex == 1,
            onClick = {
                selectedItemIndex = 1
                navigator.push(noteFavoriteScreen)
            }
        )

        DrawerItem(
            icon = Icons.Default.Star,
            text = "Tags",
            textEnd = notes.size.toString(),
            isSelected = selectedItemIndex == 2,
            onClick = {
                selectedItemIndex = 2
            }
        )

        DrawerItem(
            icon = Icons.Default.Delete,
            text = "Trash",
            textEnd = trashNotesCount.toString(),
            isSelected = selectedItemIndex == 3,
            onClick = {
                selectedItemIndex = 3
                navigator.push(noteTrashScreen)
            }
        )

        DrawerItem(
            icon = Icons.Default.Create,
            text = "Folders",
            isSelected = selectedItemIndex == 4,
            textEnd = notes.size.toString(),
            onClick = {
                selectedItemIndex = 4
            }
        )

        DrawerItem(
            icon = Icons.Default.Settings,
            text = "Settings",
            textEnd = notes.size.toString(),
            isSelected = selectedItemIndex == 5,
            onClick = {
                selectedItemIndex = 5
                navigator.push(noteSettingScreen)
            }
        )
    }
}

