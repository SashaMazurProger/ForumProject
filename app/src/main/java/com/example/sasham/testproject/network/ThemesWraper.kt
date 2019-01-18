package com.example.sasham.testproject.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThemesWraper {

    @SerializedName("users")
    @Expose
    var themeAnswers: List<ThemeAnswer>? = null
}
