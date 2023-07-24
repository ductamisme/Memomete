package com.twoup.personalfinance.local.date

import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.noteDelete.local.NoteDeleteEntity

class SearchNoteFromOldest {
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

class SearchNoteFromNewest {
    fun execute(notes: List<NoteEntity>, query: String): List<NoteEntity> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.description.trim().lowercase().contains(query.lowercase())
        }
    }
}


class FilterTagNotes {
    fun execute(notes: List<NoteEntity>, tag: String): List<NoteEntity> {
        if (tag.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.contains(tag, ignoreCase = true)
        }
    }
}


class SearchNoteDeleteFromOldest {
    fun execute(notes: List<NoteDeleteEntity>, query: String): List<NoteDeleteEntity> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.description.trim().lowercase().contains(query.lowercase())
        }
    }
}

class NoteSorter {
    enum class SortOrder {
        NEWEST, OLDEST
    }

    companion object {
        fun sortByCreated(notes: List<NoteEntity>, sortOrder: SortOrder): List<NoteEntity> {
            return when (sortOrder) {
                SortOrder.NEWEST -> notes.sortedByDescending {
                    DateTimeUtil.toEpochMillis(it.created)
                }
                SortOrder.OLDEST -> notes.sortedBy {
                    DateTimeUtil.toEpochMillis(it.created)
                }
            }
        }
    }
}
