package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseAssociateTagWithNote(private val dataSource: NoteLocalDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun insertAssociateTagWithNote(note: NoteEntity, tag: TagEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                note.id?.let {
                    tag.id?.let { it1 ->
                        dataSource.associateATagWithASpecificNote(
                            noteId = it,
                            tagId = it1
                        )
                    }
                }
            }
        }
    }
}
