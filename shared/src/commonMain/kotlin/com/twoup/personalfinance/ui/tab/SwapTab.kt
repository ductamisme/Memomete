package com.twoup.personalfinance.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.twoup.personalfinance.features.people.ui.news.NewsView

internal object SwapTab: DestinationTab {
    override val route: String
        get() = "wallet_tab"

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Favorite)

            return remember {
                TabOptions(
                    index = 2u,
                    title = "Swap",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        TabContent()
//        NewsView()
    }
}