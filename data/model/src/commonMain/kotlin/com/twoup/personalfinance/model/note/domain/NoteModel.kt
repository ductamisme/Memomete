package com.twoup.personalfinance.model.note.domain

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.remote.NoteDto
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class NoteModel(
    val id: Long?,
    val title: String,
    val description: String,
    val created: LocalDateTime,
) : Model {
    override fun toLocalDto(): NoteEntity {
        return NoteEntity(id, title, description, created)
    }

    override fun toRemoteDto(): NoteDto {
        return NoteDto(id, title, description, created)
    }
}