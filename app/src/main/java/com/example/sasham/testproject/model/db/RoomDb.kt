package com.example.sasham.testproject.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserTable::class, FavoriteThemeTable::class), version = 4)
abstract class RoomDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteThemeDao(): FavoriteThemeDao
}