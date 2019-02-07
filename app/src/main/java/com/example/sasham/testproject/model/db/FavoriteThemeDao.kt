package com.example.sasham.testproject.model.db

import androidx.room.*
import com.example.sasham.testproject.Constants

@Dao
interface FavoriteThemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTheme(theme: FavoriteThemeTable)

    @Query("SELECT * FROM ${Constants.FAVORITE_THEME_TABLE}")
    fun themes(): List<FavoriteThemeTable>

    @Delete
    fun delete(theme: FavoriteThemeTable)

    @Update
    fun update(theme: FavoriteThemeTable)


}