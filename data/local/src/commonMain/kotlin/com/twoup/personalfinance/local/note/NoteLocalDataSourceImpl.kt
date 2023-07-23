package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.local.NoteDatabaseWrapper
import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.model.note.local.FolderEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity

class NoteLocalDataSourceImpl(noteDatabaseWrapper: NoteDatabaseWrapper) : NoteLocalDataSource {
    private val database = noteDatabaseWrapper.instance
    private val dbQuery = database.noteDatabaseQueries

    override suspend fun insertNote(note: NoteEntity) {
        dbQuery.insertNote(
//            id =note.id,
            title = note.title,
            description = note.description,
            created = DateTimeUtil.toEpochMillis(note.created),
            deleteCreated = DateTimeUtil.toEpochMillis(note.deleteCreated)
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

    override suspend fun deleteAllNote() {
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
                    trash = note.trash,
                    deleteCreated = DateTimeUtil.toEpochMillis(note.deleteCreated)
                )
            }
        }
    }

    // tags and folders methods
    override suspend fun getAllTags(): List<TagEntity> {
        return dbQuery.getAllTags().executeAsList().map { it.toTag() }
    }

    override suspend fun getTagsAssociatedWithASpecificNote(noteId: Long): List<TagEntity> {
        return dbQuery.getTagsAssociatedWithASpecificNote(noteId).executeAsList().map { it.toTag() }
    }

    override suspend fun insertANewTag(tag: TagEntity) {
        dbQuery.insertANewTag(tag.name)
    }

    override suspend fun getAllFolders(): List<FolderEntity> {
        return dbQuery.getAllFolders().executeAsList().map { it.toFolder() }
    }

    override suspend fun getFoldersAssociatedWithASpecificNote(noteId: Long): List<FolderEntity> {
        return dbQuery.getFoldersAssociatedWithASpecificNote(noteId).executeAsList()
            .map { it.toFolder() }
    }

    override suspend fun insertANewFolder(folder: FolderEntity) {
        dbQuery.insertANewFolder(folder.name)
    }

    override suspend fun associateATagWithASpecificNote(noteId: Long, tagId: Long) {
        dbQuery.associateATagWithASpecificNote(noteId, tagId)
    }

    override suspend fun associateAFolderWithASpecificNote(noteId: Long, folderId: Long) {
        dbQuery.associateAFolderWithASpecificNote(noteId, folderId)
    }

    override suspend fun removeTagAssociationFromANote(noteId: Long, tagId: Long) {
        dbQuery.removeTagAssociationFromANote(noteId, tagId)
    }

    override suspend fun removeFolderAssociationFromANote(noteId: Long, folderId: Long) {
        dbQuery.removeFolderAssociationFromANote(noteId, folderId)
    }
}