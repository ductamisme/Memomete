package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.date.NoteSorter
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UseCaseSearchNoteFromOldTONew(private val dataSource: NoteLocalDataSource) {

    private val _searchResults = MutableStateFlow<List<NoteEntity>>(emptyList())
    val searchResults: StateFlow<List<NoteEntity>> = _searchResults.asStateFlow()

    suspend fun noteNewest() {
        try {
            val allNotes = dataSource.getAllNote()
            val filteredNotes = NoteSorter.sortByCreated(
                allNotes,
                NoteSorter.SortOrder.NEWEST
            )
            _searchResults.value = filteredNotes
        } catch (e: Exception) {
            // Handle the exception accordingly (e.g., log, display error message)
            e.printStackTrace()
        }
    }

    suspend fun noteOldest() {
        try {
            val allNotes = dataSource.getAllNote()
            val filteredNotes = NoteSorter.sortByCreated(
                allNotes,
                NoteSorter.SortOrder.OLDEST
            )
            _searchResults.value = filteredNotes
        } catch (e: Exception) {
            // Handle the exception accordingly (e.g., log, display error message)
            e.printStackTrace()
        }
    }
}
