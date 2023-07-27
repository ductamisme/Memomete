package com.twoup.personalfinance.features.note.ui.Note.noteApp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextPainter
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.NoteViewModel
import com.twoup.personalfinance.features.people.ui.icons.Add
import kotlin.math.roundToInt

@Composable
fun CustomTextButton(
    text: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    borderColor: Color = Color.LightGray,
    pressedBackgroundColor: Color = Color.Black,
    pressedContentColor: Color = Color.White,
    pressedBorderColor: Color = Color.Black,
    fontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    isPressed: Boolean // New parameter to indicate pressed state
) {
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = if (isPressed) pressedBackgroundColor else backgroundColor,
                shape = shape
            )
            .border(
                width = 2.dp,
                color = if (isPressed) pressedBorderColor else borderColor,
                shape = shape
            )
            .clickable {
                onClick(text)
            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text(
            text = "# $text",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = if (isPressed) pressedContentColor else contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}



@Composable
fun CustomItem(
    onClick: () -> Unit, // Pass a lambda function to receive the clicked tag title
//    onDeleteClick:(String)  -> Unit,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    borderColor: Color = Color.LightGray,
    pressedBackgroundColor: Color = Color.Black,
    pressedContentColor: Color = Color.White,
    pressedBorderColor: Color = Color.Black,
) {
    val isPressed = remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = modifier
            .background(
                color = if (isPressed.value) pressedBackgroundColor else backgroundColor,
                shape = shape
            )
            .border(
                width = 2.dp,
                color = if (isPressed.value) pressedBorderColor else borderColor,
                shape = shape
            )
            .clickable {
                onClick() // Pass the clicked tag title to the onClick lambda
                isPressed.value = !isPressed.value
            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = if (isPressed.value) pressedContentColor else contentColor,
        )
    }
}

