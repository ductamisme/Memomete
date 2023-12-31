package com.twoup.personalfinance.ui

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.twoup.personalfinance.viewmodel.ApplicationViewModel

@Composable
fun MainView(viewModel: ApplicationViewModel) {
    MainComposeView(viewModel = viewModel, modifier = Modifier.systemBarsPadding())
}
