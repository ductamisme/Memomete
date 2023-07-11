package com.twoup.personalfinance.local.noteDelete.usecase

import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
import comtwouppersonalfinancedatabase.NoteDeleteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseDeleteAllNoteDelete(private val dataSource: NoteDeleteDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteAllNoteDelete(loadNote: Unit) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.deleteALlNoteDelete()
                loadNote
            }
        }
    }

}