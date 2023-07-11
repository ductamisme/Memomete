package com.twoup.personalfinance.local.noteDelete.usecase

import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseGetAllNoteDelete(private val dataSource: NoteDeleteDataSource) {

    val noteState : MutableStateFlow<List<NoteDeleteEntity>> = MutableStateFlow(listOf())

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNoteDelete(){
        GlobalScope.launch{
            val note = withContext(Dispatchers.Main){
                dataSource.getAllNoteDelete()
            }
            noteState.value = note
        }
    }
}