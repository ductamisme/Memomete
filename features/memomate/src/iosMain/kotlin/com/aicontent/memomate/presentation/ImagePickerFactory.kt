package com.aicontent.memomate.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aicontent.memomate.core.presentation.ImagePicker
import platform.UIKit.UIViewController

actual class ImagePickerFactory(
    private val rootController: UIViewController
){

    @Composable
    actual fun createPicker(): ImagePicker {
        return remember {
            com.aicontent.memomate.core.presentation.ImagePicker(rootController)
        }
    }
}