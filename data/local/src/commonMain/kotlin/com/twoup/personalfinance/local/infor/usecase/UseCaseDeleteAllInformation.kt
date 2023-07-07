package com.twoup.personalfinance.local.infor.usecase

import com.twoup.personalfinance.local.infor.InformationLocalDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseDeleteAllInformation(private val dataSource: InformationLocalDataSource) {
    @OptIn(DelicateCoroutinesApi::class)
    fun deleteALlInformation(loadInformation: Unit) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                dataSource.deleteInformation()
                loadInformation
            }
        }
    }
}