package com.twoup.personalfinance.local.date

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

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
        // Get the current time
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        // Calculate the time difference between deleteDateTime and now
        val diffInDays = calculateDaysDifference(deleteDateTime, now)

        val remainingDays = 30 - diffInDays

        return remainingDays.coerceIn(1, 30)
    }

    fun isNoteOld(deleteDateTime: LocalDateTime): Boolean {
        // Get the current time
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        // Calculate the time difference between deleteDateTime and now
        val diffInDays = calculateDaysDifference(deleteDateTime, now)

        // Check if the time difference is exactly 30 days
        return when (diffInDays) {
            30 -> true
            else -> false
        }
    }

    private fun calculateDaysDifference(dateTime1: LocalDateTime, dateTime2: LocalDateTime): Int {
        val daysInMonth1 = dateTime1.dayOfMonth + daysInYear(dateTime1.year) + if (isLeapYear(dateTime1.year) && dateTime1.month.ordinal > Month.FEBRUARY.ordinal) 1 else 0
        val daysInMonth2 = dateTime2.dayOfMonth + daysInYear(dateTime2.year) + if (isLeapYear(dateTime2.year) && dateTime2.month.ordinal > Month.FEBRUARY.ordinal) 1 else 0

        return (dateTime2.year - dateTime1.year) * 365 + (daysInMonth2 - daysInMonth1) + (dateTime2.month.ordinal - dateTime1.month.ordinal)
    }

    private fun daysInYear(year: Int): Int {
        return if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 1 else 0
    }

    private fun isLeapYear(year: Int): Boolean {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    }
}