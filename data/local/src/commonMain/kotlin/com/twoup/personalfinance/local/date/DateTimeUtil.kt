package com.twoup.personalfinance.local.date

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {
    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun formatNoteDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if (dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year
        val hour = if (dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
        val minute = if (dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }

    fun countDownDays(deleteDateTime: LocalDateTime): Int {
        val nowTime = now().hour
        val deleteDateTimes = deleteDateTime.hour

        // Check if the deleteDateTime is in the past
        if (deleteDateTime >= now()) {
            return 0 // Countdown is already complete
        }

        val remainingHours = nowTime - deleteDateTimes
        val daysRemaining = (remainingHours / 24) + if (remainingHours % 24 > 0) 1 else 0

        return 30 - daysRemaining.coerceIn(0, 30)
    }

    fun formatNoteDeleteDate(dateTime: LocalDateTime): String {
        val day = dateTime.dayOfMonth
        return buildString {
            append(day)
        }
    }
    fun isNoteOld(createdDateTime: LocalDateTime): Boolean {
        val daysDifference = createdDateTime.time.hour
        return daysDifference >= 30 * 24
    }
}
