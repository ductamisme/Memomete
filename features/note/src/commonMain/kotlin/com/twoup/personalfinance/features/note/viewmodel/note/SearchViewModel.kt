package com.twoup.personalfinance.features.note.viewmodel.note

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.note.usecase.UseCaseSearchNote
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel() : ScreenModel, KoinComponent {

    private val useCaseSearchNote: UseCaseSearchNote by inject()
    private val _searchResults = useCaseSearchNote.searchResults
    private val _searchResultsFlow = MutableStateFlow(_searchResults)

    private val _selectedComic = MutableStateFlow<NoteEntity?>(null)
    val selectedComic: StateFlow<NoteEntity?> = _selectedComic.asStateFlow()

    val searchResults: MutableStateFlow<StateFlow<List<NoteEntity>>> = _searchResultsFlow

    fun searchComic(query: String) {
        useCaseSearchNote.searchComic(query)
    }
}
