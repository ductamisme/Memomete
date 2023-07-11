package com.twoup.personalfinance.local.noteDelete

import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity

interface NoteDeleteDataSource {
    suspend fun insertNoteDelete(note: NoteDeleteEntity)
    suspend fun getAllNoteDelete(): List<NoteDeleteEntity>
    suspend fun deleteNoteDeleteById(id: Long)
    suspend fun deleteALlNoteDelete()
    suspend fun updateNoteDelete(note: NoteDeleteEntity)
}
