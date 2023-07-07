package com.twoup.personalfinance.model.person.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.person.local.PersonEntity
import com.twoup.personalfinance.model.person.remote.PersonDto
import kotlinx.serialization.Serializable

@Serializable
data class PersonModel(
    val name: String,
    val height: String,
    val mass: String,
    val url: String
): Model {
    override fun toLocalDto(): PersonEntity {
        return PersonEntity(name, height, mass, url)
    }

    override fun toRemoteDto(): PersonDto {
        return PersonDto(name, height, mass, url)
    }

}