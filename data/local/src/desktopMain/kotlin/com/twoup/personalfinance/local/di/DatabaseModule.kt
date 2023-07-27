package com.twoup.personalfinance.local.di

import com.aicontent.database.NoteDatabase
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.twoup.personalfinance.local.NoteDatabaseWrapper
import org.koin.dsl.module

actual fun databaseModule() = module {
    single {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { NoteDatabase.Schema.create(it) }
        NoteDatabaseWrapper(NoteDatabase(driver))
    }
}