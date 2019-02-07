package com.example.sasham.testproject.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.model.FavoriteTheme

@Entity(tableName = Constants.FAVORITE_THEME_TABLE)
data class FavoriteThemeTable constructor(
        @PrimaryKey(autoGenerate = true)
        val themeId: Int? = null,
        var userId: String? = null,
        var userName: String? = null,
        var topicText: String? = null,
        var msgText: String? = null,
        var msgTime: String? = null,
        var topicUpdated: String? = null
) {

    companion object {
        fun copy(it: FavoriteTheme): FavoriteThemeTable {
            return FavoriteThemeTable(it.themeId,
                    it.userId,
                    it.userName,
                    it.topicText,
                    it.msgText,
                    it.msgTime,
                    it.topicUpdated)
        }
    }
}