package com.twoup.personalfinance.local.noteDelete.usecase

import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseDeleteNoteDeleteById(private val dataSource: NoteDeleteDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteNoteDeleteById(id: Long, loadNote: Unit) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.deleteNoteDeleteById(id)
                loadNote
            }
        }
    }
}