package com.twoup.personalfinance.features.people.di

import com.twoup.personalfinance.features.people.viewmodel.settings.SettingsViewModel
import org.koin.dsl.module

val featurePeopleModule = module {
    single { SettingsViewModel.Factory(get()) }
}