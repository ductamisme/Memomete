//package com.twoup.personalfinance.local.note.usecase
//
//import com.twoup.personalfinance.local.note.NoteLocalDataSource
//import com.twoup.personalfinance.model.note.local.FolderEntity
//import com.twoup.personalfinance.model.note.local.NoteEntity
//import com.twoup.personalfinance.model.note.local.TagEntity
//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class UseCaseRemoveTagAssociationFromNote(private val dataSource: NoteLocalDataSource) {
//
//    @OptIn(DelicateCoroutinesApi::class)
//    fun removeTagAssociationFromNote(note: NoteEntity, tag: TagEntity) {
//        GlobalScope.launch {
//            withContext(Dispatchers.Main) {
//                note.id?.let {
//                    tag.id?.let { it1 ->
//                        dataSource.removeTagAssociationFromANote(
//                            noteId = it,
//                            tagId = it1
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
