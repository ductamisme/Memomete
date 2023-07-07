package com.twoup.personalfinance.local.infor

import com.twoup.personalfinance.model.information.local.InformationEntity
import comtwouppersonalfinancedatabase.InformationDatabase

fun InformationDatabase.toInformation(): InformationEntity {
    return InformationEntity(
        name = name,
        email = email,
    )
}