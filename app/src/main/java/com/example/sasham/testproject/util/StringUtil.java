package com.example.sasham.testproject.util;

import android.net.Uri;

import com.example.sasham.testproject.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static String getAvatarUrl(String imageName) {
        return Uri.withAppendedPath(Uri.parse(Constants.WEBEST_BASE_AVATAR_URL), imageName).toString();
    }

    public static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static String getDateFromMillis(String millis, String pattern) {
        long millisNumb = Long.parseLong(millis);
        if (millisNumb != 0) {
            Date date = new Date(millisNumb);
            return new SimpleDateFormat(pattern).format(date);
        }
        return null;
    }
}
