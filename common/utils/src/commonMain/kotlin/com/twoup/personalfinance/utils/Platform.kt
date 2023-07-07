package com.twoup.personalfinance.utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform