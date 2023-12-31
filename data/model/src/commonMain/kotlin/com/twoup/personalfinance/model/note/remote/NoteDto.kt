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
    var description: String,
    val created: LocalDateTime,
    var deleteCreated: LocalDateTime,
    var favourite: Long?,
    var trash: Long?,
    val tag: String,
    var folder: String,
) : Dto {
    override fun mapToDomainModel(): NoteModel {
        return NoteModel(id, title, description, created,deleteCreated, favourite, trash,tag, folder)
    }
}