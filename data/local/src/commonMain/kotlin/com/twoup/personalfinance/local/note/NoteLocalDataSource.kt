package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.model.note.local.FolderEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity

interface NoteLocalDataSource {
    // NoteEntity related methods
    suspend fun insertNote(note: NoteEntity)
    suspend fun getNoteById(id: Long): NoteEntity?
    suspend fun getAllNote(): List<NoteEntity>
    suspend fun deleteNoteById(id: Long)
    suspend fun deleteAllNote()
    suspend fun deleteNoteDeletedById(id: Long)
    suspend fun deleteAllNoteDeleted()
    suspend fun updateNote(note: NoteEntity)

    // TagEntity related methods
    suspend fun getAllTags(): List<TagEntity>
    suspend fun getTagsAssociatedWithASpecificNote(noteId: Long): List<TagEntity>
    suspend fun insertANewTag(tag: TagEntity)

    // FolderEntity related methods
    suspend fun getAllFolders(): List<FolderEntity>
    suspend fun getFoldersAssociatedWithASpecificNote(noteId: Long): List<FolderEntity>
    suspend fun insertANewFolder(folder: FolderEntity)

    // NoteTagEntity and NoteFolderEntity related methods
    suspend fun associateATagWithASpecificNote(noteId: Long, tagId: Long)
    suspend fun associateAFolderWithASpecificNote(noteId: Long, folderId: Long)
    suspend fun removeTagAssociationFromANote(noteId: Long, tagId: Long)
    suspend fun removeFolderAssociationFromANote(noteId: Long, folderId: Long)
}
