package com.twoup.personalfinance.features.note.di

import cafe.adriel.voyager.core.registry.screenModule
import com.twoup.personalfinance.features.note.ui.Note.AddNoteScreen
import com.twoup.personalfinance.features.note.ui.Note.EditNoteScreen
import com.twoup.personalfinance.features.note.ui.Note.NoteScreen
import com.twoup.personalfinance.features.note.ui.Note.setting.SettingsScreen
import com.twoup.personalfinance.features.note.ui.Note.information.AvatarScreen
import com.twoup.personalfinance.features.note.ui.Note.navigation.SharedScreen
import com.twoup.personalfinance.features.note.ui.Note.search.SearchNoteScreen
import com.twoup.personalfinance.features.note.viewmodel.note.AvatarViewModel
import com.twoup.personalfinance.features.note.viewmodel.note.NoteViewModel
import org.koin.dsl.module

val featureNoteModule = module {
    single { NoteViewModel.Factory(get()) }
    factory { NoteViewModel() }
    single { AvatarViewModel.Factory(get()) }
    factory { AvatarViewModel() }
}

val noteModule = screenModule {
    register<SharedScreen.NoteScreen> {
        NoteScreen()
    }
    register<SharedScreen.AvatarScreen> {
        AvatarScreen()
    }
    register<SharedScreen.AddNoteScreen> {
        AddNoteScreen()
    }
    register<SharedScreen.EditNoteScreen> { provider ->
        EditNoteScreen(note = provider.note)
    }
    register<SharedScreen.SettingScreen> {
        SettingsScreen()
    }
    register<SharedScreen.SearchNote> {
        SearchNoteScreen()
    }
//    register<SharedScreen.NoteAppScreen> {
//        NoteAppScreen()
//    }
}