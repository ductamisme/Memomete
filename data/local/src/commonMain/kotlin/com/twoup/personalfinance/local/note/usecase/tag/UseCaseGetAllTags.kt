//package com.twoup.personalfinance.local.note.usecase.tag
//
//import com.twoup.personalfinance.local.note.NoteLocalDataSource
//import com.twoup.personalfinance.model.note.local.NoteEntity
//import com.twoup.personalfinance.model.note.local.TagEntity
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class UseCaseGetAllTags(private val dataSource: NoteLocalDataSource) {
//
//    val tagState: MutableStateFlow<List<TagEntity>> = MutableStateFlow(listOf())
//
//    @OptIn(DelicateCoroutinesApi::class)
//    fun getAllTag() {
//        GlobalScope.launch {
//            val note = withContext(Dispatchers.Default) {
//                dataSource.getAllTags()
//            }
//            tagState.value = note
//        }
//    }
//}