package com.twoup.personalfinance.local.infor

import com.twoup.personalfinance.model.information.local.InformationEntity

interface InformationLocalDataSource {
    suspend fun getAllInformation() : List<InformationEntity>
    suspend fun updateInformation(information : InformationEntity)
    suspend fun insertInformation(information : InformationEntity)
    suspend fun deleteInformation()
}