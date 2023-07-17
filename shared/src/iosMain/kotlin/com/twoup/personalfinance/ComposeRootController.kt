package com.twoup.personalfinance

import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Application
import com.twoup.personalfinance.ui.MainComposeView
import com.twoup.personalfinance.viewmodel.ApplicationViewModel
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun getRootController(viewModel: ApplicationViewModel) = Application("MainComposeView")  {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    MainComposeView(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        modifier = Modifier,
        viewModel = viewModel
    )
}
