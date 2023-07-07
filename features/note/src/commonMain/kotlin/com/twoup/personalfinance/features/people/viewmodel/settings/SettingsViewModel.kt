package com.twoup.personalfinance.features.people.viewmodel.settings


import com.twoup.personalfinance.domain.person.usecases.GetPersonByIdUseCase
import com.twoup.personalfinance.model.person.domain.PersonModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.brightify.hyperdrive.multiplatformx.BaseViewModel
import org.koin.core.component.KoinComponent

class SettingsViewModel(
    private val getPersonByIdUseCase: GetPersonByIdUseCase
) : BaseViewModel(), KoinComponent {

//    var isFeedbackEnabled by binding(
//        presenter.loadPeople(true, 3),
//        mapping = { it.isFeedbackEnabled },
//        set = { newValue ->
//            // TODO: Remove when `binding` supports suspend closures.
//            instanceLock.runExclusively {
//                settingsGateway.setFeedbackEnabled(newValue)
//            }
//        }
//    )
//    val observeIsFeedbackEnabled by observe(::isFeedbackEnabled)
//
//    var isRemindersEnabled by binding(
//        settingsGateway.settings(),
//        mapping = { it.isRemindersEnabled },
//        set = { newValue ->
//            // TODO: Remove when `binding` supports suspend closures.
//            instanceLock.runExclusively {
//                settingsGateway.setRemindersEnabled(newValue)
//            }
//        }
//    )
//    val observeIsRemindersEnabled by observe(::isRemindersEnabled)

//    var people: People? = null
//    val observePeople by observe(::people)

//    private val getPersonByIdUseCase: GetPersonByIdUseCase by inject()

    private val _getPeople = MutableStateFlow<PersonModel?>(null)
    val getPeople = _getPeople.asStateFlow()

    fun loadPeople(forceReload: Boolean, personId: Int) {
        kotlinx.coroutines.GlobalScope.launch {
            val map = mutableMapOf<String, Any?>()
            map["forceReload"] = forceReload
            map["personId"] = personId
            val result = getPersonByIdUseCase(map)
            _getPeople.value = result
            println("result " + result)
        }
    }

    class Factory(
        private val getPersonByIdUseCase: GetPersonByIdUseCase
    ) {

        fun create() = SettingsViewModel(getPersonByIdUseCase)
    }
}
