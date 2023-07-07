package com.twoup.personalfinance.domain.di

import com.twoup.personalfinance.domain.person.usecases.GetPersonByIdUseCase
import com.twoup.personalfinance.domain.person.repository.PersonRepository
import com.twoup.personalfinance.domain.person.repository.PersonRepositoryImpl
import org.koin.dsl.module

fun domainModule() = module {
    single<PersonRepository> { PersonRepositoryImpl(get(), get()) }
    single { GetPersonByIdUseCase(get()) }
}