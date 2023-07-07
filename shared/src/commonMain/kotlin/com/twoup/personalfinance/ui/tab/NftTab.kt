package com.twoup.personalfinance.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.TabOptions

internal object NftTab: DestinationTab {
    override val route: String
        get() = "wallet_tab"

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Favorite)

            return remember {
                TabOptions(
                    index = 3u,
                    title = "NFT",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        TabContent()
    }
}