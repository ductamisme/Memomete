package com.twoup.personalfinance.local.noteDelete

import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import comtwouppersonalfinancedatabase.NoteDatabase
import comtwouppersonalfinancedatabase.NoteDeleteDatabase
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteDeleteDatabase.toNoteDelete(): NoteDeleteEntity {
    return NoteDeleteEntity(
        id = id,
        title = title,
        description = description,
        created = Instant.fromEpochMilliseconds(created).toLocalDateTime(TimeZone.currentSystemDefault()),
    )
}