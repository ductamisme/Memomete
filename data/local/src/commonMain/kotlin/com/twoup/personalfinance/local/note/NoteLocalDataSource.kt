package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.model.note.local.NoteEntity

interface NoteLocalDataSource {

    suspend fun insertNote(note: NoteEntity)
    suspend fun getNoteById(id: Long): NoteEntity?
    suspend fun getAllNote(): List<NoteEntity>
    suspend fun deleteNoteById(id: Long)
    suspend fun deleteALlNote()
    suspend fun updateNote(note: NoteEntity)
}