//package com.twoup.personalfinance.local.note.usecase.folder
//
//import com.twoup.personalfinance.local.note.NoteLocalDataSource
//import com.twoup.personalfinance.model.note.local.FolderEntity
//import com.twoup.personalfinance.model.note.local.TagEntity
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class UseCaseInsertNewFolder(private val dataSource: NoteLocalDataSource) {
//
//    @OptIn(DelicateCoroutinesApi::class)
//    fun insertFolder(folder: FolderEntity) {
//        GlobalScope.launch {
//            withContext(Dispatchers.Main) {
//                dataSource.insertANewFolder(folder)
//            }
//        }
//    }
//}