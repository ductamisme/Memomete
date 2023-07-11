package com.twoup.personalfinance.local.noteDelete.usecase

import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseUpdateNoteDelete(private val dataSource: NoteDeleteDataSource) {
    @OptIn(DelicateCoroutinesApi::class)
    fun updateNoteDelete(note: NoteDeleteEntity, loadNote: Unit) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.updateNoteDelete(
                    note
                )
            }
        }
        loadNote
    }
}