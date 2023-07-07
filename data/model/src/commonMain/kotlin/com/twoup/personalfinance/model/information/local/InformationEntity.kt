package com.twoup.personalfinance.model.information.local

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.information.domain.InformationModel
import kotlinx.serialization.Serializable

@Serializable
class InformationEntity(
    val name: String,
    val email: String,
) : Dto {
    override fun mapToDomainModel(): InformationModel {
        return InformationModel(name, email)
    }
}