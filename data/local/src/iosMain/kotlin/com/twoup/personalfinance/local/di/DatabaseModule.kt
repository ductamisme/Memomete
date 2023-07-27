package com.twoup.personalfinance.local.di

import com.aicontent.database.NoteDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.twoup.personalfinance.local.NoteDatabaseWrapper
import org.koin.dsl.module

actual fun databaseModule() = module {
    single {
        val driver = NativeSqliteDriver(NoteDatabase.Schema, "personalfinance.db")
        NoteDatabaseWrapper(NoteDatabase(driver))
    }
}