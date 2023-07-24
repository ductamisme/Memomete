package com.twoup.personalfinance.model.note.local

import com.twoup.personalfinance.model.Dto
import com.twoup.personalfinance.model.note.domain.NoteModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class NoteEntity(
    val id: Long?,
    val title: String,
    var description: String,
    val created: LocalDateTime,
    var deleteCreated: LocalDateTime,
    var favourite: Long?,
    var trash: Long?,
    var tag: String,
    var folder: String,
) : Dto {
    override fun mapToDomainModel(): NoteModel {
        return NoteModel(id, title, description, created,deleteCreated, favourite, trash, tag, folder)
    }
}
