package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseInsertNote(private val dataSource: NoteLocalDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun insertNote(note: NoteEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.insertNote(note)
            }
        }
    }
}