package com.example.sasham.testproject.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserTable::class), version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}