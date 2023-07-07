package com.twoup.personalfinance.local.note.usecase

import androidx.compose.runtime.mutableStateListOf
import com.twoup.personalfinance.local.date.SearchNote
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UseCaseSearchNote(private val dataSource: NoteLocalDataSource) {

    private val _searchResults = mutableStateListOf<NoteEntity>()
    private val _searchResultsFlow = MutableStateFlow(_searchResults.toList())
    val searchResults: StateFlow<List<NoteEntity>> get() = _searchResultsFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun searchComic(query: String) {
        if (query.isBlank()) {
            _searchResults.clear()
            return
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val allNotes = dataSource.getAllNote()
                val filteredNotes = SearchNote().execute(allNotes, query)
                _searchResults.clear()
                _searchResults.addAll(filteredNotes)
                _searchResultsFlow.value = _searchResults.toList()
            } catch (e: Exception) {
                // Handle the exception accordingly (e.g., log, display error message)
                e.printStackTrace()
            }
        }
    }
}
