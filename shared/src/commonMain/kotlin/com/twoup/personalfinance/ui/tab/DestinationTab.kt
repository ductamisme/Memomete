package com.twoup.personalfinance.ui.tab

import cafe.adriel.voyager.navigator.tab.Tab

internal interface DestinationTab : Tab {
    val route: String
}