package com.eduramza.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eduramza.local.dao.UserDao
import com.eduramza.local.model.LoginResponse

@Database(entities = [LoginResponse.User::class], version = 1, exportSchema = false)
abstract class DogListDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

}