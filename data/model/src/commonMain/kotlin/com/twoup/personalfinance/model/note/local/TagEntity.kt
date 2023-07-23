package com.twoup.personalfinance.model.note.local

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.note.domain.FolderModel
import com.twoup.personalfinance.model.note.domain.TagModel
import kotlinx.serialization.Serializable

@Serializable
data class TagEntity(
    val id: Long?,
    val name: String
) : Dto {
    override fun mapToDomainModel(): TagModel {
        return TagModel(id, name)
    }
}