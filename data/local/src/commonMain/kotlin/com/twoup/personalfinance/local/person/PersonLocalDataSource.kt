package com.twoup.personalfinance.local.person

import com.twoup.personalfinance.model.person.local.PersonEntity


interface PersonLocalDataSource {

    fun clearDatabase()

    fun getAllPeople(): List<PersonEntity>

    fun createPeople(people: List<PersonEntity>)
}