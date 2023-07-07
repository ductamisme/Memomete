package com.twoup.personalfinance.local.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.twoup.personalfinance.database.NoteDatabase
import com.twoup.personalfinance.local.NoteDatabaseWrapper
import org.koin.dsl.module

actual fun databaseModule() = module {
    single {
        val driver = AndroidSqliteDriver(NoteDatabase.Schema, get(), "personalfinance.db")
        NoteDatabaseWrapper(NoteDatabase(driver))
    }
}