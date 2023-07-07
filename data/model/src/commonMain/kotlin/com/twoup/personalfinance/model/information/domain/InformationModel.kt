package com.twoup.personalfinance.model.information.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.information.local.InformationEntity
import com.twoup.personalfinance.model.information.remote.InformationDto
import kotlinx.serialization.Serializable

@Serializable
class InformationModel(
    val name: String,
    val email: String,
) : Model {
    override fun toLocalDto(): InformationEntity {
        return InformationEntity(name, email)
    }

    override fun toRemoteDto(): InformationDto {
        return InformationDto(name, email)
    }
}