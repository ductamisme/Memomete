package com.aicontent.image_loader

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform