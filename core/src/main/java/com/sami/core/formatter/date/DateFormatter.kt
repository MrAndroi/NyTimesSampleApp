package com.sami.core.formatter.date

import androidx.appcompat.app.AppCompatDelegate
import com.sami.core.formatter.Formatter
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class DateFormatter @Inject constructor() : Formatter {
    override fun format(value: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputDateFormat.parse(value)!!

        val currentTime = System.currentTimeMillis()
        val timeDifferenceMillis = currentTime - date.time

        val seconds = timeDifferenceMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = weeks / 4
        val years = months / 12

        val lang = AppCompatDelegate.getApplicationLocales().toLanguageTags().take(2)

        return when {
            years > 0 -> {
                if (lang == "ar") {
                    "$years سنة مضت"
                } else {
                    "$years year${if (years > 1) "s" else ""} ago"
                }
            }

            months > 0 -> {
                if (lang == "ar") {
                    "$months شهر مضى"
                } else {
                    "$months month${if (months > 1) "s" else ""} ago"
                }
            }

            weeks > 0 -> {
                if (lang == "ar") {
                    "$weeks أسبوع مضى"
                } else {
                    "$weeks week${if (weeks > 1) "s" else ""} ago"
                }
            }

            days > 0 -> {
                if (lang == "ar") {
                    "$days يوم مضى"
                } else {
                    "$days day${if (days > 1) "s" else ""} ago"
                }
            }

            else -> {
                if (lang == "ar") {
                    "اليوم"
                } else {
                    "Today"
                }
            }
        }
    }
}