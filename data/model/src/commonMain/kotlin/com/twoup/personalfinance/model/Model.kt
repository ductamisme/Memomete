package com.twoup.personalfinance.model

interface Model {
    fun toLocalDto(): Dto
    fun toRemoteDto(): Dto
}