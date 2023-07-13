package com.aicontent.extension

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform