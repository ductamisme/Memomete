package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseGetAllNote(private val dataSource: NoteLocalDataSource) {

    val noteState : MutableStateFlow<List<NoteEntity>> = MutableStateFlow(listOf())
    private var noteStateDeleted = noteState.value.filter { it.trash == 0L}
    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNote(){
        GlobalScope.launch{
            val note = withContext(Dispatchers.Main){
                dataSource.getAllNote()
            }
            noteState.value = note
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNoteNotContainTrash(){
        GlobalScope.launch{
            val note = withContext(Dispatchers.Main){
                dataSource.getAllNote()
            }
            noteStateDeleted = note
        }
    }
}