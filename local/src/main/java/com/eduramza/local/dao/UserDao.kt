package com.eduramza.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eduramza.local.model.LoginResponse

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: String): LiveData<LoginResponse.User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: LoginResponse.User)

    @Query("SELECT token FROM user WHERE id =  :id")
    fun getToken(id: String): String

}