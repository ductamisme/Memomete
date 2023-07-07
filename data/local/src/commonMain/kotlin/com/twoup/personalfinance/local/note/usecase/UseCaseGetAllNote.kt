package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseGetAllNote(private val dataSource: NoteLocalDataSource) {

    val noteState : MutableStateFlow<List<NoteEntity>> = MutableStateFlow(listOf())

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNote(){
        GlobalScope.launch{
            val note = withContext(Dispatchers.Main){
                dataSource.getAllNote()
            }
            noteState.value = note
        }
    }
}