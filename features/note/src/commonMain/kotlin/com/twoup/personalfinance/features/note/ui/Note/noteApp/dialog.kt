package com.twoup.personalfinance.features.note.ui.Note.noteApp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twoup.personalfinance.features.note.viewmodel.note.NoteViewModel

@Composable
fun dialog(
    showDeleteConfirmation: Boolean,
    onYesClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    AnimatedVisibility(
        visible = showDeleteConfirmation,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Box(
            modifier = Modifier
                .offset(y = (500).dp)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.Bottom)
                .padding(16.dp)
                .background(MaterialTheme.colors.primaryVariant)
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
                    Text(
                        text = "Are you sure you want to delete?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                onCancelClick()
//                                showDeleteConfirmation.value = false
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text("Cancel")
                        }

                        Button(
                            onClick = {
                                onYesClick()
//                                showDeleteConfirmation.value = false
                            }
                        ) {
                            Text("Yes")
                        }
                    }
                }
            }
        }
    }
}
