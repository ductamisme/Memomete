package com.twoup.personalfinance.local.note

import com.twoup.personalfinance.model.note.local.TagEntity
import comtwouppersonalfinancedatabase.Tag

fun Tag.toTag(): TagEntity {
    return TagEntity(
        id = id,
        name = name
    )
}