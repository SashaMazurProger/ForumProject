package com.example.sasham.testproject.util

import com.example.sasham.testproject.App

fun isOnline(): Boolean {
    return App.instance!!.isOnline()
}
