package com.twoup.personalfinance.model.note.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.note.local.FolderEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import com.twoup.personalfinance.model.note.remote.FolderDto
import com.twoup.personalfinance.model.note.remote.TagDto
import kotlinx.serialization.Serializable

@Serializable
data class TagModel(
    val id: Long?,
    val name: String,
) : Model {
    override fun toLocalDto(): TagEntity {
        return TagEntity(id, name)
    }

    override fun toRemoteDto(): TagDto {
        return TagDto(id, name)
    }
}