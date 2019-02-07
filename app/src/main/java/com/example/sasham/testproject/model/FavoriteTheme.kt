package com.example.sasham.testproject.model

import com.example.sasham.testproject.model.db.FavoriteThemeTable


data class FavoriteTheme constructor(
        val themeId: Int? = null,
        var userId: String? = null,
        var userName: String? = null,
        var topicText: String? = null,
        var msgText: String? = null,
        var msgTime: String? = null,
        var topicUpdated: String? = null
) {

    companion object {
        fun copy(it: FavoriteThemeTable): FavoriteTheme {
            return FavoriteTheme(it.themeId,
                    it.userId,
                    it.userName,
                    it.topicText,
                    it.msgText,
                    it.msgTime,
                    it.topicUpdated)
        }

        fun copy(theme: Theme): FavoriteTheme {
            return FavoriteTheme(theme.id!!.toInt(), theme.userId, theme.userName, theme.topicText, theme.msgText, theme.msgTime, theme.topicUpdated)
        }
    }
}