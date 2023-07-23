package com.aicontent.memomate.di

import com.aicontent.memomate.contact.data.SqlDelightContactDataSource
import com.aicontent.memomate.contact.domain.ContactDataSource
import com.aicontent.memomate.data.DatabaseDriverFactory
import com.aicontent.memomate.data.ImageStorage

//actual class AppModule {
//
//    actual val contactDataSource: ContactDataSource by lazy {
//        SqlDelightContactDataSource(
//            db = ContactDatabase(
//                driver = DatabaseDriverFactory().create()
//            ),
//            imageStorage = ImageStorage()
//        )
//    }
//}