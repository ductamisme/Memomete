package com.twoup.personalfinance.features.note.ui.Note.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import com.twoup.personalfinance.model.note.local.NoteEntity

sealed class SharedScreen : ScreenProvider{
    object NoteScreen: SharedScreen()
    object AddNoteScreen : SharedScreen()
    object AvatarScreen : SharedScreen()
    object SearchNote : SharedScreen()
    object SettingScreen: SharedScreen()
    object NoteScreenFavorite: SharedScreen()
    object NoteTrashScreen: SharedScreen()
    data class EditNoteTrashScreen(val note : NoteEntity): SharedScreen()
    data class EditNoteScreen(val note : NoteEntity): SharedScreen()

}