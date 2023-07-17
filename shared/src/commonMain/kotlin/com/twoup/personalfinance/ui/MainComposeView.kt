package com.twoup.personalfinance.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.twoup.personalfinance.features.note.ui.Note.noteApp.yourNote.NoteScreen
import com.twoup.personalfinance.ui.tab.WalletTab
import com.twoup.personalfinance.viewmodel.ApplicationViewModel

@Composable
internal fun MainComposeView(
    viewModel: ApplicationViewModel, modifier: Modifier = Modifier,
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    PersonalFinanceTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Navigator(NoteScreen())
            }
        }
    )
}

//@Composable
//internal fun PersonalFinanceTheme(
//    themeViewModel: ThemeViewModel,
//    content: @Composable () -> Unit,
//) {
//    val colors = if (themeViewModel.darkTheme.value) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
//
//    MaterialTheme(
//        colorScheme = colors,
//        content = content,
//        typography = MaterialTheme.typography
//    )
//}

@Composable
internal fun Content() {
    TabNavigator(WalletTab) {
        Scaffold(
            content = {
                CurrentTab()
            },
            bottomBar = {
                BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primary) {
                    TabNavigationItem(WalletTab)
//                    TabNavigationItem(DAppTab)
//                    TabNavigationItem(SwapTab)
//                    TabNavigationItem(NftTab)
//                    TabNavigationItem(BrowserTab)
                }
            }
        )
    }
}

@Composable
internal fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        label = { Text(text = tab.options.title) },
        selectedContentColor = MaterialTheme.colorScheme.secondaryContainer,
        unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
    )
}