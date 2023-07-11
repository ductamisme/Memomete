package com.twoup.personalfinance.local.noteDelete

import com.twoup.personalfinance.local.NoteDatabaseWrapper
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity

class NoteDeleteDataSourceImpl(noteDatabaseWrapper: NoteDatabaseWrapper) : NoteDeleteDataSource {
    private val database = noteDatabaseWrapper.instance
    private val dbQuery = database.noteDeleteDatabaseQueries

    override suspend fun insertNoteDelete(note: NoteDeleteEntity) {
        dbQuery.inserNoteDelete(
            description = note.description,
            title = note.title,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getAllNoteDelete(): List<NoteDeleteEntity> {
        dbQuery.getAllNoteDelete()
        return dbQuery.getAllNoteDelete().executeAsList().map { it.toNoteDelete() }
    }

    override suspend fun deleteNoteDeleteById(id: Long) {
        dbQuery.deleteNoteDeleteById(id)
    }

    override suspend fun deleteALlNoteDelete() {
        dbQuery.deleteAllNoteDelete()
    }

    override suspend fun updateNoteDelete(note: NoteDeleteEntity) {
        dbQuery.transaction {
            note.id?.let {
                dbQuery.updateNoteDelete(
                    title = note.title,
                    description = note.description,
                    created = DateTimeUtil.toEpochMillis(note.created),
                    id = it,
                )
            }
        }
    }
}