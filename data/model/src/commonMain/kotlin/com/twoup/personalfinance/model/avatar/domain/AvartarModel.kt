package com.twoup.personalfinance.model.avatar.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.avatar.local.AvatarEntity
import com.twoup.personalfinance.model.avatar.remote.AvatarDto
import com.twoup.personalfinance.model.information.local.InformationEntity
import com.twoup.personalfinance.model.information.remote.InformationDto
import kotlinx.serialization.Serializable

@Serializable
class AvartarModel(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photoBytes: ByteArray?
) : Model {
    override fun toLocalDto(): AvatarEntity {
        return AvatarEntity(id, firstName, lastName, email, phoneNumber, photoBytes)
    }

    override fun toRemoteDto(): AvatarDto {
        return AvatarDto(id, firstName, lastName, email, phoneNumber, photoBytes)
    }
}