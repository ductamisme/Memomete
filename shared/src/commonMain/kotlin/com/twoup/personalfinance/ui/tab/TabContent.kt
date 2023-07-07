package com.twoup.personalfinance.ui.tab

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.transitions.SlideTransition

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun Tab.TabContent() {
    val tabTitle = options.title

    LifecycleEffect(
        onStarted = { println("Navigator Start tab $tabTitle") },
        onDisposed = { println("Navigator Dispose tab $tabTitle") },
    )

    Navigator(BasicNavigationScreen(index = 0)) { navigator ->
        SlideTransition(navigator) { screen ->
            Column {
                screen.Content()
                println("Navigator Last Event: ${navigator.lastEvent}")
            }
        }
    }
}


@Composable
internal fun RowScope.TabNavigationButton(
    tab: Tab
) {
    val tabNavigator = LocalTabNavigator.current

    Button(
        enabled = tabNavigator.current.key != tab.key,
        onClick = { tabNavigator.current = tab },
        modifier = Modifier.weight(1f)
    ) {
        Text(text = tab.options.title)
    }
}
