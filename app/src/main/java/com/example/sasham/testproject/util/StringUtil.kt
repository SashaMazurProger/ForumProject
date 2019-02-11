package com.example.sasham.testproject.util

import android.net.Uri
import android.text.Html
import com.example.sasham.testproject.Constants
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object StringUtil {

    fun getAvatarUrl(imageName: String): String {
        return Uri.withAppendedPath(Uri.parse(Constants.WEBEST_BASE_AVATAR_URL), imageName).toString()
    }

    fun isNotNullOrEmpty(s: String?): Boolean {
        return s != null && !s.isEmpty()
    }

    fun getDateFromMillis(millis: String, pattern: String): String? {
        try {
            val millisNumb = java.lang.Long.parseLong(millis)
            val date = Date(TimeUnit.SECONDS.toMillis(millisNumb))
            return SimpleDateFormat(pattern).format(date)
        } catch (e: NumberFormatException) {
            return null
        }
    }

    fun stripHtml(html: String?): String? {
        if (html == null) {
            return null
        }
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(html).toString()
        }
    }
}
