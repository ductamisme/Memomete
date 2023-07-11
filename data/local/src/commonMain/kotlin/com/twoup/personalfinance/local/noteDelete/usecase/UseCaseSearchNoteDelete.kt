package com.twoup.personalfinance.local.noteDelete.usecase

import androidx.compose.runtime.mutableStateListOf
import com.twoup.personalfinance.local.date.SearchNoteDeleteFromOldest
import com.twoup.personalfinance.local.noteDelete.NoteDeleteDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class UseCaseSearchNoteDelete(private val dataSource: NoteDeleteDataSource) {

    private val _searchResults = mutableStateListOf<NoteDeleteEntity>()
    private val _searchResultsFlow = MutableStateFlow(_searchResults.toList())

    val searchResults: StateFlow<List<NoteDeleteEntity>> get() = _searchResultsFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun searchNoteDelete(query: String){
        if (query.isBlank()){
            _searchResults.clear()
            return
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val allNoteDeletes = dataSource.getAllNoteDelete()
                val filterNoteDelete = SearchNoteDeleteFromOldest().execute(allNoteDeletes,query)
                _searchResults.clear()
                _searchResults.addAll(filterNoteDelete)
                _searchResultsFlow.value = _searchResults.toList()
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}