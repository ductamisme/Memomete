package com.twoup.personalfinance.domain.person.usecases

import com.twoup.personalfinance.domain.base.UseCase
import com.twoup.personalfinance.domain.person.repository.PersonRepository
import com.twoup.personalfinance.model.person.domain.PersonModel

class GetPersonByIdUseCase(private val personRepository: PersonRepository) :
    UseCase<PersonModel>() {

    override suspend fun run(params: Map<String, Any?>): PersonModel {
        val forceReload = params["forceReload"] as Boolean
        val personId = params["personId"] as Int
        return personRepository.getPersonById(forceReload, personId)
    }
}