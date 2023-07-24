package com.twoup.personalfinance.local.note.usecase

import androidx.compose.runtime.mutableStateListOf
import com.twoup.personalfinance.local.date.FilterTagNotes
import com.twoup.personalfinance.local.date.SearchNoteFromOldest
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
//    val filterTagNotes : StateFlow<List<NoteEntity>> get() = _filterNoteTags
    val searchResults: StateFlow<List<NoteEntity>> get() = _searchResultsFlow

    private val _filterNoteTags = mutableStateListOf<NoteEntity>()
    private val _filterNoteTagsFlow = MutableStateFlow(_searchResults.toList())
    //    val filterTagNotes : StateFlow<List<NoteEntity>> get() = _filterNoteTags
    val filterNoteTags: StateFlow<List<NoteEntity>> get() = _filterNoteTagsFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun searchNote(query: String) {
        if (query.isBlank()) {
            _searchResults.clear()
            return
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val allNotes = dataSource.getAllNote()
                val notes = allNotes.filter { it.trash == 0L }
                val filteredNotes = SearchNoteFromOldest().execute(notes, query)
                _searchResults.clear()
                _searchResults.addAll(filteredNotes)
                _searchResultsFlow.value = _searchResults.toList()
            } catch (e: Exception) {
                // Handle the exception accordingly (e.g., log, display error message)
                e.printStackTrace()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun filterNote(tag: String) {
//        note.title
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val allNotes = dataSource.getAllNote()
                val notes = allNotes.filter { it.title == tag }
                val filteredNotes = FilterTagNotes().execute(notes, tag)
                _filterNoteTags.addAll(filteredNotes)
            } catch (e: Exception) {
                // Handle the exception accordingly (e.g., log, display error message)
                e.printStackTrace()
            }
        }
    }
}
