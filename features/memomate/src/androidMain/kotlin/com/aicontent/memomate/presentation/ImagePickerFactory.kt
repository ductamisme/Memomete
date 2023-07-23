package com.aicontent.memomate.presentation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aicontent.memomate.core.presentation.ImagePicker

actual class ImagePickerFactory {

    @Composable
    actual fun createPicker(): ImagePicker {
        val activity = LocalContext.current as ComponentActivity
        return remember(activity) {
            com.aicontent.memomate.core.presentation.ImagePicker(activity)
        }
    }
}