package com.twoup.personalfinance.pin.data

// PIN class to store and validate the PIN
class PIN(private val pin: String) {

    fun validate(inputPin: String): Boolean {
        return inputPin == pin
    }

    override fun toString(): String {
        return pin
    }
}