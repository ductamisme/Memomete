package com.twoup.personalfinance.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.twoup.personalfinance.resources.DarkColorPalette
import com.twoup.personalfinance.resources.LightColorPalette
import com.twoup.personalfinance.resources.Typography

@Composable
actual fun PersonalFinanceTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        content = content
    )
}