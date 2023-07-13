package com.twoup.personalfinance.features.note.ui.Note.search

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.UseCaseSearchNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter

class SearchViewModel : ScreenModel, KoinComponent {

    private val useCaseSearchNote: UseCaseSearchNote by inject()
    private val _searchResults = useCaseSearchNote.searchResults
    private val _searchResultsFlow = MutableStateFlow(_searchResults)

    private val _selectedNote = MutableStateFlow<NoteEntity?>(null)
    val selectedNote: StateFlow<NoteEntity?> = _selectedNote.asStateFlow()

    val searchResults: MutableStateFlow<StateFlow<List<NoteEntity>>> = _searchResultsFlow

    fun searchComic(query: String) {
        useCaseSearchNote.searchNote(query)
    }
}
