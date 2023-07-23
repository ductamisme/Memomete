package com.twoup.personalfinance.model.note.remote

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.note.domain.FolderModel
import com.twoup.personalfinance.model.note.domain.NoteModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class FolderDto(
    val id: Long?,
    val name: String
) : Dto {
    override fun mapToDomainModel(): FolderModel {
        return FolderModel(id, name)
    }
}