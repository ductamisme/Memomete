package com.twoup.personalfinance.model.noteDelete.remote

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.note.domain.NoteModel
import com.twoup.personalfinance.model.noteDelete.domain.NoteDeleteModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NoteDeleteDto(
    val id: Long?,
    val title: String,
    val description: String,
    val created: LocalDateTime,
) : Dto {
    override fun mapToDomainModel(): NoteDeleteModel {
        return NoteDeleteModel(id, title, description, created)
    }
}
