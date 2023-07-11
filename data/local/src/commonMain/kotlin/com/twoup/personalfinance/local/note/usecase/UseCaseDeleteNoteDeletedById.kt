package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseDeleteNoteDeletedById(private val dataSource: NoteLocalDataSource) {
    @OptIn(DelicateCoroutinesApi::class)
    fun deleteNoteDeletedById(id:Long, loadNote: Unit){
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                dataSource.deleteNoteDeletedById(id)
                loadNote
            }
        }
    }
}