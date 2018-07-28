package com.example.sasham.testproject.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThemesWraper {

    @SerializedName("objects")
    @Expose
    private List<ThemeAnswer> themeAnswers;

    public List<ThemeAnswer> getThemeAnswers() {
        return themeAnswers;
    }

    public void setThemeAnswers(List<ThemeAnswer> themeAnswers) {
        this.themeAnswers = themeAnswers;
    }
}
