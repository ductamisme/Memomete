//package com.twoup.personalfinance.local.note.usecase.folder
//
//import com.twoup.personalfinance.local.note.NoteLocalDataSource
//import com.twoup.personalfinance.model.note.local.FolderEntity
//import com.twoup.personalfinance.model.note.local.TagEntity
//import comtwouppersonalfinancedatabase.Folder
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class UseCaseFolderAssociateWithNote(private val dataSource: NoteLocalDataSource) {
//
//    @OptIn(DelicateCoroutinesApi::class)
//    fun getFolderAssociateWithNote(folder: FolderEntity) {
//        GlobalScope.launch {
//            withContext(Dispatchers.Main) {
//                folder.id?.let { dataSource.getFoldersAssociatedWithASpecificNote(it) }
//            }
//        }
//    }
//}