package com.twoup.personalfinance.local.infor

import com.twoup.personalfinance.local.NoteDatabaseWrapper
import com.twoup.personalfinance.model.information.local.InformationEntity

class InformationLocalDataSourceImpl(noteDatabaseWrapper: NoteDatabaseWrapper) :
    InformationLocalDataSource {
    private val database = noteDatabaseWrapper.instance
    private val dbQuery = database.informationDatabaseQueries

    override suspend fun getAllInformation(): List<InformationEntity> {
        dbQuery.getAllInfor()
        return dbQuery.getAllInfor().executeAsList().map { it.toInformation() }
    }

    override suspend fun updateInformation(information: InformationEntity) {
        dbQuery.updateInfor(name = information.name, email = information.email)
    }

    override suspend fun insertInformation(information: InformationEntity) {
        dbQuery.insertInfor(name = information.name, email = information.email)
    }

    override suspend fun deleteInformation() {
        dbQuery.deleteAllInfor()
    }
}