package com.twoup.personalfinance.features.note.ui.Note.noteApp.favoriteNote

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.local.date.DateTimeUtil

@Composable
fun ItemNoteFavorite(
    noteEntity: NoteEntity,
    onNoteClick: () -> Unit,
    onNoteDelete: () -> Unit,
    onShowUp: Boolean,
    viewModel: NoteViewModel
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
                        onClick = {
                            onNoteDelete()
                        },
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
            AnimatedVisibility(
                visible = !onShowUp,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Box(modifier = Modifier.padding(8.dp)) {
                    IconButton(
                        onClick = {
                            noteEntity.favourite = if (noteEntity.favourite == 0L) 1L else 0L
                            viewModel.updateNote(noteEntity)
                        },
                        modifier = Modifier.align(Alignment.TopEnd),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = if (noteEntity.favourite == 1L) MaterialTheme.colors.error else MaterialTheme.colors.primaryVariant.copy(
                                alpha = 0.6f
                            )
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
