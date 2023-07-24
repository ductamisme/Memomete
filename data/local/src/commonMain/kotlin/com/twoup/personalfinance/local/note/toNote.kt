package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.model.note.local.NoteEntity
import comtwouppersonalfinancedatabase.NoteDatabase
import kotlinx.datetime.Instant
import kotlinx.datetime.toLocalDateTime

fun NoteDatabase.toNote(): NoteEntity{
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        created = Instant.fromEpochMilliseconds(created).toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()),
        favourite = favorite,
        trash = trash,
        deleteCreated = Instant.fromEpochMilliseconds(created).toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()),
        tag = tag,
        folder = folder
    )
}



