package com.twoup.personalfinance.features.note.ui.Note.information

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.twoup.personalfinance.features.note.ui.ImageItem
import com.twoup.personalfinance.features.note.ui.Note.noteApp.viewModel.AvatarUiState
import com.twoup.personalfinance.model.information.local.InformationEntity

enum class AvatarOption(val url: String) {
    GOJO("https://ecdn.game4v.com/g4v-content/uploads/2022/09/25083529/Gojo-2-game4v-1664069728-55.jpg"),
    NARUTO("https://images.wallpapersden.com/image/download/anime-naruto-hd-2023-ai_bW5mbGmUmZqaraWkpJRmbmdlrWZlbWU.jpg"),
    DORAEMON("https://wallpapers.com/images/hd/doraemon-on-the-moon-4k-38b6ro1mslswm5i6.jpg"),
    LUFFY("https://images8.alphacoders.com/788/788705.jpg"),
    GOKU("https://wallpapercave.com/wp/wp9172976.jpg"),
    NATSU("https://prodigits.co.uk/thumbs/wallpapers/p2ls/anime/39/aa95cb2912524236.jpg"),
    MIDORIYA("https://c4.wallpaperflare.com/wallpaper/235/679/618/anime-my-hero-academia-izuku-midoriya-hd-wallpaper-preview.jpg"),
    SAITAMA("https://tophinhanhdep.com/wp-content/uploads/2021/11/Funny-One-Punch-Man-Wallpapers.jpg"),
    EREN("https://images5.alphacoders.com/114/1143212.jpg"),
    TANJIRO("https://c4.wallpaperflare.com/wallpaper/373/676/41/kimetsu-no-yaiba-anime-anime-boys-tanjiro-kamado-kamado-tanjir%C5%8D-hd-wallpaper-preview.jpg"),
    NORAGAMI("https://images3.alphacoders.com/608/608362.jpg"),
    KOROSENSE("https://images2.alphacoders.com/688/688011.jpg"),
    ASTA("https://a-static.besthdwallpaper.com/black-clover-asta-new-demon-form-wallpaper-3840x2160-53335_54.jpg"),
    SHINRA("https://images.alphacoders.com/114/1143428.jpg"),
    CONAN("https://haycafe.vn/wp-content/uploads/2021/11/hinh-anh-conan-dep-nhat-de-thuong-dang-yeu-800x450.jpg")
}

class AvatarScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { AvatarViewModel() }
        val uiState = remember { AvatarUiState() }
        val navigator = LocalNavigator.currentOrThrow
        var enteredName by rememberSaveable { mutableStateOf("") }
        var enteredEmail by rememberSaveable { mutableStateOf("") }
        var selectedAvatar by rememberSaveable { mutableStateOf(AvatarOption.GOJO) }
//        val informationState by viewModel.notes.collectAsState(emptyList())
//        val avatarInformation = informationState.component1()

        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
        )

        LaunchedEffect(navigator) {
            viewModel.getAllInformation()
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Avatar",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onPrimary
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigator.pop()
                                val note = InformationEntity(
                                    uiState.name,
                                    uiState.email
                                )
                                viewModel.insertInformation(note)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    backgroundColor = colors.primary,
                    elevation = AppBarDefaults.TopAppBarElevation
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .animateContentSize(
                            animationSpec = spring(dampingRatio = 0.6f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    ImageItem(
                        url = selectedAvatar.url,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .animateContentSize(
                                animationSpec = spring(dampingRatio = 0.6f)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                AnimatedVisibility(visible = true) {
                    Column {
                        OutlinedTextField(
                            value = uiState.name,
                            onValueChange = {
                            },
                            label = { Text("Name", style = TextStyle(color = colors.onPrimary)) },
                            colors = textFieldColors,
                            modifier = Modifier.width(330.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.email,
                            onValueChange = {
                            },
                            label = { Text("Email", style = TextStyle(color = colors.onPrimary)) },
                            colors = textFieldColors,
                            modifier = Modifier.width(330.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.height(48.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                            text = "Select an Avatar",
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    itemsIndexed(AvatarOption.values().toList().chunked(5)) { rowIndex, row ->
                        LazyRow(content = {
                            items(row) { avatar ->
                                AvatarOption(
                                    avatar = avatar.url,
                                    selected = avatar == selectedAvatar,
                                    onClick = { selectedAvatar = avatar }
                                )
                            }
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun AvatarOption(
    avatar: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clickable(onClick = onClick)
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        ImageItem(
            url = avatar,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .animateContentSize(
                    animationSpec = spring(dampingRatio = 0.6f)
                )
        )
    }
}

private fun saveInformation(uiState: AvatarUiState, viewModel: AvatarViewModel) {
    val note = InformationEntity(
        uiState.name,
        uiState.email
    )
    viewModel.updateInformation(note)
}
