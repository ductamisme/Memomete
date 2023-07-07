package com.twoup.personalfinance

import MainView
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.twoup.personalfinance.di.initKoin
import com.twoup.personalfinance.viewmodel.ApplicationViewModel
//import MainView

import org.koin.core.Koin
import org.koin.core.context.KoinContextHandler

lateinit var koin: Koin

fun main() = singleWindowApplication(
    title = "2UP Personal Finance",
    state = WindowState(width = 1280.dp, height = 768.dp),
    icon = BitmapPainter(useResource("ic_launcher.png", ::loadImageBitmap)),
) {
    if (KoinContextHandler.getOrNull() == null) {
        initKoin(enableNetworkLogs = true) { }
    }
    val koin = KoinContextHandler.get()
    val viewModel: ApplicationViewModel = koin.get()
    MainView(viewModel)
}