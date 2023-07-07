package com.twoup.personalfinance.model.person.remote

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.person.domain.PersonModel
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(
    val name: String?,
    val height: String?,
    val mass: String?,
    val url: String?
): Dto {
    override fun mapToDomainModel(): PersonModel {
        return PersonModel(name ?: "", height ?: "", mass ?: "", url ?: "")
    }

}