package com.twoup.personalfinance.model.note.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.note.local.FolderEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.remote.FolderDto
import com.twoup.personalfinance.model.note.remote.NoteDto
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class FolderModel(
    val id: Long?,
    val name: String,
) : Model {
    override fun toLocalDto(): FolderEntity {
        return FolderEntity(id, name)
    }

    override fun toRemoteDto(): FolderDto {
        return FolderDto(id, name)
    }
}