package com.twoup.personalfinance.local.infor.usecase

import com.twoup.personalfinance.local.infor.InformationLocalDataSource
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.information.local.InformationEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseInsertInformation(private val dataSource: InformationLocalDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun insertInformation(information: InformationEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.insertInformation(
                    InformationEntity(information.name, information.email)
                )
            }
        }
    }
}
