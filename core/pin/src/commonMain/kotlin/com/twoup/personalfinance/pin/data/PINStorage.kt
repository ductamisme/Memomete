package com.twoup.personalfinance.pin.data

// Interface to store and retrieve the PIN from platform-specific storage
interface IPINStorage {
    fun storePIN(pin: PIN)
    fun retrievePIN(): PIN?
}

expect class PINStorage: IPINStorage