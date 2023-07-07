package com.twoup.personalfinance.features.note.ui.Note.search

import androidx.compose.foundation.BorderStroke
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.NoteEntity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun ItemNotesSearch(
    noteEntity: NoteEntity,
    onNoteClick: () -> Unit,
    // onComicDelete: () -> Unit
) {
    val formattedDate = remember(noteEntity.created) {
        DateTimeUtil.formatNoteDate(noteEntity.created)
    }

    Card(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = { onNoteClick() })
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            NoteTitle(noteEntity.title)
            NoteDescription(noteEntity.description)
            NoteDate(formattedDate, modifier = Modifier.align(Alignment.End))
        }
    }
}

@Composable
fun NoteTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        fontStyle = FontStyle.Italic
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun NoteDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun NoteDate(date: String, modifier: Modifier) {
    Text(
        text = date,
        color = Color.DarkGray,
        modifier = modifier
    )
}
