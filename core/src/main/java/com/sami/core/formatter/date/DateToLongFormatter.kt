package com.sami.core.formatter.date

import com.sami.core.formatter.Formatter
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateToLongFormatter @Inject constructor() : Formatter {
    override fun format(value: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputDateFormat.parse(value) ?: return ""

        return date.time.toString()
    }
}