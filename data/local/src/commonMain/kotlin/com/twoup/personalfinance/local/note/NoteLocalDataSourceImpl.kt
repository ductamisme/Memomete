package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.local.NoteDatabaseWrapper
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.NoteEntity

class NoteLocalDataSourceImpl(noteDatabaseWrapper: NoteDatabaseWrapper) : NoteLocalDataSource {
    private val database = noteDatabaseWrapper.instance
    private val dbQuery = database.noteDatabaseQueries

    override suspend fun insertNote(note: NoteEntity) {
        dbQuery.insertNote(
//            id =note.id,
            title = note.title,
            description = note.description,
            created = DateTimeUtil.toEpochMillis(note.created),
        )
    }

    override suspend fun getNoteById(id: Long): NoteEntity? {
        return dbQuery.getNoteById(id).executeAsOneOrNull()?.toNote()
    }

    override suspend fun getAllNote(): List<NoteEntity> {
        dbQuery.getAllNote()
        return dbQuery.getAllNote().executeAsList().map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        dbQuery.deleteNoteById(id)
    }

    override suspend fun deleteALlNote() {
        dbQuery.deleteAllNote()
    }

    override suspend fun deleteNoteDeletedById(id: Long) {
        dbQuery.deleteNoteDeleteById(id)
    }

    override suspend fun deleteAllNoteDeleted() {
        dbQuery.deleteAllNoteDeleted()
    }

    override suspend fun updateNote(note: NoteEntity) {
        dbQuery.transaction {
            note.id?.let {
                dbQuery.updateNote(
                    title = note.title,
                    description = note.description,
                    created = DateTimeUtil.toEpochMillis(note.created),
                    id = it,
                    favorite = note.favourite,
                    trash = note.trash
                )
            }
        }
    }
}