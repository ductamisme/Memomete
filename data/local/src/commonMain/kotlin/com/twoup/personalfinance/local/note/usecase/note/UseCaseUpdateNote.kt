package com.twoup.personalfinance.local.note.usecase.note

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseUpdateNote(private val dataSource: NoteLocalDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun updateNote(note: NoteEntity, loadNote: Unit) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.updateNote(
                    NoteEntity(
                        note.id,
                        note.title,
                        note.description,
                        note.created,
                        note.deleteCreated,
                        note.favourite,
                        note.trash,
                        note.tag,
                        note.folder
                    )
                )
            }
            loadNote
        }
    }
}