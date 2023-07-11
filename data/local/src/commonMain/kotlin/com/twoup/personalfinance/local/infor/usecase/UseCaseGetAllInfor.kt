package com.twoup.personalfinance.local.infor.usecase

import com.twoup.personalfinance.local.infor.InformationLocalDataSource
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.information.local.InformationEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseGetAllInformation(private val dataSource: InformationLocalDataSource) {
    val informationState: MutableStateFlow<List<InformationEntity>> = MutableStateFlow(listOf())

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNote() {
        GlobalScope.launch {
            val information = withContext(Dispatchers.Main) {
                dataSource.getAllInformation()
            }
            informationState.value = information
        }
    }
}
