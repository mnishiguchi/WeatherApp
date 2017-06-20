package com.mnishiguchi.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * Converts a Long object into a humanized date string.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}