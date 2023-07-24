package com.twoup.personalfinance.model.note.domain

import com.twoup.personalfinance.model.Model
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.remote.NoteDto
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NoteModel(
    val id: Long?,
    val title: String,
    var description: String,
    val created: LocalDateTime,
    var deleteCreated: LocalDateTime,
    var favourite: Long?,
    var trash: Long?,
    val tag: String,
    var folder: String,
) : Model {
    override fun toLocalDto(): NoteEntity {
        return NoteEntity(id, title, description, created,deleteCreated, favourite, trash,tag, folder)
    }

    override fun toRemoteDto(): NoteDto {
        return NoteDto(id, title, description, created,deleteCreated, favourite, trash, tag, folder)
    }
}