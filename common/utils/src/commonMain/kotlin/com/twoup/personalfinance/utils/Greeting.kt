package com.twoup.personalfinance.utils

class Greeting {
    private val platform: Platform = com.twoup.personalfinance.utils.getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}