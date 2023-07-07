package com.twoup.personalfinance.model.note.remote

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.note.domain.NoteModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NoteDto(
    val id: Long?,
    val title: String,
    val description: String,
    val created: LocalDateTime,

    ) : Dto {
    override fun mapToDomainModel(): NoteModel {
        return NoteModel(id, title, description, created)
    }
}