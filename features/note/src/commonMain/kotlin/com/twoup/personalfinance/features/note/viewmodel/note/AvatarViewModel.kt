package com.twoup.personalfinance.features.note.viewmodel.note

import cafe.adriel.voyager.core.model.ScreenModel
import com.twoup.personalfinance.local.infor.InformationLocalDataSource
import com.twoup.personalfinance.local.infor.usecase.UseCaseDeleteAllInformation
import com.twoup.personalfinance.local.infor.usecase.UseCaseGetAllInformation
import com.twoup.personalfinance.local.infor.usecase.UseCaseInsertInformation
import com.twoup.personalfinance.local.infor.usecase.UseCaseUpdateInformation
import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.local.note.usecase.UseCaseInsertNote
import com.twoup.personalfinance.model.information.local.InformationEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AvatarViewModel : ScreenModel, KoinComponent {
    private val useCaseInsertInformation: UseCaseInsertInformation by inject()
    private val useCaseUpdateInformation: UseCaseUpdateInformation by inject()
    private val useCaseGetAllInformation: UseCaseGetAllInformation by inject()
    private val useCaseDeleteAllInformation: UseCaseDeleteAllInformation by inject()

    fun getAllInformation(){
        useCaseGetAllInformation.getAllNote()
    }

    init {
        getAllInformation()
    }

    fun insertInformation(information: InformationEntity) {
        useCaseInsertInformation.insertInformation(information)
    }

    fun updateInformation(information: InformationEntity){
        useCaseUpdateInformation.updateInformation(information,getAllInformation())
    }

    fun deleteInformation(){
        useCaseDeleteAllInformation.deleteALlInformation(getAllInformation())
    }
    class Factory(private val dataSource: InformationLocalDataSource) {
        fun create() = AvatarViewModel()
    }
}