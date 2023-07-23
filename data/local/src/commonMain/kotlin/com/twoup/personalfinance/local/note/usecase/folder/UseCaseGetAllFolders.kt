package com.twoup.personalfinance.local.note.usecase.folder

import com.twoup.personalfinance.local.note.NoteLocalDataSource
import com.twoup.personalfinance.model.note.local.FolderEntity
import com.twoup.personalfinance.model.note.local.TagEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseGetAllFolders(private val dataSource: NoteLocalDataSource) {

    val folderState: MutableStateFlow<List<FolderEntity>> = MutableStateFlow(listOf())

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllTag() {
        GlobalScope.launch {
            val folder = withContext(Dispatchers.Default) {
                dataSource.getAllFolders()
            }
            folderState.value = folder
        }
    }
}
