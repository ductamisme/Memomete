package com.twoup.personalfinance.pin.domain

import com.twoup.personalfinance.pin.data.PIN
import com.twoup.personalfinance.pin.data.PINStorage

// Set up PIN use case
class SetupPIN(private val pinStorage: PINStorage) {

    fun execute(newPin: String, confirmPin: String): Boolean {
        if (newPin != confirmPin) {
            return false
        }

        val pin = PIN(newPin)
        pinStorage.storePIN(pin)
        return true
    }
}