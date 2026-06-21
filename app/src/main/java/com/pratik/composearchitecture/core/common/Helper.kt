package com.pratik.composearchitecture.core.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toFormattedDate(): String {

    val formatter = SimpleDateFormat(
        "dd MMM yyyy, HH:mm",
        Locale.getDefault()
    )

    return formatter.format(Date(this))
}
