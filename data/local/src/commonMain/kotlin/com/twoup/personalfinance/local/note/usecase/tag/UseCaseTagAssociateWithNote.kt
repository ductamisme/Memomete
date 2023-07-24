//package com.twoup.personalfinance.local.note.usecase.tag
//
//import com.twoup.personalfinance.local.note.NoteLocalDataSource
//import com.twoup.personalfinance.model.note.local.NoteEntity
//import com.twoup.personalfinance.model.note.local.TagEntity
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class UseCaseTagAssociateWithNote(private val dataSource: NoteLocalDataSource) {
//
//    @OptIn(DelicateCoroutinesApi::class)
//    fun getTagAssociateWithNote(note: NoteEntity) {
//        GlobalScope.launch {
//            withContext(Dispatchers.Main) {
//                note.id?.let { dataSource.getTagsAssociatedWithASpecificNote(it) }
//            }
//        }
//    }
//}