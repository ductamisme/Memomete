package com.twoup.personalfinance.ui.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.twoup.personalfinance.features.note.ui.Note.NoteScreen
import com.twoup.personalfinance.features.note.ui.Note.setting.SettingsScreen

internal object DAppTab: DestinationTab {
    override val route: String
        get() = "wallet_tab"

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Favorite)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "DApp",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
//        PINScreen {
//            println("PIN $it")
//        }
        Navigator(SettingsScreen())

    }
}