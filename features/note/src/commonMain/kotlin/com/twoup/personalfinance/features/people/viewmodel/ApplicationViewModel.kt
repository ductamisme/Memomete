//package com.twoup.personalfinance.features.people.viewmodel
//
//import com.twoup.personalfinance.features.people.viewmodel.settings.SettingsViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import org.brightify.hyperdrive.multiplatformx.BaseViewModel
//
//class ApplicationViewModel(settingsFactory: SettingsViewModel.Factory,) : BaseViewModel(){
//    val settings by managed(settingsFactory.create())
//
//    val tabs = listOf(Tab.Schedule, Tab.MyAgenda, Tab.Sponsors, Tab.Settings)
//    var selectedTab: Tab by published(Tab.Schedule)
//    val observeSelectedTab by observe(::selectedTab)
//
//    val showSplashScreen = MutableStateFlow(true)
//
//    init {
//        lifecycle.whileAttached {
////            notificationSchedulingService.runScheduling()
//        }
//
//        lifecycle.whileAttached {
////            syncService.runSynchronization()
//        }
//    }
//
//    enum class Tab {
//        Schedule, MyAgenda, Sponsors, Settings;
//    }
//}