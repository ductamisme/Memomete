package com.twoup.personalfinance.remote.person

import com.twoup.personalfinance.model.person.remote.PersonDto

class PersonRemoteDataSource(private val personApi: PersonApi) {

    suspend fun getPersonByIdResponse(peopleId: Int): PersonDto {
        return personApi.getPersonByIdResponse(peopleId)
    }

}