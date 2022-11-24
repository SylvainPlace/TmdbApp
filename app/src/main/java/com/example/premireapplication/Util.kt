package com.example.premireapplication

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

fun stringToDate(date: String): String {
    val dt = LocalDate.parse(date)
    return dt.dayOfMonth.toString() + " " + dt.month.getDisplayName(
        TextStyle.SHORT,
        Locale.getDefault()
    ) + " " + dt.year.toString()

}