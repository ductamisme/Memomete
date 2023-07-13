package com.twoup.personalfinance.features.note.ui.Note.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

@Composable
fun AvatarScreen(
    username: String,
    avatarImage: ImageBitmap,
    onChangeAvatarClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Avatar Screen", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            bitmap = avatarImage,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = username, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onChangeAvatarClick) {
            Text(text = "Change Avatar")
        }
    }
}



class EditAvatarScreen() : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Edit Screen") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
//                        Image(
//                            bitmap = avatarImage,
//                            contentDescription = "Avatar Image",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.fillMaxSize()
//                        )
//                        customizationOptions.forEach { option ->
//                            Box(
//                                modifier = Modifier
//                                    .size(50.dp)
//                                    .offset(option.offsetX, option.offsetY)
//                            ) {
////                            Image(
////                                bitmap = option.image,
////                                contentDescription = option.description,
////                                modifier = Modifier.fillMaxSize()
////                            )
//                            }
//                        }
                    }
//                    customizationOptions.forEach { option ->
//                        Text(
//                            text = option.title,
//                            style = MaterialTheme.typography.subtitle1,
//                            modifier = Modifier.padding(vertical = 16.dp)
//                        )
//                        LazyRow(
//                            contentPadding = PaddingValues(horizontal = 16.dp),
//                            horizontalArrangement = Arrangement.spacedBy(8.dp)
//                        ) {
//                            items(option.items) { item ->
//                                CustomizationOptionItem(
//                                    item = item,
//                                    isSelected = item == option.selectedItem,
//                                    onItemSelected = { option.selectedItem = item }
//                                )
//                            }
//                        }
//                    }
                }
            },
            bottomBar = {
                BottomAppBar(
                    cutoutShape = CircleShape,
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = { }) {
                                Text(text = "Reset")
                            }
                            Button(onClick = { }) {
                                Text(text = "Save")
                            }
                        }
                    }
                )
            }
        )
    }
}

@Composable
fun CustomizationOptionItem(
    item: CustomizationItem,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clickable(onClick = onItemSelected),
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = item.image,
            contentDescription = item.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isSelected)
                        MaterialTheme.colors.secondary.copy(alpha = 0.4f)
                    else
                        MaterialTheme.colors.surface
                )
        )
    }
}

data class CustomizationOption(
    val title: String,
    val items: List<CustomizationItem>,
    var selectedItem: CustomizationItem,
    val offsetX: Dp,
    val offsetY: Dp
)

data class CustomizationItem(
    val image: ImageBitmap,
    val description: String
)