package com.twoup.personalfinance.android

import android.app.Application
import com.twoup.personalfinance.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level

class PersonalFinanceApplication : Application() {
    lateinit var koin: KoinApplication

    override fun onCreate() {
        super.onCreate()
//        val appModules = listOf()
        koin = initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@PersonalFinanceApplication)
//            modules(appModules)
        }
    }
}