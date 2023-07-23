package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.date.DateTimeUtil
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime

class UseCaseDeleteBy30Days(private val dataSource: NoteLocalDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteNoteBy30Days(id:Long, loadNote: Unit, createdDateTime: LocalDateTime){
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                if (DateTimeUtil.isNoteOld(createdDateTime)){
                    dataSource.deleteNoteDeletedById(id)
                }
                loadNote
            }
        }
    }
}