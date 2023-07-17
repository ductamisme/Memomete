package com.twoup.personalfinance.ui

import androidx.compose.runtime.Composable


@Composable
expect fun PersonalFinanceTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)