package com.twoup.personalfinance.features.note.ui.Note.setting

import PrivacySetting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.viewmodel.note.ThemeViewModel
import io.github.aakira.napier.Napier
import org.koin.core.component.getScopeName

class SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { ThemeViewModel() }
        val darkTheme by viewModel.darkTheme.collectAsState()

        Scaffold(
            topBar = {
                TopBarContent()
            },
            content = { SettingsView(viewModel, darkTheme) }
        )
    }

    @Composable
    fun SettingsView(viewModel: ThemeViewModel, darkTheme: Boolean) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            item {
                SettingOption(
                    label = "Theme",
                    control = {
                        Button(
                            onClick = { viewModel.changeDarkTheme() },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = if (darkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                                style = MaterialTheme.typography.button
                            )
                        }
                    }
                )
            }
            Napier.d(
                tag = "Test Light or Dark",
                message = viewModel.darkTheme.value.toString()
            )

            item {
                SettingOption(
                    label = "Notifications",
                    control = {
                        // Implement notification settings controls (e.g., switch, sound/vibration options)
                    }
                )
            }

            item {
                SettingOption(
                    label = "Font Size",
                    control = {
                        // Implement font size adjustment control (e.g., slider, dropdown)
                    }
                )
            }

            item {
                SettingOption(
                    label = "Backup & Sync",
                    control = {
                        // Implement backup & sync settings controls (e.g., switch, account connection)
                    }
                )
            }

            item {
                SettingOption(
                    label = "Privacy & Security",
                    control = {
                        // Implement privacy & security settings controls (e.g., account permissions, data storage)
                        PrivacySetting()
                    }
                )
            }
        }
    }

    @Composable
    fun SettingOption(
        label: String,
        control: @Composable () -> Unit,
    ) {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Row(modifier = Modifier
                .clickable { expanded = !expanded }) {
                Text(text = label, fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand/Collapse",
                    tint = Color.Gray
                )
            }
            if (expanded) {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        control()
//                        Text(
//                            text = label,
//                            style = MaterialTheme.typography.h6
//                        )
//                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        // Add your privacy and security settings controls here
                    }
                }
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray)
        }
    }
}

@Composable
private fun TopBarContent() {
    val navigator = LocalNavigator.currentOrThrow
    TopAppBar(
        title = {
            Text(
                "Settings",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigator.pop() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = AppBarDefaults.TopAppBarElevation
    )
}

@Composable
fun ThemeToggleButton(darkTheme: MutableState<Boolean>) {
    Button(
        onClick = { darkTheme.value = !darkTheme.value },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = if (darkTheme.value) "Switch to Light Theme" else "Switch to Dark Theme",
            style = MaterialTheme.typography.button
        )
    }
}
