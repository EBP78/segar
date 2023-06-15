package com.capstoneC23PS274.segar.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDate(date: String): String {
    val formater = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
    val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
    return parsedDate.format(formater)
}