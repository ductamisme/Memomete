package com.twoup.personalfinance.model.avatar.local

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.avatar.domain.AvartarModel
import com.twoup.personalfinance.model.information.domain.InformationModel
import kotlinx.serialization.Serializable

@Serializable
class AvatarEntity(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photoBytes: ByteArray?
) : Dto {
    override fun mapToDomainModel(): AvartarModel {
        return AvartarModel(id, firstName, lastName, email, phoneNumber, photoBytes)

    }
}