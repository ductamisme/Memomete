package com.twoup.personalfinance.resources

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun App() {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) darkColors() else lightColors()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var text: String by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                value = text,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = MaterialTheme.colors.onBackground
                ),
                onValueChange = { text = it }
            )

            val counter: Int = text.length
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = stringResource(MR.plurals.chars_count, counter, counter),
                color = colorResource(MR.colors.textColor),
                fontFamily = fontFamilyResource(MR.fonts.cormorant.italic)
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = stringResource(MR.strings.description_confirmPin),
                color = colorResource(MR.colors.textColor),
                fontFamily = fontFamilyResource(MR.fonts.cormorant.italic)
            )
        }
    }
}