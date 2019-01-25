package com.example.sasham.testproject.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThemesWraper {

    @SerializedName("objects")
    @Expose
    var themeAnswers: List<ThemeAnswer>? = null
}
