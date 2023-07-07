package com.twoup.personalfinance.pin.data

import java.io.File

actual class PINStorage : IPINStorage {

    private val pinFile = File("pin.txt")

    override fun storePIN(pin: PIN) {
        pinFile.writeText(pin.toString())
    }

    override fun retrievePIN(): PIN? {
        if (pinFile.exists()) {
            val pinString = pinFile.readText().trim()
            return if (pinString.isNotEmpty()) PIN(pinString) else null
        }
        return null
    }
}