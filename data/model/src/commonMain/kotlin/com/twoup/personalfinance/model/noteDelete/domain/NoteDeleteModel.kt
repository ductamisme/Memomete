package com.twoup.personalfinance.model.noteDelete.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.remote.NoteDto
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import com.twoup.personalfinance.model.noteDelete.remote.NoteDeleteDto
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NoteDeleteModel(
    val id: Long?,
    val title: String,
    val description: String,
    val created: LocalDateTime,
) : Model {
    override fun toLocalDto(): NoteDeleteEntity {
        return NoteDeleteEntity(id, title, description, created)
    }

    override fun toRemoteDto(): NoteDeleteDto {
        return NoteDeleteDto(id, title, description, created)
    }
}
