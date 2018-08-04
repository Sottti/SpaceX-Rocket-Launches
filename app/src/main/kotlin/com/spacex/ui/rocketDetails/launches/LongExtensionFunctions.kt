package com.spacex.ui.rocketDetails.launches

import android.text.format.DateUtils
import android.text.format.DateUtils.*
import java.util.*

fun Long.getYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.get(Calendar.YEAR)
}

fun Long.isCurrentYear(): Boolean {
    return Calendar.getInstance().get(Calendar.YEAR) == this.getYear()
}

fun Long.getEitherDayAndMonthWithOptionallyWeekdayAndYearOrRelativeTimeSpan(): String {
    val isCurrentYear = isCurrentYear()
    var flags = (FORMAT_SHOW_DATE
            or (if (isCurrentYear) FORMAT_NO_YEAR else FORMAT_SHOW_YEAR)
            or FORMAT_ABBREV_MONTH)

    if (isCurrentYear) {
        flags = flags or FORMAT_SHOW_WEEKDAY
    }

    return DateUtils.getRelativeTimeSpanString(
            this, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS, flags)
            .toString()
}
