package com.twoup.personalfinance.local.noteDelete.usecase

import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseInsertNoteDelete(private val dataSource: NoteDeleteDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun insertNoteDelete(noteDelete: NoteDeleteEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.insertNoteDelete(noteDelete)
            }
        }
    }
}