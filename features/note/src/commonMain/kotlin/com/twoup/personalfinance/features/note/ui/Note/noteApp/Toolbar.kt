package com.twoup.personalfinance.features.note.ui.Note.noteApp// commonMain/src/com/yourpackage/YourToolbar.kt
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Toolbar(
    title: String,
    fontSize: Float,
    textColor: Color,
    fontStyle: FontStyle
) {
    var currentFontSize by remember { mutableStateOf(fontSize) }
    var currentTextColor by remember { mutableStateOf(textColor) }
    var currentFontStyle by remember { mutableStateOf(fontStyle) }

    Surface(
        color = MaterialTheme.colors.primary,
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = currentTextColor,
                style = TextStyle(
                    fontSize = currentFontSize.sp,
                    fontWeight = if (currentFontStyle == FontStyle.Italic) FontWeight.Normal else FontWeight.Bold,
                    fontStyle = currentFontStyle
                ),
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Add UI elements to control font size, text color, and font style
            FontSizeSelector(
                currentFontSize = currentFontSize,
                onFontSizeChange = { fontSize ->
                    currentFontSize = fontSize
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextColorSelector(
                currentTextColor = currentTextColor,
                onTextColorChange = { textColor ->
                    currentTextColor = textColor
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            FontStyleSelector(
                currentFontStyle = currentFontStyle,
                onFontStyleChange = { fontStyle ->
                    currentFontStyle = fontStyle
                }
            )
        }
    }
}

@Composable
fun FontSizeSelector(
    currentFontSize: Float,
    onFontSizeChange: (Float) -> Unit
) {
    var fontSize by remember { mutableStateOf(currentFontSize) }

    // Adjust the step size and range as per your application's needs
    val step = 2f
    val minFontSize = 12f
    val maxFontSize = 40f

    Slider(
        value = fontSize,
        onValueChange = { value ->
            fontSize = value
            onFontSizeChange(value)
        },
        valueRange = minFontSize..maxFontSize,
        steps = ((maxFontSize - minFontSize) / step).toInt()
    )
}

@Composable
fun TextColorSelector(
    currentTextColor: Color,
    onTextColorChange: (Color) -> Unit
) {
    var textColor by remember { mutableStateOf(currentTextColor) }

//    ColorPicker(
//        color = textColor,
//        onColorChange = { color ->
//            textColor = color
//            onTextColorChange(color)
//        }
//    )
}

@Composable
fun FontStyleSelector(
    currentFontStyle: FontStyle,
    onFontStyleChange: (FontStyle) -> Unit
) {
    val fontStyles = listOf(FontStyle.Normal, FontStyle.Italic)

    // You can use any other UI elements to let users choose the font style (e.g., dropdown, radio buttons, etc.)
    Row {
        fontStyles.forEach { fontStyle ->
            val isSelected = fontStyle == currentFontStyle
            Text(
                text = fontStyle.toString(),
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.clickable {
                    onFontStyleChange(fontStyle)
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
