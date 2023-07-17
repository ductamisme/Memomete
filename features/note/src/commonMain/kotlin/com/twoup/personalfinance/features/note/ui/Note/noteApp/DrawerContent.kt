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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import com.twoup.personalfinance.features.people.ui.icons.Description
import com.twoup.personalfinance.features.people.ui.icons.Settings
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.flow.filter

@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    textEnd: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

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
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
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
fun DrawerContent(viewModel: NoteViewModel) {
    val navigator = LocalNavigator.currentOrThrow
    val noteScreen = rememberScreen(SharedScreen.NoteScreen)
    val noteFavoriteScreen = rememberScreen(SharedScreen.NoteScreenFavorite)
    val noteTrashScreen = rememberScreen(SharedScreen.NoteTrashScreen)
    val noteSettingScreen = rememberScreen(SharedScreen.SettingScreen)

    val noteCount = viewModel.notes.value

    val items = listOf(
        DrawerItemData(Icons.Default.Description, "Your Notes", noteCount.count { it.trash == 0L }),
        DrawerItemData(Icons.Default.Favorite, "Favorites", noteCount.count { it.favourite == 1L }),
        DrawerItemData(Icons.Default.Star, "Tags", 0),
        DrawerItemData(Icons.Default.Delete, "Trash", noteCount.count { it.trash == 1L }),
        DrawerItemData(Icons.Default.Create, "Folders", 0),
        DrawerItemData(Icons.Default.Settings, "Settings", 0)
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Menu",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        items.forEachIndexed { index, item ->
            DrawerItem(
                icon = item.icon,
                text = item.text,
                textEnd = item.count.toString(),
                isSelected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    when (index) {
                        0 -> navigator.push(noteScreen)
                        1 -> navigator.push(noteFavoriteScreen)
                        3 -> navigator.push(noteTrashScreen)
                        5 -> navigator.push(noteSettingScreen)
                    }
                }
            )
        }
    }
}

data class DrawerItemData(val icon: ImageVector, val text: String, val count: Int)

