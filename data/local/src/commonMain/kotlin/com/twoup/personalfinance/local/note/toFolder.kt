package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.model.note.local.FolderEntity
import comtwouppersonalfinancedatabase.Folder

fun Folder.toFolder(): FolderEntity {
    return FolderEntity(
        id = id,
        name = name
    )
}