package com.twoup.personalfinance.local.date

import com.twoup.personalfinance.model.note.local.NoteEntity

class SearchNote {
    fun execute(notes: List<NoteEntity>, query: String): List<NoteEntity> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.description.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.created)
        }
    }
}