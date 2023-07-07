package com.twoup.personalfinance

import androidx.compose.ui.window.Application
import com.twoup.personalfinance.ui.MainComposeView
import com.twoup.personalfinance.viewmodel.ApplicationViewModel

fun getRootController(viewModel: ApplicationViewModel) = Application("MainComposeView") {
    MainComposeView(viewModel)
}
