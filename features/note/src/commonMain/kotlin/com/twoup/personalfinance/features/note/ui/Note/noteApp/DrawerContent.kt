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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.DrawerItem
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
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
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
        Text(
            text = textEnd.takeIf { it.isNotEmpty() } ?: "",
            modifier = Modifier.weight(0.2f),
            fontSize = 16.sp

        )
    }
}

@Composable
fun DrawerContent(viewModel: NoteViewModel) {
    val colors = MaterialTheme.colors
    val selectedItemIndex by viewModel.selectedItemIndex.collectAsState(0)
    val notes by viewModel.notes.collectAsState()
    val tags by viewModel.tags.collectAsState()

    val mainNotesCount = notes.count { it.trash == 0L }
    val favoriteNotesCount = notes.count { it.favourite == 1L && it.trash == 0L }
    val trashNotesCount = notes.count { it.trash == 1L }
//    val tagNotesCount = tags.count {  }

    val navigator = LocalNavigator.currentOrThrow
    val noteScreen = rememberScreen(SharedScreen.NoteScreen)
    val noteFavoriteScreen = rememberScreen(SharedScreen.NoteScreenFavorite)
    val noteTrashScreen = rememberScreen(SharedScreen.NoteTrashScreen)
    val noteSettingScreen = rememberScreen(SharedScreen.SettingScreen)
    val noteTagScreen = rememberScreen(SharedScreen.NoteTagScreen)
    val noteFolderScreen = rememberScreen(SharedScreen.NoteFolderScreen)

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Menu",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            color = colors.primary,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(
            icon = Icons.Default.Description,
            text = "Your Notes",
            textEnd = mainNotesCount.toString(),
            isSelected = selectedItemIndex == DrawerItem.YOUR_NOTES,
            onClick = {
                navigator.push(noteScreen)
                viewModel.setSelectedItemIndex(DrawerItem.YOUR_NOTES)
            }
        )
        DrawerItem(
            icon = Icons.Default.Favorite,
            text = "Favorites",
            textEnd = favoriteNotesCount.toString(),
            isSelected = selectedItemIndex == DrawerItem.FAVORITES,
            onClick = {
                navigator.push(noteFavoriteScreen)
                viewModel.setSelectedItemIndex(DrawerItem.FAVORITES)
            }
        )

        DrawerItem(
            icon = Icons.Default.Star,
            text = "Tags",
            textEnd = tags.size.toString(),
            isSelected = selectedItemIndex == DrawerItem.TAGS,
            onClick = {
                navigator.push(noteTagScreen)
                viewModel.setSelectedItemIndex(DrawerItem.TAGS)
            }
        )

        DrawerItem(
            icon = Icons.Default.Delete,
            text = "Trash",
            textEnd = trashNotesCount.toString(),
            isSelected = selectedItemIndex == DrawerItem.TRASH,
            onClick = {
                navigator.push(noteTrashScreen)
                viewModel.setSelectedItemIndex(DrawerItem.TRASH)
            }
        )

        DrawerItem(
            icon = Icons.Default.Create,
            text = "Folders",
            isSelected = selectedItemIndex == DrawerItem.FOLDERS,
            textEnd = "0",
            onClick = {
                navigator.push(noteFolderScreen)
                viewModel.setSelectedItemIndex(DrawerItem.FOLDERS)
            }
        )

        DrawerItem(
            icon = Icons.Default.Settings,
            text = "Settings",
            textEnd = "",
            isSelected = selectedItemIndex == DrawerItem.SETTINGS,
            onClick = {
                navigator.push(noteSettingScreen)
                viewModel.setSelectedItemIndex(DrawerItem.SETTINGS)
            }
        )
    }
}

