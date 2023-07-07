package com.twoup.personalfinance.domain.person.repository

import com.twoup.personalfinance.local.person.PersonLocalDataSource
import com.twoup.personalfinance.model.person.domain.PersonModel
import com.twoup.personalfinance.model.person.local.PersonEntity
import com.twoup.personalfinance.remote.person.PersonRemoteDataSource

class PersonRepositoryImpl(private val network: PersonRemoteDataSource, private val local: PersonLocalDataSource):
    PersonRepository {
    override suspend fun getPersonById(forceReload: Boolean, peopleId: Int): PersonModel {
        val cachedPersons = local.getAllPeople()
        return if (cachedPersons.isNotEmpty() && !forceReload) {
            cachedPersons[0].mapToDomainModel()
        } else {
            val personDto = network.getPersonByIdResponse(peopleId)
            val personModel = personDto.mapToDomainModel()

            local.clearDatabase()
            val listPeople = mutableListOf<PersonEntity>()
            listPeople.add(personModel.toLocalDto())
            local.createPeople(listPeople)

            personModel
        }
    }

}