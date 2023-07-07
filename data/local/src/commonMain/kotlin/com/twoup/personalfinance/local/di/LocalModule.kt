package com.twoup.personalfinance.local.di

import com.twoup.personalfinance.local.infor.InformationLocalDataSource
import com.twoup.personalfinance.local.infor.InformationLocalDataSourceImpl
import com.twoup.personalfinance.local.infor.usecase.UseCaseDeleteAllInformation
import com.twoup.personalfinance.local.infor.usecase.UseCaseGetAllInformation
import com.twoup.personalfinance.local.infor.usecase.UseCaseInsertInformation
import com.twoup.personalfinance.local.infor.usecase.UseCaseUpdateInformation
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.local.note.NoteLocalDataSourceImpl
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseDeleteNoteById
import com.twoup.personalfinance.local.note.usecase.UseCaseGetAllNote
import com.twoup.personalfinance.local.note.usecase.UseCaseGetNoteById
import com.twoup.personalfinance.local.note.usecase.UseCaseInsertNote
import com.twoup.personalfinance.local.note.usecase.UseCaseSearchNote
import com.twoup.personalfinance.local.note.usecase.UseCaseUpdateNote
import com.twoup.personalfinance.local.person.PersonLocalDataSource
import com.twoup.personalfinance.local.person.PersonLocalDataSourceImpl
import org.koin.dsl.module

fun localModule() = module {
    single<PersonLocalDataSource> { PersonLocalDataSourceImpl(get()) }
    single<NoteLocalDataSource> { NoteLocalDataSourceImpl(get()) }
    single<InformationLocalDataSource> {InformationLocalDataSourceImpl(get())}
    //note
    single { UseCaseGetAllNote(get()) }
    single { UseCaseUpdateNote(get()) }
    single { UseCaseSearchNote(get()) }
    single { UseCaseGetNoteById(get()) }
    single { UseCaseInsertNote(get()) }
    single { UseCaseDeleteAllNote(get()) }
    single { UseCaseDeleteNoteById(get()) }
    //information
    single { UseCaseGetAllInformation(get()) }
    single { UseCaseUpdateInformation(get()) }
    single { UseCaseInsertInformation(get()) }
    single { UseCaseDeleteAllInformation(get()) }
}