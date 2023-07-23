package com.twoup.personalfinance.local.note.usecase

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.FolderEntity
import com.twoup.personalfinance.model.note.local.NoteEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseAssociateFolderWithNote(private val dataSource: NoteLocalDataSource) {

    @OptIn(DelicateCoroutinesApi::class)
    fun insertAssociateFolderWithNote(note: NoteEntity, folder: FolderEntity) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                folder.id?.let {
                    note.id?.let { it1 ->
                        dataSource.associateAFolderWithASpecificNote(
                            noteId = it1, folderId = it
                        )
                    }
                }
            }
        }
    }
}