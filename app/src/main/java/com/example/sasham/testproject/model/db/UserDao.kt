package com.example.sasham.testproject.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sasham.testproject.Constants

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: List<UserTable>)

    @Query("SELECT * FROM ${Constants.USER_TABLE}")
    fun users(): List<UserTable>

    @Query("SELECT count(*) FROM ${Constants.USER_TABLE}")
    fun count(): Int

    @Query("SELECT * FROM ${Constants.USER_TABLE} WHERE userName LIKE :search" +
            " OR about LIKE :search " +
            " OR city LIKE :search " +
            " OR country LIKE :search" +
            " OR country2 LIKE :search" +
            " OR email LIKE :search" +
            " OR birthday LIKE :search")
    fun users(search: String): List<UserTable>
}