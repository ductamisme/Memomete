package com.twoup.personalfinance.features.note.ui.Note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardAction: KeyboardAction = KeyboardAction.Default,
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state ->
                },
            keyboardOptions = keyboardOptions,
//            keyboardAction = KeyboardActions.Default.onSend
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle.merge(
                    TextStyle(color = Color.DarkGray)
                ),
                modifier = Modifier.padding(start = 4.dp) // Add some padding to the hint text
            )
        }
    }
}
@Composable
fun TransparentHintTextFieldDialog(
    text: String,
//    hint: String,
    isHintVisible: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardAction: KeyboardAction = KeyboardAction.Default,
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state ->
                },
            keyboardOptions = keyboardOptions,
//            keyboardAction = KeyboardActions.Default.onSend
        )
//        if (isHintVisible) {
//            Text(
//                text = hint,
//                style = textStyle.merge(
//                    TextStyle(color = Color.DarkGray)
//                ),
//                modifier = Modifier.padding(start = 4.dp) // Add some padding to the hint text
//            )
//        }
    }
}
